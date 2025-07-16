<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update User Information</title>
  </head>
  <body>
    <div class="container-fluid">
      <div class="row user-dash">
        <jsp:include page="sideBar.jsp"></jsp:include>
       <div class="col-md-8">
		  <form:form action="/user/update-profile" method="post" enctype="multipart/form-data">
		    <div class="card dashboard-card border-0 shadow-sm">
		      <div class="card-header bg-primary text-white">
		      	 <p class="mb-0">Update Account Information <p>${message}</p> 
		      </div>
		      <div class="card-body upadate">
		        <div class="row g-3 ">
		          <div class="col-md-6">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="username">Your login Username</label>
		              <form:input type="text" id="username" path="username" class="form-control" placeholder="Enter username"/>
		            </div>
		          </div>
		          <div class="col-md-6">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="password">Your login Password</label>
		              <form:input type="text" id="password" path="password" class="form-control" placeholder="Enter password"/>
		            </div>
		          </div>
		          <div class="col-md-6">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="fullname">Full Name</label>
		              <form:input type="text" id="fullname" path="fullName" class="form-control" placeholder="Enter full name"/>
		            </div>
		          </div>
		          <div class="col-md-6">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="email">Email</label>
		              <form:input type="email" id="email" path="email" class="form-control" placeholder="Enter email"/>
		            </div>
		          </div>
		          <div class="col-md-6">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="phone">Phone Number</label>
		              <form:input type="tel" id="phone" path="phone" class="form-control" placeholder="Enter phone number"/>
		            </div>
		          </div>
		           <div class="col-md-6">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="phone">Your Destination</label>
		              <form:input type="text" id="destination" path="destination" class="form-control" placeholder="Enter Destination"/>
		            </div>
		          </div>
		          <div class="col-md-4">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="gender">Gender</label>
		              <form:select id="gender" path="gender" class="form-control">
						  <form:option value="male">Male</form:option>
						  <form:option value="female">Female</form:option>
						  <form:option value="other">Other</form:option>
					  </form:select>
		            </div>
		          </div>
		           
		           <div class="col-md-4">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="image">Account Image</label>
		              <input type="file" id="file" name="file" class="form-control"/>
		            </div>
		          </div>
		          <div class="col-md-4">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="createdAt">Account Status</label>
		              <input type="text" id="status" value="Active" class="form-control"/>
		            </div>
		          </div>
		          <div class="col-md-6">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="createdAt">Account Update Date ${userDetail.updatedAt}</label>
		              <input type="text" id="updatedAt" value="${userDetail.updatedAt}" class="form-control"/>
		            </div>
		          </div>
		          
		          <div class="col-md-6">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="dob">Date of Birth:-  ${userDetail.dateOfBirth}</label>
		              <form:input type="datetime-local" id="dateOfBirth" path="dateOfBirth" class="form-control"/>
		            </div>
		          </div>
		          <div class="col-6">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="address">Address</label>
		              <form:textarea id="address" path="address" class="form-control" rows="3" placeholder="Enter address"/>
		            </div>
		          </div>
		          <div class="col-6">
		            <div class="info-item edit">
		              <label class="form-label text-muted mb-0" for="about">About Me</label>
		              <form:textarea id="aboutMe" path="aboutMe" class="form-control" rows="3" placeholder="Tell us about yourself"/>
		            </div>
		          </div>
		          <div class="col-12 text-end">
		            <button type="submit" class="btn btn-primary mt-3">Submit</button>
		          </div>
		        </div>
		      </div>
		    </div>
		  </form:form>
		</div>

      </div>
    </div>
  </body>
</html>