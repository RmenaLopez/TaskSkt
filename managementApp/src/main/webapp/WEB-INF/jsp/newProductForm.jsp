<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
    <title>Skytouch Task</title>
</head>

<body>

<form:form method="post" modelAttribute="product">
    <table>
        <tr>
            <td><form:label path="description">Product Description </form:label></td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td><form:label path="price">Price </form:label></td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <td><form:label path="brand">Brand </form:label></td>
            <td><form:input path="brand"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>

<form method="get" action="/products">
    <input type="submit" value="See all products">
</form>

<form method="get" action="/new-user">
    <input type="submit" value="Go to new user form">
</form>



</body>
</html>