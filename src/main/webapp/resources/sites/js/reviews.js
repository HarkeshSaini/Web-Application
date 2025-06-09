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
    const url = window.location.pathname;
    const fileInput = document.getElementById("file").files[0];
    const rating = document.querySelector('input[name="rating"]:checked')?.value;
    
    const formData = new FormData();
    formData.append("userName", userName);
    formData.append("userEmail", userEmail);
    formData.append("reviewMessage", userComment);
    formData.append("reviewUrl", url);
    formData.append("rating", rating && rating.trim() !== "" ? rating : "1");
    if (fileInput) {
        formData.append("file", fileInput);
    }
    const response = await fetch("/api/addNewReviews", {
        method: "POST",
        body: formData
    });
    const result = await response.text();
    const reviewMessageElement = document.querySelector('.review-mess');
    clearTimeout(window.reviewTimeout);
    reviewMessageElement.textContent = result;
    $('#review-form-data')[0].reset(); 
    $('input[name="rating"]').prop('checked', false);
    $('.star').removeClass('active');
    window.reviewTimeout = setTimeout(() => {
        reviewMessageElement.textContent = "";  
    }, 3000);
});
