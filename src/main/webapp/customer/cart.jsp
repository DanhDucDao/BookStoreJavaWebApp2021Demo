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
<link href="<%=getServletContext().getContextPath()%>/customer/css/cart.css" rel="stylesheet">


</head>
<body>
	<%String contextPath = getServletContext().getContextPath();%>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<p>
		<fmt:setLocale value="vi_VN"/>
		<fmt:formatDate value="${cart.createDate}" type="both"/>
		</p>
		<table class="table">
			<thead>
				<tr>
					<td>Book</td>
					<td>Picture</td>
					<td>Quantity</td>
					<td>Price</td>
					<td>Option</td>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${cart.lineitems.size() gt 0}">
							<c:forEach items="${cart.lineitems}" var="item">
								
									<tr>
										<form action="<%=contextPath%>/updateCart" method="POST">
											<input type="hidden" value="${item.book.id}" name="id"/>
											<td class="title">${item.book.title}</td>
											<td class="image-container">
												<img alt="Picture" src="<%=contextPath%>/images/upload/${item.book.coverImage}">
											</td>
											<td class="quantity">
												<input name="quantity" type="number" value="${item.quantity}">
											</td>
											<td class="price">
												${item.price}
											</td>
											<td class="options">
												<button name="op" class="btn btn-warn" value="update">Update</button>
												<button name="op"class="btn btn-danger" value="delete">Delete</button>
											</td>
										</form>
									</tr>
							</c:forEach>
					</c:when>
					
					<c:otherwise>
						<tr>
							<td rowspan="4">No Items</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		<div class="cart-footer">
			<div class="total-price"> 
				<div class="lbcon">
					<p class="label">Total</p>
				</div>
				<p class="money">
					<fmt:setLocale value="vi_VN"/>
					<fmt:formatNumber type="currency" value="${requestScope.totalPrice}"></fmt:formatNumber>
				</p>
			</div>
			<div class="button-nav">
				<a href="<%=contextPath%>/book" ><button class="button-shopping">Continue Shopping</button></a>
				<a href="<%=contextPath%>/checkOut" ><button class="button-checkout">Check out order</button></a>
			</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
    <script type="text/javascript" src="<%=contextPath%>/customer/js/book-detail.js"></script>
</body>
</html>