package es.curso.prototipo.cargadorescursos;

import java.util.List;

import es.curso.prototipo.model.Curso;
import es.utilidades.LectorFichero;

/** Base de los cargadores: lee el fichero y deja a cada subclase convertir las líneas en cursos. */
public abstract class CargadorCursos {

	private final LectorFichero lector;

	protected CargadorCursos(LectorFichero lector) {
		this.lector = lector;
	}

	public List<Curso> cargarCursos(String ruta) {
		return convertirLineas(lector.leerLineas(ruta));
	}

	protected abstract List<Curso> convertirLineas(List<String> lineas);
}
