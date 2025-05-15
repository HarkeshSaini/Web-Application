<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/resources/sites/css/page.css"/>
  <title>Blog</title>
  <meta name="description" content="Blog">
  <meta name="keywords" content="Blog" />
</head>
<body>
 <jsp:include page="../include/header.jsp"></jsp:include>
 <section class="banner">
  <div class="banner-overlay"></div>
  <div class="banner-content">
    <h2>Updates & Blogs</h2>
  </div>
</section>

 
<div class="container py-5 blog">
    <!-- <h2 class="text-center">Our Services</h2> -->
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
      <c:forEach var="data" items="${blogDetail}"> 
      <div class="col">
        <div class="card">
          <%-- <img src="${data.imgUrl}" class="card-img-top" alt="Service 1"> --%>
          <div class="card-body">
            <h5 class="card-title"><a class="heading" href="/blog/${data.titleUrl}">${data.heading}</a></h5>
            <p class="card-content">${fn:escapeXml(fn:substring(data.content.replaceAll('<.*?>',''),0,130))}</p>
          </div>
        </div>
      </div>
	</c:forEach>
    </div>
  </div>
     
 
<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>
