<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 12/5/17
  Time: 11:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="mailingList.js"></script>
<head>
    <title>Mailing List</title>
</head>
<body>

<div class="ui orange tiered menu">
    <div class="menu">
        <a class="item" href="cusrep.jsp">
            <i class="users icon"></i>Customers
        </a>
        <a class="active item">
            <i class="book icon"></i> Mailing Lists
        </a>
        <a class="item" href="suggestions.jsp">
            <i class="plane icon"></i> Flight Suggestions
        </a>
        <a class="item" href="reservartions.jsp">
            <i class="globe icon"></i> Reservation
        </a>
        <a class="item" style="float: right" href="login.jsp">
            <i class="power icon"></i>
        </a>
        <a class="item" style="float: right" href="help.html">
            Help
        </a>
    </div>
</div>

<div class="ui input" style="margin: 20px">
    <input id="filter" onkeyup="filter()" type="text" placeholder="Enter Customer Name...">
</div>

<table class="ui sortable orange celled structured table" id="customerTable" style="margin: 20px; width: 40%;">
    <thead>
    <tr>
        <th rowspan="2" class="center aligned">Customer Name</th>
        <th rowspan="2" class="center aligned">E-Mail</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
</body>
</html>

