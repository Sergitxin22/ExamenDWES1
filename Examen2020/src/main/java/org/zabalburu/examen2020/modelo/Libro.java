package org.zabalburu.examen2020.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="titles")
public class Libro {
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "title_id")
	private String id;
	
	@Column(name="title")
	private String titulo;
	
	@Column(name="type")
	private String tipo;
	
	@Column(name="price")
	private Double precio;
	
	@OneToMany(mappedBy = "libro")
	private List<LibroSeleccionado> librosSeleccionados;

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", tipo=" + tipo + ", precio=" + precio + "]";
	}	
	
}
