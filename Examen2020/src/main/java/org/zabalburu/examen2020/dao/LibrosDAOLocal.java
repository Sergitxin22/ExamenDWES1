package org.zabalburu.examen2020.dao;

import java.util.List;

import javax.ejb.Local;

import org.zabalburu.examen2020.modelo.Empleado;
import org.zabalburu.examen2020.modelo.Libro;
import org.zabalburu.examen2020.modelo.LibroSeleccionado;

@Local
public interface LibrosDAOLocal {
	List<Empleado> getEmpleadoPorNombre(String nombre);
	Empleado getEmpleado(String id);
	LibroSeleccionado nuevoLibroEmpleado(Empleado e, Libro l);
	LibroSeleccionado eliminarLibroEmpleado(Empleado e, Libro l);
	LibroSeleccionado getLibroSeleccionado(Empleado e, Libro l);
	LibroSeleccionado getLibroSeleccionado(Integer id);
	List<Libro> getLibros();
	List<String> getTipos();
	List<Libro> getLibrosPorTipo(String tipo);
	Libro getLibro(String id);
}
