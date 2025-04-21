<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% String roleUser = (String) session.getAttribute("role"); %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta id="viewport" name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
    <title>All Admin User</title>
  </head>
  <body>
    <div class="container-fluid">
      <div class="row flex-nowrap">
        <jsp:include page="sideBar.jsp"></jsp:include>
        <div class="col py-0">
          <section class="bg-light py-0">
            <div class="container">
              <div class="row justify-content-center">
                <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-12 col-xxl-4">
                   <div class="card border border-light-subtle rounded-3 shadow-sm messages">
	                  <div class="row">
    					<div class="col">
	                      <div class="message">${message}</div>
	                    </div>
	                    <div class="col link">
	                     <a href="/admin/showAllContactInfo" class="btn btn-primary user">Contact Info</a>
	                     </div>
                       </div>
                    </div>
                    <div class="card border border-light-subtle rounded-3 shadow-sm">
                    <div class="card-body p-3 p-md-4">
                      <table id="myTable" class="display">
                        <thead>
                          <tr>
                            <th>User_Id</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>View</th>
                            <th>phone</th>
                            <th>Status</th>
                            <c:if test="${userRole == 'ADMIN'}">
                             <th>Delete</th>
                            </c:if>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="data" items="${contactInfo}">
                            <tr>
                              <td>${data.id}</td>
                              <td>${data.name}</td>
                              <td>${data.email}</td>
                              <td><button type="button" class="btn user" onclick="fetchContactUsDetails('${data.id}')" data-bs-toggle="modal" data-bs-target="#staticBackdrop">more</button></td>
                              <td>${data.phone}</td>
                              <td>${data.status}</td>
                              <c:if test="${userRole=='ADMIN'}">
                               <td><a href="/admin/deleteContactUsInfo/${data.id}"><i class="fa-solid fa-trash"></i></a></td>
                              </c:if>
                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
          
        <!-- Bootstrap Modal -->
			<div class="modal fade" id="staticBackdrop" tabindex="-1">
			  <div class="modal-dialog modal-dialog-scrollable modal-lg">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5">User Details</h1>
			        <button type="button" class="btn-close close" data-bs-dismiss="modal"></button>
			      </div>
			      <div class="modal-body">
			        <div class="container">
			          <div class="row mb-2"><div class="col-4 fw-bold">Name:</div><div class="col-8" id="userName">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Email:</div><div class="col-8" id="userEmail">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Phone:</div><div class="col-8" id="userPhone">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Subject:</div><div class="col-8" id="userSubject">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Message:</div><div class="col-8" id="userMessage">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Status:</div><div class="col-8" id="userStatus">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Posted On:</div><div class="col-8" id="userPostTime">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Updated On:</div><div class="col-8" id="userUpdateTime">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Language:</div><div class="col-8" id="userLang">--</div></div>
			        </div>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary close" data-bs-dismiss="modal">Close</button>
			      </div>
			    </div>
			  </div>
			</div>
        </div>
      </div>
    </div>
    <script type="text/javascript"> 
      $(document).ready( function () {
          $("#myTable").DataTable();
      } );
    </script>
    <script>
  function fetchContactUsDetails(id) {
    fetch(`http://localhost:8080/api/findByIdContactUs/`+id)
      .then(response => {
        if (!response.ok) throw new Error('Network response was not ok');
        return response.json();
      })
      .then(data => {
        document.getElementById('userName').textContent = data.name || '--';
        document.getElementById('userEmail').textContent = data.email || '--';
        document.getElementById('userPhone').textContent = data.phone || '--';
        document.getElementById('userSubject').textContent = data.subject || '--';
        document.getElementById('userMessage').textContent = data.message || '--';
        document.getElementById('userStatus').textContent = data.status || '--';
        document.getElementById('userPostTime').textContent = data.postTime || '--';
        document.getElementById('userUpdateTime').textContent = data.updateTime || '--';
        document.getElementById('userLang').textContent = data.lang_code || '--';
        const modal = new bootstrap.Modal(document.getElementById('staticBackdrop'));
        modal.show();
      })
      .catch(error => {
        console.error('Error fetching contact details:', error);
      });
  }
  $(document).ready(function () {
      $(".close").click(function () {
          location.reload(true);
      });
  });
</script>
    
  </body>
</html>