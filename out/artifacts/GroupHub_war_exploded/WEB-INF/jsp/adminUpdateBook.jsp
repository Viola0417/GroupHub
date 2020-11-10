<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin update book</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<script type="text/javascript">
    function validateFormNotNull() {
        var bookName = document.forms["addBookForm"]["bookName"].value;
        var bookAuthor = document.forms["addBookForm"]["bookAuthor"].value;
        var bookDescription = document.forms["addBookForm"]["bookDescription"].value;
        if (bookName == null || bookName == "") {
            alert("book name can not be empty!");
            return false;
        } else if (bookAuthor == null || bookAuthor == "") {
            alert("book author can not be empty!");
            return false;
        } else if (bookDescription == null || bookDescription == "") {
            alert("book description can not be empty!");
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
                    <small>update book</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
        <input type="hidden" name="bookId" value="${queryBook.bookId}">

        book title：<input type="text" name="bookName" value="${queryBook.bookName}"><br><br><br>
        book author：<input type="text" name="bookAuthor" value="${queryBook.bookAuthor}"><br><br><br>
        description：<br><textarea name="description" rows="10" cols="100">${queryBook.description}</textarea><br><br><br>
        <input type="submit" value="update">
    </form>

</div>
</body>
</html>

