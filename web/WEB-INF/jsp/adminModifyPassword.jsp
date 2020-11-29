<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Modify Password</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/bulma@0.9.0/css/bulma.min.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    <style>

        .navbar {
            background: #00aeef;
        }
        .navbar .HeaderTitle {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            font-size: 23px;
            line-height: 22px;
            font-weight: 600;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
            display: flex;
            align-items:center;
            color: #fff;
        }

        .navbar .ArrowBack {
            cursor: pointer;
            color: #fff;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            align-items:center;
        }

        .review {
            padding: 0 12rem;
            display: flex;
            justify-content: center;
        }

        .reviewInput {
            border: 2px solid #00aeef;
            border-radius: 7px;
        }

        .kt-button {
            background-color: #00aeef;
            border: none;
            color: white;
            padding: 3px 18px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 5px 3px;
            cursor: pointer;
            border-radius: 7px;
            align-content: center;
            justify-content: center;
            font-weight: 650
        }

        .kt-button:hover {
            background-color: #00a0da;
        }
    </style>
</head>
<body>

<div>

    <div class="limiter">
        <nav class="navbar">
            <div class="container">
                <div class="navbar-brand"><a href="${pageContext.request.contextPath}/admin/toAdminFunction" class="ArrowBack">
                    <svg viewBox="0 0 27 27" width="33px" height="33px">
                        <path fill="white" d="M20 11H7.83l5.59-5.59L12 4l-8 8 8 8 1.41-1.41L7.83 13H20v-2z">
                        </path>
                    </svg></a></div>
                <div class="navbar-brand">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span class="HeaderTitle">GroupHub</span>
                    </span>
                </div>
            </div>
        </nav>
    </div><br><br><br>

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
        <div class="container">
            <div class="box content">
                <div class="review">
                    <form name="modifyPasswordForm" action="${pageContext.request.contextPath}/admin/modifyPassword" method="post"
                          onsubmit="return validateFormNotNull()">
                        <input type="hidden" name="adminName" value="${adminName}">
                        <input class="reviewInput" type="password" id="originPassword" name="originPassword" placeholder="origin password"><br><br>
                        <input class="reviewInput" type="password" id="newPassword" name="newPassword" placeholder="new password"><br><br>
                        <div style="display: flex; justify-content: center">
                            <button class="kt-button" type="submit">submit</button>
                        </div>
                        <br><span style="color:red;font-weight: bold">${modifyError}</span>
                    </form>
                </div>
                <a href="${pageContext.request.contextPath}/admin/toAdminFunction" type="hidden">${returnMsg}</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
