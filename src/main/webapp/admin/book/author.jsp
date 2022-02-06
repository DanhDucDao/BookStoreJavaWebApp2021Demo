<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <title>Author</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
    <link href="<%=getServletContext().getContextPath()%>/admin/css/header.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">
    	<jsp:include page="../header.jsp"></jsp:include>
    	<h3>Author</h3>
    	<button data-target="#addModal" data-toggle="modal">Thêm tác giả</button>
    	<table class="table">
    		<thead>
    			<tr>
    				<th>ID</th>
    				<th>Name</th>
    				<th>Biography</th>
    				<th style="max-width: 70px">Option</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach var="author" items="${authors}">
    			<tr>
    				<td><c:out value="${author.id}"></c:out></td>	
    				<td><c:out value="${author.name}"></c:out></td>	
    				<td><c:out value="${author.biography}"></c:out></td>	
    				<td><a href="author/edit?id=${author.id}"><button>Edit</button></a></td>	
    			</tr>
    			</c:forEach>
    		</tbody>
    	</table>
    </div>
    
    <div class="modal fade" id="addModal" aria-hidden="true" tabindex="-1">
    	<div class="modal-dialog" role="document">
    	<div class="modal-content">
    		<div class="modal-header">Add Author</div>
    		<div class="modal-body">
    			<form action="author/add" method="POST">
    			<label for="addAuthorName" class="form-label">Name</label>
    			<input type="text" class="form-control" id="addAuthorName" name="name" placeholder="Author Name"/>
    			
    			<label for="addAuthorName" class="form-label">Name</label>
    			<textarea rows="3" class="form-control" id="addAuthorBiography" name="bio" placeholder="Author Biography"></textarea>
    			
    			<button type="submit" class="btn btn-primary mb-3">Add Author</button>
    			</form>
    		</div>
    		<div class="modal-footer">
    			<button type="button" data-dismiss="modal">Close</button>
    		</div>
    	</div>
    	</div>
    </div>
   
     <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
  </body>
</html>