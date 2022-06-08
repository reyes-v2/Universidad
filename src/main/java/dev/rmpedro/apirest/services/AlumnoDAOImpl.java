package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.models.entities.Alumno;
import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.repositories.AlumnoRepository;
import dev.rmpedro.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    @Override
    public Iterable<Alumno> buscarTodosAlumnos() {
        List<Alumno> alumnos = (List<Alumno>) ((AlumnoRepository)repository).buscarTodosAlumnos();
        if (alumnos.isEmpty()) {
            throw new NotFoundException("No existen alumnos");

        }

        return alumnos;
    }

    @Override
    public Optional<Alumno> buscarAlumnoPorId(Integer id) {
        Optional<Alumno> buscarAlumno = ((AlumnoRepository)repository).buscarAlumnoId(id);
        if (!buscarAlumno.isPresent()) {
            throw new NotFoundException("No existe el alumno con el ID " + id);
        }
        return buscarAlumno;
    }

}
