<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta id="viewport" name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
    <title>Add Category</title>
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
	                      <a href="/admin/getAllCategory" class="btn btn-primary user">All Category Details</a>
	                     </div>
                       </div>
                    </div>
                    <div class="card border border-light-subtle rounded-3 shadow-sm">
                    <div class="card-body p-3 p-md-4">
                       
                       <!-- start -->
                      <form action="/admin/addCategory" method="post" enctype="multipart/form-data">
                       <div class="row">
    					<div class="col">
	                        <input type="text" name="metaTitle" class="form-control" placeholder="Enter Title*" required="required">
	                    </div>
	                    <div class="col">
	                       <input type="text" name="metaDescription" class="form-control" placeholder="Enter description*" required="required">
	                    </div>
	                    <div class="col">
	                       <input type="text" name="metaKeywords" class="form-control" placeholder="Enter keywords*" required="required">
	                    </div>
                       </div>
                       
                       <div class="row">
    					<div class="col">
	                       <select name="categoryName" class="form-control" required="required">
							  <option value="" disabled selected>Select Category</option>
							  <c:forEach var="data" items="${infoCategory}">
							    <option id="${data.url}" value="${data.name}">${data.name}</option>
							  </c:forEach> 
							</select>
 	                    </div>
	                    <div class="col">
						  <input type="text" id="titleUrl" name="categoryUrl" class="form-control" placeholder="Enter categoryUrl*" required="required">
	                    </div>
	                    <div class="col">
	                       <input type="text" name="categoryType" class="form-control" placeholder="Enter Category Type">
	                    </div>
                       </div>
                       
                       <div class="row">
                           <div class="form-outline col-md-12 mb-3">
                               <textarea id="content" name="content" class="form-control form-control-lg" required="required"></textarea>
                           </div>
                        </div>
                       <div class="row">
    					<div class="col">
	                       <input type="file" name="file" class="form-control">
	                    </div>
	                    <div class="col">
	                        <input type="text" name="createdBy" class="form-control" placeholder="CreatedBy*" required="required">
	                    </div>
                       </div>
                        <button type="submit" class="btn btn-primary user">Submit</button>
                        </form>
                      <!-- end -->
                       
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
        </div>
      </div>
    </div>
    <script>
  document.addEventListener("DOMContentLoaded", function() {
    const categorySelect = document.querySelector("select[name='categoryName']");
    const titleUrlInput = document.getElementById("titleUrl");

    categorySelect.addEventListener("change", function() {
      const selectedOption = categorySelect.options[categorySelect.selectedIndex];
      titleUrlInput.value = selectedOption.id || ''; // If no id, clear the input
    });
  });
</script>
    <script src="/resources/admin/js/script.js"></script>
    <script>CKEDITOR.replace( 'content' );</script>
  </body>
</html>