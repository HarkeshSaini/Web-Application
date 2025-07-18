<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
  </head>
  <body>
    <div class="container-fluid">
      <div class="row user-dash">
        <jsp:include page="sideBar.jsp"></jsp:include>
        <div class="col-md-8">
          <div class="card dashboard-card border-0 shadow-sm">
            <div class="card-header bg-primary text-white">
               <p class="mb-0">User Account Information <p>${message}</p> 
            </div>
            <div class="card-body">
              <div class="row g-3">
                <div class="col-md-6">
                
                  <div class="user-profile">
				    <img src="/resources/admin/image/${userDetail.imgUrl}" alt="User Profile Picture" class="user-image">
				</div>
				<a href="/user/update-profile" class="btn btn-primary user_edit_info">Edit Profile</a>
				<div class="main-detail">
				 <div class="info-item">
				  <label class="form-label text-muted mb-0">Username  </label>
                    <p class="fw-semibold fs-6 mb-0">${userDetail.username}</p>
                  </div>
                  <div class="info-item">
                    <label class="form-label text-muted mb-0">Password  </label>
                    <p class="fw-semibold fs-6 mb-0">${userDetail.password}</p>
                  </div>
                 </div> 
                  
                </div>
                <div class="col-md-6">
                  <div class="info-item">
                    <label class="form-label text-muted mb-0">Full Name  </label>
                    <p class="fw-semibold fs-6 mb-0">${userDetail.fullName}</p>
                  </div>
                 
                  <div class="info-item">
                    <label class="form-label text-muted mb-0">Email  </label>
                    <p class="fw-semibold fs-6 mb-0">${userDetail.email}</p>
                  </div>
                
                  <div class="info-item">
                    <label class="form-label text-muted mb-0">Phone Number  </label>
                    <p class="fw-semibold fs-6 mb-0">${userDetail.phone}</p>
                  </div>
                
                  <div class="info-item">
                    <label class="form-label text-muted mb-0"> Gender  </label>
                    <p class="fw-semibold fs-6 mb-0">${userDetail.gender}</p>
                  </div>
                 
                  <div class="info-item">
                    <label class="form-label text-muted mb-0">Create  </label>
                    <p class="fw-semibold fs-6 mb-0">${userDetail.createdAt}</p>
                  </div>
                 
                  <div class="info-item">
                    <label class="form-label text-muted mb-0">Date of birth  </label>
                    <p class="fw-semibold fs-6 mb-0">${userDetail.dateOfBirth}</p>
                  </div>
                 
                  <div class="info-item">
                    <label class="form-label text-muted mb-0">Address  </label>
                    <p class="fw-semibold fs-6 mb-0">${userDetail.address}</p>
                  </div>
                
                  <div class="info-item">
                    <label class="form-label text-muted mb-0">About Me  </label>
                    <p class="fw-semibold fs-6 mb-0">${userDetail.aboutMe}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>