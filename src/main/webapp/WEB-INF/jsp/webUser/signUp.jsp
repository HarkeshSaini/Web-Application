<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta id="viewport" name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
	<link rel="stylesheet" href="/resources/admin/css/bootstrap.min.css">
	<link rel="stylesheet" href="/resources/admin/css/styles.css"/>
    <title>Add new User</title>
  </head>
  <body>
 <section class="bg-light py-3 py-md-5">
  <div class="containder">
    <div class="row justify-content-center">
      <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5 col-xxl-4">
        <div class="card border border-light-subtle rounded-3 shadow-sm">
          <div class="card-body p-3">
          <div class="rounded-3 messages">
              <div class="message text-center message">${message}</div>
              <c:if test="${not empty Password}">
               <div class="message text-center">!Your Sign in Password:- <p class="Password">${Password}</p></div>
              </c:if>
           </div>
              <!-- start -->
             <form action="/sign-up" method="post" enctype="multipart/form-data">
              <div class="row">
				<div class="col">
                <input type="text" name="fullName" class="form-control" placeholder="Enter full name *" required="required">
            </div>
            <div class="col">
                <input type="text" name="username" class="form-control" placeholder="Enter username *" required="required">
            </div>
            </div>
             <div class="row">
            <div class="col">
               <input type="email" name="email" class="form-control" placeholder="Enter email addrass *" required="required">
            </div>
            <div class="col">
               <input type="number" name="phone" class="form-control" placeholder="Enter mobile no *" required="required">
            </div>
          </div>
          
           <div class="row">
            <div class="col">
               <input type="datetime-local" name="dateOfBirth" class="form-control" placeholder="dd-mm-yyyy *" required="required">
            </div>
            <div class="col">
            <select name="gender" class="form-control" required="required">
               <option>Select gender*</option>
               	 <option value="male">Male</option>
               	 <option value="female">Female</option>
               </select>
            </div>
          </div> 
              
              
           <div class="row">
			<div class="col">
               <input type="file" name="file" class="form-control">
            </div>
              
            <div class="col">
               <select name="age" class="form-control" required="required">
               <option>Select Age*</option>
                <c:forEach var="data" begin="1" end="60">
               		<option value="${data}">${data}</option>
                </c:forEach>
               </select>
             </div>
              </div>
              <div class="row">
                  <div class="form-outline col-md-12 mb-3">
                      <textarea name="address" class="form-control address" placeholder="Enter full address *" required="required"></textarea>
                  </div>
               </div>
               <div class="col-12">
                  <div class="d-grid my-3">
                    <button class="btn btn-primary btn-lg" type="submit">Submit</button>
                  </div>
                </div>
               </form>
                <div class="col-12">
                  <p class="m-0 text-secondary text-center">Do you already have an account? <a href="/login" class="link-primary text-decoration-none">Sign in</a></p>
                </div>
             <!-- end -->
              
           </div>
          </div>
         </div>
     	</div>
      </div>
     </section>
  </body>
</html>