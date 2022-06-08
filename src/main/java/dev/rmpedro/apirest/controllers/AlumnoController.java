package dev.rmpedro.apirest.controllers;

import dev.rmpedro.apirest.exceptions.BadRequestException;
import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.mapper.AlumnoMapper;
import dev.rmpedro.apirest.models.dto.AlumnoDTO;
import dev.rmpedro.apirest.models.entities.Alumno;
import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.services.AlumnoDAO;
import dev.rmpedro.apirest.services.CarreraDAO;
import dev.rmpedro.apirest.services.PersonaDAO;
import dev.rmpedro.apirest.utils.ValidationData;
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
@RequestMapping("/alumno")

public class AlumnoController {


    @Autowired
    @Qualifier("alumnoDAOImpl")
    private PersonaDAO alumnoDAO;

    @Autowired
    private CarreraDAO carreraDAO;

    @Operation(summary = "Crear nuevo alumno", description = "Guardar un nuevo alumno en la BD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alumno creado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion invalida", content = @Content)})

    @PostMapping("/crear")
    public ResponseEntity<?> crearAlumno(@Valid @RequestBody Persona alumno, BindingResult result) {
        if(result.hasErrors()){
            return  new ResponseEntity<>(ValidationData.validarDatos(result),HttpStatus.BAD_REQUEST);
        }
        Persona alumnoGuardado = alumnoDAO.guardar(alumno);
        return new ResponseEntity<>(alumnoGuardado, HttpStatus.CREATED);
    }


    @Operation(summary = "Listar alumnos", description = "Muestra una lista de los alumnos disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumnos encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class))}),
            @ApiResponse(responseCode = "404", description = "No hay alumnos que mostrar", content = @Content)})
    @GetMapping("/lista")
    public ResponseEntity<?> buscarTodos() {

        return new ResponseEntity<>(
                (List<Alumno>) ((AlumnoDAO) alumnoDAO).buscarTodosAlumnos(), HttpStatus.OK);
    }

    @Operation(summary = "Buscar Alumno", description = "Busca un alumno por su ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class))}),
            @ApiResponse(responseCode = "404", description = "No existe el alumno", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida el ID debe ser Integer", content = @Content)})
    @GetMapping("/buscar/id/{alumnoId}")
    public ResponseEntity<?> buscarAlumnoPorId(@PathVariable Integer alumnoId) {

        return new ResponseEntity<Persona>(
                ((AlumnoDAO) alumnoDAO).buscarAlumnoPorId(alumnoId).get(), HttpStatus.OK);

    }

    @Operation(summary = "Actualizar Alumno", description = "Actualiza el alumno con el ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno actualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class))}),
            @ApiResponse(responseCode = "404", description = "No existe el alumno", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, parametros no validos", content = @Content)})
    @PutMapping("/upd/alumnoId/{alumnoId}")
    public ResponseEntity<?> actualizarAlummno(@PathVariable Integer alumnoId, @RequestBody Persona alumno) {
        Persona alumnoActualizado = ((AlumnoDAO) alumnoDAO).actualizarAlumno(((AlumnoDAO) alumnoDAO).buscarAlumnoPorId(alumnoId).get(), alumno);
        return new ResponseEntity<>(alumnoActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar Alumno", description = "Elimina el alumno con el ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno eliminado", content = @Content),
            @ApiResponse(responseCode = "404", description = "No existe el alumno", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, parametros no validos", content = @Content)})
    @DeleteMapping("/del/alumnoId/{alumnoId}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Integer alumnoId) {
        Map<String, Object> respuesta = new HashMap<>();
        alumnoDAO.eliminarPorId(alumnoId);
        respuesta.put("OK", "Alumno ID:" + alumnoId + "eliminado exitosamente");
        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }

    @Operation(summary = "Asignar carrera alumno", description = "Asigna la carrera establecida al alumno con  el ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrera asignada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class))}),
            @ApiResponse(responseCode = "404", description = "No existe el alumno o la carrera", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, parametros no validos", content = @Content)})
    @PutMapping("/alumnoId/{alumnoId}/carreraId/{carreraId}")
    public ResponseEntity<?> asignarCarrera(@PathVariable Integer alumnoId, @PathVariable Integer carreraId) {
        Optional<Alumno> oAlumno = ((AlumnoDAO) alumnoDAO).buscarAlumnoPorId(alumnoId);
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
        Persona alumno = ((AlumnoDAO) alumnoDAO).asignarCarrera(oAlumno.get(), oCarrera.get());
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }

    @Operation(summary = "Listar alumnos en formato breve", description = "Muestra una lista de los alumnos disponibles con sus datos esenciales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumnos encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlumnoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "No hay alumnos que mostrar", content = @Content)})
    @GetMapping("/lista/dto")
    public ResponseEntity<?> obtenerAlumnosDto() {
        List<Alumno> alumnos = (List<Alumno>) ((AlumnoDAO) alumnoDAO).buscarTodosAlumnos();
        List<AlumnoDTO> alumnosDto = alumnos.
                stream()
                .map(AlumnoMapper::mapperAlumno)
                .collect(Collectors.toList());

        return new ResponseEntity<>(alumnosDto, HttpStatus.OK);


    }

}
