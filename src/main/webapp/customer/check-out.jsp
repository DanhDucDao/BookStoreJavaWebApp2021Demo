<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Check out</title>
    <link href="<%=getServletContext().getContextPath()%>/customer/css/header.css" rel="stylesheet">
	<link href="<%=getServletContext().getContextPath()%>/customer/css/home.css" rel="stylesheet">
	<link href="<%=getServletContext().getContextPath()%>/customer/css/check-out.css" rel="stylesheet">
    
</head>
<body>

	<%String contextPath = getServletContext().getContextPath(); %>
	<div class="container">
	<jsp:include page="header.jsp"></jsp:include>
    <div class="checkout-container">
    	<form action="<%=contextPath%>/checkOut" method="POST">

        <div class="order-info">
            <p>Thong tin</p>
            <div class="control-line">
                <label for="name">Tên*</label>
                <input type="text" name="name" id="name" value="${user.fullname.firstName} ${user.fullname.lastName}" required>
            </div>
            <div class="control-line">
                <label for="email">Emal*</label>
                <input type="text" name="email" id="email" value="${user.email}" required>
            </div>
            <div class="control-line">
                <label for="phone">Số điện thoại*</label>
                <input type="text" name="phone" id="phone" value="${user.phoneNumber}" required>
            </div>
            <div class="control-line">
                <label for="note">Ghi chú*</label>
                <input type="text" name="note" id="note" placeholder="Note" required>
            </div>

            <div class="control-line">
                <label for="street">Địa chỉ</label>
                <input type="text" name="street" id="street" placeholder="street" value="${user.address.street}" required>
            </div>

            <div class="box-2">
                <div class="control-line">
                    <label for="subdistrict">Xã/Phường</label>
                    <input type="text" name="subdistrict" id="subdistrict" placeholder="subdistrict" value="${user.address.subDistrict}"  required>
                </div>
    
                <div class="control-line">
                    <label for="district">Quận/Huyện</label>
                    <input type="text" name="district" id="district" placeholder="district" value="${user.address.district}" required>
                </div>
            </div>
            
            <div class="box-2">
                <div class="control-line">
                    <label for="city">Tỉnh/Thành phố</label>
                    <input type="text" name="city" id="city" placeholder="city" value="${user.address.city}" required>
                </div>
                <div class="control-line">
                    <label for="city">Kiểu ship</label>
                    <select name="shipType">
                        <option value="fast">Ship nhanh</option>
                        <option value="standard">Tiêu chuẩn</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="payment">
            <p>Thanh toan</p>
            <div class="control-line">
                <label for="paymentType">Thanh toán</label>
                <select id="paymentType" name="paymentType" required>
                    <option value="cash" selected>Tiền mặt</option>
                    <option value="check">Check</option>
                    <option value="credit">Credit</option>
                </select>
            </div>

            <div id="cashType" class="payment-container">

            </div>

            <div id="checkType" class="payment-container">
                <div class="control-line">
                    <label for="bankName">Name</label>
                    <input type="text" name="bankName" id="bankName" placeholder="Name" >
                </div>
                <div class="control-line">
                    <label for="bankID">Bank ID</label>
                    <input type="text" name="bankID" id="bankID" placeholder="Bank ID">
                </div>
            </div>

            <div id="creditType" class="payment-container">
                <div class="control-line">
                    <label for="number">Number</label>
                    <input type="text" name="number" id="number" placeholder="Number" >
                </div>
                <div class="control-line">
                    <label for="creditType">Type</label>
                    <input type="text" name="creditType" id="creditType" placeholder="Credit Type">
                </div>
                <div class="control-line">
                    <label for="expDate">Expired Date</label>
                    <input type="date" name="expDate" id="expDate" placeholder="Bank ID">
                </div>
            </div>
        </div>

        <div class="cartTable">
            <table>
                <thead>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cart.lineitems}">
                    	<tr>
                    		<td class="image-container">
                    	 		<img src="<%=contextPath%>/images/upload/${item.book.coverImage}">
                    		</td>
                    		<td>${item.book.title}</td>
                    		<td>${item.quantity}</td>
                    		<td><fmt:formatNumber value="${item.price}" type="currency"></fmt:formatNumber></td>
                    	</tr>
                    </c:forEach>
                </tbody>
            </table>

            <div id="countPrice">
                <div class="count-line">
                	<p class="detail">Tổng</p>
                	<p class="content">
                		<fmt:formatNumber value="${subTotal}" type="currency"></fmt:formatNumber>
                	</p>
                </div>
                <div class="count-line">
                	<p class="detail">Trừ</p>
                	<p class="content">
                	<fmt:formatNumber value="${discount}" type="currency"></fmt:formatNumber>
                	
                	</p>
                </div>
                <div class="count-line">
                	<p class="detail">Thành tiền</p>
                	<p class="content total">
                	<fmt:formatNumber value="${total}" type="currency"></fmt:formatNumber>
                	</p>
                </div>
            </div>
            
            <div class="button-nav">
            	<button class="btn-checkout">Check out</button>
            </div>
        </div>
           
           </form>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
    </div>
    
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
    <script type="text/javascript" src="<%=contextPath%>/customer/js/book-detail.js"></script>
    <script src="<%=contextPath%>/customer/js/check-out.js"></script>
</body>
</html>
