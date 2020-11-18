<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Add Book</title>
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
            }
            return true;
        }
    </script>

    <form action="/book/toBook" method="post">
        <button type="submit">return</button>
    </form>

    <form name="addBookForm" action="/book/addBook" method="post" onsubmit="return validateFormNotNull()">

        <br><br>
        Book Title<br>
        <input id="bookName" name="bookName" type="text" width="100" placeholder="Add title here"/>
        <br><br>
        Book Author<br>
        <input id="bookAuthor" name="bookAuthor" type="text" width="100" placeholder="Add book author here">
        <br><br>
        Book Description<br>
        <textarea id="bookDescription" name="bookDescription" rows="10" cols="100" placeholder="Add description here"></textarea>

        <br><br>
        <button type="submit">submit</button>
    </form>

    <br><span style="color:red;font-weight: bold">${errorMsg}</span>
    <a href="/book/toBook" type="hidden">${msg}</a>

</body>
</html>

