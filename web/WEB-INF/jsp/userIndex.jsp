<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GroupHub Index Page</title>
</head>
<body>
    <script type="text/javascript">
        function validateFormNotNull() {
            var userName = document.forms["loginForm"]["userName"].value;
            var userPassword = document.forms["loginForm"]["userPassword"].value;
            if (userName == null || userName == "") {
                alert("username can not be empty!");
                return false;
            } else if (userPassword == null || userPassword == "") {
                alert("password can not be empty!");
                return false;
            }
            return true;
        }
    </script>

Welcome to GroupHub!

<!--Sign In Form-->
<form name="loginForm" action="${pageContext.request.contextPath}/user/login" method="post" onsubmit="return validateFormNotNull()">
    <input type="text" id="userName" name="userName" placeholder="username">
    <input type="password" id="userPassword" name="userPassword" placeholder="password">
    <button type="submit">Log in</button>
    <span style="color:red;font-weight: bold">${error}</span>
</form>

    Have no account now? <a href="${pageContext.request.contextPath}/user/toUserSignUp">Sign up</a> first!
<h3>
    <a href="${pageContext.request.contextPath}/admin/toAdminLogIn">admin</a>
</h3>
</body>
</html>
