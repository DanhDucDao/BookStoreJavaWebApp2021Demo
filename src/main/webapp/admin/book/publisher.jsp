<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <title>Admin | Publisher</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
    <link href="<%=getServletContext().getContextPath()%>/admin/css/header.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">
    	<jsp:include page="../header.jsp"></jsp:include>
    	<h3>Publisher</h3>
    	<button data-target="#addModal" data-toggle="modal">Thêm Nhà xuất bản</button>
    	<table class="table">
    		<thead>
    			<tr>
    				<th>ID</th>
    				<th>Name</th>
    				<th>Description</th>
    				<th>Email</th>
    				<th>Address</th>
    				<th>Phone Number</th>
    				<th style="max-width: 70px">Option</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach var="item" items="${publishers}">
    			<tr>
    				<td><c:out value="${item.id}"></c:out></td>	
    				<td><c:out value="${item.name}"></c:out></td>	
    				<td><c:out value="${item.description}"></c:out></td>
    				<td><c:out value="${item.email}"></c:out></td>	
    				<td><c:out value="${item.address}"></c:out></td>
    				<td><c:out value="${item.phoneNumber}"></c:out></td>		
    				<td><a href="publisher/edit?id=${item.id}"><button>Edit</button></a></td>	
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
    			<form action="publisher" method="POST">
    			<label for="addPubName" class="form-label">Name</label>
    			<input type="text" class="form-control" id="addPubName" name="name" placeholder="Name"/>
    			
    			<label for="addPubDesc" class="form-label">Description</label>
    			<textarea rows="3" class="form-control" id="addPubDesc" name="description" placeholder="Description"></textarea>
    			

    			<label for="addPubEmail" class="form-label">Email</label>
    			<input type="text" class="form-control" id="addPubEmail" name="email" placeholder="Email"/>
    			
    			<label for="addPubAddr" class="form-label">Address</label>
    			<input type="text" class="form-control" id="addPubAddr" name="address" placeholder="Address"/>
    			

    			<label for="addPubPhone" class="form-label">Phone</label>
    			<input type="text" class="form-control" id="addPubPhone" name="phoneNumber" placeholder="Phone"/>
    	
    			<button type="submit" class="btn btn-primary mb-3">Add Publisher</button>
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