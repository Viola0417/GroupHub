<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin update travel</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <script type="text/javascript">
        function validateFormNotNull() {
            var travelName = document.forms["addTravelForm"]["travelName"].value;
            var travelCountry = document.forms["addTravelForm"]["travelCountry"].value;
            var travelDescription = document.forms["addTravelForm"]["travelDescription"].value;
            if (travelName == null || travelName == "") {
                alert("travel destination can not be empty!");
                return false;
            } else if (travelCountry == null || travelCountry == "") {
                alert("travel country can not be empty!");
                return false;
            } else if (travelDescription == null || travelDescription == "") {
                alert("travel description can not be empty!");
                return false;
            }
            return true;
        }
    </script>

    <form action="/travel/toTravel" method="post">
        <button type="submit">return</button>
    </form>

    <div class="container">

        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>update travel</small>
                    </h1>
                </div>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/travel/updateTravel" method="post">
            <input type="hidden" name="travelId" value="${queryTravel.travelId}">

            travel destination：<input type="text" name="travelName" value="${queryTravel.travelName}"><br><br><br>
            travel country：<input type="text" name="travelCountry" value="${queryTravel.travelCountry}"><br><br><br>
            description：<br><textarea name="description" rows="10" cols="100">${queryTravel.description}</textarea><br><br><br>
            <input type="submit" value="update">
        </form>

    </div>
</body>
</html>
