package dev.rmpedro.apirest.controllers;
import dev.rmpedro.apirest.exceptions.BadRequestException;
import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.mapper.AulaMapper;
import dev.rmpedro.apirest.models.dto.AulaDTO;
import dev.rmpedro.apirest.models.entities.Aula;
import dev.rmpedro.apirest.services.AulaDAO;
import dev.rmpedro.apirest.utils.ValidationData;
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
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    private AulaDAO aulaDAO;

    @Operation(summary = "Crear aula", description = "Guarda una nueva aula en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aula guardada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aula.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta", content = @Content)})
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody Aula aula, BindingResult result) {
        Map<String, Object> validaciones = new HashMap<>();
        if (result.hasErrors()) {
            return new ResponseEntity<>(ValidationData.validarDatos(result), HttpStatus.BAD_REQUEST);
        }
        Aula nuevaAula = aulaDAO.guardar(aula);
        return new ResponseEntity<>(nuevaAula, HttpStatus.CREATED);
    }


    @Operation(summary = "Listar aulas", description = "Muestra una lista de las aulas existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aulas existentes",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aula.class))}),
            @ApiResponse(responseCode = "404", description = "No existen aulas que mostrar", content = @Content)})
    @GetMapping("/listar")
    public ResponseEntity<?> buscarTodos() {


        return new ResponseEntity<>(aulaDAO.buscarTodos(), HttpStatus.OK);

    }

    @Operation(summary = "Buscar aula", description = "Busca el aula con el Id correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aula.class))}),
            @ApiResponse(responseCode = "404", description = "No existe el aula", content = @Content),
            @ApiResponse(responseCode = "400", description = "Parametros no validos, el Id debe ser entero")})
    @GetMapping("/buscar/aulaId/{aulaId}")
    public ResponseEntity<?> buscarAula(@PathVariable Integer aulaId) {

        return new ResponseEntity<>(aulaDAO.buscarPorId(aulaId).get(), HttpStatus.OK);

    }

    @Operation(summary = "Actualizar aula", description = "Actualiza el aula con el Id correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aula actualizada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aula.class))}),
            @ApiResponse(responseCode = "404", description = "No existe el aula",content = @Content),
            @ApiResponse(responseCode = "400", description = "Parametros no validos",content = @Content)})
    @PutMapping("/upd/aulaId/{aulaId}")
    public ResponseEntity<?> actualizarAula(@PathVariable Integer aulaId, @RequestBody Aula aula) {

        Aula aulaActualizada = aulaDAO.actualizar(aulaDAO.buscarPorId(aulaId).get(), aula);
        return new ResponseEntity<>(aulaActualizada, HttpStatus.CREATED);

    }


    @Operation(summary = "Eliminar Aula", description = "Elimina el aula con el ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula eliminada", content = @Content),
            @ApiResponse(responseCode = "404", description = "No existe el aula", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, parametros no validos", content = @Content)})
    @DeleteMapping("/del/aulaId/{aulaId}")
    public ResponseEntity<?> eliminarAula(@PathVariable Integer aulaId) {
        aulaDAO.eliminarPorId(aulaId);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("OK", "Aula ID:" + aulaId + "eliminado exitosamente");
        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }


    @Operation(summary = "Listar aulas en formato breve", description = "Muestra una lista de las aulas disponibles con sus datos esenciales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aulas encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AulaDTO.class))}),
            @ApiResponse(responseCode = "404", description = "No hay aulas que mostrar", content = @Content)})
    @GetMapping("/lista/dto")
    public ResponseEntity<?> obtenerAulasDto() {
        List<Aula> aulas = (List<Aula>) aulaDAO.buscarTodos();
        List<AulaDTO> aulasDto = aulas.
                stream()
                .map(AulaMapper::mapperAula)
                .collect(Collectors.toList());
        return new ResponseEntity<>(aulasDto, HttpStatus.OK);


    }



}
