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
        //console.log(response); // You can log the response to see the data format
        var reviewsContainer = $("#reviews-container");
        reviewsContainer.empty();

        response.forEach(function (data) {
            // Format the timestamp using the convertTimestamp function
            const dates = convertTimestamp(data.postTime);

            // Construct the review card and insert the formatted date
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
    });
};

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
