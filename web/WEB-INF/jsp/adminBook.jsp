<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container">

        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>Book List</small>
                    </h1>
                </div>
            </div>
        </div>

        <div class="row">

            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">add book</a>
            </div>

        </div>

        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>Book Name</th>
                        <th>Book Author</th>
                        <th>Description</th>
                        <th>Review Number</th>
                        <th>Rate Score</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="book" items="${requestScope.get('bookList')}">
                        <tr>
                            <td>${book.getBookName()}</td>
                            <td>${book.getBookAuthor()}</td>
                            <td>${book.getDescription()}</td>
                            <td>${book.getTotalRateNumber()}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${book.getTotalRateScore()==0}">
                                        no rate
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatNumber type="number" value="${book.getTotalRateScore()/book.getTotalRateNumber()}" maxFractionDigits="2"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <a href="${pageContext.request.contextPath}/book/toUpdateBook?bookId=${book.getBookId()}">Update</a> |
                                <a href="${pageContext.request.contextPath}/book/deleteBook?bookId=${book.getBookId()}">Delete</a>
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

