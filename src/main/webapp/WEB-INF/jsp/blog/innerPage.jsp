<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<c:forEach var="data" items="${blogDetail}"> 
<head>
  <title>${data.title}</title>
  <meta name="description" content="${data.description}">
  <meta name="keywords" content="${data.keywords}" />
  <jsp:include page="../include/comman.jsp"></jsp:include>
  <link rel="stylesheet" href="/resources/sites/css/page.css"/>
</head>
<body>
 <jsp:include page="../include/header.jsp"></jsp:include>
 <section class="banner">
  <div class="banner-overlay"></div>
  <div class="banner-content">
    <h2>${data.heading}</h2>
  </div>
</section>
<div class="container py-5">
  <div class="custom-row">
    <div class="custom-col custom-col-9">
      <p class="text-center">${data.content}</p>
      
    </div>
    <div class="custom-col custom-col-3">
      <div class="recent-blog-data">
        <section class="recent-blog">
		    <div class="blog-grid">
		    <p class="recent">Recent Blog Update</p>
		    <c:forEach var="dataR" items="${recentBlog}"> 
		      <article class="blog-card">
		        <div class="blog-content">
		          <a class="blog-heading" href="/blog/${dataR.titleUrl}">${dataR.heading}</a>
		          <p>${fn:escapeXml(fn:substring(dataR.content.replaceAll('<.*?>',''),0,50))}</p>
		        </div>
		      </article>
		      </c:forEach>
		    </div>
		</section>
      </div>
    </div>
    
  </div>
  <jsp:include page="../include/review.jsp" />
</div>


<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</c:forEach>
</html>
