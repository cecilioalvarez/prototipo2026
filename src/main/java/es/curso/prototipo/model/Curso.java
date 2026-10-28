package es.curso.prototipo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

@Entity
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	private String autor;

	private int duracion;

	/** Un curso se compone de n imparticiones. */
	@OneToMany(mappedBy = "curso")
	@OrderBy("fechaInicio")
	private List<Imparticion> imparticiones = new ArrayList<>();

	protected Curso() {
	}

	public Curso(String titulo, String autor, int duracion) {
		this.titulo = titulo;
		this.autor = autor;
		this.duracion = duracion;
	}

	public Long getId() {
		return id;
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

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public List<Imparticion> getImparticiones() {
		return Collections.unmodifiableList(imparticiones);
	}
}
