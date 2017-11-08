<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 11/7/17
  Time: 10:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Login</h1>
<form action="/login" method="post">
    username: <input type="text" name="username" width="25"/>
    password: <input type="password" name="password" width="25"/>
    <input type="submit" value="Login"/>
</form>
</body>
</html>
