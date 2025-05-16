<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<c:forEach var="data" items="${defaultDetail}"> 
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/resources/sites/css/page.css"/>
  <title>${data.category}</title>
</head>
<body>
 <jsp:include page="include/header.jsp"></jsp:include>
 <section class="banner">
  <div class="banner-overlay"></div>
  <div class="banner-content">
    <h3>${data.heading}</h3>
  </div>
</section>
<div class="container py-5">
    <div class="text-data">
      <p class="text-center">${data.content}</p>
    </div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</c:forEach>
</html>
