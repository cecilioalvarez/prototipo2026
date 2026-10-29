package es.curso.prototipo.cargadorescursos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.curso.prototipo.model.Curso;
import es.utilidades.LectorFichero;

/** Carga cursos de un fichero con bloques clave=valor separados por una línea en blanco. */
public class CargadorCursoTxt extends CargadorCursos {

	public CargadorCursoTxt(LectorFichero lector) {
		super(lector);
	}

	@Override
	protected String getExtension() {
		return ".txt";
	}

	@Override
	protected List<Curso> convertirLineas(List<String> lineas) {
		List<Curso> cursos = new ArrayList<>();
		Map<String, String> datos = new HashMap<>();

		for (String linea : lineas) {
			if (linea.isBlank()) {
				añadirCurso(datos, cursos);
				continue;
			}
			String[] partes = linea.split("=", 2);
			datos.put(partes[0].trim(), partes[1].trim());
		}
		// El último bloque puede no terminar en línea en blanco
		añadirCurso(datos, cursos);
		return cursos;
	}

	private void añadirCurso(Map<String, String> datos, List<Curso> cursos) {
		if (datos.isEmpty()) {
			return;
		}
		cursos.add(new Curso(datos.get("titulo"), datos.get("autor"), Integer.parseInt(datos.get("duracion"))));
		datos.clear();
	}
}
