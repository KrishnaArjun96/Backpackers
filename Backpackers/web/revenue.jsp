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
<script src="revenue.js"></script>
<head>
    <title>Revenue</title>
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
        <a class="item" href="reservations.jsp">
            <i class="book icon"></i> Reservations
        </a>
        <a class="active item">
            <i class="bitcoin icon"></i> Revenue
        </a>
        <a class="item" style="float: right">
            <i class="power icon"></i>
        </a>
    </div>
</div>

<div class="ui form" style="margin: 20px" id="fl">
    <div class="fields">
        <div class="three wide field">
            <label>Choose Type:</label>
            <select class="ui search selection dropdown" id="search-select">
                <option value="flightNo">Flight Number</option>
                <option value="city">City</option>
                <option value="customer">Customer</option>
                <option value="customerRep">Customer Representative</option>
            </select>
        </div>
        <div class="field">
            <label>Enter Value:</label>
            <input type="text" id="value">
        </div>
        <div class="field">
            <button class="ui orange button" id="goButton" style="position: relative; top: 20px;">Go</button>
        </div>
    </div>
</div>


</body>
</html>
