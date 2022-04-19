<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css"/>
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Book Club Login and Registration</title>
</head>
<body>
	<div>
		<h4 class="ms-5">Book Club</h4>
		<br>
		<h6 class="ms-5">A place for friends to share their thoughts on books.</h6>
	</div>
	<div class="container">
		<div class="d-flex">
			<!-- REGISTER -->
			<form:form action="/register" method="post" modelAttribute="newUser" class="p-3 col-4 mx-auto my-5 bg-dark text-light">
				<h2 class="text-center text-primary">Register</h2>
				<div class="form-group">
					<label>User Name:</label>
					<form:input path="userName" class="form-control"/>
					<form:errors path="userName" class="text-danger"/>
				</div>
				<div class="form-group">
					<label>Email:</label>
					<form:input path="email" class="form-control" />
					<form:errors path="email" class="text-danger" />
				</div>
				<div class="form-group">
					<label>Password:</label>
					<form:input path="password" class="form-control" type="password" />
					<form:errors path="password" class="text-danger" />
				</div>
				<div class="form-group">
					<label>Confirm Password:</label>
					<form:input path="confirm" class="form-control" type="password" />
					<form:errors path="confirm" class="text-danger" />
				</div>
				<input type="submit" value="Register" class="btn btn-primary"/>
			</form:form>
			<!-- LOGIN -->
			<form:form action="/login" method="post" modelAttribute="newLogin" class="p-3 col-4 mx-auto my-5 bg-dark text-light">
				<h2 class="text-center text-primary">Login</h2>
				<div class="form-group">
					<label>Email:</label>
					<form:input path="email" class="form-control"/>
					<form:errors path="email" class="text-danger"/>
				</div>
				<div class="form-group">
					<label>Password:</label>
					<form:input path="password" class="form-control" type="password"/>
					<form:errors path="password" class="text-danger"/>
				</div>
				<input type="submit" value="Login" class="btn btn-success"/>		
			</form:form>		
		</div>	
	</div>
</body>
</html>