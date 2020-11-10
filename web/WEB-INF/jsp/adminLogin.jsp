<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Log in</title>
</head>
<body>
<h2>Administrator Log in</h2>
<div>
    <script type="text/javascript">
        function validateFormNotNull() {
            var adminName = document.forms["loginForm"]["adminName"].value;
            var adminPassword = document.forms["loginForm"]["adminPassword"].value;
            if (adminName == null || adminName == "") {
                alert("Admin username can not be empty!");
                return false;
            } else if (adminPassword == null || adminPassword == "") {
                alert("Admin password can not be empty!");
                return false;
            }
            return true;
        }
    </script>

    <!--Sign In Form-->
    <form name="loginForm" action="${pageContext.request.contextPath}/admin/login" method="post" onsubmit="return validateFormNotNull()">
        <input type="text" id="adminName" name="adminName" placeholder="username">
        <input type="password" id="adminPassword" name="adminPassword" placeholder="password">
        <button type="submit">Log in</button>
    </form>
    <span style="color:#ff0000;font-weight: bold">${error}</span>
</div>
</body>
</html>
