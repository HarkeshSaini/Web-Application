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
            <img src="/resources/sites/image/image1.jpg" class="card-img-top" alt="Service 1">
            <div class="card-body">
              <h5 class="card-title">Web Design</h5>
              <p class="card-text">Responsive and modern web designs that capture your audience's attention.</p>
            </div>
          </div>
        </div>
        <!-- Card 2 -->
        <div class="col">
          <div class="card">
            <img src="/resources/sites/image/image2.jpg" class="card-img-top" alt="Service 2">
            <div class="card-body">
              <h5 class="card-title">Development</h5>
              <p class="card-text">Custom web and mobile applications built with cutting-edge technologies.</p>
            </div>
          </div>
        </div>
        <!-- Card 3 -->
        <div class="col">
          <div class="card">
            <img src="/resources/sites/image/image3.jpg" class="card-img-top" alt="Service 3">
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
      </section>
    </div>
    <section class="why-us" id="why-us">
      <div class="container">
        <div class="section-header">
          <h2>Why Choose Us?</h2>
          <p>We are committed to providing the best service for our customers. Here’s why you should choose us for your needs.</p>
        </div>
        <div class="row">
          <div class="column">
            <img src="/resources/sites/image/why-us.avif" alt="Quality Service" class="section-image">
          </div>
          <div class="column">
            <div class="benefits-list">
              <p>United Airlines has an operating route to multiple international and domestic destinations. So, the flight of the airline to those destinations from an assigned airport and Buffalo Niagara International Airport is among them. This airport is located in Cheektowaga, New York, and is 
                laced with distinct services to enhance an overall Airlines has an an operating route to multiple international and an operating route multiple international and an operating route to multiple international and domestic destinat domestic destinat operating route to multiple travel experience  Airlines has an operating route to multiple international and domestic destinations. So, the flight of the airline to those destinations from an assigned airport and Buffalo Niagara International Airport is among them. This airport is located in Cheektowaga, New York, and is 
                laced with distinct services to enhance an overall travel experience. Whenever you get an issue related to the airline at the airport, then approach its respective customer service team for a resolution. Moreover, the explanation of its contact methods has been displayed in the subheads.
              </p>
            </div>
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