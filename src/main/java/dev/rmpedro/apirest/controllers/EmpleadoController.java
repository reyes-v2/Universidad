package dev.rmpedro.apirest.controllers;

import dev.rmpedro.apirest.exceptions.BadRequestException;
import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.mapper.AlumnoMapper;
import dev.rmpedro.apirest.mapper.EmpleadoMapper;
import dev.rmpedro.apirest.models.dto.AlumnoDTO;
import dev.rmpedro.apirest.models.dto.EmpleadoDTO;
import dev.rmpedro.apirest.models.entities.Alumno;
import dev.rmpedro.apirest.models.entities.Empleado;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.services.AlumnoDAO;
import dev.rmpedro.apirest.services.EmpleadoDAO;
import dev.rmpedro.apirest.services.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/crear")
    public ResponseEntity<?> crearEmpleado(@RequestBody Persona empleado){
        Persona empleadoGuardado = empleadoDAO.guardar(empleado);
        return new ResponseEntity<Persona>(empleadoGuardado, HttpStatus.CREATED);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> buscarTodos(){
        List<Empleado> empleados = (List<Empleado>) ((EmpleadoDAO)empleadoDAO).buscarTodosEmpleado();
        if(empleados.isEmpty()){
            throw new NotFoundException("No existen empleados");

        }
        return new ResponseEntity<List<Empleado>>(empleados,HttpStatus.OK);
    }
    @GetMapping("/buscar/id/{empleadoId}")
    public ResponseEntity<?> buscarEmpleadoPorId(@PathVariable Integer empleadoId){
        Optional<Empleado> oEmpleado = ((EmpleadoDAO)empleadoDAO).buscarEmpleadoPorId(empleadoId);
        if(!oEmpleado.isPresent()){
            throw new NotFoundException("No existe el empleado con el ID " + empleadoId);
        }
        return new ResponseEntity<Empleado>(oEmpleado.get(),HttpStatus.OK);

    }
    @PutMapping("/upd/empleadoId/{empleadoId}")
    public ResponseEntity<?> empleadoProfesor(@PathVariable Integer empleadoId,@RequestBody Empleado empleado){
        Optional<Empleado> actualizarEmpleado= ((EmpleadoDAO)empleadoDAO).buscarEmpleadoPorId(empleadoId);
        if(!actualizarEmpleado.isPresent()){
            throw new NotFoundException("No existe el empleado con el ID " + empleadoId);

        }
        Persona empleadoActualizado =  ((EmpleadoDAO)empleadoDAO).actualizarEmpleado(actualizarEmpleado.get(),empleado);
        return new ResponseEntity<Persona>(empleadoActualizado,HttpStatus.OK);
    }


    @DeleteMapping("/del/empleadoId/{empleadoId}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Integer empleadoId){
        Map<String,Object> respuesta = new HashMap<String,Object>();
        Optional<Empleado> oEmpleado = ((EmpleadoDAO)empleadoDAO).buscarEmpleadoPorId(empleadoId);
        if(!oEmpleado.isPresent()){
            throw new NotFoundException("No existe el empleado con el ID " + empleadoId);
        }
        empleadoDAO.eliminarPorId(empleadoId);
        respuesta.put("OK","Empleado ID:" + empleadoId + "eliminado exitosamente");
        return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NO_CONTENT);

    }
    @GetMapping("/lista/dto")
    public ResponseEntity<?> obtenerEmpleadosDto(){
        List<Empleado> empleados = (List<Empleado>) ((EmpleadoDAO)empleadoDAO).buscarTodosEmpleado();
        if(empleados.isEmpty())
            throw new BadRequestException("No existen empleados");
        List<EmpleadoDTO> empleadosDto = empleados.
                stream()
                .map(EmpleadoMapper::mapperEmpleado)
                .collect(Collectors.toList());

        return new ResponseEntity<List<EmpleadoDTO>>(empleadosDto,HttpStatus.OK);


    }

}
