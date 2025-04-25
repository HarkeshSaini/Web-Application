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
	                    <div class="col link">
	                     <a href="/admin/addCategory" class="btn btn-primary user">Add Category</a>
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
                          <c:forEach var="data" items="${infoRequests}">
                            <tr>
                              <td>${data.categoryName}</td>
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
          </section>
        </div>
      </div>
    </div>
    <script type="text/javascript"> 
      $(document).ready( function () {
          $("#myTable").DataTable();
      } );
    </script>
  </body>
</html>