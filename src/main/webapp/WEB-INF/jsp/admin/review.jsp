<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% String roleUser = (String) session.getAttribute("role"); %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta id="viewport" name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
    <title>All Review</title>
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
	                     <a href="/admin/getAllReviews" class="btn btn-primary user">All Reviews Info</a>
	                     </div>
                       </div>
                    </div>
                    <div class="card border border-light-subtle rounded-3 shadow-sm">
                    <div class="card-body p-3 p-md-4">
                      <table id="myTable" class="display">
                        <thead>
                          <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Status</th>
                            <th>View</th>
                            <c:if test="${userRole == 'ADMIN'}">
                             <th>Delete</th>
                            </c:if>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="data" items="${infoRequests}">
                            <tr>
                              <td>${data.userName}</td>
                              <td>${data.userEmail}</td>
                               <c:if test="${data.reviewStatus=='Active'}">
                                <td><input type="checkbox" checked="checked" onclick="checkbox('${data.id}')" id="opt1"> <span id="msg">${data.reviewStatus}</span></td>
                              </c:if>
                              <c:if test="${data.reviewStatus=='InActive'}">
                              	<td><input type="checkbox" onclick="checkbox('${data.id}')" id="opt1"> <span id="msg">${data.reviewStatus}</span></td>
                              </c:if>
                              <td><button type="button" class="btn user" onclick="findByIdReviews('${data.id}')" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Detail</button></td>
                              <c:if test="${userRole=='ADMIN'}">
                               <td><a href="/admin/deleteReviewInfo/${data.id}"><i class="fa-solid fa-trash"></i></a></td>
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
			        <h1 class="modal-title fs-5">Reviews Details</h1>
			        <button type="button" class="btn-close close" data-bs-dismiss="modal"></button>
			      </div>
			      <div class="modal-body">
			        <div class="container">
			          <div class="row mb-2"><div class="col-4 fw-bold">Id:</div><div class="col-8" id="id">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Name:</div><div class="col-8" id="userName">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Email:</div><div class="col-8" id="userEmail">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Phone:</div><div class="col-8" id="userPhone">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Message:</div><div class="col-8" id="reviewMessage">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Status:</div><div class="col-8" id="reviewStatus">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Review Date:</div><div class="col-8" id="reviewDate">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Posted On:</div><div class="col-8" id="postTime">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Updated On:</div><div class="col-8" id="updateTime">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Language:</div><div class="col-8" id="lang_code">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Rating:</div><div class="col-8" id="reviewRating">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Page URL:</div><div class="col-8" id="reviewUrl">--</div></div>
			          <div class="row mb-2"><div class="col-4 fw-bold">Rpy-Id:</div><div class="col-8" id="reviewRpyId">--</div></div>
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
  function findByIdReviews(id) {
    fetch(`/api/findByIdReviews/`+id)
      .then(response => {
        if (!response.ok) throw new Error('Network response was not ok');
        return response.json();
      })
      .then(data => {
    	document.getElementById('id').textContent = data.id || '--';
        document.getElementById('userName').textContent = data.userName || '--';
        document.getElementById('userEmail').textContent = data.userEmail || '--';
        document.getElementById('userPhone').textContent = data.userPhone || '--';
        document.getElementById('reviewMessage').textContent = data.reviewMessage || '--';
        document.getElementById('reviewStatus').textContent = data.reviewStatus || '--';
        document.getElementById('reviewDate').textContent = data.reviewDate || '--';
        document.getElementById('postTime').textContent = data.postTime || '--';
        document.getElementById('updateTime').textContent = data.updateTime || '--';
        document.getElementById('lang_code').textContent = data.lang_code || '--';
        document.getElementById('reviewRating').textContent = data.reviewRating || '--';
        document.getElementById('reviewUrl').textContent = data.reviewUrl || '--';
        document.getElementById('reviewRpyId').textContent = data.reviewRpyId || '--';
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
  
  function checkbox(id) {
	  var checked = 'InActive';
	  var value = '0';
	  if (document.querySelector('#opt1:checked')) {
		  checked= 'Active';
	      value = '1';
	  }
	  document.getElementById('msg').innerText = checked;
	  var settings = {
	 	 "url": "/api/updateReviewsByStatus/"+id+"/"+value,
	 	 "method": "POST",
	 	 "timeout": 0,
	  };
	  $.ajax(settings).done(function (response) {
		 // console.log(response);
	  });
	}
</script>
    
  </body>
</html>