<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Modify Password</title>
</head>
<body>

<div>
    <!--check password-->
    <script type="text/javascript">
        function validateFormNotNull() {
            var originPassword = document.forms["modifyPasswordForm"]["originPassword"].value;
            var newPassword = document.forms["modifyPasswordForm"]["newPassword"].value;
            if (originPassword == null || originPassword == "") {
                alert("original password can not be empty!");
                return false;
            } else if (newPassword == null || newPassword == "") {
                alert("new password can not be empty!");
                return false;
            } else if (newPassword == originPassword) {
                alert("your new password is the same as the original one!");
                return false;
            }
            return true;
        }
    </script>

    <!--Modify Password Form-->
    <form name="modifyPasswordForm" action="${pageContext.request.contextPath}/admin/modifyPassword" method="post"
          onsubmit="return validateFormNotNull()">
        <input type="hidden" name="adminName" value="${adminName}">
        <input type="password" id="originPassword" name="originPassword" placeholder="origin password">
        <input type="password" id="newPassword" name="newPassword" placeholder="new password">
        <button type="submit">submit</button>
        <br><span style="color:red;font-weight: bold">${modifyError}</span>
    </form>

    <a href="${pageContext.request.contextPath}/admin/toAdminFunction" type="hidden">${returnMsg}</a>
</div>

</body>
</html>
