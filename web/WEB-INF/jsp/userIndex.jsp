<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>GroupHub Index Page</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/WEB-INF/shared/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/WEB-INF/shared/additional.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/WEB-INF/css/userLogin.css">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">

    <style>

        .Header {
            position: fixed;
            width: 100%;
            top: 0px;
            left: 0px;
            right: 0px;
            z-index: 11;
            background: #00aeef;
        }

        .Header .HeaderContainer {
            max-width: 480px;
            width: 100%;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            height: 60px;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: justify;
            -ms-flex-pack: justify;
            justify-content: space-between;
            margin: 0px auto;
            padding: 0px 1em;
        }

        .Header .ArrowBack {
            cursor: pointer;
            color: #fff;
            margin-right: 20px;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
        }

        .Header .HeaderTitle {
            font-size: 16px;
            line-height: 22px;
            font-weight: 600;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
            -webkit-box-flex: 1;
            -ms-flex: 1 1 0px;
            flex: 1 1 0;
            color: #fff;
        }

        .Layout {
            background-color: #fff;
            position: relative;
            max-width: 480px;
            width: 100%;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
            min-height: 100vh;
            margin: 0px auto;
            color: #333;
        }

        .Layout .LayoutContainer {
            width: 100%;
            padding: 70px 16px;
            margin-right: auto;
            margin-left: auto;
        }

        .title {
            text-align: center;
            font-size: 20px;
            margin: 16px 0;
            font-weight: 400;
            color: #434343;
        }

        .form-group {
            margin-bottom: .75em;
            display: block;
        }

        .form-control {
            display: block;
            width: 100%;
            height: 45px;
            padding: 9.5px 9.5px 9.5px 15px;
            font-size: 14px;
            font-weight: 500;
            line-height: 24px;
            color: black;
            background-color: #fff;
            background-clip: padding-box;
            border: 1px solid #e8e9eb;
            border-radius: 3px;
            -webkit-transition: all 0.3s ease 0s;
            transition: all 0.3s ease 0s;
            outline: 0;
        }

        .form-control:focus {
            border-color: #00aeef;
        }

        .login-form {
            margin-top: 2em;
        }

        .form-label a {
            display: block;
            margin-bottom: 0.375rem;
            font-weight: 400;
            font-size: 14px;
            color: #00aeef;
            text-decoration: none;
        }

        .kt-button {
            display: inline-block;
            font-weight: 700;
            line-height: normal;
            text-align: center;
            vertical-align: middle;
            cursor: pointer;
            width: 100%;
            font-size: 15px;
            background-color: #00aeef;
            color: white;
            border: 0;
            outline: 0;
            -webkit-transition: all 0.35s ease 0s;
            transition: all 0.35s ease 0s;
            text-decoration: none;
            border-radius: 4px;
            padding: 13px 45px;
            text-transform: uppercase;
        }

        .kt-button:hover {
            background-color: #00a0da;
        }

        .register-block {
            color: #4d4d4d;
            font-size: 15px;
            font-weight: 400;
            line-height: 1.5;
            text-align: center;
            margin: 1em 0;
        }

        .register-block a {
            text-align: center;
            color: #00aeef;
            cursor: pointer;
            text-decoration: none;
            -webkit-transition: all 0.35s ease 0s;
            transition: all 0.35s ease 0s;
        }

        .divider {
            margin: 0px -16px;
            border: 0;
            border-top: 7px solid #f7f7f7;
        }

        .mt-15 {
            margin-top: 15px;
        }

        .mx-0 {
            margin-left: 0;
            margin-right: 0;
        }

        .sosmed-btn {
            display: inline-block;
            font-weight: 700;
            line-height: normal;
            text-align: center;
            vertical-align: middle;
            cursor: pointer;
            width: 100%;
            font-size: 1em;
            border: 0;
            -webkit-transition: all 0.35s ease 0s;
            transition: all 0.35s ease 0s;
            text-decoration: none;
            border-radius: 4px;
            padding: 11px 37.5px;
            outline: 0;
        }
    </style>

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
</head>

<body>
<!--Sign In Form-->
<div class="app">

    <header class="Header">
        <div class="HeaderContainer">
            <span class="HeaderTitle">GroupHub</span>
        </div>
    </header>

    <main class="Layout">
        <div class="LayoutContainer">
            <h3 class="title">Welcome to GroupHub!</h3>

            <form class="login-form" name="loginForm" action="${pageContext.request.contextPath}/user/login" method="post" onsubmit="return validateFormNotNull()">
                <div class="form-group">
                    <input class="form-control" type="text" id="userName" name="userName" placeholder="Username"></div>
                <div class="form-group">
                    <div class="input-icon">
                    <input class="form-control" type="password" id="userPassword" name="userPassword" placeholder="Password"></div>
                </div>
                <div class="form-group">
                    <div class="form-label">
                        <a href="${pageContext.request.contextPath}/admin/toAdminLogIn">Admin Log in</a>
                    </div>
                </div>
                <button class="kt-button" align="center" type="submit">Sign in</button>
            <span style="color:red;font-weight: bold">${error}</span>
            </form>

            <p class="register-block"> Have no account now? <a href="${pageContext.request.contextPath}/user/toUserSignUp">Sign up</a> first! </p>

        </div>
    </main>
</div>
</body>
</html>
