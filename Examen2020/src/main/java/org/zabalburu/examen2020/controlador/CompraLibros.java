package org.zabalburu.examen2020.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.zabalburu.examen2020.modelo.Empleado;
import org.zabalburu.examen2020.modelo.Libro;
import org.zabalburu.examen2020.modelo.LibroSeleccionado;
import org.zabalburu.examen2020.servicio.LibrosServicio;

/**
 * Servlet implementation class CompraLibros
 */
@WebServlet("/CompraLibros")
public class CompraLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB
	private LibrosServicio servicio;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompraLibros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		String pagina = "";
		HttpSession sesion = request.getSession();
		
		if (accion == null) {
			pagina = "login.jsp";
		} else if (accion.equals("Entrar")) {
			pagina = entrar(request,sesion);
		} else if (accion.equals("Buscar")) {
			pagina = buscar(request,sesion);
		} else if (accion.equals("Comprar")) {
			pagina = comprar(request,sesion);
		} else if (accion.equals("Salir")) {
			sesion.invalidate();
			pagina = "login.jsp";
		} else if (accion.equals("VerCarrito")) {
			pagina = "carrito.jsp";
		} else if (accion.equals("Quitar")) {
			pagina = quitar(request,sesion);
		} else if (accion.equals("Finalizar")) {
			pagina = finalizar(request,sesion);
		} else if (accion.equals("Volver")) {
			List<String> tipos = servicio.getTipos();
			request.setAttribute("tipos", tipos);
			request.setAttribute("libros", servicio.getLibrosPorTipo(tipos.get(0)));
			pagina="seleccionLibro.jsp";
		} 
		
		request.getRequestDispatcher(pagina).forward(request, response);
	}

	private String finalizar(HttpServletRequest request, HttpSession sesion) {
		Empleado e = (Empleado) sesion.getAttribute("empleado");
		List<LibroSeleccionado> lista = e.getLibros()
				.stream()
				.collect(Collectors.toList());
		for (LibroSeleccionado l : lista) {
			servicio.eliminarLibroEmpleado(e, l.getLibro());
		}
		sesion.invalidate();
		return "login.jsp";
	}

	private String quitar(HttpServletRequest request, HttpSession sesion) {
		String[] strIds = request.getParameterValues("id");
		Empleado e = (Empleado) sesion.getAttribute("empleado");
		LibroSeleccionado ls = null;
		if (strIds == null) {
			request.setAttribute("error", "Debe seleccionarse algún libro");
		} else {
			for(String strId : strIds) {
				ls = servicio.getLibroSeleccionado(Integer.parseInt(strId));
				ls = servicio.eliminarLibroEmpleado(e,ls.getLibro());
			}
			sesion.setAttribute("empleado", e);
			request.setAttribute("mensaje", "Se han eliminado " + strIds.length + " libros.");
		}
		return "carrito.jsp";
	}

	private String comprar(HttpServletRequest request, HttpSession sesion) {
		String[] strIds = request.getParameterValues("id");
		LibroSeleccionado ls = null;
		if (strIds == null) {
			request.setAttribute("error", "Debe seleccionar algún id");
		} else {
			Empleado e = (Empleado) sesion.getAttribute("empleado");
			for(String id : strIds) {
				ls = servicio.nuevoLibroEmpleado(e, servicio.getLibro(id));
			}
			sesion.setAttribute("empleado", e);
			request.setAttribute("msg", "Se han añadido " + strIds.length + " libros a su carrito");
		}
		List<String> tipos = servicio.getTipos();
		request.setAttribute("tipos", tipos);
		String tipo = request.getParameter("tipo");
		if (tipo.isEmpty()) {
			tipo = tipos.get(0);
		}
		request.setAttribute("libros", servicio.getLibrosPorTipo(tipo));
		return "seleccionLibro.jsp";
	}

	private String buscar(HttpServletRequest request, HttpSession sesion) {
		String tipo =request.getParameter("tipo");
		List<String> tipos = servicio.getTipos();
		request.setAttribute("tipos", tipos);
		request.setAttribute("libros", servicio.getLibrosPorTipo(tipo));
		return "seleccionLibro.jsp";
	}

	private String entrar(HttpServletRequest request, HttpSession sesion) {
		String nombre = request.getParameter("nombre");
		String pwd = request.getParameter("identificador");
		List<String> errores = new ArrayList<>();
		if (nombre.trim().isEmpty()) {
			errores.add("Debe especificarse el campo NOMBRE");
		}
		if (pwd.trim().isEmpty()) {
			errores.add("Debe especificarse el campo IDENTIFICADOR");
		}
		if (errores.isEmpty()) {
			Empleado e = servicio.login(nombre, pwd);
			if (e == null){
				errores.add("Usuario / Identificador erróneos");
			} else {
				sesion.setAttribute("empleado", e);
				List<String> tipos = servicio.getTipos();
				request.setAttribute("tipos", tipos);
				request.setAttribute("libros", servicio.getLibrosPorTipo(tipos.get(0)));
				return "seleccionLibro.jsp";
			}
		}
		request.setAttribute("errores", errores);
		return "login.jsp";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
