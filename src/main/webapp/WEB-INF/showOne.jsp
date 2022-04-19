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
<title>Book Club</title>
</head>
<body>
	<a href="/books">back to the bookshelves</a>
	<h1>${book.title}</h1>
	<p></p>
	<h5> ${book.bookUser.userName} read ${book.title} by ${book.author}</h5>
	<p></p>
	<h6>Here are ${book.bookUser.userName}'s thoughts:</h6>
	<hr>
	<p>${book.thoughts}</p>
	
	<c:if test="${user_id == book.bookUser.id}">
		<a class="btn btn-primary" href="/books/${id}/edit">edit</a>
	</c:if>

</body>
</html>