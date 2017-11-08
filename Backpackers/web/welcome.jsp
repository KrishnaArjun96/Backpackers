<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 11/7/17
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1>Welcome to Backpackers, ${username}</h1>

<br><br>

<form action="/Search" method="get">
    source: <input type="text" name="username" width="25"/>
    destination: <input type="text" name="destination" width="25"/>
    departing: <input type="date" name="departing" width="25"/>
    returning: <input type="date" name="returning" width="25"/>
    <input type="submit" value="submit"/>
</form>

</body>
</html>
