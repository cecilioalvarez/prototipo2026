package es.curso.prototipo.repository;

import java.util.List;
import java.util.Optional;

import es.curso.prototipo.model.Alumno;

public interface AlumnoRepository {

	List<Alumno> buscarTodos();

	Optional<Alumno> buscarPorDni(String dni);

	Alumno guardar(Alumno alumno);
}
