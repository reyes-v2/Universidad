package dev.rmpedro.apirest.controllers;


import dev.rmpedro.apirest.mapper.CarreraMapper;
import dev.rmpedro.apirest.models.dto.CarreraDTO;
import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.exceptions.BadRequestException;
import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.services.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private CarreraDAO carreraDAO;


    @GetMapping("/lista/carreras")
    public List<Carrera> buscarTodos(){
        List<Carrera> carreras = (List<Carrera>) carreraDAO.buscarTodos();
        if(carreras.isEmpty())
            throw new BadRequestException("No existen carreras");
        return carreras;

    }
    @GetMapping("/id/{carreraId}")
    public Carrera buscarCarreraPorId(@PathVariable Integer carreraId){
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
        if(!oCarrera.isPresent()){
            throw new BadRequestException("No existe la carrera con el ID " + carreraId);
        }
        return oCarrera.get();

    }

    @PostMapping("/crear")
    public ResponseEntity<?> guardarCarrera(@Valid @RequestBody Carrera carrera, BindingResult result){
        Map<String,Object> validaciones = new HashMap<String,Object>();
        if(result.hasErrors()){
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo :'" + errores.getField() + "'" + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista errores", listaErrores);
            return new ResponseEntity<Map<String,Object>>(validaciones,HttpStatus.BAD_REQUEST);
        }
        Carrera carreraGuardada = carreraDAO.guardar(carrera);
        return new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED);

    }

    @PutMapping("/upd/carreraId/{carreraId}")
    public ResponseEntity<?> actualizarCarrera(@PathVariable Integer carreraId, @RequestBody Carrera carrera){
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
        if(!oCarrera.isPresent()){
            throw new NotFoundException("No existe la carrera con el ID " + carreraId);
        }
        Carrera carreraActualizada = carreraDAO.actualizarCarrera(oCarrera.get(),carrera);
        return new ResponseEntity<Carrera>(carreraActualizada,HttpStatus.OK);
    }
    @DeleteMapping("/del/carreraId/{carreraId}")
    public ResponseEntity<?> eliminarCarrera(@PathVariable Integer carreraId){
        Map<String,Object> respuesta = new HashMap<String,Object>();
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
        if(!oCarrera.isPresent()){
            throw new NotFoundException("No existe la carrera con el ID " + carreraId);
        }
        carreraDAO.eliminarPorId(carreraId);
        respuesta.put("OK","Carrera ID:" + carreraId + "eliminado exitosamente");
        return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NO_CONTENT);

    }

    @GetMapping("/carreras/dto")
    public ResponseEntity<?> obtenerCarrerasDto(){
        List<Carrera> carreras = (List<Carrera>) carreraDAO.buscarTodos();
        if(carreras.isEmpty())
            throw new BadRequestException("No existen carreras");
        List<CarreraDTO> carrerasDto = carreras.
                stream()
                .map(CarreraMapper::mapperCarrera)
                .collect(Collectors.toList());

        return new ResponseEntity<List<CarreraDTO>>(carrerasDto,HttpStatus.OK);


    }


}
