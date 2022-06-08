package dev.rmpedro.apirest.controllers;

import dev.rmpedro.apirest.exceptions.BadRequestException;
import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.mapper.EmpleadoMapper;
import dev.rmpedro.apirest.models.dto.EmpleadoDTO;
import dev.rmpedro.apirest.models.entities.Empleado;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.services.EmpleadoDAO;
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
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    @Qualifier("empleadoDAOImpl")
    private PersonaDAO empleadoDAO;

    @Operation(summary = "Crear nuevo empleado", description = "Guardar un nuevo emmpleado en la BD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado creado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empleado.class))}),
            @ApiResponse(responseCode = "400", description = "Peticion invalida", content = @Content)})
    @PostMapping("/crear")
    public ResponseEntity<?> crearEmpleado(@Valid @RequestBody Persona empleado, BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(ValidationData.validarDatos(result), HttpStatus.BAD_REQUEST);
        }
        Persona empleadoGuardado = empleadoDAO.guardar(empleado);
        return new ResponseEntity<>(empleadoGuardado, HttpStatus.CREATED);
    }


    @Operation(summary = "Listar empleados", description = "Muestra una lista de los empleados disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleados encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empleado.class))}),
            @ApiResponse(responseCode = "404", description = "No hay empleados que mostrar", content = @Content)})
    @GetMapping("/lista")
    public ResponseEntity<?> buscarTodos() {

        return new ResponseEntity<>(
                (List<Empleado>) ((EmpleadoDAO) empleadoDAO).buscarTodosEmpleado(), HttpStatus.OK);
    }


    @Operation(summary = "Buscar Empleado", description = "Busca un empleado por su ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empleado.class))}),
            @ApiResponse(responseCode = "404", description = "No existe el empleado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida el ID debe ser Integer", content = @Content)})
    @GetMapping("/buscar/id/{empleadoId}")
    public ResponseEntity<?> buscarEmpleadoPorId(@PathVariable Integer empleadoId) {

        return new ResponseEntity<>(
                ((EmpleadoDAO) empleadoDAO).buscarEmpleadoPorId(empleadoId).get(), HttpStatus.OK);

    }

    @Operation(summary = "Actualizar Empleado", description = "Actualiza el empleado con el ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado actualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empleado.class))}),
            @ApiResponse(responseCode = "404", description = "No existe el empleado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, parametros no validos", content = @Content)})
    @PutMapping("/upd/empleadoId/{empleadoId}")
    public ResponseEntity<?> empleadoProfesor(@PathVariable Integer empleadoId, @RequestBody Empleado empleado) {

        Persona empleadoActualizado = ((EmpleadoDAO) empleadoDAO).
                actualizarEmpleado(((EmpleadoDAO) empleadoDAO).
                        buscarEmpleadoPorId(empleadoId).get(), empleado);
        return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
    }


    @Operation(summary = "Eliminar Empleado", description = "Elimina el empleado con el ID correspondiente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado eliminado", content = @Content),
            @ApiResponse(responseCode = "404", description = "No existe el empleado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Peticion invalida, parametros no validos", content = @Content)})
    @DeleteMapping("/del/empleadoId/{empleadoId}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Integer empleadoId) {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        empleadoDAO.eliminarPorId(empleadoId);
        respuesta.put("OK", "Empleado ID:" + empleadoId + "eliminado exitosamente");
        return new ResponseEntity<>(respuesta, HttpStatus.NO_CONTENT);

    }


    @Operation(summary = "Listar empleados en formato breve", description = "Muestra una lista de los empleados disponibles con sus datos esenciales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleados encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpleadoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "No hay empleados que mostrar", content = @Content)})
    @GetMapping("/lista/dto")
    public ResponseEntity<?> obtenerEmpleadosDto() {
        List<Empleado> empleados = (List<Empleado>) ((EmpleadoDAO) empleadoDAO).buscarTodosEmpleado();
        List<EmpleadoDTO> empleadosDto = empleados.
                stream()
                .map(EmpleadoMapper::mapperEmpleado)
                .collect(Collectors.toList());

        return new ResponseEntity<>(empleadosDto, HttpStatus.OK);


    }

}
