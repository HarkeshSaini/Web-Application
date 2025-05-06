<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="/resources/admin/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
<link rel="stylesheet" href="/resources/sites/css/dashboard.css">
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top shadow-sm">
  <div class="container">
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
        <li class="nav-item"><a class="nav-link active" href="/user/dashboard">Home</a></li>
        <li class="nav-item"><a class="nav-link active" href="#">Settings</a></li>
        <li class="nav-item"><a class="nav-link active" href="/user/information">Account Info</a></li>
        <li class="nav-item"><a class="nav-link active" href="/user/logout">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="col-md-3 col-sm-12 mb-4">
  <div class="card text-center p-3">
    <img src="/resources/admin/image/${userDetail.imgUrl}" alt="User" class="profile-img mx-auto mb-3" />
    <h5 class="card-title">${userDetail.fullName}</h5>
    <p class="card-text text-muted">${userDetail.destination}</p>
    <a href="/user/update-profile" class="btn btn-primary w-100">Edit Profile</a>
  </div>
  <div class="card p-3">
    <h5>Address</h5>
    <p><strong>Street:</strong> 123 Main St</p>
    <p><strong>City:</strong> New York</p>
    <p><strong>State:</strong> NY</p>
    <p><strong>ZIP:</strong> 10001</p>
    <p><strong>Country:</strong> USA</p>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>