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
/*=========================================*/
 const input = document.getElementById('titleUrl');
    input.addEventListener('input', function () {
      let value = input.value;
      value = value.replace(/[^a-zA-Z0-9]/g, '-');
      value = value.replace(/-+/g, '-');
      value = value.replace(/^-|-$/g, '-');
      input.value = value;
    });