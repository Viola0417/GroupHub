<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Movie Post</title>
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

<form action="/movie/toUserMovie" method="post">
    <button type="submit">return</button>
</form>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>${movieTitle}</small>
                </h1>

                <div class="col-md-4 column">
                    <form action="${pageContext.request.contextPath}/rate/toAddMovieRate" method="post">
                        <input type="submit" value="Rate" class="btn btn-primary"/>
                    </form>
                </div>

                ${movieReviews}<br>
                ${movieScore}
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <br><span style="color:red;font-weight: bold">${errorMsg}</span>
        <c:forEach var="rate" items="${requestScope.get('rateList')}">
            <div class="block">
                ${rate.getRateScore()}/5<br>
                <b>${rate.getRateTitle()}</b><br>
                ${rate.getRateAuthor()}&nbsp;&nbsp;&nbsp;&nbsp;${rate.getRateCreateTime()}<br>

                <div style="overflow:hidden;">
                        ${rate.getRateContent()}<br>
                </div>

                    <a href="${pageContext.request.contextPath}/comment/showComment?rateId=${rate.getRateId()}">
                        Comment(${rate.getRateTotalReply()})
                    </a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/rate/toMovieRate?movieId=${rate.getRateCategoryId()}">
                        Close
                    </a>
            </div>

                <form name="contentForm" action="${pageContext.request.contextPath}/comment/addTopComment"
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
                        &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/comment/toAddReply?commentParentId=${topComment.key.getCommentId()}">Reply</a>

                        <c:if test="${userName eq topComment.key.commentAuthor}">
                            &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/comment/userDeleteMovieComment?commentId=${topComment.key.getCommentId()}">Delete</a>
                            &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/comment/userToUpdateMovieComment?commentId=${topComment.key.getCommentId()}">Update</a>
                        </c:if>
                        <br>

                        <c:choose>
                            <c:when test="${modifyComment eq 1 && topComment.key.commentId eq commentId}">
                                <form action="/comment/userUpdateMovieComment" method="post">
                                    <input type="text" name="commentContent" value="${topComment.key.commentContent}">
                                    <input type="hidden" name="commentId" value="${topComment.key.commentId}">
                                    &nbsp;&nbsp;
                                    <input type="submit" value="update">
                                </form>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${topComment.key.isEdited == 1}">
                                    <span style="color:blue">(Edited)</span>
                                </c:if>
                                ${topComment.key.commentContent}
                            </c:otherwise>
                        </c:choose>
                        <br>

                        <c:forEach items="${topComment.value}" var="replyComment">
                            &nbsp;&nbsp;&nbsp;<b>${replyComment.commentAuthor}</b>&nbsp;&nbsp;${replyComment.commentCreateTime}
                                &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/comment/toAddReply?commentParentId=${replyComment.getCommentId()}">Reply</a>

                                <c:if test="${userName eq replyComment.commentAuthor}">
                                    &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/comment/userDeleteMovieComment?commentId=${replyComment.getCommentId()}">Delete</a>
                                    &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/comment/userToUpdateMovieComment?commentId=${replyComment.getCommentId()}">Update</a>
                                </c:if>

                            <br>
                            &nbsp;&nbsp;&nbsp;
                            <c:choose>
                                <c:when test="${modifyComment eq 1 && replyComment.commentId eq commentId}">
                                    <form action="/comment/userUpdateMovieComment" method="post">
                                        <input type="text" name="commentContent" value="${replyComment.commentContent}">
                                        <input type="hidden" name="commentId" value="${replyComment.commentId}">
                                        &nbsp;&nbsp;
                                        <input type="submit" value="update">
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${replyComment.isEdited == 1}">
                                        <span style="color:blue">(Edited)</span>
                                    </c:if>
                                    ${replyComment.commentContent}
                                </c:otherwise>
                            </c:choose>
                            <br>

                            <c:if test="${commentParentId eq replyComment.getCommentId()}">
                                <c:set var="hasReply" value="1"/>
                            </c:if>

                        </c:forEach>

                        <c:if test="${hasReply eq 1}">
                            &nbsp;&nbsp;
                            <form name="replyForm" action="${pageContext.request.contextPath}/comment/addReply"
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
