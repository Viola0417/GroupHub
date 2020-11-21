<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Travel Post</title>
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

<script type="text/javascript">
    function validateReplyContentFormNotNull() {
        var replyContent = document.forms["replyForm"]["replyContent"].value;
        if (replyContent== null || replyContent == "") {
            alert("reply content can not be empty!");
            return false;
        }
        return true;
    }
</script>

<form action="/travel/toUserTravel" method="post">
    <button type="submit">return</button>
</form>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>${travelTitle}</small>
                </h1>

                <div class="col-md-4 column">
                    <form action="${pageContext.request.contextPath}/rate/toAddTravelRate" method="post">
                        <input type="submit" value="Rate" class="btn btn-primary"/>
                    </form>
                </div>

                ${travelReviews}<br>
                ${travelScore}
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <br><span style="color:red;font-weight: bold">${errorMsg}</span>
        <c:forEach var="rate" items="${requestScope.get('rateList')}">
            <div class ="block">
                ${rate.getRateScore()}/5<br>
                <b>${rate.getRateTitle()}</b><br>
                ${rate.getRateAuthor()}&nbsp;&nbsp;&nbsp;&nbsp;${rate.getRateCreateTime()}<br>

                <div style="overflow:hidden;">
                        ${rate.getRateContent()}<br>
                </div>

                    <a href="${pageContext.request.contextPath}/comment/showCommentForTravel?rateId=${rate.getRateId()}">
                        Comment(${rate.getRateTotalReply()})
                    </a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/rate/toTravelRate?travelId=${rate.getRateCategoryId()}">
                        Close
                    </a>
            </div>

                <form name="contentForm" action="${pageContext.request.contextPath}/comment/addTopCommentForTravel"
                      method="post">
                    <input type="text" width="400px" name="commentContent" id="commentContent" placeholder="Enter your comment..."/>
                    <input type="hidden" name="commentRateId" value="${rate.getRateId()}"/>
                    &nbsp;&nbsp;&nbsp;
                    <button type="submit">submit</button>
                </form>

                <c:if test="${rootRateId eq rate.getRateId()}">
                    <c:forEach items="${commentHashMap}" var="topComment">
                        <c:set var="hasReply" value="0"/>
                        <c:if test="${commentParentId eq topComment.key.getCommentId()}">
                            <c:set var="hasReply" value="1"/>
                        </c:if>
                        <b>${topComment.key.commentAuthor}</b>&nbsp;&nbsp;${topComment.key.commentCreateTime}
                        &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/comment/toAddReplyForTravel?commentParentId=${topComment.key.getCommentId()}">Reply</a>
                        <br>
                        ${topComment.key.commentContent}
                        <br>
                        <c:forEach items="${topComment.value}" var="replyComment">
                            &nbsp;&nbsp;&nbsp;<b>${replyComment.commentAuthor}</b>&nbsp;&nbsp;${replyComment.commentCreateTime}
                            &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/comment/toAddReplyForTravel?commentParentId=${replyComment.getCommentId()}">Reply</a>
                            <br>
                            &nbsp;&nbsp;&nbsp;${replyComment.commentContent}<br>
                            <c:if test="${commentParentId eq replyComment.getCommentId()}">
                                <c:set var="hasReply" value="1"/>
                            </c:if>
                        </c:forEach>

                        <c:if test="${hasReply eq 1}">
                            &nbsp;&nbsp;
                            <form name="replyForm" action="${pageContext.request.contextPath}/comment/addReplyForTravel"
                                  method="post" onsubmit="return validateReplyContentFormNotNull()">
                                <input type="text" width="400px" name="replyContent" id="replyContent" placeholder="Enter your reply..."/>
                                <input type="hidden" name="replyRateId" value="${rate.getRateId()}"/>
                                <input type="hidden" name="replyParentId" value="${commentParentId}"/>
                                &nbsp;&nbsp;&nbsp;
                                <button type="submit">submit</button>
                            </form>
                            <br>
                        </c:if>
                        <br>
                    </c:forEach>
                </c:if>
            <br>
        </c:forEach>
    </div>
</div>
</body>
</html>
