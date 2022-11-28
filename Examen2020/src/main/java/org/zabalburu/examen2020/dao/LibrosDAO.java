package org.zabalburu.examen2020.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.zabalburu.examen2020.modelo.Empleado;
import org.zabalburu.examen2020.modelo.Libro;
import org.zabalburu.examen2020.modelo.LibroSeleccionado;

/**
 * Session Bean implementation class LibrosDAO
 */
@Stateful
@Transactional
public class LibrosDAO implements LibrosDAOLocal {
	@PersistenceContext(unitName = "Examen2020")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public LibrosDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Empleado> getEmpleadoPorNombre(String nombre) {
		Query q = em.createQuery("Select e From Empleado e Where upper(e.nombre)=upper(:nombre)");
		q.setParameter("nombre", nombre);
		return q.getResultList();
	}

	@Override
	public Empleado getEmpleado(String id) {
		return em.find(Empleado.class, id);
	}

	@Override
	public LibroSeleccionado nuevoLibroEmpleado(Empleado e, Libro l) {
		LibroSeleccionado ls = new LibroSeleccionado();
		ls.setEmpleado(e);
		ls.setLibro(l);
		em.persist(ls);
		e.getLibros().add(ls);
		return ls;
	}

	@Override
	public LibroSeleccionado eliminarLibroEmpleado(Empleado e, Libro l) {
		Query q = em.createQuery("Select ls From LibroSeleccionado ls Where ls.empleado=:e and ls.libro=:l");
		q.setParameter("e", e);
		q.setParameter("l", l);
		LibroSeleccionado ls = (LibroSeleccionado) q.getResultList().get(0);
		em.remove(ls);
		e.getLibros().remove(ls);
		return ls;
	}

	@Override
	public LibroSeleccionado getLibroSeleccionado(Empleado e, Libro l) {
		Query q = em.createQuery("Select l From LibroSeleccionado l Where l.empleado=:e and l.libro = :l");
		q.setParameter("e", e);
		q.setParameter("l", l);
		LibroSeleccionado ls = null;
		try {
			ls = (LibroSeleccionado) q.getSingleResult();
		} catch (NoResultException ex) {}
		return ls;
	}

	@Override
	public List<Libro> getLibros() {
		Query q = em.createQuery("Select l From Libro l Order by l.titulo");
		return q.getResultList();
	}

	@Override
	public List<String> getTipos() {
		Query q = em.createQuery("Select distinct l.tipo From Libro l Order by l.tipo");
		return q.getResultList();
	}

	@Override
	public List<Libro> getLibrosPorTipo(String tipo) {
		Query q = em.createQuery("Select l From Libro l Where l.tipo=:tipo Order by l.titulo");
		q.setParameter("tipo", tipo);
		return q.getResultList();
	}

	@Override
	public Libro getLibro(String id) {
		return em.find(Libro.class, id);
	}

	@Override
	public LibroSeleccionado getLibroSeleccionado(Integer id) {
		// TODO Auto-generated method stub
		return em.find(LibroSeleccionado.class, id);
	}
	
	

}
