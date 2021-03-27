<%@page import="ar.com.educationit.enums.ViewsEnums"%>
<%@page import="ar.com.educationit.wssoap.Producto"%>

<html>
<header>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
		crossorigin="anonymous">
</header>

<body>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Título</th>
				<th scope="col">Código</th>
				<th scope="col">Precio</th>
				<th scope="col">Tipo Producto/th>
			</tr>
		</thead>
		<tbody>
			<%
				Producto[] productos = (Producto[])request.getAttribute(ViewsEnums.LISTADO_PRODUCTOS.name());
				for(Producto producto : productos) {
			%>
				<tr>
					<th scope="row"><%=producto.getId() %></th>
					<td><%=producto.getTitulo() %></td>
					<td><%=producto.getCodigo() %></td>
					<td><%=producto.getPrecio() %></td>
					<td><%=producto.getTipoProducto().getDescripcion()%></td>
				</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>