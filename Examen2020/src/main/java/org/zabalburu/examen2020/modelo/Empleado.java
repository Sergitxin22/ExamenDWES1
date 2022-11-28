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
@Table(name="employee")
public class Empleado {
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "emp_id")
	private String id;
	
	@Column(name = "fname")
	private String nombre;
	
	@Column(name="lname")
	private String apellido;
	
	@Column(name="minit")
	private String inicial;
	

	@OneToMany(mappedBy = "empleado")
	private List<LibroSeleccionado> libros;


	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", inicial=" + inicial + "]";
	}
	
	
	
}
