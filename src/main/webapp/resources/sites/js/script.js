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
$(document).ready(function () {
  $('#contact-form').on('click', function (e) {
    e.preventDefault(); // prevent form from submitting normally

    const form = $(this).closest('form')[0]; // Get the form element
    const name = $('#name').val();
    const phone = $('#phone').val();
    const email = $('#email').val();
    const message = $('#message').val();

    $.ajax({
      url: '/api/submitContactInfo',
      method: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({
        name: name,
        phone: phone,
        email: email,
        message: message
      }),
      success: function (response) {
        $('.message-contact-form').text(response || 'Thank you for contacting us!');
        setTimeout(() => {
          form.reset();  
        }, 2000);
      },
      error: function (xhr) {
        $('.message-contact-form').text('Something went wrong. Please try again.');
      }
    });
  });
});
/*======================/subscribe=======================*/
$(document).ready(function () {
  $('.subscribe').on('click', function (e) {
    e.preventDefault();

    const form = $(this).closest('form')[0];
    const email = $(form).find('#email').val();
	console.log(email);
    if (!email) {
      $('.message-subscribe').text('Please enter a valid email.');
      return;
    }

    $.ajax({
      url: '/api/subscribe',
      method: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({ email }),
      success: function (response) {
        const message = typeof response === 'string'
          ? response : (response || 'Thank you for subscribing!');
        $('.message-subscribe').text(message);
        
        setTimeout(() => {
          form.reset();  
        }, 2000);
      },
      error: function (xhr) {
        console.error('Error:', xhr.responseText);
        $('.message-subscribe').text('Something went wrong. Please try again.');
        
      }
    });
  });
});
