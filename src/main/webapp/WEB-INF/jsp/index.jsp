<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Index</title>
    <meta name="description" content="Index">
    <meta name="keywords" content="Index" />
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/resources/sites/css/main.css"/>
    <jsp:include page="./include/comman.jsp"></jsp:include>
  </head>
  <body>
    <jsp:include page="./include/header.jsp"></jsp:include>
    <section class="banner">
      <div class="banner-overlay"></div>
      <div class="banner-content">
         
         
      </div>
    </section>
    <div class="container py-5">
      <h2 class="text-center mb-4">Our Services</h2>
      <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        <!-- Card 1 -->
        <div class="col">
          <div class="card main-card">
            <div class="card-body">
              <h5 class="card-title">Web Design</h5>
              <p class="card-text">Responsive and modern web designs that capture your audience's attention.</p>
            </div>
          </div>
        </div>
        <!-- Card 2 -->
        <div class="col">
          <div class="card main-card">
            <div class="card-body">
              <h5 class="card-title">Development</h5>
              <p class="card-text">Custom web and mobile applications built with cutting-edge technologies.</p>
            </div>
          </div>
        </div>
        <!-- Card 3 -->
        <div class="col">
          <div class="card main-card">
            <div class="card-body">
              <h5 class="card-title">SEO Optimization</h5>
              <p class="card-text">Increase your site's visibility with our effective SEO strategies.</p>
            </div>
          </div>
        </div>
      </div>
      <section class="reviews-section">
        <h2 class="reviews-title">What Our Customers Say</h2>
        <div class="reviews-container review-show"></div>
        <div class="arrow-icon">
	        <button id="left" onclick="getclickname(this); return false;" type="button" class="left-arrow"><img src="/resources/sites/image/left-icon.png" class="left-icon" alt="left-icon"></button>
	        <button id="right" onclick="getclickname(this); return false;" type="button" class="right-arrow"><img src="/resources/sites/image/right-icon.png" class="right-icon" alt="right-icon"></button>
        </div>
      </section>
    </div>
    <section class="why-us" id="why-us">
      <div class="container py-5">
      <h2 class="why_choose"> Why Choose Us?</h2>
        <div class="row">
          <div class="column section-images">
            <img src="/resources/sites/image/about.webp" alt="Quality Service" class="section-image">
          </div>
          <div class="column">
            <div class="benefits-list">
              <p class="aboutUsContent">${abotutList[0].content}</p>
            </div>
            <a class="read_more" href="/about-us">Learn mare content about us..</a>
          </div>
        </div>
      </div>
    </section>
    <div class="container">
      <section class="reviews-section ">
        <h2 class="reviews-title">Check out our latest blog post for fresh insights and updates!</h2>
        <div class="reviews-container" id="blog-show"></div>
      </section>
    </div>
   
    <jsp:include page="./include/footer.jsp"></jsp:include>
    <script src="/resources/sites/js/index.js"></script>
  </body>
</html>