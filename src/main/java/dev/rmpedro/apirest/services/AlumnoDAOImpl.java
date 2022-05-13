package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.models.entities.Alumno;
import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.repositories.AlumnoRepository;
import dev.rmpedro.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO{
    @Autowired
    public AlumnoDAOImpl(@Qualifier("repositorioAlumnos")PersonaRepository repository) {
        super(repository);
    }


    @Override
    public Iterable<Persona> buscarAlumnoPorNombreCarrera(String carrera) {

        return ((AlumnoRepository) repository).buscarAlumnoPorNombreCarrera(carrera);
    }
    @Override
    public Persona actualizarAlumno(Persona alumnoEncontrado, Persona alumno) {
        Persona alumnoActualizado = null;
        alumnoEncontrado.setNombre(alumno.getNombre());
        alumnoEncontrado.setApellido(alumno.getApellido());
        alumnoEncontrado.setDireccion(alumno.getDireccion());
        alumnoActualizado=repository.save(alumnoEncontrado);
        return alumnoActualizado;
    }

    @Override
    public Persona asignarCarrera(Persona alumno, Carrera carrera) {
        ((Alumno)alumno).setCarrera(carrera);
        return repository.save(alumno);
    }
}
