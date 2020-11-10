<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Book Index Page</title>
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
                    <small>Book List</small>
                </h1>

                <form action="/user//toUserFunction" method="post">
                    <button type="submit">return</button>
                </form>

            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 column">
            <form action="${pageContext.request.contextPath}/book/userQueryBook" method="post">
                <input type="text" name="queryBookName" class="form-control" placeholder="Book Name">
                <input type="submit" value="Query" class="btn btn-primary">
            </form>
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
                        <td>${book.getTotalRateScore()}</td>

                        <td>
                            <a href="${pageContext.request.contextPath}/post/toBookPost?bookId=${book.getBookId()}">More</a>
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