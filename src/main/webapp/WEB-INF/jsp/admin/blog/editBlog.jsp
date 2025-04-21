<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta id="viewport" name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
    <title>Update Blog</title>
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
	                      <a href="/admin/getAllBlog" class="btn btn-primary user">All Blog</a>
	                     </div>
                       </div>
                    </div>
                    <div class="card border border-light-subtle rounded-3 shadow-sm">
                    <div class="card-body p-3 p-md-4">
                       
                       <!-- start -->
                      <form:form action="/admin/editBlogInfo/${id}" method="post" enctype="multipart/form-data">
                       <div class="row">
    					<div class="col">
	                        <form:input type="text" path="title" class="form-control" placeholder="Enter blog Title*" required="required"/>
	                    </div>
	                    <div class="col">
	                       <form:input type="text" path="description" class="form-control" placeholder="Enter blog description*" required="required"/>
	                    </div>
	                    <div class="col">
	                       <form:input type="text" path="keywords" class="form-control" placeholder="Enter blog keywords*" required="required"/>
	                    </div>
                       </div>
                       
                       <div class="row">
    					<div class="col">
	                        <form:input type="text" path="heading" class="form-control" placeholder="Enter blog Heading*" required="required"/>
	                    </div>
	                    <div class="col">
	                       <form:input type="text" id="titleUrl" path="titleUrl" class="form-control" placeholder="Enter blog pageUrl*" required="required"/>
	                    </div>
	                    <div class="col">
	                       <form:input type="text" path="extarTag" class="form-control" placeholder="Enter blog Extar Key world"/>
	                    </div>
                       </div>
                       
                       <div class="row">
                           <div class="form-outline col-md-12 mb-3">
                               <form:textarea id="content" path="content" class="form-control form-control-lg" required="required"/> 
                           </div>
                        </div>
                       <div class="row">
    					<div class="col">
	                       <input type="file" name="file" class="form-control"/>
	                    </div>
	                       
	                     <c:if test="${not empty command.imgUrl}">
		                    <div class="col">
		                       <input type="text"  value="${command.imgUrl}" class="form-control"/>
		                    </div>
	                     </c:if> 
	                    
	                    <div class="col">
	                       <form:select path="status" class="form-control" required="required">
	                       	 <form:option value="Active">Active</form:option>
	                       	 <form:option value="InActive">InActive</form:option>
	                       </form:select>
 	                    </div>
	                    
	                     
                       </div>
                       <div class="row">
	                    <div class="col">
	                        <form:input type="number" path="tfnHeader" class="form-control" placeholder="Enter blog tfnHeader"/>
	                    </div>
	                    <div class="col">
	                       <form:input type="number" path="tfnFooter" class="form-control" placeholder="Enter blog tfnFooter"/>
	                    </div> 
	                    <div class="col">
	                       <form:input type="number" path="tfnPopup" class="form-control" placeholder="Enter blog tfnPopup"/>
	                    </div> 
	                     
                       </div>
                        <button type="submit" class="btn btn-primary user">Submit</button>
                        </form:form>
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
    <script src="/resources/admin/js/script.js"></script>
    <script>CKEDITOR.replace( 'content' );</script>
  </body>
</html>