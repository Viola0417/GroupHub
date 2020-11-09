<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin update movie</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <script type="text/javascript">
        function validateFormNotNull() {
            var movieName = document.forms["addMovieForm"]["movieName"].value;
            var movieYear = document.forms["addMovieForm"]["movieYear"].value;
            var movieDescription = document.forms["addMovieForm"]["movieDescription"].value;
            if (movieName == null || movieName == "") {
                alert("movie name can not be empty!");
                return false;
            } else if (movieYear == null || movieYear == "") {
                alert("movie year can not be empty!");
                return false;
            } else if (movieDescription == null || movieDescription == "") {
                alert("movie description can not be empty!");
                return false;
                return true;
            }
        }
    </script>

    <div class="container">

        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>update movie</small>
                    </h1>
                </div>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/movie/updateMovie" method="post">
            <input type="hidden" name="movieId" value="${queryMovie.movieId}">

            movie title：<input type="text" name="movieName" value="${queryMovie.movieName}"><br><br><br>
            year：<input type="text" name="movieYear" value="${queryMovie.movieYear}"><br><br><br>
            description：<br><textarea name="description" rows="10" cols="100">${queryMovie.description}</textarea><br><br><br>
            <input type="submit" value="update">
        </form>

    </div>
</body>
</html>
