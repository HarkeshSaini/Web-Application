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
  $('.submit-btn').on('click', function (e) {
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
        $('.message').text(response || 'Thank you for contacting us!');
        setTimeout(() => {
          form.reset();  
        }, 2000);
      },
      error: function (xhr) {
        $('.message').text('Something went wrong. Please try again.');
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

    if (!email) {
      $('.message').text('Please enter a valid email.');
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
        $('.message').text(message);
        
        setTimeout(() => {
          form.reset();  
        }, 2000);
      },
      error: function (xhr) {
        console.error('Error:', xhr.responseText);
        $('.message').text('Something went wrong. Please try again.');
        
      }
    });
  });
});
