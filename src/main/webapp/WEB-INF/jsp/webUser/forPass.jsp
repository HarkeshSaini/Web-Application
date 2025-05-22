<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Forgot Password</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/admin/css/auth.page.css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
  </head>

  <body>
    <div class="header_auth">
      <jsp:include page="../include/header.jsp"></jsp:include>
    </div>

    <div class="container">
      <div class="card border border-light-subtle rounded-3 shadow-sm login-card mx-auto" style="max-width: 900px;">
        <div class="row g-0">

          <!-- Left Side: Forgot Password Form -->
          <div class="col-12 col-md-6">
            <div class="card-body">
              <h2 class="fs-6 fw-normal text-center text-secondary mb-4 forgot">${message}</h2>
              <form action="/forgotPassword" method="post">
                <div class="row g-3">
                  <div class="col-12 col-md-6">
                    <div class="form-floating forgot">
                      <label for="email">Email Id</label>
                      <input type="email" class="form-control" name="email" id="email" required>
                    </div>
                  </div>
                  <div class="col-12 col-md-6 forgot">
                    <div class="form-floating forgot">
                      <label for="username">Username</label>
                      <input type="text" class="form-control" name="username" id="username" required>
                    </div>
                  </div>
                  <div class="col-12">
                    <div class="form-floating forgot">
                      <input type="datetime-local" name="dateOfBirth" id="dateOfBirth" class="form-control" required>
                      <label for="dateOfBirth">Date of Birth</label>
                    </div>
                  </div>
                  <div class="col-12">
                    <div class="d-grid my-3">
                      <button class="btn btn-primary btn-lg" type="submit">Submit</button>
                    </div>
                  </div>
                  <div class="col-12">
                    <p class="m-0 text-secondary text-center">Don't have an account?
                      <a href="/sign-up" class="link-primary text-decoration-none">Sign up</a>
                    </p>
                  </div>
                </div>
              </form>
            </div>
          </div>

          <!-- Right Side: Social Login -->
          <div class="col-12 col-md-6 d-flex flex-column justify-content-center align-items-center p-4 bg-light text-center">
            <img class="img-fluid mb-3" src="/resources/admin/image/forgot-pass.jpg" alt="Forgot Password" style="max-width: 120px;">
            <h5 class="mb-3">Or log in with</h5>
            <div class="login-buttons d-flex gap-3 mb-4">
              <a href="#" class="btn btn-outline-secondary rounded-circle"><i class="fab fa-google"></i></a>
              <a href="#" class="btn btn-outline-secondary rounded-circle"><i class="fab fa-github"></i></a>
              <a href="#" class="btn btn-outline-secondary rounded-circle"><i class="fab fa-facebook"></i></a>
              <a href="#" class="btn btn-outline-secondary rounded-circle"><i class="fab fa-linkedin"></i></a>
            </div>
            <p class="text-secondary px-3">
              Already have an account?
              <a href="/login" class="link-primary text-decoration-none">Sign in</a>
            </p>
          </div>

        </div>
      </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
