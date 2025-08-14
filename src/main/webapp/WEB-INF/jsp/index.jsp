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
    
     <section class="section">
    <h2>Why Choose Us?</h2>
    <div class="why-choose">
      <div class="features">
        <div class="feature-box">
          <div class="feature-icon">⏱️</div>
          <div class="feature-text">
            <h4>Less Time in the Chair</h4>
            <p>Justo non dolor lectus ac egestas dictum. Leo tempus nec amet fringilla.</p>
          </div>
        </div>
        <div class="feature-box">
          <div class="feature-icon">🦷</div>
          <div class="feature-text">
            <h4>More Efficient</h4>
            <p>Leo tempus nec amet fringilla. Eu semper velit tristique semper.</p>
          </div>
        </div>
        <div class="feature-box">
          <div class="feature-icon">🩸</div>
          <div class="feature-text">
            <h4>Longer Lasting</h4>
            <p>Laoreet mi lacus nisi diam in. Tempus nec amet fringilla.</p>
          </div>
        </div>
        <div class="feature-box">
          <div class="feature-icon">😊</div>
          <div class="feature-text">
            <h4>More Comfortable Experience</h4>
            <p>Justo non dolor lectus ac egestas dictum. Eu semper velit.</p>
          </div>
        </div>
       <div class="feature-box">
          <div class="feature-icon">🩸</div>
          <div class="feature-text">
            <h4>Longer Lasting</h4>
            <p>Laoreet mi lacus nisi diam in. Tempus nec amet fringilla.</p>
          </div>
        </div>
      </div>
      <div class="image-container">
        <img src="/resources/sites/image/about.webp" alt="Quality Service" class="why-choose-image">
      </div>
    </div>
  </section>
  
  <section class="services-section">
    <h2>Service We Provide</h2>
    <p>Your ultimate destination for expert care, training, and wellness to keep your furry friends happy and healthy!</p>

    <div class="service-cards">
      <div class="card">
        <img src="https://img.icons8.com/color/96/000000/dog.png" alt="Grooming Icon" />
        <h3>Grooming</h3>
        <p>Full grooming for a happy, clean coat: bath, brush, nail trim, and more.</p>
      </div>

      <div class="card highlight">
        <img src="https://img.icons8.com/color/96/heart-with-pulse.png" alt="Health Check Icon" />
        <h3>Health Check</h3>
        <p>Regular vet check-ups to keep your cat & dog in top shape so they can lead healthy life.</p>
      </div>

      <div class="card">
        <img src="https://img.icons8.com/color/96/cat-footprint.png" alt="Pet Sitting Icon" />
        <h3>Pet Sitting</h3>
        <p>Safe, cozy care while you're away, with plenty of love and play on a routine basis.</p>
      </div>

      <div class="card">
        <img src="https://img.icons8.com/color/96/meal.png" alt="Nutrition Icon" />
        <h3>Nutrition</h3>
        <p>Personalized diet plans for a healthy, happy cat or dog to live a very healthy life.</p>
      </div>
    </div>
  </section>
    
    <div class="container py-5">
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