<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Add Group</title>
</head>
<body>
    <script type="text/javascript">
        function validateFormNotNull() {
            var categoryName = document.forms["addGroupForm"]["categoryNameInput"].value;
            var categoryDescription = document.forms["addGroupForm"]["categoryDescriptionText"].value;
            if (categoryName == null || categoryName == "") {
                alert("category name can not be empty!");
                return false;
            } else if (categoryDescription == null || categoryDescription == "") {
                alert("category description can not be empty!");
                return false;
            }
            return true;
        }
    </script>

Add a new group here!<br><br><br>

<!--admin add cateoryName, description here
    categoryName can be chosen from Drop down box, eg. movie, book, travelling
    can set ratings be 0 at first
-->
choose type<br>

<form name="addGroupForm" action="/category/addGroup" method="post" onsubmit="return validateFormNotNull()">
    <select name="categoryTypeSelection">
        <option value="movies">movies</option>
        <option value="books">books</option>
        <option value="travel">travel</option>
    </select>

    <br><br><br>
    <input id="categoryNameInput" name="categoryNameInput" type="text" placeholder="Add title here"/>

    <br><br>
    <textarea id="categoryDescriptionText" name="categoryDescriptionText" rows="10" cols="100" placeholder="Add description here"></textarea>

    <br><br>
    <button type="submit">submit</button>
</form>

    <br><span style="color:red;font-weight: bold">${errorMsg}</span>
    <a href="${pageContext.request.contextPath}/admin/toAdminFunction" type="hidden">${msg}</a>

</body>
</html>
