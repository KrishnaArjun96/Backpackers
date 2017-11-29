<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 11/29/17
  Time: 3:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="manager.js"></script>
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
            <i class="bar chart icon"></i> sales report
        </a>
        <a class="item" href="flights.jsp">
            <i class="plane icon"></i> flights
        </a>
        <a class="active item">
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
        <button id="fno" class="ui orange button" onclick="changeButton('all')">Flight Number</button>
        <button id="cn" class="ui button" onclick="changeButton('cn')">Customer Name</button>
        <button id="cof" class="ui button" onclick="changeButton('cof')">Customers On Flight</button>
    </div>
</div>

</body>
</html>
