package es.curso.prototipo.cargadorescursos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.utilidades.LectorFichero;

/** Registra el lector y los cargadores como beans para poder inyectarlos. */
@Configuration
public class CargadoresConfig {

	@Bean
	public LectorFichero lectorFichero() {
		return new LectorFichero();
	}

	@Bean
	public CargadorCursoCSV cargadorCursoCSV(LectorFichero lectorFichero) {
		return new CargadorCursoCSV(lectorFichero);
	}

	@Bean
	public CargadorCursoTxt cargadorCursoTxt(LectorFichero lectorFichero) {
		return new CargadorCursoTxt(lectorFichero);
	}
}
