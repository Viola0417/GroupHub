<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Travel Rate</title>
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

<form action="/travel/toTravel" method="post">
    <button type="submit">return</button>
</form>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>${travelTitle}</small>
                </h1>

                ${travelReviews}<br>
                ${travelScore}
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <c:forEach var="rate" items="${requestScope.get('travelRateList')}">
            <div class="block">
                    ${rate.getRateScore()}/5<br>
                <b>${rate.getRateTitle()}</b><br>
                    ${rate.getRateAuthor()}&nbsp;&nbsp;&nbsp;&nbsp;${rate.getRateCreateTime()}<br>

                <div style="overflow:hidden;">
                        ${rate.getRateContent()}<br>
                </div>
                <a href="${pageContext.request.contextPath}/rate/deleteTravelRate?rateId=${rate.getRateId()}">Delete Rate</a>
                &nbsp;&nbsp;

                <a href="${pageContext.request.contextPath}/comment/showTravelCommentToAdmin?rateId=${rate.getRateId()}">
                    Comment(${rate.getRateTotalReply()})
                </a>

                &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/rate/toDeleteTravelRate?travelId=${rate.getRateCategoryId()}">
                    Close
                </a>
            </div>

            <c:if test="${rootRateId eq rate.getRateId()}">
                <c:forEach items="${commentHashMap}" var="topComment">
                    <b>${topComment.key.commentAuthor}</b>&nbsp;&nbsp;${topComment.key.commentCreateTime}
                    &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/comment/adminDeleteTravelComment?commentId=${topComment.key.getCommentId()}">Delete</a>
                    <br>
                    ${topComment.key.commentContent}
                    <br>
                    <c:forEach items="${topComment.value}" var="replyComment">
                        &nbsp;&nbsp;&nbsp;<b>${replyComment.commentAuthor}</b>&nbsp;&nbsp;${replyComment.commentCreateTime}
                        &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/comment/adminDeleteTravelComment?commentId=${replyComment.getCommentId()}">Delete</a>
                        <br>
                        &nbsp;&nbsp;&nbsp;${replyComment.commentContent}<br>
                    </c:forEach>
                    <br>
                </c:forEach>
            </c:if>
            <br>
        </c:forEach>
        <span style="color:blue;font-weight: bold">${delTravelRateMsg}</span>
    </div>
</div>
</body>
</html>
