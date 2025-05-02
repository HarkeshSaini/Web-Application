<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/resources/sites/css/main.css"/>
  <title>Index</title>
</head>
<body>
 <jsp:include page="./include/header.jsp"></jsp:include>
<section class="banner">
  <div class="banner-overlay"></div>
  <div class="banner-content">
    <!-- <h1>Welcome to Our Website</h1> -->
    <p>Discover great content, stay connected, and grow with us.</p>
    <a href="#about" class="btn">Learn More</a>
  </div>
</section>

<div class="container py-5">
    <h2 class="text-center mb-4">Our Services</h2>

    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
      <!-- Card 1 -->
      <div class="col">
        <div class="card">
          <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Service 1">
          <div class="card-body">
            <h5 class="card-title">Web Design</h5>
            <p class="card-text">Responsive and modern web designs that capture your audience's attention.</p>
          </div>
        </div>
      </div>

      <!-- Card 2 -->
      <div class="col">
        <div class="card">
          <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Service 2">
          <div class="card-body">
            <h5 class="card-title">Development</h5>
            <p class="card-text">Custom web and mobile applications built with cutting-edge technologies.</p>
          </div>
        </div>
      </div>

      <!-- Card 3 -->
      <div class="col">
        <div class="card">
          <img src="https://via.placeholder.com/300x200" class="card-img-top" alt="Service 3">
          <div class="card-body">
            <h5 class="card-title">SEO Optimization</h5>
            <p class="card-text">Increase your site's visibility with our effective SEO strategies.</p>
          </div>
        </div>
      </div>
    </div>
  </div>
<div class="container">
<section class="reviews-section">
    <h2 class="reviews-title">What Our Customers Say</h2>
    <div class="reviews-container">
      <div class="review-card">
        <div class="review-header">
          <div class="review-avatar">
            <img src="https://i.pravatar.cc/100?img=1" alt="User Avatar">
          </div>
          <div class="review-author">Alice Smith</div>
        </div>
        <div class="review-content">
          “Amazing experience! Highly recommend their service. Everything was smooth and professional.”
        </div>
      </div>

      <div class="review-card">
        <div class="review-header">
          <div class="review-avatar">
            <img src="https://i.pravatar.cc/100?img=2" alt="User Avatar">
          </div>
          <div class="review-author">John Doe</div>
        </div>
        <div class="review-content">
          “The quality exceeded my expectations. The team was supportive and responsive throughout.”
        </div>
      </div>

      <div class="review-card">
        <div class="review-header">
          <div class="review-avatar">
            <img src="https://i.pravatar.cc/100?img=3" alt="User Avatar">
          </div>
          <div class="review-author">Samantha Lee</div>
        </div>
        <div class="review-content">
          “I will definitely use this again. Great value and even better customer service!”
        </div>
      </div>
    </div>
  </section>
 </div>
<jsp:include page="./include/footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/resources/sites/js/script.js"></script>
</body>
</html>
