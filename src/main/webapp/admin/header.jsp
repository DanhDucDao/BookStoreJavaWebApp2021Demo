<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="navbar">
    <div class="dropdown">
        <button class="dropbtn">Manage Book
        </button>
        <div class="dropdown-content">
            <a href="<%=getServletContext().getContextPath()%>/admin/book">Book</a>
            <a href="">Book Item</a>
            <a href="<%=getServletContext().getContextPath()%>/admin/author">Author</a>
            <a href="<%=getServletContext().getContextPath()%>/admin/publisher">Publisher</a>
        </div>
  	</div>
  	<div class="dropdown">
  		<button class="dropbtn">Process Order
        </button>
        <div class="dropdown-content">
            <a href="">Wating Order</a>
            <a href="">Order </a>
            <a href="">All</a>
        </div>
  	</div>
  	<div class="dropdown account">
  		<button class="dropbtn">Account
        </button>
        <div class="dropdown-content">
            <a href="">Wating Order</a>
            <a href="">Order </a>
            <a href="">All</a>
        </div>
  	</div>
</div>