<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Index</title>
</head>
<body>
 <jsp:include page="../include/header.jsp"></jsp:include>
 <jsp:include page="../comman.jsp"></jsp:include>
<section class="banner">
  <div class="banner-overlay"></div>
  <div class="banner-content">
    <h1>Our Blog</h1>
  </div>
</section>

<div class="container py-5">

    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
     <c:forEach var="data" items="${blogDetail}">
      <div class="col">
        <div class="card">
          <img src="${data.imgUrl}" class="card-img-top" alt="Service 1">
          <div class="card-body">
            <h5 class="card-title">${data.heading}</h5>
            <p class="card-text">${data.heading}</p>
          </div>
        </div>
      </div>
	</c:forEach>
       
    </div>
  </div>
<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>
