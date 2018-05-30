<%@ page import="com.skt.finaltask.commonLibrary.model.Product" %>
<%@ page import="java.util.List" %>
<html>

<head>
    <title>Skytouch Task</title>
</head>

<body>

<table border="1px solid black" style="width: 50%">
    <tr>
        <th>Product description</th>
        <th>Price</th>
        <th>Brand</th>
    </tr>
    <%
        List<Product> productList = (List<Product>) request.getAttribute("products");
        for (Product product: productList){  %>
    <tr>
        <th><%=product.getDescription()  %></th>
        <th><%=product.getPrice()  %></th>
        <th><%=product.getBrand()  %></th>
    </tr>
    <%}%>
</table>

<br>
<form method="get" action="/new-product">
    <input type="submit" value="Go back to Product form">
</form>

</body>

</html>