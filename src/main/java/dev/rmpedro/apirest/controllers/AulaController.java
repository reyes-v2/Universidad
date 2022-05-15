package dev.rmpedro.apirest.controllers;

import dev.rmpedro.apirest.exceptions.BadRequestException;
import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.mapper.AulaMapper;
import dev.rmpedro.apirest.mapper.CarreraMapper;
import dev.rmpedro.apirest.models.dto.AulaDTO;
import dev.rmpedro.apirest.models.dto.CarreraDTO;
import dev.rmpedro.apirest.models.entities.Aula;
import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.services.AulaDAO;
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

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody Aula aula, BindingResult result){
        Map<String,Object> validaciones = new HashMap<String,Object>();
        if(result.hasErrors()){
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo :'" + errores.getField() + "'" + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista errores", listaErrores);
            return new ResponseEntity<Map<String,Object>>(validaciones,HttpStatus.BAD_REQUEST);
        }
        Aula nuevaAula = aulaDAO.guardar(aula);
        return new ResponseEntity<Aula>(aula, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> buscarTodos(){
        List<Aula> aulas = (List<Aula>) aulaDAO.buscarTodos();
        if(aulas.isEmpty()){
            throw new NotFoundException("No hay aulas que mostrar");
        }
        return new ResponseEntity<List<Aula>>(aulas,HttpStatus.OK);

    }

    @GetMapping("/buscar/aulaId/{aulaId}")
    public ResponseEntity<?> buscarAula(@PathVariable Integer aulaId){
        Optional<Aula> aula = aulaDAO.buscarPorId(aulaId);
        if(!aula.isPresent()){
            throw new NotFoundException("No existe el aula con ID " + aulaId);
        }
        return new ResponseEntity<Aula>(aula.get(),HttpStatus.OK);

    }

    @PutMapping("/upd/aulaId/{aulaId}")
    public ResponseEntity<?> actualizarAula(@PathVariable Integer aulaId, @RequestBody Aula aula){
        Optional<Aula> aulaEncontrada = aulaDAO.buscarPorId(aulaId);
        if(!aulaEncontrada.isPresent()){
            throw new NotFoundException("No existe el aula con ID " + aulaId);
        }
        Aula aulaActualizada = aulaDAO.actualizar(aulaEncontrada.get(),aula);

        return new ResponseEntity<Aula>(aulaActualizada,HttpStatus.CREATED);

    }

    @DeleteMapping("/del/aulaId/{aulaId}")
    public ResponseEntity<?> eliminarAula(@PathVariable Integer aulaId){

        Optional<Aula> aulaEncontrada = aulaDAO.buscarPorId(aulaId);
        if(!aulaEncontrada.isPresent()){
            throw new NotFoundException("No existe el aula con ID " + aulaId);
        }
        aulaDAO.eliminarPorId(aulaId);
        Map<String,String> respuesta = new HashMap<String,String>();
        respuesta.put("OK","Aula ID:" + aulaId + "eliminado exitosamente");
        return new ResponseEntity<Map<String,String>>(respuesta,HttpStatus.NO_CONTENT);

    }
    @GetMapping("/lista/dto")
    public ResponseEntity<?> obtenerAulasDto(){
        List<Aula> aulas = (List<Aula>) aulaDAO.buscarTodos();
        if(aulas.isEmpty())
            throw new BadRequestException("No existen aulas");
        List<AulaDTO> aulasDto = aulas.
                stream()
                .map(AulaMapper::mapperAula)
                .collect(Collectors.toList());

        return new ResponseEntity<List<AulaDTO>>(aulasDto,HttpStatus.OK);


    }



}
