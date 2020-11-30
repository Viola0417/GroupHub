<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie Rate</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/bulma@0.9.0/css/bulma.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/WEB-INF/css/forum.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/WEB-INF/css/searchbox.css">

    <link rel='stylesheet' href='https://unpkg.com/bulma@0.9.0/css/bulma.min.css'>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/overlayscrollbars/1.9.1/css/OverlayScrollbars.min.css'>
    <link rel='stylesheet' href='https://kingsora.github.io/OverlayScrollbars/etc/os-theme-thin-dark.css'>

    <style>
        .limiter {
            width: 100%;
            margin: 0 auto;
        }

        .title {
            font-size:35px;
            color: #0a0a0a;
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

        .box {
            box-shadow: 0 2px 6px 0 #00aeef;
        }

        .commentBox {
            box-shadow: 0 2px 6px 0 #00d1b2;
        }

        .commentText {
            padding: 2rem 2rem 2rem 2rem;
        }

        .boxTitle {
            font-size: 22px;
            font-weight:700;
        }

        .boxScore {
            color: #0171e0;
            font-size: 21px;
            font-weight:550;
            display: inline;
            align-content: center;
        }

        .boxAuthor {
            display: inline;
            color: #808080;
        }

        .boxComment {
            position: absolute;
            right: 20px;
            display: inline;
        }

        .left {
            font-size: 18px;
            line-height: 23px;
        }

        input:-ms-input-placeholder,
        textarea:-ms-input-placeholder {
            color: #bfbdbd;
        }

        input::-webkit-input-placeholder,
        textarea::-webkit-input-placeholder {
            color: #bfbdbd;
        }
    </style>
</head>

<body>
<!-- header -->
    <div class="limiter">
        <nav class="navbar">
            <div class="container">
                <div class="navbar-brand"><a href="${pageContext.request.contextPath}/movie/toMovie" class="ArrowBack">
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
    </div>

    <div class="container">
        <div class="page-header">
            <h1 class="title">${movieTitle}</h1>
            <h2 class="left">
                ${movieReviews}<br>
                ${movieScore}
            </h2>
            <br><br>
        </div>
        <div class="row clearfix">
            <c:forEach var="rate" items="${requestScope.get('movieRateList')}">
                <div class="box">
                    <h3 class="boxScore">
                        <svg t="1606119717647" class="icon" viewBox="0 0 900 900" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="18030" width="200" height="200"><path d="M257.4 915.3l-47.2-83.8-96.2 1c-23.2 0.2-37.9-24.9-26.3-45l127-220 48.5 28-95.5 165.4c-3.9 6.7 1 15.1 8.8 15l66.2-0.7 32.5 57.7c3.8 6.7 13.5 6.8 17.4 0.1l78.6-136.2 48.5 28L309.5 915.6C297.9 935.7 268.8 935.6 257.4 915.3z" fill="#1296db" p-id="18031"></path><path d="M713.2 915.6L603.5 725.7l48.5-28 78.1 135.3c3.9 6.7 13.6 6.7 17.4-0.1l32.5-57.7 66.2 0.7c7.7 0.1 12.6-8.3 8.8-15l-95.4-165.3 48.5-28 127 220c11.6 20.1-3.1 45.2-26.3 45l-96.2-1-47.2 83.8C753.9 935.6 724.8 935.7 713.2 915.6z" fill="#1296db" p-id="18032"></path><path d="M511.3 758.5c-46.8 0-92.3-9.2-135.1-27.3-41.3-17.5-78.4-42.5-110.3-74.4s-56.9-69-74.4-110.3c-18.1-42.8-27.3-88.3-27.3-135.1 0-46.8 9.2-92.3 27.3-135.1 17.5-41.3 42.5-78.4 74.4-110.3 31.9-31.9 69-56.9 110.3-74.4 42.8-18.1 88.3-27.3 135.1-27.3s92.3 9.2 135.1 27.3c41.3 17.5 78.4 42.5 110.3 74.4 31.9 31.9 56.9 69 74.4 110.3 18.1 42.8 27.3 88.3 27.3 135.1 0 46.8-9.2 92.3-27.3 135.1-17.5 41.3-42.5 78.4-74.4 110.3-31.9 31.9-69 56.9-110.3 74.4C603.6 749.3 558.2 758.5 511.3 758.5zM511.3 120.4c-77.7 0-150.8 30.3-205.8 85.2-55 55-85.2 128.1-85.2 205.8 0 77.7 30.3 150.8 85.2 205.8 55 55 128.1 85.2 205.8 85.2s150.8-30.3 205.8-85.2c55-55 85.2-128.1 85.2-205.8 0-77.7-30.3-150.8-85.2-205.8C662.2 150.7 589.1 120.4 511.3 120.4z" fill="#1296db" p-id="18033"></path><path d="M602.6 588c-6 0-12.1-1.5-17.7-4.4l-72.6-38.2c-0.6-0.3-1.3-0.3-1.9 0l-72.6 38.2c-12.9 6.8-28.2 5.7-40-2.9s-17.6-22.8-15.1-37.2l13.9-80.8c0.1-0.6-0.1-1.3-0.6-1.8l-58.7-57.3c-10.4-10.2-14.1-25.1-9.6-39s16.3-23.8 30.7-25.9l81.2-11.8c0.7-0.1 1.2-0.5 1.5-1.1l36.3-73.6c6.4-13.1 19.5-21.2 34.1-21.2 14.6 0 27.6 8.1 34.1 21.2l36.3 73.6c0.3 0.6 0.9 1 1.5 1.1l81.2 11.8c14.4 2.1 26.2 12 30.7 25.9s0.8 28.8-9.6 39l-58.7 57.3c-0.5 0.5-0.7 1.1-0.6 1.8l13.9 80.8c2.5 14.4-3.3 28.6-15.1 37.2C618.2 585.6 610.4 588 602.6 588zM402.4 389l32.6 31.8c13.7 13.3 19.9 32.5 16.7 51.3l-7.7 44.9 40.3-21.2c16.9-8.9 37.1-8.9 54 0l40.3 21.2-7.7-44.9c-3.2-18.8 3-38 16.7-51.3l32.6-31.8-45.1-6.6c-18.9-2.7-35.2-14.6-43.7-31.7l-20.2-40.9-20.2 40.9c-8.5 17.1-24.8 29-43.7 31.7L402.4 389z" fill="#1296db" p-id="18034"></path></svg>
                            &nbsp;${rate.getRateScore()}/5</h3><br><br>
                    <b class="boxTitle">${rate.getRateTitle()}</b><br>
                    <div style="overflow:hidden; font-size: 20px">
                        <h3>${rate.getRateContent()} </h3><br>
                    </div>
                    <div class="boxAuthor"><b>${rate.getRateAuthor()}&nbsp;&nbsp;&nbsp;&nbsp;${rate.getRateCreateTime()}</div>
                    <div class="boxComment">
                        <a href="${pageContext.request.contextPath}/rate/deleteMovieRate?rateId=${rate.getRateId()}">
                            Delete Rate
                        </a>&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/comment/showMovieCommentToAdmin?rateId=${rate.getRateId()}">
                            Comment(${rate.getRateTotalReply()})
                        </a>&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/rate/toDeleteMovieRate?movieId=${rate.getRateCategoryId()}">
                            Close
                        </a>
                    </div>
                </div>

                <c:if test="${rootRateId eq rate.getRateId()}">
                    <c:forEach items="${commentHashMap}" var="topComment">
                        <div class="commentBox">
                            <div class="commentText">
                                <br><div class="boxAuthor"><b>${topComment.key.commentAuthor}</b>&nbsp;&nbsp;${topComment.key.commentCreateTime}</div>&nbsp;&nbsp
                                <a href="${pageContext.request.contextPath}/comment/adminDeleteMovieComment?commentId=${topComment.key.getCommentId()}">Delete</a>
                                <br>
                                    ${topComment.key.commentContent}
                                <br>
                                <c:forEach items="${topComment.value}" var="replyComment">
                                    &nbsp;&nbsp;&nbsp;<br>&emsp;&emsp; <div class="boxAuthor"><b>${replyComment.commentAuthor}</b>&nbsp;&nbsp;${replyComment.commentCreateTime}</div>
                                    &nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/comment/adminDeleteMovieComment?commentId=${replyComment.getCommentId()}">Delete</a>
                                    <br>
                                    &emsp;&emsp; ${replyComment.commentContent}<br>
                                </c:forEach>
                                <br>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <br>
            </c:forEach>
            <span style="color:blue;font-weight: bold">${delMovieRateMsg}</span>
        </div>
    </div>
</body>
</html>
