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
<title>User Home Page</title>
</head>
<body>
	<div class="container">
		<h1>${activeUser.userName}</h1>
		<h1>Welcome ${userName} id: ${user_id}</h1>
		<a href="/logout">logout</a>
	</div>
	<div>
		<h6>Books from everyone's shelves:</h6>
		<a href="/books/new">+ Add a book to my shelf!</a>
	</div>
	<div>
		<table class="table table-bordered table-striped">
			<thead>
				<tr class="table-primary">
					<th>Id</th>
					<th>Title</th>
					<th>Author Name</th>
					<th>Posted By</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${allBooks}" var="book">
				<tr>
					<td><c:out value="${book.id}" /></td>
					<td><a href="/books/${book.id}">${book.title}</a></td>
					<td><c:out value="${book.author}" /></td>
					<td><c:out value="${book.bookUser.userName}" /></td>
					<c:if test="${user_id == book.bookUser.id}">
						<td>
							<form action="/books/${book.id}/delete" method="post">
							<input type="hidden" name="_method" value="Delete">
							<input type="submit" value="Delete">
							</form>
						</td>
					</c:if>
				</tr>
				</c:forEach>
			</tbody>
		</table>	
	</div>
	
</body>
</html>