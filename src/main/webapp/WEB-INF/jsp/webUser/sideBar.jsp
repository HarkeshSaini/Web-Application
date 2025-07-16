<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="/resources/admin/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
<link rel="stylesheet" href="/resources/sites/css/dashboard.css">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top shadow-sm">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Dashboard</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="nav-link active" href="/user/dashboard">Dashboard</a></li>
        <li class="nav-item"><a class="nav-link active" href="#">Settings</a></li>
        <li class="nav-item"><a class="nav-link active" href="/user/information">Account Info</a></li>
        <li class="nav-item"><a class="nav-link active" href="/user/logout">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="col-md-3 col-sm-12 mb-4 side-bar">
  <%-- <div class="card text-center p-3">
    <img src="/resources/admin/image/${userDetail.imgUrl}" alt="User" class="profile-img mx-auto mb-3" />
    <h5 class="card-title">${userDetail.fullName}</h5>
    <p class="card-text text-muted">${userDetail.destination}</p>
    <a href="/user/update-profile" class="btn btn-primary w-100">Edit Profile</a>
  </div> --%>
  <div class="card p-3 mobile">
    <div class="search-logo-data">
		<div class="search">
		    <input type="search" id="search" placeholder="Search list item...">
		</div> 
	</div>
     <ul id="list" class="list-group nav-pills box flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start">
      <li class="nav-item c"><a href="/user/dashboard" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Dashboard</span> </a></li>
      <li class="nav-item c"><a href="/user/information" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Account info</span> </a></li>
      <li class="nav-item c"><a href="/user/update-profile" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Change info</span> </a></li>
      <li class="nav-item c"><a href="#" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline"> Calendar info</span> </a></li>
      <li class="nav-item c"><a href="#" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline"> live Chat</span> </a></li>
      <li class="nav-item c"><a href="#" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline"> Email data</span> </a></li>
	  <li class="nav-item c"><a href="#" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline"> Event Process</span> </a></li>
	  <li class="nav-item c"><a href="#" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline"> Service detail</span> </a></li>
	  <li class="nav-item c"><a href="/user/logout" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">logout</span> </a></li>   
    </ul>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

<script type="text/javascript">
document.getElementById("search").addEventListener("keyup", function() {
    let filter = this.value.toLowerCase();
    let items = document.querySelectorAll("#list li");

    items.forEach(function(item) {
        let text = item.textContent.toLowerCase();
        item.style.display = text.includes(filter) ? "block" : "none";
    });
});

</script>
