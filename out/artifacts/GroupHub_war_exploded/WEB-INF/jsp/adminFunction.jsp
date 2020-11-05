<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Function Page</title>
</head>
<body>
Hi ${adminName}!<br><br><br>

<form action="/admin/toAdminModifyPassword" method="post">
    <button type="submit">Modify Password</button>
</form>

<form action="/admin/toAdminAddGroup" method="post">
    <button type="submit">Add Group</button>
</form>

</body>
</html>
