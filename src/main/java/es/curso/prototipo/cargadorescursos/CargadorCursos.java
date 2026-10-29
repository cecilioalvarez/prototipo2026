package es.curso.prototipo.cargadorescursos;

import java.io.InputStream;
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

	public List<Curso> cargarCursos(InputStream entrada) {
		return convertirLineas(lector.leerLineas(entrada));
	}

	/** Indica si este cargador sabe leer el fichero, según su extensión. */
	public boolean admite(String nombreFichero) {
		return nombreFichero != null && nombreFichero.toLowerCase().endsWith(getExtension());
	}

	protected abstract String getExtension();

	protected abstract List<Curso> convertirLineas(List<String> lineas);
}
