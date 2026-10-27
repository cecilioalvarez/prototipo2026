package es.curso.prototipo.repository;

import java.util.List;
import java.util.Optional;

import es.curso.prototipo.model.Curso;

public interface CursoRepository {

	List<Curso> buscarTodos();

	Optional<Curso> buscarPorId(Long id);

	List<Curso> buscarPorAutor(String autor);

	Curso guardar(Curso curso);

	void borrar(Long id);
}
