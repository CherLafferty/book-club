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
<title>Edit Book</title>
</head>
<body>
<h1>Change your Entry</h1>
	<a href="/books">back to the shelves</a>
	<div>
		<form:form action="/books/${book.id}/edit" method="post" modelAttribute="book">
		<input type="hidden" name="_method" value="put">
			<div>
				<form:label path="title">Title:</form:label>
				<form:errors path="title"/>
				<form:input path="title"/>
			</div>
			<div>
				<form:label path="author">Author:</form:label>
				<form:errors path="author"/>
				<form:input path="author"/>
			</div>
			<div>
				<form:label path="thoughts">My Thoughts:</form:label>
				<form:errors path="thoughts"/>
				<form:textarea path="thoughts"/>
			</div>
			<form:hidden path="bookUser" value="${user_id}"/>
			<input type="submit" value="Submit" class="btn btn-primary"/>
		</form:form>
	</div>

</body>
</html>