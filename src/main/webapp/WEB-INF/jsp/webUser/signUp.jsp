<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta id="viewport" name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
    <title>User Login</title>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/admin/css/auth.page.css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
  </head>
  <body>
  <div class="header_auth">
  	<jsp:include page="../include/header.jsp"></jsp:include>
  </div>
<div class="container">
<div class="card border border-light-subtle rounded-3 shadow-sm login-card">
  <div class="row g-0">
    <!-- Left Side: Login Form -->
    <div class="col-md-6 login-form-side p-4">
      <div class="card-body">
       <div class="rounded-3 messages">
       <h2 class="fs-6 fw-normal text-center text-secondary mb-4 heading message">${message}</h2>
       <c:if test="${not empty Password}">
        <div class="message text-center">!Your Sign in Password:- <p class="Password">${Password}</p></div>
       </c:if>
       </div>
       <!-- start -->
          <form action="/sign-up" method="post" enctype="multipart/form-data">
           <div class="row">
		  <div class="col">
             <input type="text" name="fullName" class="form-control" placeholder="Enter full name *" required="required">
         </div>
         <div class="col">
             <input type="text" name="username" class="form-control" placeholder="Enter username *" required="required">
         </div>
         </div>
          <div class="row">
         <div class="col">
            <input type="email" name="email" class="form-control" placeholder="Enter email addrass *" required="required">
         </div>
         <div class="col">
            <input type="number" name="phone" class="form-control" placeholder="Enter mobile no *" required="required">
         </div>
       </div>
       
        <div class="row">
         <div class="col">
            <input type="datetime-local" name="dateOfBirth" class="form-control" placeholder="dd-mm-yyyy *" required="required">
         </div>
         <div class="col">
         <select name="gender" class="form-control" required="required">
            <option>Select gender*</option>
            	 <option value="male">Male</option>
            	 <option value="female">Female</option>
            </select>
         </div>
       </div> 
           
           
        <div class="row">
		<div class="col">
            <input type="file" name="file" class="form-control">
         </div>
           
         <div class="col">
            <select name="age" class="form-control" required="required">
            <option>Select Age*</option>
             <c:forEach var="data" begin="1" end="60">
        		<option value="${data}">${data}</option>
         </c:forEach>
        </select>
      </div>
       </div>
       <div class="row">
           <div class="form-outline col-md-12 mb-3">
               <textarea name="address" class="form-control address" placeholder="Enter full address *" required="required"></textarea>
           </div>
        </div>
        <div class="col-12">
           <div class="d-grid my-3">
             <button class="btn btn-primary btn-lg" type="submit">Submit</button>
           </div>
         </div>
        </form>
      </div>
    </div>

    <!-- Right Side: Login Icons -->
    <div class="col-md-6 login-icon-side d-flex flex-column justify-content-center align-items-center p-4 bg-light">
      <img class="login-icon" src="/resources/admin/image/sign-up.png" alt="Logo">
      <h5 class="text-center mb-4">Or log in with</h5>
      <div class="login-buttons d-flex gap-3">
        <a href="#" class="login-btn google-btn"><i class="fab fa-google"></i></a>
        <a href="#" class="login-btn github-btn"><i class="fab fa-github"></i></a>
        <a href="#" class="login-btn facebook-btn"><i class="fab fa-facebook"></i></a>
        <a href="#" class="login-btn linkedin-btn"><i class="fab fa-linkedin"></i></a>
      </div>
      <p class="text-center mb-4">Welcome! Please log in to access your dashboard and view your profile. Do you already have an account? <a href="/login" class="link-primary text-decoration-none">Sign in</a></p> 
    </div>
  </div>
</div>
</div> 