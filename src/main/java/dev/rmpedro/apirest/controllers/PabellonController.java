package dev.rmpedro.apirest.controllers;


import dev.rmpedro.apirest.exceptions.BadRequestException;
import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.mapper.PabellonMapper;
import dev.rmpedro.apirest.models.dto.AlumnoDTO;
import dev.rmpedro.apirest.models.dto.PabellonDTO;
import dev.rmpedro.apirest.models.entities.Alumno;
import dev.rmpedro.apirest.models.entities.Aula;
import dev.rmpedro.apirest.models.entities.Pabellon;
import dev.rmpedro.apirest.services.AulaDAO;
import dev.rmpedro.apirest.services.PabellonDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pabellon")
public class PabellonController {

    @Autowired
    private PabellonDAO pabellonDAO;

    @Autowired
    private AulaDAO aulaDAO;

    @Operation(summary = "Crear nuevo pabellon", description = "Guardar un nuevo pabellon en la BD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pabellon creado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pabellon.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion invalida", content = @Content)})
    @PostMapping("/crear")
    public ResponseEntity<?> crearPabellon(@Valid @RequestBody Pabellon pabellon, BindingResult result){
        Map<String,Object> validaciones = new HashMap<String,Object>();
        if(result.hasErrors()){
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo :'" + errores.getField() + "'" + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista errores", listaErrores);
            return new ResponseEntity<>(validaciones, HttpStatus.BAD_REQUEST);
        }
        Pabellon nuevoPabellon = pabellonDAO.guardar(pabellon);
        return new ResponseEntity<>(nuevoPabellon, HttpStatus.OK);


    }
    @GetMapping("/buscar/localidad/{localidad}")
    public ResponseEntity<?> buscarPabellonPorLocalidad(@PathVariable String localidad){
        return new ResponseEntity<>(pabellonDAO.findPabellonsByDireccionLocalidad(localidad),HttpStatus.OK);
    }

    @GetMapping("/buscar/{pabellonId}")
    public ResponseEntity<?> buscarPabellonId(@PathVariable Integer pabellonId){
        return new ResponseEntity<>(pabellonDAO.findById(pabellonId),HttpStatus.ACCEPTED);

    }
    @GetMapping("/buscar/nombre/{pabellonNombre}")
    public ResponseEntity<?> buscarPabellonNombre(@PathVariable String pabellonNombre){
        return new ResponseEntity<>(pabellonDAO.findPabellonByNombreEquals(pabellonNombre),HttpStatus.ACCEPTED);

    }


    @Operation(summary = "Listar pabellones", description = "Muestra una lista de los pabellones disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pabellones encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pabellon.class))}),
            @ApiResponse(responseCode = "404", description = "No hay pabellones que mostrar", content = @Content)})
    @GetMapping("/lista")
    public ResponseEntity<?> buscarTodos(){
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.buscarTodos();
        return new ResponseEntity<>(pabellones,HttpStatus.OK);
    }


    @Operation(summary = "Actualizar Pabellon", description = "Actualiza el pabellon con el ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pabellon actualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pabellon.class))}),
            @ApiResponse(responseCode = "404", description = "No existe el pabellon", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, parametros no validos", content = @Content)})
    @PutMapping("upd/pabellonId/{pabellonId}")
    public ResponseEntity<?> actualizarPabellon(@PathVariable Integer pabellonId, @RequestBody Pabellon pabellon){
        Optional<Pabellon> oPabellon=pabellonDAO.buscarPorId(pabellonId);
        if(!oPabellon.isPresent()){
            throw new NotFoundException("No existe el pabellon con el Id " + pabellonId);

        }
        Pabellon pabellonActualizado = pabellonDAO.actualizarPabellon(oPabellon.get(),pabellon);
        return new ResponseEntity<>(pabellonActualizado, HttpStatus.OK);

    }


    @Operation(summary = "Eliminar Pabellon", description = "Elimina el pabellon con el ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pabellon eliminado", content = @Content),
            @ApiResponse(responseCode = "404", description = "No existe el pabellon", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, parametros no validos", content = @Content)})
    @DeleteMapping("/del/pabellonId/{pabellonId}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Integer pabellonId){
        Map<String,Object> respuesta = new HashMap<String,Object>();
        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(pabellonId);
        if(!oPabellon.isPresent()){
            throw new NotFoundException("No existe el pabellon con el ID " + pabellonId);
        }
        List<Aula> aulas = (List<Aula>) aulaDAO.findAulaByPabellonNombre(oPabellon.get().getNombre());
        aulas.forEach(aula -> aulaDAO.eliminarPorId(aula.getId()));
        pabellonDAO.eliminarPorId(pabellonId);
        respuesta.put("OK","Pabellon ID:" + pabellonId + "eliminado exitosamente");
        return new ResponseEntity<>(respuesta, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Listar pabellones en formato breve", description = "Muestra una lista de los pabellones disponibles con sus datos esenciales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pabellones encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PabellonDTO.class))}),
            @ApiResponse(responseCode = "404", description = "No hay pabellones que mostrar", content = @Content)})
    @GetMapping("/lista/dto")
    public ResponseEntity<?> obtenerPabellonesDto(){
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.buscarTodos();
        if(pabellones.isEmpty())
            throw new BadRequestException("No existen pabellones");
        List<PabellonDTO> pabellonesDto = pabellones.
                stream()
                .map(PabellonMapper::mapperPabellon)
                .collect(Collectors.toList());

        return new ResponseEntity<>(pabellonesDto, HttpStatus.OK);


    }




}
