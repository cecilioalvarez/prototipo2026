package es.curso.prototipo.controller.profesor;

import es.curso.prototipo.controller.UsuarioForm;
import es.curso.prototipo.model.Profesor;

/** Datos del formulario de alta de profesor. */
public class ProfesorForm extends UsuarioForm {

	public Profesor toProfesor() {
		return new Profesor(dniNormalizado(), nombreLimpio(), apellidosLimpios());
	}
}
