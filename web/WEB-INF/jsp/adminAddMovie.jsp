<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Add Movie</title>
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
        }
        return true;
    }
</script>

<form name="addMovieForm" action="/movie/addMovie" method="post" onsubmit="return validateFormNotNull()">

    <br><br>
    Movie Title<br>
    <input id="movieName" name="movieName" type="text" width="100" placeholder="Add title here"/>
    <br><br>
    Movie Year<br>
    <input id="movieYear" name="movieYear" type="text" width="100" placeholder="Add Year here xxxx, eg.1998">
    <br><br>
    Movie Description<br>
    <textarea id="movieDescription" name="movieDescription" rows="10" cols="100" placeholder="Add description here"></textarea>

    <br><br>
    <button type="submit">submit</button>
</form>

<br><span style="color:red;font-weight: bold">${errorMsg}</span>
<a href="/movie/toMovie" type="hidden">${msg}</a>

</body>
</html>
