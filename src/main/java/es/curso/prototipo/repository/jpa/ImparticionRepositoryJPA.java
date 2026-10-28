package es.curso.prototipo.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.curso.prototipo.model.Imparticion;
import es.curso.prototipo.repository.ImparticionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class ImparticionRepositoryJPA implements ImparticionRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Imparticion> buscarPorCurso(Long cursoId) {
		return em.createQuery("select i from Imparticion i where i.curso.id = :cursoId order by i.fechaInicio",
				Imparticion.class)
				.setParameter("cursoId", cursoId)
				.getResultList();
	}

	@Override
	public Optional<Imparticion> buscarPorId(Long id) {
		return Optional.ofNullable(em.find(Imparticion.class, id));
	}

	@Override
	@Transactional
	public Imparticion guardar(Imparticion imparticion) {
		if (imparticion.getId() == null) {
			em.persist(imparticion);
			return imparticion;
		}
		return em.merge(imparticion);
	}

	@Override
	@Transactional
	public void borrar(Long id) {
		buscarPorId(id).ifPresent(em::remove);
	}
}
