<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
   <head>
      <meta http-equiv="content-type" content="text/html;charset=utf-8" />
      <meta id="viewport" name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
      <title>!Welcome Admin Dashboard</title>
   </head>
   <body>
      <div class="container-fluid">
         <div class="row flex-nowrap">
            <jsp:include page="sideBar.jsp"></jsp:include>
            <div class="col py-3">
               <section class="bg-light py-3 py-md-5">
                  <div class="container">
                     <div class="row justify-content-center">
                        <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5 col-xxl-4">
                           <div class="card border border-light-subtle rounded-3 shadow-sm">
                              <div class="card-body p-3 p-md-4 p-xl-5">
                                 <div class="text-center mb-3">
                                 </div>
                                 <h2 class="fs-6 fw-normal text-center text-secondary mb-4 heading">${message}</h2>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </section>
            </div>
         </div>
      </div>
   </body>
</html>