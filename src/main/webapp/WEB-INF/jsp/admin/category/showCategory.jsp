<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% String roleUser = (String) session.getAttribute("role"); %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta id="viewport" name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
    <title>All Category content</title>
  </head>
  <body>
    <div class="container-fluid">
      <div class="row flex-nowrap">
        <jsp:include page="../sideBar.jsp"></jsp:include>
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
	                    <div class="col">
	                      <button type="button" class="btn user" data-bs-toggle="modal" data-bs-target="#staticBackdrop">New Category</button>
	                    </div>
	                    <div class="col link">
	                     <a href="/admin/addCategory" class="btn btn-primary user">Add Category Details</a>
	                     </div>
                       </div>
                    </div>
                    <div class="card border border-light-subtle rounded-3 shadow-sm">
                    <div class="card-body p-3 p-md-4">
                      <table id="myTable" class="display">
                        <thead>
                          <tr>
                            <th>Category Name</th>
                            <th>Category Type</th>
                            <th>Create With</th>
                            <th>Update With</th>
                            <th>Status</th>
                            <th>Edit</th>
                            <c:if test="${userRole == 'ADMIN'}">
                             <th>Delete</th>
                            </c:if>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="data" items="${categoryInfo}">
                            <tr>
                              <td>${data.categoryUrl}</td>
                              <td>${data.categoryType}</td>
                              <td>${data.createdBy}</td>
                              <c:if test="${not empty data.updatedBy}">
                                <td>${data.updatedBy}</td>
                              </c:if>
                              <c:if test="${empty data.updatedBy}">
                                <td>---</td>
                              </c:if>
                              <td>${data.status}</td>
                              <td><a href="/admin/editCategoryInfo/${data.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
                              <c:if test="${userRole=='ADMIN'}">
                               <td><a href="/admin/deleteCategoryInfo/${data.id}"><i class="fa-solid fa-trash"></i></a></td>
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
            <!-- Bootstrap Modal -->
			<div class="modal fade" id="staticBackdrop" tabindex="-1">
			  <div class="modal-dialog modal-dialog-scrollable modal-lg">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5">Submit new category</h1>
			        <div class="response" id="successResponse"></div>
			        <button type="button" class="btn-close close" data-bs-dismiss="modal"></button>
			      </div>
			      <div class="modal-body">
			        <div class="container">
			        <div class="card border border-light-subtle rounded-3 shadow-sm messages">
	                 <!-- Nav tabs -->
					<ul class="nav nav-tabs" id="myTab" role="tablist">
					  <li class="nav-item" role="presentation"><button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">All Category</button></li>
					  <li class="nav-item" role="presentation"><button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Add Category</button></li>
					  <c:if test="${userRole == 'ADMIN'}">
					   <li class="nav-item" role="presentation"><button class="nav-link" id="delete-tab" data-bs-toggle="tab" data-bs-target="#delete-tab-pane" type="button" role="tab" aria-controls="delete-tab-pane" aria-selected="false">Delete Category</button></li>
					  </c:if>
					</ul>
					<div class="tab-content mt-3" id="myTabContent">
					  <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab">
					   <table id="myTable_cat" class="display category">
                        <thead>
                          <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Url</th>
                            <th>Status</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="data" items="${infoCategory}">
                            <tr>
                              <td>${data.id}</td>
                              <td>${data.name}</td>
                              <td>${data.url}</td>
                              <td>${data.status}</td>
                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
					  </div>
					  <div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab">
					    <form class="category">
		                  <div class="row">
	    					<div class="col">
	    					  <input type="text" name="name" class="form-control" placeholder="Enter Category Name*" required="required">
				        	</div>
				        	<div class="col">
	    					  <input type="text" id="titleUrl" name="url" class="form-control" placeholder="Enter Category Url"required="required">
				        	</div>
				        	<div class="col-2">
					          <button type="button" id="category_sub" class="btn user">Submit</button>
					        </div>
					      </div>
			           </form>
					  </div>
					  <div class="tab-pane fade" id="delete-tab-pane" role="tabpanel" aria-labelledby="delete-tab">
					    <form class="category delete">
		                  <div class="row">
	    					<div class="col">
	    					  <input type="text" name="id" class="form-control" placeholder="Enter Category id for permanently delete" required="required">
				        	</div>
				        	<div class="col-2">
					          <button type="button" id="category_delete" class="btn user">Submit</button>
					        </div>
					      </div>
			           </form>
					  </div>
					</div>
			         </div>
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
          $("#myTable_cat").DataTable();
      } );
</script>
<script src="/resources/admin/js/script.js"></script>
  </body>
</html>