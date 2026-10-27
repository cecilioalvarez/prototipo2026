package es.curso.prototipo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.curso.prototipo.model.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class CursoRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Curso> buscarTodos() {
		return em.createQuery("select c from Curso c", Curso.class).getResultList();
	}

	public Optional<Curso> buscarPorId(Long id) {
		return Optional.ofNullable(em.find(Curso.class, id));
	}

	public List<Curso> buscarPorAutor(String autor) {
		return em.createQuery("select c from Curso c where c.autor = :autor", Curso.class)
				.setParameter("autor", autor)
				.getResultList();
	}

	@Transactional
	public Curso guardar(Curso curso) {
		if (curso.getId() == null) {
			em.persist(curso);
			return curso;
		}
		return em.merge(curso);
	}

	@Transactional
	public void borrar(Long id) {
		buscarPorId(id).ifPresent(em::remove);
	}
}
