package es.curso.prototipo.controller.curso;

import es.curso.prototipo.model.Curso;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/** Datos del formulario de alta de curso. */
public class CursoForm {

	@NotBlank(message = "El título es obligatorio")
	private String titulo;

	@NotBlank(message = "El autor es obligatorio")
	private String autor;

	@NotNull(message = "La duración es obligatoria")
	@Min(value = 1, message = "La duración debe ser al menos 1 hora")
	private Integer duracion;

	public Curso toCurso() {
		return new Curso(titulo.trim(), autor.trim(), duracion);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
}
