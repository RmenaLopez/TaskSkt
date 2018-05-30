<%@ page import="com.skt.finaltask.commonLibrary.model.User" %>
<%@ page import="java.util.List" %>

<html>

<head>
    <title>Skytouch Task</title>
</head>

<body>

<table border="1px solid black" style="width: 50%">
    <tr>
        <th>User name</th>
        <th>Age</th>
    </tr>
    <%
        List<User> userList = (List<User>) request.getAttribute("users");
        for (User user : userList){  %>
    <tr>
        <th><%=user.getName()  %></th>
        <th><%=user.getAge()  %></th>
    </tr>
    <%}%>
</table>

<br>
<form method="get" action="/new-user">
    <input type="submit" value="Go to new user form">
</form>

</body>

</html>