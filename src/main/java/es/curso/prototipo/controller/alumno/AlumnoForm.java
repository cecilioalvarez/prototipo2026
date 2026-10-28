package es.curso.prototipo.controller.alumno;

import es.curso.prototipo.controller.UsuarioForm;
import es.curso.prototipo.model.Alumno;

/** Datos del formulario de alta de alumno. */
public class AlumnoForm extends UsuarioForm {

	public Alumno toAlumno() {
		return new Alumno(dniNormalizado(), nombreLimpio(), apellidosLimpios());
	}
}
