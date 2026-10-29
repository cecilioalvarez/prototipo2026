package es.curso.prototipo.cargadorescursos;

import java.util.ArrayList;
import java.util.List;

import es.curso.prototipo.model.Curso;
import es.utilidades.LectorFichero;

/** Carga cursos de un fichero con formato titulo;autor;duracion y cabecera. */
public class CargadorCursoCSV extends CargadorCursos {

	private static final String SEPARADOR = ";";

	public CargadorCursoCSV(LectorFichero lector) {
		super(lector);
	}

	@Override
	protected List<Curso> convertirLineas(List<String> lineas) {
		List<Curso> cursos = new ArrayList<>();

		// La primera línea es la cabecera
		for (String linea : lineas.subList(Math.min(1, lineas.size()), lineas.size())) {
			if (linea.isBlank()) {
				continue;
			}
			String[] campos = linea.split(SEPARADOR);
			cursos.add(new Curso(campos[0].trim(), campos[1].trim(), Integer.parseInt(campos[2].trim())));
		}
		return cursos;
	}
}
