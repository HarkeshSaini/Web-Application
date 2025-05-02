<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="dateValue" class="java.util.Date"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>404 Not Found</title>
  <style>
     * { margin: 0; padding: 0; box-sizing: border-box; } 
     body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f5f5f5; color: #333; display: flex; align-items: center; justify-content: center; height: 100vh; text-align: center; }
    .container { padding: 20px; } .error-code { font-size: 10rem; font-weight: bold; color: #e74c3c; }
    .error-message { font-size: 2rem; margin-top: -20px; } .error-description { font-size: 1.1rem; margin: 20px 0; color: #666; } 
    .home-button { display: inline-block; padding: 12px 24px; background-color: #3498db; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s; }
    .home-button:hover { background-color: #2980b9; }
  </style>
</head>
<body>
  <div class="container">
    <h1 class="error-code">404</h1>
    <jsp:setProperty name="dateValue" property="time" value="${timestamp}"/>
    
    <p class="error-message">${message}<p>
    <p class="error-description">Sorry, the page you're looking for doesn't exist or has been moved.</p>
    <p><fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm"/></p>
    <a href="/" class="home-button">Go to Homepage</a>
  </div>
</body>
</html>
