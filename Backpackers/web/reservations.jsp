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
<script src="reservations.js"></script>
<head>
    <title>Reservations</title>
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
        <a class="item" href="flights.jsp">
            <i class="plane icon"></i> Flights
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
        <button id="flightNo" class="ui orange button" onclick="changeButton('flightNo')">Flight Number</button>
        <button id="customerName" class="ui button" onclick="changeButton('customerName')">Customer Name</button>
        <button id="customerOnFlight" class="ui button" onclick="changeButton('customerOnFlight')">Customers On Flight</button>
    </div>
</div>

<div class="ui form" style="margin: 20px" id="fl">
    <div class="fields">
        <div class="field">
            <label>Flight Number</label>
            <input type="text" placeholder="Flight Number" id="flightNoInput">
        </div>
        <div class="field">
            <label>Date</label>
            <input type="date" id="date" id="date">
        </div>
        <div class="field">
            <button class="ui orange button" id="flightNoButton" style="position: relative; top: 20px;">Go</button>
        </div>
    </div>
</div>

<div class="ui form" style="margin: 20px; display: none" id="cus" >
    <div class="fields">
        <div class="field">
            <label>Customer Name</label>
            <input type="text" placeholder="Customer Name" id="name">
        </div>
        <div class="field">
            <button class="ui orange button" id="cusNameButton" style="position: relative; top: 20px;">Go</button>
        </div>
    </div>
</div>


</body>
</html>
