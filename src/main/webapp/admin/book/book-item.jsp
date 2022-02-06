<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Item</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
    <link href="<%=getServletContext().getContextPath()%>/admin/css/header.css" rel="stylesheet">
    <link href="<%=getServletContext().getContextPath()%>/admin/book/css/book-item.css" rel="stylesheet">

</head>
<body>
	<%final String contextPath = getServletContext().getContextPath();%>
	
	<div class="container">
		<jsp:include page="../header.jsp"></jsp:include>
		<h3>Book Item</h3>
		<hr/>
		<div class="row">
			<div class="col-md-6 col-sm-12 col-6">
				<img alt="Your Image" src="<%=contextPath%>/images/upload/${book.coverImage}">
				
				<div class="detail-line">
					<p class="label">ISBN:</p>
					<p class="content">${book.isbn}</p>
				</div>
				
				<div class="detail-line">
					<p class="label">Tiêu đề:</p>
					<p class="content">${book.title}</p>
				</div>
				
				<div class="detail-line">
				<p class="label">Tác giả:</p>
				<p class="content">
					<c:forEach var="author" items="${book.authors}" varStatus="loop">
						<span>${author.name}</span>
						<c:if test="${not loop.last}"><span>,</span></c:if>
					</c:forEach>
				</p>
				</div>
				
				<div class="detail-line">
					<p class="label">Nội dung:</p>
					<p class="content">${book.summary}</p>
				</div>
				
				<div class="detail-line">
					<p class="label">Ngày xuất bản:</p>
					<p class="content">
						<fmt:parseDate type="both" dateStyle="medium" timeStyle="medium" value="${book.publicationDate}" pattern="yyyy-MM-dd"></fmt:parseDate>
					</p>
				</div>
				
				<div class="detail-line">
					<p class="label">Số trang:</p>
					<p class="content">${book.numberOfPages}</p>
				</div>
				
				<div class="detail-line">
				<p class="label">Ngôn ngữ:</p>
				<p class="content">${book.language}</p>
				</div>
				
				<div class="detail-line">
					<p class="label">Giá:</p>
					<p class="content">${book.price}</p>
				</div>
				
				<div class="detail-line">
					<p class="label">Trạng thái:</p>
					<p class="content">${book.status}</p>
				</div>
				<div class="detail-line">
					<p class="label">Ngày tạo:</p>
					<p class="content">
						<fmt:parseDate type="both" dateStyle="short" timeStyle="short" value="${book.createDate}" pattern="yyyy-MM-dd HH:mm:ss.S"></fmt:parseDate>
					</p>
				</div>
				
			</div>
			<div class="col-md-6 col-sm-12 col-6">
				<button data-target="#addModal" data-toggle="modal">Thêm Sách</button>
				<table class="table table-bordered">
					<thead class="thead-dark">
						<tr>
							<td>ID</td>
							<td>Bar Code</td>
							<td>Import Date</td>
							<td>Import Price</td>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${bookItems.size() eq 0}">
								<td colspan="4">No Item Avalible</td>
							</c:when>
							<c:when test="${bookItems.size() gt 0}">
								<c:forEach var="item" items="${bookItems}">
									<tr>
										<td><c:out value="${item.id}"></c:out></td>
										<td><c:out value="${item.barCode}"></c:out></td>
										<td><c:out value="${item.importDate}"></c:out></td>
										<td><c:out value="${item.importPrice}"></c:out></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>	
					</tbody>
				</table>
			</div>
			
		</div>
	</div>
	
	<div class="modal fade" id="addModal" aria-hidden="true" tabindex="-1">
    	<div class="modal-dialog" role="document">
    	<div class="modal-content">
    		<div class="modal-header">Add Book</div>
    		<div class="modal-body">
    			<form action="<%=contextPath%>/admin/bookItem" method="POST">
    			<input type="hidden" name="bookId" value="${book.id}"/>
    			<label for="barCode" class="form-label">Bar Code</label>
    			<input type="text" class="form-control" id="barCode" name="barCode" placeholder="Book Barcode"/>
    			
    			<label for="importPrice" class="form-label">Import Price</label>
    			<input type="number" min="1" class="form-control" id="importPrice" name="importPrice" placeholder="Price"/>
    			
    			<button type="submit" class="btn btn-primary mb-3">Add book</button>
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
    <script type="text/javascript" src="<%=contextPath%>/admin/book/js/book.js"></script>

</body>
</html>