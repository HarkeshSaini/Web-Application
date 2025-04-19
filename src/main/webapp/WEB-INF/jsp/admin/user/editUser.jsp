<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	                      <a href="/admin/getAllUser" class="btn btn-primary user">All Users</a>
	                     </div>
                       </div>
                    </div>
                    <div class="card border border-light-subtle rounded-3 shadow-sm">
                    <div class="card-body p-3 p-md-4">
                       
                       <!-- start -->
                      <form:form action="/admin/editUserInfo/${id}" method="post" enctype="multipart/form-data">
                       <div class="row">
    					<div class="col">
	                        <form:input type="text" path="name" class="form-control" placeholder="Enter full name*" required="required"/>
	                    </div>
	                    <div class="col">
	                       <form:input type="email" path="email" class="form-control" placeholder="Enter email addrass*" required="required"/>
	                    </div>
	                    <div class="col">
	                       <form:input type="number" path="phone" class="form-control" placeholder="Enter mobile no*" required="required"/>
	                    </div>
                       </div>
                       <div class="row">
    					<div class="col">
	                       <input type="file" name="file" class="form-control" required="required">
	                    </div>
	                    <c:if test="${not empty command.imgUrl}">
		                    <div class="col">
		                       <form:input type="text"  path="imgUrl" class="form-control"/>
		                    </div>
	                    </c:if>  
	                    <div class="col">
	                       <form:select path="age" class="form-control" required="required">
	                        <c:forEach var="data" begin="1" end="60">
	                       		<form:option value="${data}">${data}</form:option>
	                        </c:forEach>
	                       </form:select>
 	                    </div>
                       </div>
                       <div class="row">
                           <div class="form-outline col-md-12 mb-3">
                               <form:textarea id="content" path="comment" class="form-control form-control-lg" required="required"></form:textarea>
                           </div>
                        </div>
                         <div class="row">
    					<div class="col">
	                        <form:input type="text" path="password" class="form-control" placeholder="Enter password*" required="required"/>
	                    </div>
	                     
	                    <div class="col">
	                       <form:select path="status" class="form-control" required="required">
	                       	 <form:option value="Active">Active</form:option>
	                       	 <form:option value="InActive">InActive</form:option>
	                       </form:select>
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
    <script>CKEDITOR.replace( 'content' );</script>
  </body>
</html>