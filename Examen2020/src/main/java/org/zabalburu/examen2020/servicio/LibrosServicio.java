package org.zabalburu.examen2020.servicio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.hibernate.Hibernate;
import org.zabalburu.examen2020.dao.LibrosDAO;
import org.zabalburu.examen2020.dao.LibrosDAOLocal;
import org.zabalburu.examen2020.modelo.Empleado;
import org.zabalburu.examen2020.modelo.Libro;
import org.zabalburu.examen2020.modelo.LibroSeleccionado;

/**
 * Session Bean implementation class LibrosServicio
 */
@Stateless
@LocalBean
public class LibrosServicio {

	@EJB
	private LibrosDAOLocal dao;
    
	public LibrosServicio() {
        // TODO Auto-generated constructor stub
    }

    public Empleado login(String nombre, String pwd) {
    	Empleado e = dao.getEmpleadoPorNombre(nombre)
    			.stream()
    			.filter(emp -> emp.getId().equals(pwd))
    			.findAny()
    			.orElse(null);
    	if (e != null) {
    		Hibernate.initialize(e.getLibros());
    	}
    	return e;
    }
    
    public Empleado getEmpleado(String id) {
		return dao.getEmpleado(id);
	}
	
	public LibroSeleccionado nuevoLibroEmpleado(Empleado e, Libro l) {
		return dao.nuevoLibroEmpleado(e,l);
	}
	
	public LibroSeleccionado eliminarLibroEmpleado(Empleado e, Libro l) {
		return  dao.eliminarLibroEmpleado(e,l);
	}
	
	public LibroSeleccionado getLibroSeleccionado(Empleado e, Libro l) {
		return dao.getLibroSeleccionado(e, l);
	}
	
	public List<Libro> getLibros(){
		return dao.getLibros();
	}
	
	public List<String> getTipos(){
		return dao.getTipos();
	}
	
	public List<Libro> getLibrosPorTipo(String tipo) {
		return dao.getLibrosPorTipo(tipo);
	}
	
	public Libro getLibro(String id) {
		return dao.getLibro(id);
	}
	
	public LibroSeleccionado getLibroSeleccionado(Integer id) {
		return dao.getLibroSeleccionado(id);
	}
}
