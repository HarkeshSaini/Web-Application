<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>User Sign-Up</title>

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

          <!-- Left Side: Sign-Up Form -->
          <div class="col-12 col-md-6 left-so">
            <div class="card-body">
              <div class="rounded-3 mb-4">
                <h2 class="fs-6 fw-normal text-center text-secondarys mb-3">${message}</h2>
                <c:if test="${not empty Password}">
                  <div class="alert alert-info text-center">
                    Your Sign in Password:
                    <p class="fw-bold">${Password}</p>
                  </div>
                </c:if>
              </div>

              <form action="/sign-up" method="post" enctype="multipart/form-data">
                <div class="row">
                  <div class="col-12 col-md-6 mb-3">
                    <input type="text" name="fullName" class="form-control" placeholder="Full name *" required>
                  </div>
                  <div class="col-12 col-md-6 mb-3">
                    <input type="text" name="username" class="form-control" placeholder="Username *" required>
                  </div>

                  <div class="col-12 col-md-6 mb-3">
                    <input type="email" name="email" class="form-control" placeholder="Email address *" required>
                  </div>
                  <div class="col-12 col-md-6 mb-3">
                    <input type="tel" name="phone" class="form-control" placeholder="Mobile no *" required>
                  </div>

                  <div class="col-12 col-md-6 mb-3">
                    <input type="datetime-local" name="dateOfBirth" class="form-control" placeholder="Date of Birth *" required>
                  </div>
                  <div class="col-12 col-md-6 mb-3">
                    <select name="gender" class="form-select" required>
                      <option disabled selected>Select gender *</option>
                      <option value="male">Male</option>
                      <option value="female">Female</option>
                    </select>
                  </div>

                  <div class="col-12 col-md-6 mb-3">
                    <input type="file" name="file" class="form-control">
                  </div>
                  <div class="col-12 col-md-6 mb-3">
                    <select name="age" class="form-select" required>
                      <option disabled selected>Select Age *</option>
                      <c:forEach var="data" begin="1" end="60">
                        <option value="${data}">${data}</option>
                      </c:forEach>
                    </select>
                  </div>

                  <div class="col-12">
                    <textarea name="address" class="form-control" rows="3" placeholder="Full address *" required></textarea>
                  </div>
                </div>
                <div class="d-grid my-4">
                  <button class="btn btn-primary btn-lg" type="submit">Submit</button>
                </div>
              </form>
            </div>
          </div>

          <!-- Right Side: Social Login -->
          <div class="col-12 col-md-6 d-flex flex-column justify-content-center align-items-center p-4 bg-light text-center">
            <img class="img-fluid mb-3" src="/resources/admin/image/sign-up.png" alt="Sign-up Icon" style="max-width: 120px;">
            <h5 class="mb-3">Or sign up with</h5>
            <div class="login-buttons d-flex gap-3 mb-4">
              <a href="#" class="btn btn-outline-secondary rounded-circle"><i class="fab fa-google"></i></a>
              <a href="#" class="btn btn-outline-secondary rounded-circle"><i class="fab fa-github"></i></a>
              <a href="#" class="btn btn-outline-secondary rounded-circle"><i class="fab fa-facebook"></i></a>
              <a href="#" class="btn btn-outline-secondary rounded-circle"><i class="fab fa-linkedin"></i></a>
            </div>
            <p class="text-secondarys px-3">
	              Already have an account?
	              <a href="/login" class="link-primary text-decoration-none">Sign in</a>
	            </p>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap JS (Optional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
