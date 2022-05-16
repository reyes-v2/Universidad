package dev.rmpedro.apirest.controllers;


import dev.rmpedro.apirest.exceptions.BadRequestException;
import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.mapper.CarreraMapper;
import dev.rmpedro.apirest.models.dto.CarreraDTO;
import dev.rmpedro.apirest.models.entities.Alumno;
import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.services.CarreraDAO;
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
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private CarreraDAO carreraDAO;

    @Operation(summary = "Listar Carreras", description = "Muestra una lista de las carreras disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carreras encontradas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Carrera.class))}),
            @ApiResponse(responseCode = "404", description = "No hay carreras que mostrar", content = @Content)})
    @GetMapping("/lista/carreras")
    public List<Carrera> buscarTodos() {
        List<Carrera> carreras = (List<Carrera>) carreraDAO.buscarTodos();
        if (carreras.isEmpty())
            throw new BadRequestException("No existen carreras");
        return carreras;

    }

    @Operation(summary = "Buscar Carrera", description = "Busca una carrera por su ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrera encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Carrera.class))}),
            @ApiResponse(responseCode = "404", description = "No existe la carrera", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida el ID debe ser Integer", content = @Content)})
    @GetMapping("/id/{carreraId}")
    public Carrera buscarCarreraPorId(@PathVariable Integer carreraId) {
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
        if (!oCarrera.isPresent()) {
            throw new BadRequestException("No existe la carrera con el ID " + carreraId);
        }
        return oCarrera.get();

    }

    @Operation(summary = "Crear nueva carrera", description = "Guardar una nueva carrera en la BD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrera creado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Carrera.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion invalida", content = @Content)})
    @PostMapping("/crear")
    public ResponseEntity<?> guardarCarrera(@Valid @RequestBody Carrera carrera, BindingResult result) {
        Map<String, Object> validaciones = new HashMap<String, Object>();
        if (result.hasErrors()) {
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo :'" + errores.getField() + "'" + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista errores", listaErrores);
            return new ResponseEntity<>(validaciones, HttpStatus.BAD_REQUEST);
        }
        Carrera carreraGuardada = carreraDAO.guardar(carrera);
        return new ResponseEntity<>(carreraGuardada, HttpStatus.CREATED);

    }


    @Operation(summary = "Actualizar Carrera", description = "Actualiza la carrera con el ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrera actualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class))}),
            @ApiResponse(responseCode = "404", description = "No existe la carrera", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, parametros no validos", content = @Content)})
    @PutMapping("/upd/carreraId/{carreraId}")
    public ResponseEntity<?> actualizarCarrera(@PathVariable Integer carreraId, @RequestBody Carrera carrera) {
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
        if (!oCarrera.isPresent()) {
            throw new NotFoundException("No existe la carrera con el ID " + carreraId);
        }
        Carrera carreraActualizada = carreraDAO.actualizarCarrera(oCarrera.get(), carrera);
        return new ResponseEntity<>(carreraActualizada, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar Carrera", description = "Elimina la carrera con el ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrera eliminada", content = @Content),
            @ApiResponse(responseCode = "404", description = "No existe la carrera", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, parametros no validos", content = @Content)})
    @DeleteMapping("/del/carreraId/{carreraId}")
    public ResponseEntity<?> eliminarCarrera(@PathVariable Integer carreraId) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
        if (!oCarrera.isPresent()) {
            throw new NotFoundException("No existe la carrera con el ID " + carreraId);
        }
        carreraDAO.eliminarPorId(carreraId);
        respuesta.put("OK", "Carrera ID:" + carreraId + "eliminado exitosamente");
        return new ResponseEntity<>(respuesta, HttpStatus.NO_CONTENT);

    }

    @Operation(summary = "Listar carreras en formato breve", description = "Muestra una lista de las carreras disponibles con sus datos esenciales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carreras encontradas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarreraDTO.class))}),
            @ApiResponse(responseCode = "404", description = "No hay carreras que mostrar", content = @Content)})
    @GetMapping("/carreras/dto")
    public ResponseEntity<?> obtenerCarrerasDto() {
        List<Carrera> carreras = (List<Carrera>) carreraDAO.buscarTodos();
        if (carreras.isEmpty())
            throw new BadRequestException("No existen carreras");
        List<CarreraDTO> carrerasDto = carreras.
                stream()
                .map(CarreraMapper::mapperCarrera)
                .collect(Collectors.toList());

        return new ResponseEntity<>(carrerasDto, HttpStatus.OK);


    }


}
