<html>

<head>
    <title>Skytouch Task</title>
</head>

<body>

<form method="post" action="/new-product">
    Description: <input type="text" name="description">
    Price: <input type="number" step="any" name="price">
    Brand: <input type="text" name="brand">
    <input type="submit">

</form>


<form method="get" action="/products">
    <input type="submit" value="See all products">
</form>

<form method="get" action="/">
    <input type="submit" value="Go to new user form">
</form>



</body>
</html>