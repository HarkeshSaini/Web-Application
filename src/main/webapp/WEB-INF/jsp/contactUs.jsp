<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Contact Us</title>
<link rel="stylesheet" href="/resources/sites/css/main.css" />
</head>
<body>
	<jsp:include page="./include/header.jsp"></jsp:include>

  <div class="contact-wrapper container">
    <!-- Google Map Section -->
    <div class="map-container">
      <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3153.019807690128!2d-122.42177858468116!3d37.77492977975995!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x8085809cbe7c8b5f%3A0x8f7d01e398f72e58!2sSan%20Francisco%20City%20Hall!5e0!3m2!1sen!2sus!4v1611785127997!5m2!1sen!2sus" allowfullscreen="" loading="lazy"></iframe>
    </div>
    <!-- Contact Form Section -->
    <div class="form-container">
      <h1>Contact Us</h1>
      <p class="message message-contact-form">We'd love to hear from you! Please fill out the form below and we'll get back to you as soon as possible.</p>
      <form class="contact-form">
        <div class="row">
          <div class="col">
            <div class="form-group">
              <!-- <label for="name">Your Name</label> -->
              <input type="text" id="name" name="name" required placeholder="Your Name">
            </div>
          </div>
          <div class="col">
            <div class="form-group">
              <!-- <label for="phone">Your Phone</label> -->
              <input type="number" id="phone" name="phone" required placeholder="Your Phone">
            </div>
          </div>
        </div>

        <div class="form-group">
          <!-- <label for="email">Your Email</label> -->
          <input type="email" id="email" name="email" required placeholder="Your Email">
        </div>

        <div class="form-group">
         <!--  <label for="message">Your Message</label> -->
          <textarea id="message" name="message" rows="4" required placeholder="Your Message"></textarea>
        </div>

        <button type="submit" class="submit-btn" id="contact-form">Submit</button>
      </form>
    </div>
  </div>

	<jsp:include page="./include/footer.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="/resources/sites/js/script.js"></script>
</body>
</html>
