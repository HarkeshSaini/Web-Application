<html xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" xmlns:th="http://www.thymeleaf.org" lang="en">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css">
    </head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form id="loginForm" th:action="@{/login}" method="post" onsubmit="return validateForm()">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
            <input type="submit" value="Login">
            <div class="error-message" id="errorMessage"></div>
        </form>
    </div>
    <script src="/resources/js/script.js"></script>
</body>
</html>
