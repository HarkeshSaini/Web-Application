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
