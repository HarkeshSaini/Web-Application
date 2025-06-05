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
    if (!rating || rating.trim() === "") { 
        formData.append("rating", "1"); 
    } else { 
        formData.append("rating", rating); 
    }
    formData.append("reviewUrl", url);
    if (fileInput) {
        formData.append("file", fileInput);
    }
    const response = await fetch("/api/addNewReviews", {
        method: "POST",
        body: formData
    });
    const result = await response.text();
    $('.review-mess').text(result); 
    $('#review-form-data')[0].reset(); 
    $('input[name="rating"]').prop('checked', false);
    $('.star').removeClass('active');
});