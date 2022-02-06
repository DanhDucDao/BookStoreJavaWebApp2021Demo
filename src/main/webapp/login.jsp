<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }
        .login-container {
            text-align: center;
        }

        .login-form-container {
            padding: 26px 20px;
            text-align: left;
            border: 1px solid rgba(0, 0, 0, 0.2);
            width: 300px;
            border-radius: 5px;
            margin: 0 auto;
        }

        .login-form-container .title {
            font-weight: normal;
            font-size: 1.5em;
            margin: 0;
            margin-bottom: 15px;
        }
        .login-form-container .control {
            margin-bottom: 15px;
        }

        .login-form-container label {
            display: block;
            font-weight: bolder;
            font-size: 0.8em;
            margin-bottom: 2px;
        }

        .login-form-container input[type="text"], input[type="password"] {
            width: 100%;
            border: 1px solid rgba(0, 0, 0, 0.2);
            border-radius: 2px;
            height: 30px;
            font-size: 12px;
        }
        
        .login-form-container input:focus {
            outline: none;
            border: 1px solid rgba(0, 0, 0, 0.2);
            box-shadow: 0px 0px  10px orange;
        }

        .login-form-container input[type="submit"] {
            height: 30px;
            outline: none;
            border: 1px solid rgba(0, 0, 0, 0.2);
            width: 100%;
            background-image: linear-gradient(#f3ce71, #f0ca6c);
            border-radius: 2px;
        }

        .login-form-container .helper {
            font-size: 13px;
            margin-top: 20px;
        }

        .register-nav button {
            width: 340px;
            height: 30px;
        }

        .register-nav p {
            font-size: 13px;
            color: rgba(0, 0, 0, 0.7);
        }


    </style>
</head>
<body>
    <div class="login-container">
        <p>Book Store</p>
        <form class="login-form-container" action="<%=getServletContext().getContextPath() %>/login" method="POST">
        	<c:if test="${not empty param.returnUrl}">
        		<input type="hidden" name="returnUrl" value="${param.returnUrl}">
        	</c:if>
        	
        	<c:if test="${not empty requestScope.returnUrl}">
        		<input type="hidden" name="returnUrl" value="${requestScope.returnUrl}">
        	</c:if>
        	
            <p class="title">Đăng nhập</p>
            <div class="control">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" required>
            </div>
            <div class="control">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" required >
            </div>
            <input type="submit" value="Login">
            <p class="helper">Đăng nhập để bắt đầu mua sắm.</p>
        </form>
        <div class="register-nav">
            <p>New to Book store ?</p>
            <button>Đăng ký tài khoản Book Store</button>
        </div>
        
    </div>
    
</body>