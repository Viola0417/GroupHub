<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

    <form action="/admin/toAdminFunction" method="post">
        <button type="submit">return</button>
    </form>

    <div class="container">

        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>Movie List</small>
                    </h1>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/movie/toAddMovie">add movie</a>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>Movie Name</th>
                        <th>Year</th>
                        <th>Description</th>
                        <th>Review Number</th>
                        <th>Rate Score</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="movie" items="${requestScope.get('movieList')}">
                        <tr>
                            <td>${movie.getMovieName()}</td>
                            <td>${movie.getMovieYear()}</td>
                            <td>${movie.getDescription()}</td>
                            <td>${movie.getTotalRateNumber()}</td>
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

                            <td>
                                <a href="${pageContext.request.contextPath}/movie/toUpdateMovie?movieId=${movie.getMovieId()}">Update</a> |
                                <a href="${pageContext.request.contextPath}/movie/deleteMovie?movieId=${movie.getMovieId()}">Delete</a> |
                                <a href="${pageContext.request.contextPath}/rate/toDeleteMovieRate?movieId=${movie.getMovieId()}">Manage Rate</a>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>
</html>
