<%@page isErrorPage="true" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<!------ Include the above in your HEAD tag ---------->
<style type="text/css">
.error-template {padding: 40px 15px;text-align: center;}
.error-actions {margin-top:15px;margin-bottom:15px;}
.error-actions .btn { margin-right:10px; }
</style>
<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="error-template">
                <h1>
                    Oops!</h1>
                <h2>
                    404 Not Found</h2>
                <div class="error-details">
                    <%=exception.toString()%>
                </div>
                <div class="error-actions">
					<a href="<%=request.getContextPath()%>"
						class="btn btn-primary btn-lg">
						<span class="glyphicon glyphicon-home"></span> Take Me Home 
					</a>
                </div>
            </div>
        </div>
    </div>
</div>