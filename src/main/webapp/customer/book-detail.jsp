<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${book.title}</title>

<link href="<%=getServletContext().getContextPath()%>/customer/css/header.css" rel="stylesheet">
<link href="<%=getServletContext().getContextPath()%>/customer/css/home.css" rel="stylesheet">
<link href="<%=getServletContext().getContextPath()%>/customer/css/book-detail.css" rel="stylesheet">


</head>
<body>
	<%String contextPath = getServletContext().getContextPath();%>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<h3>Book Details</h3>
		
		<div class="detail-container">
			<div class="left-column">
				<img src="<%=contextPath%>/images/upload/${book.coverImage}" alt="${book.title}">
			</div>
		
			<div class="center-column">
				<p class="title">${book.title}</p>
				<p class="price">
					<fmt:setLocale value="vi_VN"/>
					<fmt:formatNumber value="${book.price}" type="currency"/>
				</p>
				
				<div class="detail-line">
						<p class="label">ISBN :</p>
						<p class="content">${book.isbn}</p>
					</div>
					
					<div class="detail-line">
					<p class="label">Tác giả :</p>
					<p class="content">
						<c:choose>
							<c:when test="${book.authors.size() gt 0}">
								<c:forEach var="author" items="${book.authors}" varStatus="loop">
									<span>${author.name}</span>
									<c:if test="${not loop.last}"><span>,</span></c:if>
								</c:forEach>
							</c:when>
							
							<c:when test="${book.authors.size() eq 0}">
								<span>Unknown</span>
							</c:when>
						</c:choose>
						
					</p>
					</div>
					
					<div class="detail-line">
						<p class="label">Nội dung :</p>
						<p class="content">${book.summary}</p>
					</div>
					
					<div class="detail-line">
						<p class="label">Ngày xuất bản:</p>
						<p class="content">
							<fmt:setLocale value="vi_VN"/>
							<fmt:formatDate type="date" value="${book.publicationDate}"></fmt:formatDate>
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
						<p class="label">Trạng thái:</p>
						<p class="content">${book.status}</p>
					</div>
					
					<div class="detail-line">
					<p class="label">Nhà Xuất Bản:</p>
					<p class="content">
						<c:choose>
							<c:when test="${book.publishers.size() gt 0}">
								<c:forEach var="publisher" items="${book.publishers}" varStatus="loop">
									<span>${publisher.name}</span>
									<c:if test="${not loop.last}"><span>,</span></c:if>
								</c:forEach>
							</c:when>
							
							<c:when test="${book.publishers.size() eq 0}">
								<span>Unknown</span>
							</c:when>
						</c:choose>						
					</p>
					</div>
			</div>
		
			<div class="right-column">
				<div class="form-container">
					<ul class="advice">
						<li class="advice-item">Hàng thật 100%.</li>
						<li class="advice-item">Đổi trả trong 7 ngày nếu phát hiện sản phẩm lỗi.</li>
						<li class="advice-item">Hỗ trợ ship toàn quốc</li>
					</ul>
					<c:if test="${ empty sessionScope.user}">
						<form action="<%=contextPath %>/login" method="GET">
						<input type="hidden" name="returnUrl" value='${requestScope["javax.servlet.forward.request_uri"]}?bookId=${book.id}'/>
						<div class="quantity">
							<label for="number">Số lượng </label>
							<input type="number" min="1" id="quantity" value="1">
						</div>
						<button type="submit" value="Thêm vào giỏ hàng">Thêm vào giỏ hàng</button>
					</form>
					</c:if>
					<c:if test="${not empty sessionScope.user}">
						<form id="addToCartForm" action="#" method="POST">
						<input type="hidden" name="bookId" value=${book.id}>
						<div class="quantity">
							<label for="number">Số lượng </label>
							<input type="number" name="quantity" min="1" id="quantity" value="1">
						</div>
						<button type="submit" value="Thêm vào giỏ hàng">Thêm vào giỏ hàng</button>
					</form>
					</c:if>
					
						
						
				</div>
			</div>
			
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
		
	</div>
	
	<!--pop up-->
	
	
	<div class="modal fade" id="myModal" aria-hidden="true" tabindex="-1">
    	<div class="modal-dialog" role="document">
    	<div class="modal-content">
    		<div class="modal-header">Giỏ hàng hiện tại</div>
    		<div class="modal-body">
    			<table id="cartTable" class="table table-bordered">
				<thead>
					<tr>
						<td>Book</td>
						<td>Quantity</td>
						<td>Price</td>
						<td>Money</td>
					</tr>
				</thead>
				<tbody id="popupCartBody">
					
				</tbody>
				
				
			</table>
			<a href="<%=contextPath%>/book"><button type="button" class="btn btn-danger">Continue shopping</button></a>
			<a href="<%=contextPath%>/cart"><button type="button" class="btn btn-success">Go to cart</button></a>
    		</div>
    		<div class="modal-footer">
    			<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
    		</div>
    	</div>
    	</div>
    </div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
    <script type="text/javascript" src="<%=contextPath%>/customer/js/book-detail.js"></script>
</body>
</html>