window.onload = function() {
   var settings = {
    "url": "/api/getLatestBlog",  
    "method": "GET",
    "timeout": 0,
    "headers": {
        "Cookie": "JSESSIONID=60844BAF5B1DA20699159290C853472F"
    },
};

$.ajax(settings).done(function (response) {
    var reviewsContainer = $("#blog-show");
    reviewsContainer.empty(); // Clear existing content

    // Check if the response is null or empty
    if (!response || response.length === 0) {
        // If no blog posts, print 6 static blog boxes
        for (let i = 0; i < 6; i++) {
            var staticBlogBox = `
                <div class="review-card">
                    <div class="review-content">
                        <p class="time-date">No posts available</p>
                        <a class="data-url" href="#">No Blog Post Available</a>
                    </div>
                </div>
            `;
            reviewsContainer.append(staticBlogBox);
        }
    } else {
        // If there are blog posts, process and display them
        response.forEach(function (data) {
            // Format the timestamp using the convertTimestamp function
            const dates = convertTimestamp(data.postTime);

            // Construct the blog card with the formatted date
            var reviewCard = `
                <div class="review-card">
                    <div class="review-content">
                        <p class="time-date">${dates}</p>
                        <a class="data-url" href="/blog/${data.titleUrl}">${data.heading}</a>
                    </div>
                </div>
            `;
            reviewsContainer.append(reviewCard);
        });
    }
});

// Function to convert the timestamp to a formatted date string
function convertTimestamp(timestamp) {
    const date = new Date(timestamp);
    const day = date.toLocaleString('en-US', { weekday: 'long' });  
    const dateOfMonth = date.getDate();   
    const month = date.toLocaleString('en-US', { month: 'long' });   
    const year = date.getFullYear();  
    const hours = date.getHours();  
    const minutes = date.getMinutes(); 
    const seconds = date.getSeconds();  
    const time = `${hours}:${minutes}:${seconds}`;
    return `${day}, ${month} ${dateOfMonth}, ${year} - ${time}`;
}

/*===================================review==============================================*/
 
var settings = {
  "url": "/api/getAllReviews",
  "method": "GET",
  "timeout": 0,
  "headers": {
    "Cookie": "JSESSIONID=6D28E55B514E4D4FF5C338E72DE274AD"
  },
};

$.ajax(settings).done(function (response) {
  var reviewsContainer = $('.review-show');
  reviewsContainer.empty(); // Clear existing static content

  // Check if response is null or empty
  if (!response || response.length === 0) {
    // If no reviews, print 6 static review boxes
    for (let i = 0; i < 6; i++) {
      var staticReviewBox = `
        <div class="review-card review-size-count">
          <div class="review-header">
            <div class="review-avatar">
              <img src="https://i.pravatar.cc/100?img=${i + 1}" alt="User Avatar">
            </div>
            <div class="review-data">
              <div class="review-author name"><progress class="progress"></progress></div>
              <div class="review-stars">☆☆☆☆☆</div>
            </div>
          </div>
          <div class="review-content content-data">
            “No reviews yet.”  
          </div>
        </div>
      `;
      reviewsContainer.append(staticReviewBox);
    }
  } else {
    // If there are reviews, process and display them
    response.forEach(function (review, index) {
      var avatarUrl = `https://i.pravatar.cc/100?img=${index + 1}`;
      var starsHtml = '';

      // Assuming review.rating is a number (e.g., 4)
      var rating = review.reviewRating || 0;
      for (var i = 1; i <= 5; i++) {
        starsHtml += i <= rating ? '★' : '☆';
      }

      var reviewCard = `
        <div class="review-card review-size-count">
          <div class="review-header">
            <div class="review-avatar">
              <img src="${avatarUrl}" alt="User Avatar">
            </div>
            <div class="review-data">
              <div class="review-author name">${review.userName}</div>
              <div class="review-stars">${starsHtml}</div>
            </div>
          </div>
          <div class="review-content content-data">
            “${review.reviewMessage}”
          </div>
        </div>
      `;

      reviewsContainer.append(reviewCard);
    });
  }
});

};



/*=============================reviews next or priv=================================*/


function getclickname(obj) {
  var idString = obj.id;
  var settings = {
    "url": "/api/getReviewsWithPaginate/" + idString,
    "method": "GET",
    "timeout": 0,
    "headers": {
      "Cookie": "JSESSIONID=6D28E55B514E4D4FF5C338E72DE274AD"
    },
  };

  $.ajax(settings).done(function (response) {
    var reviewsContainer = $('.review-show');
    reviewsContainer.empty();  // Clear existing reviews

    // Check if response is null or empty
    if (!response || response.length === 0) {
      // If no reviews, print 6 static review boxes
      for (let i = 0; i < 6; i++) {
        var staticReviewBox = `
          <div class="review-card review-size-count">
            <div class="review-header">
              <div class="review-avatar">
                <img src="/resources/sites/image/defoult-img.png" alt="User Avatar">
              </div>
              <div class="review-data">
                <div class="review-author name"><progress></progress></div>
                <div class="review-stars"><progress></progress></div>
              </div>
            </div>
            <div class="review-content content-data"><progress class="progress"></progress></div>
          </div>
        `;
        reviewsContainer.append(staticReviewBox);
      }
    } else {
      // If there are reviews, process and display them
      response.forEach(function (review, index) {
        var avatarUrl = `https://i.pravatar.cc/100?img=${index + 1}`;
        var starsHtml = '';
        var rating = review.reviewRating || 0;
        for (var i = 1; i <= 5; i++) {
          starsHtml += i <= rating ? '★' : '☆';
        }
        var reviewCard = `
          <div class="review-card review-size-count">
            <div class="review-header">
              <div class="review-avatar">
                <img src="${avatarUrl}" alt="User Avatar">
              </div>
              <div class="review-data">
                <div class="review-author name">${review.userName}</div>
                <div class="review-stars">${starsHtml}</div>
              </div>
            </div>
            <div class="review-content content-data">
              “${review.reviewMessage}”
            </div>
          </div>
        `;
        reviewsContainer.append(reviewCard);
      });
    }
  });
}
