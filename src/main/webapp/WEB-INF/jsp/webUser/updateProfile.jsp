<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update User Information</title>
  </head>
  <body>
    <div class="container-fluid py-5">
      <div class="row user-dash">
        <jsp:include page="sideBar.jsp"></jsp:include>
        <div class="col-md-8">
		  <form action="#" method="post">
		    <div class="card dashboard-card border-0 shadow-sm">
		      <div class="card-header bg-primary text-white">
		        <h5 class="mb-0"><i class="bi bi-person-circle me-2"></i>Update Information</h5>
		      </div>
		      <div class="card-body">
		        <div class="row g-3">
		          <div class="col-md-6">
		            <div class="info-item">
		              <label class="form-label text-muted mb-0" for="username">Your login Username</label>
		              <input type="text" id="username" name="username" class="form-control" placeholder="Enter username">
		            </div>
		          </div>
		          <div class="col-md-6">
		            <div class="info-item">
		              <label class="form-label text-muted mb-0" for="password">Your login Password</label>
		              <input type="password" id="password" name="password" class="form-control" placeholder="Enter password">
		            </div>
		          </div>
		          <div class="col-md-6">
		            <div class="info-item">
		              <label class="form-label text-muted mb-0" for="fullname">Full Name</label>
		              <input type="text" id="fullname" name="fullname" class="form-control" placeholder="Enter full name">
		            </div>
		          </div>
		          <div class="col-md-6">
		            <div class="info-item">
		              <label class="form-label text-muted mb-0" for="email">Email</label>
		              <input type="email" id="email" name="email" class="form-control" placeholder="Enter email">
		            </div>
		          </div>
		          <div class="col-md-6">
		            <div class="info-item">
		              <label class="form-label text-muted mb-0" for="phone">Phone Number</label>
		              <input type="tel" id="phone" name="phone" class="form-control" placeholder="Enter phone number">
		            </div>
		          </div>
		          <div class="col-md-6">
		            <div class="info-item">
		              <label class="form-label text-muted mb-0" for="gender">Gender</label>
		              <input type="text" id="gender" name="gender" class="form-control" placeholder="Enter gender">
		            </div>
		          </div>
		          <div class="col-md-6">
		            <div class="info-item">
		              <label class="form-label text-muted mb-0" for="accountDate">Account Create Date</label>
		              <input type="date" id="accountDate" name="accountDate" class="form-control">
		            </div>
		          </div>
		          <div class="col-md-6">
		            <div class="info-item">
		              <label class="form-label text-muted mb-0" for="dob">Date of Birth</label>
		              <input type="date" id="dob" name="dob" class="form-control">
		            </div>
		          </div>
		          <div class="col-12">
		            <div class="info-item">
		              <label class="form-label text-muted mb-0" for="address">Address</label>
		              <textarea id="address" name="address" class="form-control" rows="3" placeholder="Enter address"></textarea>
		            </div>
		          </div>
		          <div class="col-12">
		            <div class="info-item">
		              <label class="form-label text-muted mb-0" for="about">About Me</label>
		              <textarea id="about" name="about" class="form-control" rows="3" placeholder="Tell us about yourself"></textarea>
		            </div>
		          </div>
		          <div class="col-12 text-end">
		            <button type="submit" class="btn btn-primary mt-3">Submit</button>
		          </div>
		        </div>
		      </div>
		    </div>
		  </form>
		</div>

      </div>
    </div>
  </body>
</html>