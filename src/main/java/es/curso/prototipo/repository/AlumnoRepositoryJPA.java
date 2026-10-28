package es.curso.prototipo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.curso.prototipo.model.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class AlumnoRepositoryJPA implements AlumnoRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Alumno> buscarTodos() {
		return em.createQuery("select p from Alumno p order by p.apellidos, p.nombre", Alumno.class)
				.getResultList();
	}

	@Override
	public Optional<Alumno> buscarPorDni(String dni) {
		return em.createQuery("select p from Alumno p where p.dni = :dni", Alumno.class)
				.setParameter("dni", dni)
				.getResultStream()
				.findFirst();
	}

	@Override
	@Transactional
	public Alumno guardar(Alumno alumno) {
		if (alumno.getId() == null) {
			em.persist(alumno);
			return alumno;
		}
		return em.merge(alumno);
	}
}
