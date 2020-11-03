<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GroupHub Index Page</title>
</head>
<body>
Welcome to GroupHub!

<!--Sign In Form-->
<form action="" method="post">
    <input type="text" id="userName" name="userName" placeholder="username">
    <input type="password" id="userPassword" name="userPassword" placeholder="password">
    <button type="submit">Log in</button>
</form>

<h3>
    <a href="${pageContext.request.contextPath}/admin/toAdminLogIn">admin</a>
</h3>
</body>
</html>
