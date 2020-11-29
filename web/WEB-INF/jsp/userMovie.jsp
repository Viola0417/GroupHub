<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Movie Index Page</title>
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

    .navbar-menu .navbar-item {
        padding: 0 2rem;
    }
    aside.menu {
        padding-top: 3rem;
    }
    aside.menu .menu-list {
        line-height: 1.5;
    }
    aside.menu .menu-label {
        padding-left: 10px;
        font-weight: 700;
    }

    .search-box-wrapper {
        display: inline-flex;
        float: right;
        font-size: 20px;
    }

    .search-box-input {
        font-size: inherit;
        border: 0.2em solid#4CE1AF;
        border-radius: 0.5em 0 0 0.5em;
        padding: 0.2em 0.5em;
        outline: 0;
    }

    .search-box-input:hover,
    .search-box-input:focus {
        border-color: #4CE1AF;
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
    .search-box-button {
        font-size: inherit;
        border: 0.2em solid #4CE1AF;
        border-radius: 0 0.5em 0.5em 0;
        background-color: #4CE1AF;
        border-left: 0;
        padding: 0 0.75em;
        color: white;
        font-weight: bold;
        outline: 0;
        cursor: pointer;
    }

    .search-box-button:hover,
    .search-box-button:focus {
        border-color: #4CE1AF;
        background-color: #4CE1AF;
    }

    .kt-button {
        display: inline-block;
        font-weight: bold;
        line-height: normal;
        text-align: center;
        vertical-align: middle;
        cursor: pointer;
        width: 100%;
        font-size: inherit;
        background-color: #00aeef;
        color: white;
        -webkit-transition: all 0.35s ease 0s;
        transition: all 0.35s ease 0s;
        text-decoration: none;
        padding: 14px 18px;
        text-transform: uppercase;
        border: 0.2em;
        border-radius: 0.5em 0.5em 0.5em 0.5em;
    }

    .kt-button:hover {
        background-color: #00a0da;
    }

    .limiter {
        width: 100%;
        margin: 0 auto;
    }

    .container-table100 {
        width: 100%;
        min-height: 100vh;

        display: -webkit-box;
        display: -webkit-flex;
        display: -moz-box;
        display: -ms-flexbox;
        display: flex;
        flex-wrap: wrap;
        padding: 33px 30px;
    }

    .wrap-table100 {
        width: 1300px;
    }

    table {
        width: 100%;
        background-color: #fff;
    }

    th, td {
        font-weight: unset;
    }

    .column100 {
        width: 130px;
        text-align:center;
    }

    .column100.column1 {
        width: 200px;
        text-align:center;
    }
    .rate {
        width: 200px;
        text-align:center;
    }

    .rate:hover {
        background-color: #09223E;
        color: #fff;
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

    .table100.ver1 .row100:hover {
        background-color: #f2f2f2;
    }

    .table100.ver1 .hov-column-ver1 {
        background-color: #f2f2f2;
    }

    .table100.ver1 .hov-column-head-ver1 {
        background-color: #484848 !important;
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
        <div class="navbar-brand"><a href="${pageContext.request.contextPath}/user/toUserFunction" class="ArrowBack">
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

<nav class="navbar is-white">
    <div class="container">
        <div class="navbar-menu">
            <div class="navbar-start">
                <h1 class="title">
                    Movie List
                </h1>
            </div>

            <div class="navbar-end">
                <div class="navbar-item">
                    <form action="${pageContext.request.contextPath}/movie/userQueryMovie" method="post" class="search-box-wrapper">
                        <input type="text" name="queryMovieName" class="search-box-input" placeholder="Movie Name">
                        <button class="search-box-button">Search</button>
                    </form>
                </div>
                <div>
                    <h2>
                        <a href="${pageContext.request.contextPath}/request/toAddRequest" title="Could not find your interest group? Request here!" class="kt-button">Request</a>
                    </h2>
                </div>
            </div>
        </div>
    </div>
</nav>



    <div class="container-table100">
        <div class="wrap-table100">
            <div class="table100 ver1 m-b-110">
                <table data-vertable="ver1">
                    <thead>
                    <tr class="row100 head">
                        <th class="column100 column3">Movie Name</th>
                        <th class="column100 column2">Year</th>
                        <th class="column100 column1">Description</th>
                        <th class="column100 column4">Review Number</th>
                        <th class="column100 column5">Rate Score</th>
                        <th></th>
                    </tr>
                    </thead>

                    <tbody class="row100">
                    <c:forEach var="movie" items="${requestScope.get('movieList')}">
                        <tr>
                            <td class="column100 column3">${movie.getMovieName()}</td>
                            <td class="column100 column2">${movie.getMovieYear()}</td>
                            <td class="column100 column1">${movie.getDescription()}</td>
                            <td class="column100 column4">${movie.getTotalRateNumber()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${movie.getTotalRateScore()==0}">
                                        no rate
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatNumber type="number" value="${movie.getTotalRateScore()/movie.getTotalRateNumber()}" maxFractionDigits="2"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="rate">
                                <a href="${pageContext.request.contextPath}/rate/toMovieRate?movieId=${movie.getMovieId()}">Rate</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
    </div>
</body>
</html>
