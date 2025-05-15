    <link rel="stylesheet" href="/resources/sites/css/review.css"/>
    <section class="review-container">
        <div class="review-form">
            <h2 class="review-heading">Submit Your Review</h2>
            <form class="review-form-content">
                <!-- Review Section at the top -->
                <div class="form-group">
                <div class="form-group row">
                    <div class="col">
                      Submit Your Review
                    </div>
                    <div class="col">
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
                </div>
                </div>
                <div class="form-group row">
                    <div class="col">
                        <label for="user-name" class="form-label">Your Name</label>
                        <input type="text" id="user-name" class="form-input" placeholder="Enter your name">
                    </div>
                    <div class="col">
                        <label for="user-email" class="form-label">Your Email</label>
                        <input type="email" id="user-email" class="form-input" placeholder="Enter your email">
                    </div>
                </div>
                 <div class="form-group">
                    <label for="user-comment" class="form-label">Your Comment</label>
                    <textarea id="user-comment" class="form-textarea" rows="4" placeholder="Write your review here..."></textarea>
                </div>
                <button type="submit" class="btn btn-primary review-submit">Submit Review</button>
            </form>
        </div>
    </section>
 
