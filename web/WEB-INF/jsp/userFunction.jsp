<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Function Page</title>
</head>
<body>
Hi ${userName}!

<form action="/user/toUserModifyPassword" method="post">
    <button type="submit">Modify Password</button>
</form>

</body>
</html>
