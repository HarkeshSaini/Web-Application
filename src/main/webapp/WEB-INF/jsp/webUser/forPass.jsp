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
        <h2 class="fs-6 fw-normal text-center text-secondary mb-4 heading message">${message}</h2>
        <form action="/forgotPassword" method="post">
         <div class="row gy-2 overflow-hidden">
           <div class="col-6">
             <div class="form-floating mb-3">
               <input type="email" class="form-control" name="email" id="email" required>
               <label for="email" class="form-label">Enter your registered email</label>
             </div>
           </div>
           <div class="col-6">
             <div class="form-floating mb-3">
               <input type="text" class="form-control" name="username" id="username" required>
               <label for="username" class="form-label">Enter your username.</label>
             </div>
           </div>
           <div class="col-12">
             <div class="form-floating mb-3">
               <input type="datetime-local" name="dateOfBirth" class="form-control" required>
               <label for="dateOfBirth" class="form-label">Enter your date of birth.</label>
             </div>
           </div>
           <div class="col-12">
             <div class="d-grid my-3">
               <button class="btn btn-primary btn-lg" type="submit">Submit</button>
             </div>
           </div>
           <div class="col-12">
              <p class="m-0 text-secondary text-center">Don't have an account? <a href="/sign-up" class="link-primary text-decoration-none">Sign up</a></p>
           </div>
           
         </div>
       </form>
      </div>
    </div>

    <!-- Right Side: Login Icons -->
    <div class="col-md-6 login-icon-side d-flex flex-column justify-content-center align-items-center p-4 bg-light">
      <img class="login-icon" src="/resources/admin/image/forgot-pass.jpg" alt="Logo">
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