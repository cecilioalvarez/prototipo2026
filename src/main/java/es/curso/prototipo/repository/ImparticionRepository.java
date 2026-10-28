package es.curso.prototipo.repository;

import java.util.List;
import java.util.Optional;

import es.curso.prototipo.model.Imparticion;

public interface ImparticionRepository {

	List<Imparticion> buscarPorCurso(Long cursoId);

	Optional<Imparticion> buscarPorId(Long id);

	Imparticion guardar(Imparticion imparticion);

	void borrar(Long id);
}
