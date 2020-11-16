<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Book Post</title>
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

<form action="/book/toUserBook" method="post">
    <button type="submit">return</button>
</form>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>${bookTitle}</small>
                </h1>

                <div class="col-md-4 column">
                    <form action="${pageContext.request.contextPath}/rate/toAddBookRate" method="post">
                        <input type="submit" value="Rate" class="btn btn-primary"/>
                    </form>
                </div>

                ${bookReviews}<br>
                ${bookScore}
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <c:forEach var="rate" items="${requestScope.get('rateList')}">
            <div>
                ${rate.getRateScore()}/5<br>
                <b>${rate.getRateTitle()}</b><br>
                ${rate.getRateAuthor()}&nbsp;&nbsp;&nbsp;&nbsp;${rate.getRateCreateTime()}<br>

                <div style="overflow:hidden;">
                        ${rate.getRateContent()}<br>
                </div>
            </div>
            <br>
        </c:forEach>
    </div>
</div>
</body>
</html>
