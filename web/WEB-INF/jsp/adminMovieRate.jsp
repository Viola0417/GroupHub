<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie Rate</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .block {
            background-color: lightgray;
        }
    </style>
</head>
<body>

    <form action="/movie/toMovie" method="post">
        <button type="submit">return</button>
    </form>

    <div class="container">

        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>${movieTitle}</small>
                    </h1>

                    ${movieReviews}<br>
                    ${movieScore}
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <c:forEach var="rate" items="${requestScope.get('movieRateList')}">
                <div class="block">
                        ${rate.getRateScore()}/5<br>
                    <b>${rate.getRateTitle()}</b><br>
                        ${rate.getRateAuthor()}&nbsp;&nbsp;&nbsp;&nbsp;${rate.getRateCreateTime()}<br>

                    <div style="overflow:hidden;">
                            ${rate.getRateContent()}<br>
                    </div>
                            <a href="${pageContext.request.contextPath}/rate/deleteMovieRate?rateId=${rate.getRateId()}">Delete Rate</a>
                </div>
                <br>
            </c:forEach>
            <span style="color:blue;font-weight: bold">${delMovieRateMsg}</span>
        </div>
    </div>
</body>
</html>
