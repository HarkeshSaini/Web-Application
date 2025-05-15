<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Frequently Asked Questions</title>
<link rel="stylesheet" href="/resources/sites/css/main.css" />
</head>
<body>
	<jsp:include page="./include/header.jsp"></jsp:include>
	<div class="container">
	<section class="faq-section">
	
	 <div class="row align-items-start">
	  <div class="col-12 col-md-4 col-lg-3">
	    <div class="image-fqas">
	      <img class="faq-image" src="/resources/sites/image/faq-image.avif" alt="Logo" style="max-width:100%;">
	    </div>
	  </div>
	  <div class="col-12 col-md-8 col-lg-9">
	    <h2 class="faq-title">Frequently Asked Questions</h2>
	    <div class="faq-item">
	      <input type="checkbox" id="faq1">
	      <label for="faq1" class="faq-question">
	        What is your return policy?
	        <span class="faq-toggle">+</span>
	      </label>
	      <div class="faq-answer">
	        We offer a 30-day return policy on all items. Make sure the items are unused and in original packaging.
	      </div>
	    </div>
	
	    <div class="faq-item">
	      <input type="checkbox" id="faq2">
	      <label for="faq2" class="faq-question">
	        Do you offer customer support?
	        <span class="faq-toggle">+</span>
	      </label>
	      <div class="faq-answer">
	        Yes, our support team is available 24/7 via email and live chat to assist you with any queries.
	      </div>
	    </div>
	
	    <div class="faq-item">
	      <input type="checkbox" id="faq3">
	      <label for="faq3" class="faq-question">
	        How long does shipping take?
	        <span class="faq-toggle">+</span>
	      </label>
	      <div class="faq-answer">
	        Shipping typically takes 5-7 business days for domestic orders and 10-15 days for international ones.
	      </div>
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
