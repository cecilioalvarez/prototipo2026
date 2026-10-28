package es.curso.prototipo.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/** Campos y validaciones comunes a los formularios de profesor y alumno. */
public abstract class UsuarioForm {

	@NotBlank(message = "El DNI es obligatorio")
	@Pattern(regexp = "\\s*(\\d{8}[A-Za-z])?\\s*", message = "El DNI debe tener 8 números y una letra")
	private String dni;

	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;

	@NotBlank(message = "Los apellidos son obligatorios")
	private String apellidos;

	/** DNI sin espacios y con la letra en mayúscula. */
	public String dniNormalizado() {
		return dni.trim().toUpperCase();
	}

	protected String nombreLimpio() {
		return nombre.trim();
	}

	protected String apellidosLimpios() {
		return apellidos.trim();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
}
