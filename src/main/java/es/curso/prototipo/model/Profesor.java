package es.curso.prototipo.model;

import jakarta.persistence.Entity;

@Entity
public class Profesor extends Usuario {

	protected Profesor() {
	}

	public Profesor(String dni, String nombre, String apellidos) {
		super(dni, nombre, apellidos);
	}
}
