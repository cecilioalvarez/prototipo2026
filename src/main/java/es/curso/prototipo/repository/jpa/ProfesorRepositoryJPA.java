package es.curso.prototipo.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.curso.prototipo.model.Profesor;
import es.curso.prototipo.repository.ProfesorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class ProfesorRepositoryJPA implements ProfesorRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Profesor> buscarTodos() {
		return em.createQuery("select p from Profesor p order by p.apellidos, p.nombre", Profesor.class)
				.getResultList();
	}

	@Override
	public Optional<Profesor> buscarPorDni(String dni) {
		return em.createQuery("select p from Profesor p where p.dni = :dni", Profesor.class)
				.setParameter("dni", dni)
				.getResultStream()
				.findFirst();
	}

	@Override
	@Transactional
	public Profesor guardar(Profesor profesor) {
		if (profesor.getId() == null) {
			em.persist(profesor);
			return profesor;
		}
		return em.merge(profesor);
	}
}
