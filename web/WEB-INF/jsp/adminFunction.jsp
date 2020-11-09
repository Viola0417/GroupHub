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

<form action="/movie/toMovie" method="post">
    <button type="submit">Movie</button>
</form>

<form action="/admin/toBook" method="post">
    <button type="submit">Book</button>
</form>

<form action="/admin/toTravel" method="post">
    <button type="submit">Travel</button>
</form>

</body>
</html>
