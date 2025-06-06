<link rel="stylesheet" href="/resources/sites/css/header.css"/>
<nav class="navbar navbar-expand-lg bg-darks border-bottom border-body" data-bs-theme="dark">
  <div class="container"> 
    <a class="navbar-brand" href="#">
      <img src="/resources/sites/image/logo.png" alt="Logo" height="40">
    </a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <img src="/resources/sites/image/botton icon.png" alt="Logo" height="35">
    </button>
 
    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
      <ul class="navbar-nav align-items-center">
        <li class="nav-item category">
		  <button class="nav-link services" id="service-toggle">Technology <!-- <img class="drp-icon" src="/resources/sites/image/icon-drop.png" alt="Logo"> --></button>
		  <div class="dropdown-content" id="dropdown-content"></div>
		</li>
        <li class="nav-item"><a class="nav-link" href="/">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="/blog">Blog</a></li>
        <li class="nav-item"><a class="nav-link" href="/about-us">About Us</a></li>
        <li class="nav-item"><a class="nav-link" href="/contact-us">Contact Us</a></li>
        <li class="nav-item">
          <a class="nav-link popup-login">
            <button class="popup-btn"><img src="/resources/sites/image/login-icon-vector.jpg" alt="Login" class="rounded-pill" height="40"></button>
          </a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div class="popup" id="popup">
<span class="close"><img src="/resources/sites/image/close-icon.png" alt="close" class="close"></span>
   <div class="login-data">
     <ul class=" login-main navbar-nav align-items-center text-center">
        <li class="nav-item"><a class="nav-link in-data" href="/login">Login</a></li>
        <li class="nav-item"><a class="nav-link in-data" href="/sign-up">Sign Up</a></li>
     </ul>
    <h3>Or log in with </h3>
    <div class="social-icons">
        <a href="#" class="google">G</a>
        <a href="#" class="facebook">f</a>
        <a href="#" class="linkedin">In</a>
        <a href="#" class="github">Git</a>
    </div>
   <p>Welcome! Please log in to access your dashboard and view your profile.</p>
  </div>
</div>
<script>
document.querySelector(".popup-btn").addEventListener("click", function() {
    document.getElementById("popup").style.display = "block";
});

document.querySelector(".close").addEventListener("click", function() {
    document.getElementById("popup").style.display = "none";
});
</script>
<script src="/resources/sites/js/header.js"></script>

