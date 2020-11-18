<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Add Travel</title>
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

    <form name="addTravelForm" action="/travel/addTravel" method="post" onsubmit="return validateFormNotNull()">

        <br><br>
        Travel Destination<br>
        <input id="travelName" name="travelName" type="text" width="100" placeholder="Add destination here"/>
        <br><br>
        Travel Country<br>
        <input id="travelCountry" name="travelCountry" type="text" width="100" placeholder="Add country here">
        <br><br>
        Travel Description<br>
        <textarea id="travelDescription" name="travelDescription" rows="10" cols="100" placeholder="Add description here"></textarea>

        <br><br>
        <button type="submit">submit</button>
    </form>

    <br><span style="color:red;font-weight: bold">${errorMsg}</span>
    <a href="/travel/toTravel" type="hidden">${msg}</a>

</body>
</html>