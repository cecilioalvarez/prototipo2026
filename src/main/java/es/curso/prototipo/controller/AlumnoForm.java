package es.curso.prototipo.controller;

import es.curso.prototipo.model.Alumno;

/** Datos del formulario de alta de alumno. */
public class AlumnoForm extends UsuarioForm {

	public Alumno toAlumno() {
		return new Alumno(dniNormalizado(), nombreLimpio(), apellidosLimpios());
	}
}
