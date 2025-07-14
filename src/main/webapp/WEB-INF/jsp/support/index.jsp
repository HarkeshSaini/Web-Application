<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/resources/sites/css/page.css"/>
  <title>Services</title>
</head>
<body>
 <jsp:include page="../include/header.jsp"></jsp:include>
 <section class="banner">
  <div class="banner-overlay"></div>
  <div class="banner-content">
    <h3>What i' Can Help You ?</h3>
    <div class="row subscribe-row search">
      <div class="cols-9 col-email">
         <input type="search" class="form-control" id="search" placeholder="I'Can help you?.." required>
      </div> 
      <div class="cols-3 col-button">
         <button class="btn btn-primary search" type="submit">Search</button>
      </div>
    </div>
  </div>
</section>
<div class="container py-5">
    <div class="text-data">
      <p class="text-center">data</p>
    </div>
</div>
<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>
