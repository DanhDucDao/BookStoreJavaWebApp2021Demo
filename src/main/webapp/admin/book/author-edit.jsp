<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Author</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<link href="<%=getServletContext().getContextPath()%>/admin/css/header.css" rel="stylesheet">
</head>
<body>
	<div class="container">
	<jsp:include page="../header.jsp"></jsp:include>
	<form action="<%=getServletContext().getContextPath() %>/admin/author/edit" method="POST" style="margin: 0 auto;">
		<input type ="hidden" name="id" value="${author.id}"/>
		<label for="addAuthorName" class="form-label">Name</label>
		<input type="text" class="form-control" id="addAuthorName" 
		name="name" placeholder="Author Name" value="${author.name}"/>
		
		<label for="addAuthorName" class="form-label">Biography</label>
		<textarea rows="3" class="form-control" 
			id="addAuthorBiography" 
			name="biography" placeholder="Author Biography">
			<c:out value="${author.biography}"></c:out>
		</textarea>
    			
    	<input type="submit" class="btn btn-primary mb-3" value="Edit"/>
    </form>
	</div>


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
</body>
</html>