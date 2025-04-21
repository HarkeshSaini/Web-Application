<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta id="viewport" name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
    <title>Add Blog</title>
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
                      <form action="/admin/addBlog" method="post" enctype="multipart/form-data">
                       <div class="row">
    					<div class="col">
	                        <input type="text" name="title" class="form-control" placeholder="Enter blog Title*" required="required">
	                    </div>
	                    <div class="col">
	                       <input type="text" name="description" class="form-control" placeholder="Enter blog description*" required="required">
	                    </div>
	                    <div class="col">
	                       <input type="text" name="keywords" class="form-control" placeholder="Enter blog keywords*" required="required">
	                    </div>
                       </div>
                       
                       <div class="row">
    					<div class="col">
	                        <input type="text" name="heading" class="form-control" placeholder="Enter blog Heading*" required="required">
	                    </div>
	                    <div class="col">
	                       <input type="text" id="titleUrl" name="titleUrl" class="form-control" placeholder="Enter blog pageUrl*" required="required">
	                    </div>
	                    <div class="col">
	                       <input type="text" name="extarTag" class="form-control" placeholder="Enter blog Extar Key world">
	                    </div>
                       </div>
                       
                       <div class="row">
                           <div class="form-outline col-md-12 mb-3">
                               <textarea id="content" name="content" class="form-control form-control-lg" required="required"></textarea>
                           </div>
                        </div>
                       <div class="row">
    					<div class="col">
	                       <input type="file" name="file" class="form-control" required="required">
	                    </div>
	                    <div class="col">
	                        <input type="number" name="tfnHeader" class="form-control" placeholder="Enter blog tfnHeader">
	                    </div>
	                    <div class="col">
	                       <input type="number" name="tfnFooter" class="form-control" placeholder="Enter blog tfnFooter">
	                    </div> 
	                    <div class="col">
	                       <input type="number" name="tfnPopup" class="form-control" placeholder="Enter blog tfnPopup">
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
    <script src="/resources/admin/js/script.js"></script>
    <script>CKEDITOR.replace( 'content' );</script>
  </body>
</html>