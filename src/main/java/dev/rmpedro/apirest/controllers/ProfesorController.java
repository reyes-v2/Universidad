package dev.rmpedro.apirest.controllers;

import dev.rmpedro.apirest.exceptions.BadRequestException;
import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.mapper.ProfesorMapper;
import dev.rmpedro.apirest.models.dto.ProfesorDTO;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.models.entities.Profesor;
import dev.rmpedro.apirest.services.CarreraDAO;
import dev.rmpedro.apirest.services.PersonaDAO;
import dev.rmpedro.apirest.services.ProfesorDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    @Qualifier("profesorDAOImpl")
    private PersonaDAO profesorDAO;

    @Autowired
    private CarreraDAO carreraDAO;

    @Operation(summary = "Crear nuevo profesor", description = "Guardar un nuevo profesor en la BD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profesor creado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profesor.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, formato incorrecto", content = @Content)})
    @PostMapping("/crear")
    public ResponseEntity<?> crearProfesor(@Valid @RequestBody Persona profesor, BindingResult result){
        Map<String,Object> validaciones = new HashMap<String,Object>();
        if(result.hasErrors()){
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo :'" + errores.getField() + "'" + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista errores", listaErrores);
            return new ResponseEntity<>(validaciones, HttpStatus.BAD_REQUEST);
        }
        Persona profesorGuardado = profesorDAO.guardar(profesor);
        return new ResponseEntity<>(profesorGuardado, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar profesores", description = "Muestra una lista de los profesores disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesores encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profesor.class))}),
            @ApiResponse(responseCode = "404", description = "No hay profesores que mostrar", content = @Content)})
    @GetMapping("/lista")
    public ResponseEntity<?> buscarTodos(){
        List<Profesor> profesores = (List<Profesor>) ((ProfesorDAO)profesorDAO).buscarTodosProfesor();
        if(profesores.isEmpty()){
            throw new NotFoundException("No existen profesores");

        }
        return new ResponseEntity<>(profesores, HttpStatus.OK);
    }

    @Operation(summary = "Buscar Profesor", description = "Busca un profesor por su ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesor encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profesor.class))}),
            @ApiResponse(responseCode = "404", description = "No existe el profesor", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida el ID debe ser Integer", content = @Content)})
    @GetMapping("/buscar/id/{profesorId}")
    public ResponseEntity<?> buscarProfesorPorId(@PathVariable Integer profesorId){
        Optional<Profesor> oProfesor = ((ProfesorDAO)profesorDAO).buscarProfesorPorId(profesorId);
        if(!oProfesor.isPresent()){
            throw new NotFoundException("No existe el profesor con el ID " + profesorId);
        }
        return new ResponseEntity<>(oProfesor.get(), HttpStatus.OK);

    }

    @Operation(summary = "Actualizar Profesor", description = "Actualiza el profesor con el ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesor actualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profesor.class))}),
            @ApiResponse(responseCode = "404", description = "No existe el profesor", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, parametros no validos", content = @Content)})
    @PutMapping("/upd/profesorId/{profesorId}")
    public ResponseEntity<?> actualizarProfesor(@PathVariable Integer profesorId,@RequestBody Profesor profesor){
        Optional<Profesor> actualizarProfesor= ((ProfesorDAO)profesorDAO).buscarProfesorPorId(profesorId);
        if(!actualizarProfesor.isPresent()){
            throw new NotFoundException("No existe el profesor con el ID " + profesorId);

        }
        Profesor profesorActualizado = (Profesor) ((ProfesorDAO)profesorDAO).actualizarProfesor(actualizarProfesor.get(),profesor);
        return new ResponseEntity<Persona>(profesorActualizado,HttpStatus.OK);
    }


    @Operation(summary = "Eliminar profesor", description = "Elimina el profesor con el ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesor eliminado", content = @Content),
            @ApiResponse(responseCode = "404", description = "No existe el profesor", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, parametros no validos", content = @Content)})
    @DeleteMapping("/del/profesorId/{profesorId}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Integer profesorId){
        Map<String,Object> respuesta = new HashMap<String,Object>();
        Optional<Persona> oProfesor = profesorDAO.buscarPorId(profesorId);
        if(!oProfesor.isPresent()){
            throw new NotFoundException("No existe el profesor con el ID " + profesorId);
        }
        profesorDAO.eliminarPorId(profesorId);
        respuesta.put("OK","Profesor ID:" + profesorId + "eliminado exitosamente");
        return new ResponseEntity<>(respuesta, HttpStatus.NO_CONTENT);

    }


    @Operation(summary = "Listar profesores en formato breve", description = "Muestra una lista de los profesores disponibles con sus datos esenciales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesores encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfesorDTO.class))}),
            @ApiResponse(responseCode = "404", description = "No hay profesores que mostrar", content = @Content)})
    @GetMapping("/lista/dto")
    public ResponseEntity<?> obtenerProfesoresDto(){
        List<Profesor> profesores = (List<Profesor>) ((ProfesorDAO)profesorDAO).buscarTodosProfesor();
        if(profesores.isEmpty())
            throw new BadRequestException("No existen profesores");
        List<ProfesorDTO> profesoresDto = profesores.
                stream()
                .map(ProfesorMapper::mapperProfesor)
                .collect(Collectors.toList());

        return new ResponseEntity<>(profesoresDto, HttpStatus.OK);


    }


}
