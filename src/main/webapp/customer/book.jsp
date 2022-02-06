<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="<%=getServletContext().getContextPath()%>/customer/css/header.css" rel="stylesheet">
<link href="<%=getServletContext().getContextPath()%>/customer/css/home.css" rel="stylesheet">


</head>
<body>
	<%String contextPath = getServletContext().getContextPath(); %>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<h4>Display ${pageIndex * pageSize + 1} - ${pageIndex * pageSize + pageSize} Products | Total ${totalBook}</h4>
		<div class="grid-container">
			<c:forEach var="item" items="${books}">
				<div class="item">
					<div class="card">
						<div class="image-container">
							<img src="<%=contextPath%>/images/upload/${item.coverImage}" alt="${item.title}"/>
						</div>
						<p class="title">${item.title}</p>
						<div class="price">
							<fmt:setLocale value="vi_VN"/>
							<fmt:formatNumber value="${item.price}" type="currency"/>
						</div>
						<p class="summary">${item.summary}</p>
						<a href="<%=contextPath%>/bookDetail?bookId=${item.id}"><button>View Detail</button></a>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<div class="pagination-container">
			<div class="pagination-custom">
			<c:forEach var="entry" items="${mapPages}">
				<c:choose>
					<c:when test="${entry.key == pageIndex}">
						<a href="#" class="active">${entry.key}</a>
					</c:when>
					<c:when test="${entry.key != pageIndex}">
						<a href="<%=contextPath%>${entry.value}">${entry.key}</a>
					</c:when>
				</c:choose>
			</c:forEach>
		</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>