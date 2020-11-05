<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Function Page</title>
</head>
<body>
Hi ${adminName}!<br><br><br>

<form action="/admin/toAdminModifyPassword" method="post">
    <input type="hidden" name="adminName" value="${currentAdminName}">
    <button type="submit">Modify Password</button>
</form>

</body>
</html>
