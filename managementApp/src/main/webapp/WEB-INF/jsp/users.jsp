<%@ page import="com.skt.finaltask.commonLibrary.model.User" %>
<%@ page import="java.util.List" %>

<html>

<head>
    <title>Skytouch Task</title>
</head>

<body>


<%
    List<User> userList = (List<User>) request.getAttribute("users");
    for (User user : userList){  %>
<span><%= user.toString() %></span>
<br>

<%}%>

<br>
<form method="get" action="/">
    <input type="submit" value="Go back no User form">
</form>

</body>



</html>