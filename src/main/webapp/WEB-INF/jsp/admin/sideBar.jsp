<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="/resources/admin/css/dataTables.css" />
<link rel="stylesheet" href="/resources/admin/css/responsive.dataTables.min.css">
<link rel="stylesheet" href="/resources/admin/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/admin/css/styles.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">



<!-- <div class="crose-icon slide-toggle"> <i class="fa-solid fa-xmark"></i></div>  -->
<div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark ">

  <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
    <div class="search-logo-data">
		<div class="search">
		    <input type="search" id="search" placeholder="Search list item...">
		</div> 
	</div>
    <ul id="list" class="list-group nav-pills box flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
      <li class="nav-item">
        <a href="/admin/dashboard" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> 
        <span class="d-none d-sm-inline">Main DashBoard</span> </a>
      </li>
      <c:if test="${userRole == 'ADMIN'}">
      <li class="nav-item">
        <a href="/admin/getAllUserAdmin" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> 
        <span class="d-none d-sm-inline">Admin Information</span> </a>
      </li>
      <li class="nav-item">
        <a href="/admin/getAllUserWebSite" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> 
        <span class="d-none d-sm-inline">User Information</span> </a>
      </li>
      
      
      
      
      </c:if>
      
      <li class="nav-item">
        <a href="/admin/showAllContactInfo" class="nav-link align-middle px-0"> 
        <i class="fs-4 bi-house"></i> 
        <span class="d-none d-sm-inline">Contact Information</span> </a>
      </li>
      <li class="nav-item">
        <a href="/admin/getAllCategory" class="nav-link align-middle px-0"> 
        <i class="fs-4 bi-house"></i> 
        <span class="d-none d-sm-inline">Category Details</span> </a>
      </li>
      <li class="nav-item">
        <a href="/admin/getAllBlog" class="nav-link align-middle px-0"> 
        <i class="fs-4 bi-house"></i> 
        <span class="d-none d-sm-inline">Blogs InfoDetails</span> </a>
      </li>
      <li class="nav-item">
        <a href="/admin/dashboard" class="nav-link align-middle px-0"> 
        <i class="fs-4 bi-house"></i> 
        <span class="d-none d-sm-inline">FAQ Information</span> </a>
      </li>
      <li class="nav-item">
        <a href="/admin/getAllReviews" class="nav-link align-middle px-0"> 
        <i class="fs-4 bi-house"></i> 
        <span class="d-none d-sm-inline">Reviews Details</span> </a>
      </li>
      <li class="nav-item">
        <a href="/admin/getAllDefaultContant" class="nav-link align-middle px-0"> 
        <i class="fs-4 bi-house"></i> 
        <span class="d-none d-sm-inline">Default Info Page </span> </a>
      </li>
      <li class="nav-item">
        <a href="/admin/showAllSubscribeInfo" class="nav-link align-middle px-0"> 
        <i class="fs-4 bi-house"></i> 
        <span class="d-none d-sm-inline">Subscribe Information </span> </a>
      </li>
      <li class="nav-item">
        <a href="/admin/logout" class="nav-link align-middle px-0"> 
        <i class="fs-4 bi-house"></i> 
        <span class="d-none d-sm-inline">Log Out </span> </a>
      </li>
    </ul>
    <%-- <div class="dropdown pb-4">
      <a href="/admin/logout" class="align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="true">
      <img src="/resources/admin/image/defoult-img.png" alt="hugenerd" width="30" height="30" class="rounded-circle" />
      <br><span class="d-none d-sm-inline mx-1">${user}</span>
      </a>
    </div> --%>
    
  </div>
</div>

<script src="https://cdn.ckeditor.com/4.20.1/standard/ckeditor.js"></script>
<script src="/resources/admin/js/jquery-3.6.0.min.js"></script>
<script src="/resources/admin/js/dataTables.js"></script>
<script src="/resources/admin/js/bootstrap.bundle.min.js"></script>


<script>
$(document).ready(function(){
	  $(".slide-toggle").click(function(){
	    $(".box").animate({
	      width: "toggle"
	    });
	  });
	});
	
document.getElementById("search").addEventListener("keyup", function() {
    let filter = this.value.toLowerCase();
    let items = document.querySelectorAll("#list li");

    items.forEach(function(item) {
        let text = item.textContent.toLowerCase();
        item.style.display = text.includes(filter) ? "block" : "none";
    });
});

</script>

