    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="/resources/sites/css/review.css"/>
    <section class="review-container">
        <div class="review-form">
            <h2 class="review-heading">Submit Your Review</h2>
            <form class="review-form-content">
               <div class="container">
			    <div class="row form-group-review main-reviews">
			        <div class="col-md-6 col-review">
			           <p class="reviews-data">Submit your reviews on the discussion and your feelings for our blog.</p>
				        </div>
				        <div class="col-md-6 col-review">
				            <div class="row">
				                <div class="col-md-4">
				                <div class="rating-stars">
			                        <input type="radio" name="rating" value="5" id="star-5">
			                        <label for="star-5" class="star">&#9733;</label>
			                        <input type="radio" name="rating" value="4" id="star-4">
			                        <label for="star-4" class="star">&#9733;</label>
			                        <input type="radio" name="rating" value="3" id="star-3">
			                        <label for="star-3" class="star">&#9733;</label>
			                        <input type="radio" name="rating" value="2" id="star-2">
			                        <label for="star-2" class="star">&#9733;</label>
			                        <input type="radio" name="rating" value="1" id="star-1">
			                        <label for="star-1" class="star">&#9733;</label>
			                    </div>
				                </div>
				                <div class="col-md-4">
				                    <div class="fileUploadInput">
								    <input type="file" name="file" id="file"/>
								    <button>+</button></div>
				                </div>
				            </div>
				        </div>
				    </div><hr class="hori-line">
				</div><br>
                <div class="form-group-review row">
                    <div class="col-review">
                        <label for="user-name" class="form-label">Your Name</label>
                        <input type="text" id="user-name" class="form-input" placeholder="Enter your name">
                    </div> 
                    <div class="col-review">
                        <label for="user-email" class="form-label">Your Email</label>
                        <input type="email" id="user-email" class="form-input" placeholder="Enter your email">
                    </div>
                </div><br>
                 <div class="form-group-review">
                    <label for="user-comment" class="form-label">Your Comment</label>
                    <textarea id="user-comment" class="form-textarea" rows="4" placeholder="Write your review here..."></textarea>
                </div>
                <button type="button" class="btn btn-primary review-submit">Submit Review</button>
           		<p class="review-mess"></p>
            </form>
        </div>
    </section>
 
<script>
document.querySelectorAll('.rating-stars input').forEach(star => {
    star.addEventListener('change', function() {
        let selectedRating = this.value;
        document.querySelectorAll('.star').forEach(label => {
            label.style.color = label.htmlFor.split('-')[1] <= selectedRating ? '#5da3bc' : 'gray';
        });
    });
});

document.querySelector(".review-submit").addEventListener("click", async function () {
    const userName = document.getElementById("user-name").value;
    const userEmail = document.getElementById("user-email").value;
    const userComment = document.getElementById("user-comment").value;
    const url = window.location.pathname
    const fileInput = document.getElementById("file").files[0];
    const rating = document.querySelector('input[name="rating"]:checked')?.value;
    
    const formData = new FormData();
    formData.append("userName", userName);
    formData.append("userEmail", userEmail);
    formData.append("reviewMessage", userComment);
    formData.append("rating", rating);
    formData.append("reviewUrl", url);
    if (fileInput) {
        formData.append("file", fileInput);
    }
    try {
        const response = await fetch("/api/addNewReviews", {
            method: "POST",
            body: formData
        });

        const result = await response.json();
        $('.review-mess').text(result);
        setTimeout(() => {
            form.reset();  
          }, 2000);
        
    } catch (error) {
    	$('.review-mess').text(error);
    	setTimeout(() => {
            form.reset();  
          }, 2000);
    }
});

</script>