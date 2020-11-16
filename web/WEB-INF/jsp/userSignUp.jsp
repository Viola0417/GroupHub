<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Sign Up</title>
</head>
<body>

<form action="/user/toUserIndex" method="post">
    <button type="submit">return</button>
</form>

<script type="text/javascript">
    function validateFormNotNull() {
        var userName = document.forms["signUpForm"]["userName"].value;
        var userPassword = document.forms["signUpForm"]["userPassword"].value;
        var email = document.forms["signUpForm"]["email"].value;
        if (userName == null || userName == "") {
            alert("username can not be empty!");
            return false;
        } else if (userPassword == null || userPassword == "") {
            alert("password can not be empty!");
            return false;
        } else if (email == null || email == "") {
            alert("email can not be empty!");
            return false;
        }
        return true;
    }
</script>

<div>
    <form name="signUpForm" action="${pageContext.request.contextPath}/user/signUp" method="post" onsubmit="return validateFormNotNull()">
        UserName: <input type="text" id="userName" name="userName" placeholder="username"><br><br>
        Password:<input type="password" id="userPassword" name="userPassword" placeholder="password"><br><br>
        Email:<input type="email" id="email" name="email" placeholder="email"><br><br>
        <button type="submit">Sign up</button><br>
        <span style="color:red;font-weight: bold">${errorMsg}</span>
        <a href="${pageContext.request.contextPath}/user/toUserIndex" type="hidden">${successMsg}</a>
    </form>
</div>


</body>
</html>
