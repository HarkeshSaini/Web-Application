<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User Login</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="/resources/admin/css/auth.page.css"/>

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
  </head>

  <body>
    <!-- Header Include -->
    <div class="header_auth">
      <jsp:include page="../include/header.jsp"></jsp:include>
    </div>

    <!-- Login Container -->
    <div class="container">
      <div class="card border border-light-subtle rounded-3 shadow-sm login-card mx-auto" style="max-width: 900px;">
        <div class="row g-0">

          <!-- Left Side: Login Form -->
          <div class="col-12 col-md-6 login-form-side">
            <div class="card-body">
              <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
                <div class="alert alert-danger" role="alert">
                  <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                </div>
              </c:if>

              <c:if test="${empty SPRING_SECURITY_LAST_EXCEPTION.message}">
                <h2 class="fs-6 fw-normal text-center text-secondarys mb-4">${message}</h2>
              </c:if>

              <form action="/loginUser" method="post">
                <div class="form-floating mb-3">
                  <input type="text" class="form-control" name="username" id="email" placeholder="name@example.com" required>
                  <label for="email">Username</label>
                </div>
                <div class="form-floating mb-3">
                  <input type="password" class="form-control" name="password" id="password" placeholder="Password" required>
                  <label for="password">Password</label>
                </div>
                <div class="d-flex justify-content-between align-items-center mb-3">
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="rememberMe" id="rememberMe">
                    <label class="form-check-label text-secondary" for="rememberMe">Keep me logged in</label>
                  </div>
                  <a href="/forgotPassword" class="link-primary text-decoration-none">Forgot password?</a>
                </div>
                <div class="d-grid mb-3">
                  <button class="btn btn-primary btn-lg" type="submit">Log in</button>
                </div>
                <p class="text-secondarys text-center">Don't have an account? <a href="/sign-up" class="link-primary">Sign up</a></p>
              </form>
            </div>
          </div>

          <!-- Right Side: Social Login -->
          <div class="col-12 col-md-6 login-icon-side d-flex flex-column justify-content-center align-items-center p-4 bg-light">
            <img class="login-icon img-fluid mb-3" src="/resources/admin/image/login-icon.jpg" alt="Login Icon" style="max-width: 120px;">
            <h5 class="text-center mb-4">Or log in with</h5>
            <div class="login-buttons d-flex gap-3 mb-4">
              <a href="#" class="btn btn-outline-secondary rounded-circle"><i class="fab fa-google"></i></a>
              <a href="#" class="btn btn-outline-secondary rounded-circle"><i class="fab fa-github"></i></a>
              <a href="#" class="btn btn-outline-secondary rounded-circle"><i class="fab fa-facebook"></i></a>
              <a href="#" class="btn btn-outline-secondary rounded-circle"><i class="fab fa-linkedin"></i></a>
            </div>
            <p class="text-center">
              Welcome! Please log in to access your dashboard and view your profile.<br>
              Don’t have an account? <a href="/sign-up" class="link-primary text-decoration-none">Sign Up</a>
            </p>
          </div>

        </div>
      </div>
    </div>

    <!-- Bootstrap JS (Optional for dropdowns, modals, etc.) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
