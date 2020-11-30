<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Add Movie</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/bulma@0.9.0/css/bulma.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/WEB-INF/css/forum.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    <style>

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

        .kt-button {
            background-color: #00aeef;
            border: none;
            color: white;
            padding: 3px 18px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 5px 3px;
            cursor: pointer;
            border-radius: 7px;
            align-content: center;
            justify-content: center;
            font-weight: 650
        }

        .kt-button:hover {
            background-color: #00a0da;
        }

        input:-ms-input-placeholder,
        textarea:-ms-input-placeholder {
            color: #bfbdbd;
        }

        input::-webkit-input-placeholder,
        textarea::-webkit-input-placeholder {
            color: #bfbdbd;
        }

        .review {
            padding: 0 12rem;
            display: flex;
            justify-content: center;
        }

        .reviewInput {
            border: 2px solid #00aeef;
            border-radius: 7px;
        }

    </style>
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
        <div class="box content">
            <div class="review">
                <form name="addMovieForm" action="/movie/addMovie" method="post" onsubmit="return validateFormNotNull()">
                    <div style="text-align: center">
                        <br><span style="color:red;font-weight: bold; font-size: 20px;">${errorMsg}</span>
                        <a href="/movie/toMovie" type="hidden"  style="font-size: 20px;">${msg}</a>
                    </div>
                    <br><h3 style="display: inline; font-size: 23px; ">Movie Title</h3>&emsp;
                    <input class="reviewInput" id="movieName" name="movieName" type="text" width="100" placeholder="Add title here"/>
                    <br><br><br><h3 style="display: inline; font-size: 23px; ">Movie Year</h3>&emsp;
                    <input class="reviewInput" id="movieYear" name="movieYear" type="text" width="100" placeholder="Add Year here xxxx, eg.1998">
                    <br><br><br><h3 style="display: inline; font-size: 23px; ">Movie Description </h3><br><br>
                    <textarea class="reviewInput" id="movieDescription" name="movieDescription" rows="10" cols="100" placeholder="Add description here"></textarea>
                    <br><br><br>
                    <div style="display: flex; justify-content: center">
                        <button class="kt-button" type="submit">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
