<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    
<head>
</head>
	<meta charset="UTF-8">
<title>Admin</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="<%=getServletContext().getContextPath()%>/customer/css/header.css" rel="stylesheet">
<body>
	<div class="navbar">
    <div class="dropdown">
        <button class="dropbtn">Book</button>
        <div class="dropdown-content">
            <a href="<%=getServletContext().getContextPath()%>/book">Book</a>

        </div>
  	</div>
  	<div class="dropdown">
  		<button class="dropbtn">Account
        </button>
        <div class="dropdown-content">
            <a href="">Cart</a>
            <a href="">Order </a>
            <a href="">Manage Profile</a>
        </div>
  	</div>
  	<div class="dropdown account">
  		<c:choose>
  			<c:when test="${not empty sessionScope.user}">
  				<button class="dropbtn">${sessionScope.user.fullname.lastName}
        		</button>
  			</c:when>
  			
  			<c:otherwise>
  				<button class="dropbtn">Account
        		</button>
  			</c:otherwise>
  		</c:choose>
  		
        <div class="dropdown-content">
            <a href="<%=getServletContext().getContextPath()%>/cart">Cart</a>
            <a href="">Order </a>
			<c:if test="${empty sessionScope.user}">
				 <a href="<%=getServletContext().getContextPath()%>/login">Login</a>
			</c:if>
            
        </div>
  	</div>
</div>
</body>
