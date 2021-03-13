<!-- jsp -->

<%@page import="ar.com.educationit.enums.ViewsEnums"%>
<%
	//casteo
  	String nombreRequest = (String)request.getAttribute(ViewsEnums.NOMBRE_REQUEST.name());

	String nombreSession = (String)session.getAttribute(ViewsEnums.NOMBRE_SESSION.name());
%>
<html>
	<header>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	</header>
	
	<div class="container">
		<div class="row">
			<div class="col">
				<div class="alert alert-primary" role="alert">
				  <%=nombreRequest %>
				</div>
				<div class="alert alert-secondary" role="alert">
				  <%=nombreSession %>
				</div>
			</div>
		</div>
	</div>
</html>