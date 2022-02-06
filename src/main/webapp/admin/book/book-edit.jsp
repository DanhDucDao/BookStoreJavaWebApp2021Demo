<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="model.book.Publisher"%>
<%@page import="model.book.Author"%>
<%@page import="dao.book.AuthorDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.book.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <title>Admin | Publisher</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
    <%final String servletPath = getServletContext().getContextPath();%>
    <link href="<%=servletPath%>/admin/css/header.css" rel="stylesheet">
    <link href="<%=servletPath%>/admin/book/css/book-edit.css" rel="stylesheet">
    <link href="<%=servletPath%>/admin/book/css/book-item.css" rel="stylesheet">
  </head>
  <body>
  	<%Book b = (Book) request.getAttribute("book");
  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  		String strDate = sdf.format(b.getPublicationDate());
  	%>
    <div class="container">
    	<jsp:include page="../header.jsp"></jsp:include>
    	<h3>Book Edit</h3>
    	
    

   		<div class="container">
   			<form action="<%=servletPath%>/admin/book/edit" method="POST" enctype="multipart/form-data">
   			<input type="hidden" value="${book.id}" name="id">
   			
   			<label for="addISBN" class="form-label">ISBN</label>
   			<input type="text" class="form-control" id="addISBN" name="isbn" value="${book.isbn}"/>
   			
   			<label for="title" class="form-label">Title</label>
   			<input type="text" class="form-control" id="title" name="title" value="${book.title}"/>
   			
   			<label for="summary" class="form-label">Description</label>
   			<textarea rows="3" class="form-control" id="summary" name="summary" >${book.summary}</textarea>
   			

   			<label for="publicationDate" class="form-label">Publication Date</label>
   			<input type="date" class="form-control" id="publicationDate" name="publicationDate" value="<%=strDate%>"/>
   			
   			<label for="numberOfPages" class="form-label">Number of pages</label>
   			<input type="number" min="1" class="form-control" id="numberOfPages" name="numberOfPages" value="${book.numberOfPages}"/>
   			

   			<label for="language" class="form-label">Language</label>
   			<input type="text" class="form-control" id="language" name="language" value="${book.language}"/>
   			
   			<label for="coverImage" class="form-label">Cover Image</label>
   			<input type="file" class="form-control" id="coverImage" name="coverImage" placeholder="Page" accept="image/gif, image/jpeg, image/png"/>
   			
   			<img alt="Your Image" src="<%=servletPath%>/images/upload/${book.coverImage}" id="loadImage">
   			<label for="price" class="form-label">Price</label>
   			<input type="number" min="1" class="form-control" id="price" name="price" value="${book.price}"/>
   			
   			<label for="authors" class="form-label">Authors</label>
   			<select class="form-select form-control" id="authors" name="authors" size="3" multiple>
				<%
	   				List<Author> authors = (List<Author>) request.getAttribute("authors");
	   				List<Author> bookAus = b.getAuthors();
   					for(Author au : authors) {
   						boolean present = false;
   						for(Author ba : bookAus) {
   							if(au.getId() == ba.getId()) {
   								present = true;
   								break;
   							}
   						}
   						if(present == true) {
   						%>
   							<option value="<%=au.getId()%>" selected><%=au.getName() %></option>
   						<% } else {%>
   							<option value="<%=au.getId()%>"><%=au.getName() %></option>
   						<% }
   					}
   				
   				%>
   			</select>
   			
   			<label for="publishers" class="form-label">Publishers</label>
   			<select class="form-select form-control" id="publishers" name="publishers" size="3" multiple >
   				<%
	   				List<Publisher> pubs = (List<Publisher>) request.getAttribute("publishers");
	   				List<Publisher> bookPubs = b.getPublishers();
   					for(Publisher p : pubs) {
   						boolean present = false;
   						for(Publisher bp : bookPubs) {
   							if(bp.getId() == p.getId()) {
   								present = true;
   								break;
   							}

   						}
   						if(present == true) {
   						%>
   							<option value="<%=p.getId()%>" selected><%=p.getName()%></option>
   						<% } else {%>
   							<option value="<%=p.getId()%>"><%=p.getName()%></option>
   						<% }
   					}
   				%>
   			</select>
   			    	
   			<button type="submit" class="btn btn-primary mb-3" style="margin-top: 15px">Add Book</button>
   			</form>
   		</div>
   	</div>
   
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
    <script type="text/javascript" src="<%=servletPath%>/admin/book/js/book.js"></script>
  </body>
</html>