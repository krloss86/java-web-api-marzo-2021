<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="alert alert-danger" role="alert">
						Usuario / Password Inválidos
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-12">	
					<form action="<%=request.getContextPath()%>/login.jsp">
						<button type="submit" class="btn btn-primary">Volver</button>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
