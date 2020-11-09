<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

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

            <!--
            <div class="col-md-8 column">
                <form action="${pageContext.request.contextPath}/movie/queryMovie" method="post" style="float:right">
                    <input type="text" name="queryBookName" class="form-control" placeholder="Movie Name">
                    <input type="submit" value="Query" class="btn btn-primary">
                </form>
            </div>
            -->
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
                            <td>${movie.getTotalRateScore()}</td>

                            <td>
                                <a href="${pageContext.request.contextPath}/movie/toUpdateMovie?movieId=${movie.getMovieId()}">Update</a> |
                                <a href="${pageContext.request.contextPath}/movie/deleteMovie?movieId=${movie.getMovieId()}">Delete</a>
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
