<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="/resources/admin/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
<link rel="stylesheet" href="/resources/sites/css/dashboard.css">
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top shadow-sm">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Dashboard</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
		    <div class="search">
		        <form action="" class="search-form">
		            <input type="search" name="" required="required" placeholder="Search...">
		        </form>
		    </div>
		</li>
        <li class="nav-item"><a class="nav-link active" href="/user/dashboard">Dashboard</a></li>
        <li class="nav-item"><a class="nav-link active" href="#">Settings</a></li>
        <li class="nav-item"><a class="nav-link active" href="/user/information">Account Info</a></li>
        <li class="nav-item"><a class="nav-link active" href="/user/logout">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="col-md-3 col-sm-12 mb-4 side-bar">
  <div class="card text-center p-3">
    <img src="/resources/admin/image/${userDetail.imgUrl}" alt="User" class="profile-img mx-auto mb-3" />
    <h5 class="card-title">${userDetail.fullName}</h5>
    <p class="card-text text-muted">${userDetail.destination}</p>
    <a href="/user/update-profile" class="btn btn-primary w-100">Edit Profile</a>
  </div>
  <div class="card p-3 mobile">
    <h5>Other information</h5>
     <ul class="list-group nav-pills box flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start">
      <li class="nav-item c"><a href="/user/dashboard" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Dashboard Overview and Summary</span> </a></li>
      <li class="nav-item c"><a href="/user/information" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Your Account Information Detail</span> </a></li>
      <li class="nav-item c"><a href="/user/update-profile" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Change Your Account Information</span> </a></li>
      <li class="nav-item c"><a href="#" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Change Your Account Settings</span> </a></li>
      <li class="nav-item c"><a href="/user/logout" class="nav-link align-middle px-0"> <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Logout</span> </a></li>
    </ul>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
