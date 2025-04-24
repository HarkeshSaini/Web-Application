<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/sites/css/style.css" />
<title>Index</title>
</head>
<body>
	<jsp:include page="./include/header.jsp"></jsp:include>
	<div class="contact-container">
        <h1>Contact Us</h1>
        <p>We'd love to hear from you! Please fill out the form below and we'll get back to you as soon as possible.</p>
        
        <form class="contact-form">
        
          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="name">Your Name</label>
                <input type="text" id="name" name="name" required placeholder="Your Name">
              </div>
              </div>
             <div class="col">
              <div class="form-group">
                <label for="name">Your Phone</label>
                <input type="number" id="phone" name="name" required placeholder="Your Phone">
              </div>
             </div>
			</div>
            <div class="form-group">
                <label for="email">Your Email</label>
                <input type="email" id="email" name="email" required placeholder="Your Email">
            </div>

            <div class="form-group">
                <label for="message">Your Message</label>
                <textarea id="message" name="message" rows="4" required placeholder="Your Message"></textarea>
            </div>

            <button type="submit" class="submit-btn">Submit</button>
        </form>
    </div>
	
	<jsp:include page="./include/footer.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="/resources/sites/js/script.js"></script>
</body>
</html>
