package es.curso.prototipo.repository;

import java.util.List;
import java.util.Optional;

import es.curso.prototipo.model.Profesor;

public interface ProfesorRepository {

	List<Profesor> buscarTodos();

	Optional<Profesor> buscarPorDni(String dni);

	Profesor guardar(Profesor profesor);
}
