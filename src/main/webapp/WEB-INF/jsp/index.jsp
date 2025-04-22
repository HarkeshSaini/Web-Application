<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Index</title>
</head>
<body>
 <jsp:include page="./include/header.jsp"></jsp:include>
 <jsp:include page="comman.jsp"></jsp:include>
<section class="banner">
  <div class="banner-overlay"></div>
  <div class="banner-content">
    <h1>Welcome to Our Website</h1>
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

<div class="container py-5">
    <h2 class="text-center mb-4">Customer Reviews</h2>

    <div class="row">
      <!-- Review 1 -->
      <div class="col-12 col-md-6 col-lg-4 mb-4">
        <div class="review-card">
          <div class="text-center">
            <img src="https://via.placeholder.com/80" alt="Customer 1">
            <div class="review-name">John Doe</div>
            <div class="review-rating">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-half"></i>
            </div>
          </div>
          <p class="review-text">“This product exceeded my expectations! Highly recommended for anyone looking for quality.”</p>
        </div>
      </div>

      <!-- Review 2 -->
      <div class="col-12 col-md-6 col-lg-4 mb-4">
        <div class="review-card">
          <div class="text-center">
            <img src="https://via.placeholder.com/80" alt="Customer 2">
            <div class="review-name">Jane Smith</div>
            <div class="review-rating">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star"></i>
            </div>
          </div>
          <p class="review-text">“The service was great, but the product could be improved. Still, I'm satisfied overall.”</p>
        </div>
      </div>

      <!-- Review 3 -->
      <div class="col-12 col-md-6 col-lg-4 mb-4">
        <div class="review-card">
          <div class="text-center">
            <img src="https://via.placeholder.com/80" alt="Customer 3">
            <div class="review-name">Michael Brown</div>
            <div class="review-rating">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
            </div>
          </div>
          <p class="review-text">“Excellent product! Very happy with my purchase, will definitely buy again.”</p>
        </div>
      </div>

      <!-- Review 4 -->
      <div class="col-12 col-md-6 col-lg-4 mb-4">
        <div class="review-card">
          <div class="text-center">
            <img src="https://via.placeholder.com/80" alt="Customer 4">
            <div class="review-name">Emily Davis</div>
            <div class="review-rating">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star"></i>
              <i class="bi bi-star"></i>
            </div>
          </div>
          <p class="review-text">“Good product, but there are some improvements needed in terms of durability.”</p>
        </div>
      </div>

      <!-- Review 5 -->
      <div class="col-12 col-md-6 col-lg-4 mb-4">
        <div class="review-card">
          <div class="text-center">
            <img src="https://via.placeholder.com/80" alt="Customer 5">
            <div class="review-name">Alex Johnson</div>
            <div class="review-rating">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
            </div>
          </div>
          <p class="review-text">“Perfect! Exactly what I needed, great value for money!”</p>
        </div>
      </div>

      <!-- Review 6 -->
      <div class="col-12 col-md-6 col-lg-4 mb-4">
        <div class="review-card">
          <div class="text-center">
            <img src="https://via.placeholder.com/80" alt="Customer 6">
            <div class="review-name">Sara Lee</div>
            <div class="review-rating">
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-fill"></i>
              <i class="bi bi-star-half"></i>
              <i class="bi bi-star"></i>
            </div>
          </div>
          <p class="review-text">“Good quality, but the shipping took longer than expected.”</p>
        </div>
      </div>
    </div>
  </div>
<jsp:include page="./include/footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>
