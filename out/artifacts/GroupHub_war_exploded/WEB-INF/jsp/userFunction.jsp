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

<form action="/movie/toUserMovie" method="post">
    <button type="submit">Movie</button>
</form>

<form action="/book/toUserBook" method="post">
    <button type="submit">Book</button>
</form>

</body>
</html>
