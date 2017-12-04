<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 11/29/17
  Time: 2:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="flights.js"></script>
<head>
    <title>Flights</title>
</head>
<body>

<div class="ui orange tiered menu">
    <div class="menu">
        <a class="item" href="manager.jsp">
            <i class="users icon"></i>
            Employees
        </a>
        <a class="item" href="sales.jsp">
            <i class="bar chart icon"></i> Sales Report
        </a>
        <a class="active item">
            <i class="plane icon"></i> Flights
        </a>
        <a class="item" href="reservations.jsp">
            <i class="book icon"></i> Reservations
        </a>
        <a class="item" href="revenue.jsp">
            <i class="bitcoin icon"></i> Revenue
        </a>
        <a class="item" style="float: right">
            <i class="power icon"></i>
        </a>
    </div>
</div>

<div style="margin: 20px">
    <div class="ui buttons" name="type">
        <button id="all" class="ui orange button" onclick="changeButton('all')">All</button>
        <button id="ma" class="ui button" onclick="changeButton('ma')">Most Active</button>
        <button id="airport" class="ui button" onclick="changeButton('airport')">Airport</button>
        <button id="status" class="ui button" onclick="changeButton('status')">Status</button>
    </div>
</div>



</body>
</html>
