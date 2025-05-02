<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% String roleUser = (String) session.getAttribute("role"); %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta id="viewport" name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
    <title>All Subscribe Information</title>
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
	                     <a href="/admin/showAllSubscribeInfo" class="btn btn-primary user">Subscribe Info</a>
	                     </div>
                       </div>
                    </div>
                    <div class="card border border-light-subtle rounded-3 shadow-sm">
                    <div class="card-body p-3 p-md-4">
                      <table id="myTable" class="display">
                        <thead>
                          <tr>
                            <th>User_Id</th>
                            <th>Email</th>
                            <th>Date</th>
                            <th>Status</th>
                            <c:if test="${userRole == 'ADMIN'}">
                             <th>Delete</th>
                            </c:if>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="data" items="${subscribeInfo}">
                            <tr>
                              <td>${data.id}</td>
                              <td>${data.email}</td>
                              <td>${data.postTime}</td>
                              <c:if test="${data.status=='Active'}">
                                <td><input type="checkbox" checked="checked" onclick="checkbox('${data.id}')" id="opt1"> <span id="msg">${data.status}</span></td>
                              </c:if>
                              <c:if test="${data.status=='InActive'}">
                              	<td><input type="checkbox" onclick="checkbox('${data.id}')" id="opt1"> <span id="msg">${data.status}</span></td>
                              </c:if>
                              <c:if test="${userRole=='ADMIN'}">
                               <td><a href="/admin/deleteSubscribeInfo/${data.id}"><i class="fa-solid fa-trash"></i></a></td>
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
        </div>
      </div>
    </div>
    <script type="text/javascript"> 
    $(document).ready( function () {
        $("#myTable").DataTable();
    } );
  
    function checkbox(id) {
	  var checked = 'InActive';
	  var value = '0';
	  if (document.querySelector('#opt1:checked')) {
		  checked= 'Active';
	      value = '1';
	  }
	  document.getElementById('msg').innerText = checked;
	  var settings = {
	 	 "url": "/api/updateStatusOfSubscribeInfoByStatus/"+id+"/"+value,
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