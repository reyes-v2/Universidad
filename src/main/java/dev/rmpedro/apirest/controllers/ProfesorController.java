package dev.rmpedro.apirest.controllers;


import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.models.entities.Profesor;
import dev.rmpedro.apirest.services.AlumnoDAO;
import dev.rmpedro.apirest.services.CarreraDAO;
import dev.rmpedro.apirest.services.PersonaDAO;
import dev.rmpedro.apirest.services.ProfesorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    @Qualifier("profesorDAOImpl")
    private PersonaDAO profesorDAO;

    @Autowired
    private CarreraDAO carreraDAO;

    @PostMapping("/crear")
    public ResponseEntity<?> crearProfesor(@RequestBody Persona profesor){
        Persona profesorGuardado = profesorDAO.guardar(profesor);
        return new ResponseEntity<Persona>(profesorGuardado, HttpStatus.CREATED);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> buscarTodos(){
        List<Profesor> profesores = (List<Profesor>) ((ProfesorDAO)profesorDAO).buscarTodosProfesor();
        if(profesores.isEmpty()){
            throw new NotFoundException("No existen profesores");

        }
        return new ResponseEntity<List<Profesor>>(profesores,HttpStatus.OK);
    }
    @GetMapping("/buscar/id/{profesorId}")
    public ResponseEntity<?> buscarProfesorPorId(@PathVariable Integer profesorId){
        Optional<Profesor> oProfesor = ((ProfesorDAO)profesorDAO).buscarProfesorPorId(profesorId);
        if(!oProfesor.isPresent()){
            throw new NotFoundException("No existe el profesor con el ID " + profesorId);
        }
        return new ResponseEntity<Profesor>(oProfesor.get(),HttpStatus.OK);

    }
    @PutMapping("/upd/profesorId/{profesorId}")
    public ResponseEntity<?> actualizarProfesor(@PathVariable Integer profesorId,@RequestBody Profesor profesor){
        Optional<Profesor> actualizarProfesor= ((ProfesorDAO)profesorDAO).buscarProfesorPorId(profesorId);
        if(!actualizarProfesor.isPresent()){
            throw new NotFoundException("No existe el profesor con el ID " + profesorId);

        }
        Profesor profesorActualizado = (Profesor) ((ProfesorDAO)profesorDAO).actualizarProfesor(actualizarProfesor.get(),profesor);
        return new ResponseEntity<Persona>(profesorActualizado,HttpStatus.OK);
    }


    @DeleteMapping("/del/profesorId/{profesorId}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Integer profesorId){
        Map<String,Object> respuesta = new HashMap<String,Object>();
        Optional<Persona> oProfesor = profesorDAO.buscarPorId(profesorId);
        if(!oProfesor.isPresent()){
            throw new NotFoundException("No existe el profesor con el ID " + profesorId);
        }
        profesorDAO.eliminarPorId(profesorId);
        respuesta.put("OK","Profesor ID:" + profesorId + "eliminado exitosamente");
        return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NO_CONTENT);

    }


}
