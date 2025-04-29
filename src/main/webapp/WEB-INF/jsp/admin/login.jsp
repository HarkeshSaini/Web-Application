<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta id="viewport" name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
    <title>Admin Login</title>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/admin/css/styles.css"/>
  </head>
  <body>
    <section class="bg-light py-3 py-md-5">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5 col-xxl-4">
            <div class="card border border-light-subtle rounded-3 shadow-sm">
              <div class="card-body p-3 p-md-4 p-xl-5">
               <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
                  <div class="error message">
                    <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                  </div>
                </c:if>
                <c:if test="${empty SPRING_SECURITY_LAST_EXCEPTION.message}">
                <h2 class="fs-6 fw-normal text-center text-secondary mb-4 heading">${message}</h2>
                </c:if>
                <form th:action="@{/login}" method="post">
                  <div class="row gy-2 overflow-hidden">
                    <div class="col-12">
                      <div class="form-floating mb-3">
                        <input type="email" class="form-control" name="username" id="email" placeholder="name@example.com" required>
                        <label for="email" class="form-label">Email</label>
                      </div>
                    </div>
                    <div class="col-12">
                      <div class="form-floating mb-3">
                        <input type="hidden" name="loginType" value="ADMIN"/>
                        <input type="password" class="form-control" name="password" id="password" value="" placeholder="Password" required>
                        <label for="password" class="form-label">Password</label>
                      </div>
                    </div>
                    <div class="col-12">
                      <div class="d-grid my-3">
                        <button class="btn btn-primary btn-lg" type="submit">Log in</button>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </body>
</html>