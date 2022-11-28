<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div class="row text-center">
			<h1>Introduzca sus datos</h1>
		</div>
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
				<form action="CompraLibros">
					<div class="mb-3">
						<label for="nombre" class="form-label">Seleccione su
							nombre</label> <input type="text" class="form-control"
							id="exampleInputPassword1" name="nombre" value="Anabela">
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">Identificador</label>
						<input type="password" class="form-control"
							id="exampleInputPassword1" name="identificador" value="ARD36773F">
					</div>
					<button type="submit" name="accion" class="btn btn-primary"
						value="Entrar">Entrar</button>
				</form>
			</div>
			<c:if test="${! empty errores }">
				<c:forEach var="error" items="${errores }">
					<div class="row mt-3">
						<div class="col-2"></div>
						<div class="col-8">
							<div class="alert alert-danger">${error }</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>

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