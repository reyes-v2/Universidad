package dev.rmpedro.apirest.controllers;


import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.models.entities.Aula;
import dev.rmpedro.apirest.models.entities.Pabellon;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.services.AulaDAO;
import dev.rmpedro.apirest.services.PabellonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pabellon")
public class PabellonController {

    @Autowired
    private PabellonDAO pabellonDAO;

    @Autowired
    private AulaDAO aulaDAO;

    @PostMapping("/crear")
    public ResponseEntity<?> crearPabellon(@RequestBody Pabellon pabellon){
        Pabellon nuevoPabellon = pabellonDAO.guardar(pabellon);
        return new ResponseEntity<Pabellon>(nuevoPabellon, HttpStatus.OK);


    }

    @GetMapping("/lista")
    public ResponseEntity<?> buscarTodos(){
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.buscarTodos();
        return new ResponseEntity<>(pabellones,HttpStatus.OK);
    }

    @PutMapping("upd/pabellonId/{pabellonId}")
    public ResponseEntity<?> actualizarPabellon(@PathVariable Integer pabellonId, @RequestBody Pabellon pabellon){
        Optional<Pabellon> oPabellon=pabellonDAO.buscarPorId(pabellonId);
        if(!oPabellon.isPresent()){
            throw new NotFoundException("No existe el pabellon con el Id " + pabellonId);

        }
        Pabellon pabellonActualizado = pabellonDAO.actualizarPabellon(oPabellon.get(),pabellon);
        return new ResponseEntity<Pabellon>(pabellonActualizado,HttpStatus.OK);

    }

    @DeleteMapping("/del/pabellonId/{pabellonId}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Integer pabellonId){
        Map<String,Object> respuesta = new HashMap<String,Object>();
        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(pabellonId);
        if(!oPabellon.isPresent()){
            throw new NotFoundException("No existe el pabellon con el ID " + pabellonId);
        }
        List<Aula> aulas = (List<Aula>) aulaDAO.findAulaByPabellonNombre(oPabellon.get().getNombre());
        aulas.forEach(aula -> aulaDAO.eliminarPorId(aula.getId()));
        pabellonDAO.eliminarPorId(pabellonId);
        respuesta.put("OK","Pabellon ID:" + pabellonId + "eliminado exitosamente");
        return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.NO_CONTENT);
    }




}
