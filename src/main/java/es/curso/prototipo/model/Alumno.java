package es.curso.prototipo.model;

import jakarta.persistence.Entity;

@Entity
public class Alumno extends Usuario {

	protected Alumno() {
	}

	public Alumno(String dni, String nombre, String apellidos) {
		super(dni, nombre, apellidos);
	}
}
