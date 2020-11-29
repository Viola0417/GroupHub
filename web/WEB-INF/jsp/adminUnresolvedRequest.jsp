<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Unresolved Requests</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <link rel="shortcut icon" href="../images/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    <!-- Bulma Version 0.9.0-->
    <link rel="stylesheet" href="https://unpkg.com/bulma@0.9.0/css/bulma.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/WEB-INF/css/forum.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/WEB-INF/css/searchbox.css">
    <style>
        input::placeholder {
            color: antiquewhite;
        }

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

        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color: #E1DEDE;
        }

        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color: #E1DEDE;
        }

        input:-ms-input-placeholder,
        textarea:-ms-input-placeholder {
            color: #E1DEDE;
        }

        input::-webkit-input-placeholder,
        textarea::-webkit-input-placeholder {
            color: #E1DEDE;
        }

        .limiter {
            width: 100%;
            margin: 0 auto;
        }

        table {
            width: 100%;
            background-color: #fff;
        }

        th, td {
            font-weight: unset;
        }

        .row100.head th {
            padding-top: 24px;
            padding-bottom: 20px;
            text-align:center;
        }

        .row100 td {
            padding-top: 18px;
            padding-bottom: 14px;
        }


        .table100.ver1 td {
            font-family: Montserrat-Regular;
            font-size: 15px;
            color: #808080;
            line-height: 1.4;
            text-align:center;
        }

        .table100.ver1 th {
            font-family: Montserrat-Medium;
            font-size: 17px;
            color: #fff;
            line-height: 1.4;
            text-transform: uppercase;
            text-align:center;
            background-color: #09223E;
        }

        .table100.ver1 .row100 td:hover {
            background-color: #09223E;
            color: #fff;
        }

        .title {
            font-size:35px;
            color: #4CE1AF;
        }
    </style>
</head>
<body>

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

    <c:choose>
        <c:when test="${unresolvedRequestList.size() eq 0}">
            There is no unresolved request!
        </c:when>

        <c:otherwise>
            <div class="container">

                <nav class="navbar is-white">
                    <div class="container">
                        <div class="navbar-menu">
                            <div class="navbar-start">
                                <h1 class="title">
                                    Unresolved Requests
                                </h1>
                            </div>
                        </div>
                    </div>
                </nav>

                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-hover table-striped">
                            <thead class="table100 ver1 m-b-110">
                            <tr>
                                <th>Title</th>
                                <th>Description</th>
                                <th>Time</th>
                                <th>Type</th>
                                <th>Operations</th>
                            </tr>
                            </thead>

                            <tbody >
                            <c:forEach var="request" items="${requestScope.get('unresolvedRequestList')}">
                                <tr style="text-align: center;">
                                    <td>${request.getRequestTitle()}</td>
                                    <td>${request.getRequestDescription()}</td>
                                    <td>${request.getRequestTime()}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${request.getRequestCategoryId() eq 1}">
                                                Movie
                                            </c:when>
                                            <c:when test="${request.getRequestCategoryId() eq 2}">
                                                Book
                                            </c:when>
                                            <c:when test="${request.getRequestCategoryId() eq 3}">
                                                Travel
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${request.getRequestCategoryId() eq 1}">
                                                <a href="${pageContext.request.contextPath}/movie/toAddMovie" >Add </a>
                                            </c:when>
                                            <c:when test="${request.getRequestCategoryId() eq 2}">
                                                <a href="${pageContext.request.contextPath}/book/toAddBook">Add </a>
                                            </c:when>
                                            <c:when test="${request.getRequestCategoryId() eq 3}">
                                                <a href="${pageContext.request.contextPath}/travel/toAddTravel">Add </a>
                                            </c:when>
                                        </c:choose>
                                        &emsp;&emsp;<a href="${pageContext.request.contextPath}/request/markResolved?requestId=${request.getRequestId()}" >Mark Resolved</a><br>
                                        <a href="${pageContext.request.contextPath}/request/markIncorrect?requestId=${request.getRequestId()}" >Mark as incorrect Information</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

    <br><span style="color:blue;font-weight: bold">${successMsg}</span>
</div>