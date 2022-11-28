<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>Introduzca sus datos</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-10"></div>
			<div class="col-2">
				${empleado.nombre } ${empleado.inicial } ${empleado.apellido }<br>
				<a href="CompraLibros?accion=VerCarrito">${empleado.libros.size() }</a> 
			</div>
		</div>

		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
				<h1>Seleccione el Tipo de Libro</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-2"></div>
			<div class="col-10">
				<form action="CompraLibros">
					<select class="form-select" name="tipo">
						<c:forEach var="tipo" items="${tipos}">

							<option ${param.tipo == tipo?"SELECTED":""} value="${tipo}">${tipo}</option>
						</c:forEach>
					</select>
					<button type="submit" name="accion" class="btn btn-primary"
						value="Buscar">Buscar</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-2"></div>
			<div classs="col-8">
				<form action="CompraLibros">
					<input type="hidden" value="${param.tipo }" name="tipo">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Título</th>
								<th scope="col">Precio</th>
								<th scope="col">Seleccione</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="l" items="${libros }">
								<tr>
									<th scope="row">${l.id }</th>
									<td>${l.titulo }</td>
									<td><fmt:formatNumber value="${l.precio }" type="currency"></fmt:formatNumber>
									</td>
									<td><input type="checkbox" name="id"
										class="form-check-input" value="${l.id}"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<button type="submit" name="accion" class="btn btn-primary"
						value="Comprar">Comprar</button>
					<button type="submit" name="accion" class="btn btn-primary"
						value="Salir">Salir</button>
				</form>
			</div>
			<c:if test="${!empty error }">
				<div class="row">
					<div class="col-2"></div>
					<div class="col-8">
						<div class="alert alert-danger">${error }</h1>
					</div>
				</div>
			</c:if>
		</div>

		<!-- Optional JavaScript; choose one of the two! -->

		<!-- Option 1: Bootstrap Bundle with Popper -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
			crossorigin="anonymous"></script>

		<!-- Option 2: Separate Popper and Bootstrap JS -->
		<!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    -->
</body>
</html>