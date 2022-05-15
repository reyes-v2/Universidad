package dev.rmpedro.apirest.controllers;

import dev.rmpedro.apirest.exceptions.BadRequestException;
import dev.rmpedro.apirest.mapper.AlumnoMapper;
import dev.rmpedro.apirest.mapper.CarreraMapper;
import dev.rmpedro.apirest.models.dto.AlumnoDTO;
import dev.rmpedro.apirest.models.dto.CarreraDTO;
import dev.rmpedro.apirest.models.entities.Alumno;
import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.services.AlumnoDAO;
import dev.rmpedro.apirest.services.CarreraDAO;
import dev.rmpedro.apirest.services.PersonaDAO;
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

    @PostMapping("/crear")
    public ResponseEntity<?> crearAlumno(@Valid @RequestBody Persona alumno, BindingResult result){
        Map<String,Object> validaciones = new HashMap<String,Object>();
        if(result.hasErrors()){
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo :'" + errores.getField() + "'" + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista errores", listaErrores);
            return new ResponseEntity<Map<String,Object>>(validaciones,HttpStatus.BAD_REQUEST);
        }
        Persona alumnoGuardado = alumnoDAO.guardar(alumno);
        return new ResponseEntity<Persona>(alumnoGuardado, HttpStatus.CREATED);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> buscarTodos(){
        List<Alumno> alumnos = (List<Alumno>) ((AlumnoDAO)alumnoDAO).buscarTodosAlumnos();
        if(alumnos.isEmpty()){
            throw new NotFoundException("No existen alumnos");

        }
        return new ResponseEntity<List<Alumno>>(alumnos,HttpStatus.OK);
    }
    @GetMapping("/buscar/id/{alumnoId}")
    public ResponseEntity<?> buscarAlumnoPorId(@PathVariable Integer alumnoId){
        Optional<Alumno> oAlumno = ((AlumnoDAO)alumnoDAO).buscarAlumnoPorId(alumnoId);
        if(!oAlumno.isPresent()){
            throw new NotFoundException("No existe el alumno con el ID " + alumnoId);
        }
        return new ResponseEntity<Persona>(oAlumno.get(),HttpStatus.OK);

    }
    @PutMapping("/upd/alumnoId/{alumnoId}")
    public ResponseEntity<?> actualizarAlummno(@PathVariable Integer alumnoId,@RequestBody Persona alumno){
        Optional<Alumno> actualizarAlumno = ((AlumnoDAO)alumnoDAO).buscarAlumnoPorId(alumnoId);
        if(!actualizarAlumno.isPresent()){
            throw new NotFoundException("No existe el alumno con el ID " + alumnoId);

        }
        Persona alumnoActualizado = ((AlumnoDAO)alumnoDAO).actualizarAlumno(actualizarAlumno.get(),alumno);
        return new ResponseEntity<Persona>(alumnoActualizado,HttpStatus.OK);
    }
    @DeleteMapping("/del/alumnoId/{alumnoId}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Integer alumnoId){
        Map<String,Object> respuesta = new HashMap<String,Object>();
        Optional<Alumno> oAlumno = ((AlumnoDAO)alumnoDAO).buscarAlumnoPorId(alumnoId);
        if(!oAlumno.isPresent()){
            throw new NotFoundException("No existe el alumno con el ID " + alumnoId);
        }
        alumnoDAO.eliminarPorId(alumnoId);
        respuesta.put("OK","Alumno ID:" + alumnoId + "eliminado exitosamente");
        return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NO_CONTENT);

    }
    @PutMapping("/alumnoId/{alumnoId}/carreraId/{carreraId}")
    public ResponseEntity<?> asignarCarrera(@PathVariable Integer alumnoId,@PathVariable Integer carreraId){
        Optional<Alumno> oAlumno = ((AlumnoDAO)alumnoDAO).buscarAlumnoPorId(alumnoId);
        if(!oAlumno.isPresent()){
            throw new NotFoundException("No existe el alumno con el ID " + alumnoId);
        }
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
        if(!oCarrera.isPresent()){
            throw new NotFoundException("No existe la carrera con el ID " + carreraId);
        }
        Persona alumno = ((AlumnoDAO)alumnoDAO).asignarCarrera(oAlumno.get(),oCarrera.get());
        return new ResponseEntity<Persona>(alumno,HttpStatus.OK);
    }

    @GetMapping("/lista/dto")
    public ResponseEntity<?> obtenerAlumnosDto(){
        List<Alumno> alumnos = (List<Alumno>) ((AlumnoDAO)alumnoDAO).buscarTodosAlumnos();
        if(alumnos.isEmpty())
            throw new BadRequestException("No existen alumnos");
        List<AlumnoDTO> alumnosDto = alumnos.
                stream()
                .map(AlumnoMapper::mapperAlumno)
                .collect(Collectors.toList());

        return new ResponseEntity<List<AlumnoDTO>>(alumnosDto,HttpStatus.OK);


    }

}
