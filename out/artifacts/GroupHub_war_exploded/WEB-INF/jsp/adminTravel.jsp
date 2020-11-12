<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Travel</title>
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
                    <small>Travel Records List</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row">

        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/travel/toAddTravel">add travel</a>
        </div>

    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>Travel Destination</th>
                    <th>Travel Country</th>
                    <th>Description</th>
                    <th>Review Number</th>
                    <th>Rate Score</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="travel" items="${requestScope.get('travelList')}">
                    <tr>
                        <td>${travel.getTravelName()}</td>
                        <td>${travel.getTravelCountry()}</td>
                        <td>${travel.getDescription()}</td>
                        <td>${travel.getTotalRateNumber()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${travel.getTotalRateScore()==0}">
                                    no rate
                                </c:when>
                                <c:otherwise>
                                    <fmt:formatNumber type="number" value="${travel.getTotalRateScore()/travel.getTotalRateNumber()}" maxFractionDigits="2"/>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <a href="${pageContext.request.contextPath}/travel/toUpdateTravel?travelId=${travel.getTravelId()}">Update</a> |
                            <a href="${pageContext.request.contextPath}/travel/deleteTravel?travelId=${travel.getTravelId()}">Delete</a>
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
