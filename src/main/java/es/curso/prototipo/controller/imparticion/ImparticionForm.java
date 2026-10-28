package es.curso.prototipo.controller.imparticion;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import es.curso.prototipo.model.Curso;
import es.curso.prototipo.model.Imparticion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/** Datos del formulario de alta y edición de impartición. */
public class ImparticionForm {

	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;

	@NotNull(message = "La fecha de inicio es obligatoria")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fechaInicio;

	@NotNull(message = "La fecha de fin es obligatoria")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fechaFin;

	public static ImparticionForm desde(Imparticion imparticion) {
		ImparticionForm form = new ImparticionForm();
		form.nombre = imparticion.getNombre();
		form.fechaInicio = imparticion.getFechaInicio();
		form.fechaFin = imparticion.getFechaFin();
		return form;
	}

	/** True si las dos fechas están informadas y la de fin es anterior a la de inicio. */
	public boolean fechaFinAnteriorAInicio() {
		return fechaInicio != null && fechaFin != null && fechaFin.isBefore(fechaInicio);
	}

	public Imparticion toImparticion(Curso curso) {
		return new Imparticion(curso, nombre.trim(), fechaInicio, fechaFin);
	}

	public void copiarEn(Imparticion imparticion) {
		imparticion.setNombre(nombre.trim());
		imparticion.setFechaInicio(fechaInicio);
		imparticion.setFechaFin(fechaFin);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
}
