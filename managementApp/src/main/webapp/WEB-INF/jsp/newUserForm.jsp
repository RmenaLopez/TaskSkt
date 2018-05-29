<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
    <title>Skytouch Task</title>
</head>

<body>

<form:form method="post" modelAttribute="user">
    <table>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="age">Age</form:label></td>
            <td><form:input path="age"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
    
    
</form:form>


<form method="get" action="/users">
    <input type="submit" value="See all users">
</form>
<form method="get" action="/new-product">
    <input type="submit" value="Go to new product form">
</form>



</body>



</html>