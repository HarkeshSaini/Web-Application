$(document).ready(function(){
  // Open sidebar
  $(".open-btn").click(function(){
    $(".sidebar").css("width","250px");
    $(".content").css("margin-left","250px");
  });

  // Close sidebar
  $(".close-btn").click(function(){
    $(".sidebar").css("width","0");
    $(".content").css("margin-left","0");
  });
});

// Select the form element
const form = document.getElementById('contactForm');

// Add an event listener for form submission
if (form) {
form.addEventListener('submit', async function (event) {
    // Prevent the default form submission
    event.preventDefault();

    // Get the values of the form fields
    const name = document.getElementById('name').value;
    const phone = document.getElementById('phone').value;
    const email = document.getElementById('email').value;
    const message = document.getElementById('message').value;

    // Create the data object to send
    const formData = {
        name: name,
        phone: phone,
        email: email,
        message: message
    };

    // Specify the API endpoint (replace this with your actual API URL)
    const apiUrl = 'http://localhost:8080/api/submitContactInfo'; // Replace with your API endpoint

    try {
        // Make the API request using fetch
        const response = await fetch(apiUrl, {
            method: 'POST', // Use POST for form submissions
            headers: {
                'Content-Type': 'application/json', // We are sending JSON data
            },
            body: JSON.stringify(formData), // Send form data as JSON
        });

        // Handle the response
        if (response.ok) {
            // If the request is successful, notify the user
            alert('Thank you for contacting us! We will get back to you soon.');
            form.reset(); // Clear the form after submission
        } else {
            // If the response is not OK, notify the user of an error
            alert('There was an error with your submission. Please try again later.');
        }
    } catch (error) {
        // Handle any errors that occur during the fetch request
        console.error('Error submitting form:', error);
        alert('There was an error with your submission. Please try again later.');
    }
});
} 

