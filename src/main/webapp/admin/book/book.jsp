<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <title>Admin | Publisher</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
    <link href="css/header.css" rel="stylesheet">
    <link href="book/css/book.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">
    	<jsp:include page="../header.jsp"></jsp:include>
    	<h3>Books</h3>
    	<button data-target="#addModal" data-toggle="modal">Add Book</button>
    	<table class="table">
    		<thead>
    			<tr>
    				<th>ID</th>
    				<th>ISBN</th>
    				<th>Title</th>
    				<th>Price</th>
    				<th>Cover Image</th>
    				<th style="max-width: 70px">Option</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach var="item" items="${books}">
    			<tr>
    				<td><c:out value="${item.id}"></c:out></td>	
    				<td><c:out value="${item.isbn}"></c:out></td>	
    				<td><c:out value="${item.title}"></c:out></td>
    				<td><c:out value="${item.price}"></c:out></td>	
    				<td><c:out value="${item.status}"></c:out>
    				</td>	
    				<td><a href="book/edit?id=${item.id}"><button>Edit</button></a></td>	
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
    			<form action="book" method="POST" enctype="multipart/form-data">
    			<label for="addISBN" class="form-label">ISBN</label>
    			<input type="text" class="form-control" id="addISBN" name="isbn" placeholder="ISBN"/>
    			
    			<label for="title" class="form-label">Title</label>
    			<input type="text" class="form-control" id="title" name="title" placeholder="ISBN"/>
    			
    			<label for="summary" class="form-label">Description</label>
    			<textarea rows="3" class="form-control" id="summary" name="summary" placeholder="Summary"></textarea>
    			

    			<label for="publicationDate" class="form-label">Publication Date</label>
    			<input type="date" class="form-control" id="publicationDate" name="publicationDate" placeholder="Publication Date"/>
    			
    			<label for="numberOfPages" class="form-label">Number of pages</label>
    			<input type="number" min="1" class="form-control" id="numberOfPages" name="numberOfPages" placeholder="Number Of Pages"/>
    			

    			<label for="language" class="form-label">Language</label>
    			<input type="text" class="form-control" id="language" name="language" placeholder="Language"/>
    			
    			<label for="coverImage" class="form-label">Cover Image</label>
    			<input type="file" class="form-control" id="coverImage" name="coverImage" placeholder="Page" accept="image/gif, image/jpeg, image/png"/>
    			
    			<img alt="Your Image" src="#" id="loadImage">
    			<label for="price" class="form-label">Price</label>
    			<input type="number" min="1" class="form-control" id="price" name="price" placeholder="Price"/>
    			
    			<label for="authors" class="form-label">Authors</label>
    			<select class="form-select form-control" id="authors" name="authors" size="3" multiple="multiple">
    				<c:forEach var="item" items="${authors}">
    					<option value="${item.id}">${item.name}</option>
    				</c:forEach>
    			</select>
    			
    			<label for="publishers" class="form-label">Publishers</label>
    			<select class="form-select form-control" id="publishers" name="publishers" size="3" multiple="multiple" >
    				<c:forEach var="item" items="${publishers}">
    					<option value="${item.id}">${item.name}</option>
    				</c:forEach>
    			</select>
    			    	
    			<button type="submit" class="btn btn-primary mb-3" style="margin-top: 15px">Add Book</button>
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
    <script type="text/javascript" src="book/js/book.js"></script>
  </body>
</html>