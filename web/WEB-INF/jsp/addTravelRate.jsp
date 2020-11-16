<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Travel Rate</title>
</head>
<body>

<script type="text/javascript">
    function validateAddFormNotNull() {
        var rateTitle = document.forms["addTravelRateForm"]["rateTitle"].value;
        var rateContent = document.forms["addTravelRateForm"]["rateContent"].value;
        if (rateTitle == null || rateTitle == "") {
            alert("rate title can not be empty!");
            return false;
        } else if (rateContent == null || rateContent == "") {
            alert("rate content can not be empty!");
            return false;
        }
        return true;
    }

    function validateUpdateFormNotNull() {
        var rateTitle = document.forms["addTravelRateForm"]["newRateTitle"].value;
        var rateContent = document.forms["addTravelRateForm"]["newRateContent"].value;
        if (rateTitle == null || rateTitle == "") {
            alert("rate title can not be empty!");
            return false;
        } else if (rateContent == null || rateContent == "") {
            alert("rate content can not be empty!");
            return false;
        }
        return true;
    }
</script>

<form name="submitForm" action="/travel/toUserTravel" method="post">
    <button type="submit">return</button>
</form>

<c:choose>
    <c:when test="${travelRate.getRateScore()==0}">
        <!--this user has not added rate to this movie before-->
        <form name="addTravelRateForm" action="/rate/addRateForTravel" method="post" onsubmit="return validateAddFormNotNull()">
            <br><br>
            <div>
                <h3>Your rating</h3>
                <select id="rateScore" name="rateScore">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>

            <div>
                <h3>Your Review</h3>
                <input id="rateTitle" name="rateTitle" type="text" size="100" placeholder="Write a headline for your review here"/>
                <br><br>
                <textarea id="rateContent" name="rateContent" rows="10" cols="100" placeholder="Write your review here"></textarea>

                <br><br>
                <button type="submit">submit</button>
            </div>
        </form>

        <br><span style="color:blue;font-weight: bold">${successMsg}</span>
    </c:when>
    <c:otherwise>
        <!-- this user has added rate to this movie-->
        <form name="updateTravelRateForm" action="/rate/updateTravelRate" method="post" onsubmit="return validateUpdateFormNotNull()">
            <br><br>
            <div>
                Your previous score is ${travelRate.getRateScore()}
                <h3>Your rating</h3>
                <select id="newRateScore" name="newRateScore">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>

            <div>
                <h3>Your Review</h3>
                <input id="rateId" name="rateId" type="hidden" value="${rateId}"/><br>
                <input id="newRateTitle" name="newRateTitle" type="text" size="100" value="${travelRate.getRateTitle()}"/>
                <br><br>
                <textarea id="newRateContent" name="newRateContent" rows="10" cols="100">${travelRate.getRateContent()}</textarea>

                <br><br>
                <button type="submit">submit</button>
            </div>
        </form>
        <br><span style="color:blue;font-weight: bold">${successMsg}</span>
    </c:otherwise>
</c:choose>
</body>
</html>
