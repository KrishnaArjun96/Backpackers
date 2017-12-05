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

<div style="margin: 20px">
    <div class="ui buttons" name="type">
        <button id="fnoBut" class="ui orange button" onclick="changeButton('fnoBut')">Flight Number</button>
        <button id="cityBut" class="ui button" onclick="changeButton('cityBut')">City</button>
        <button id="cusBut" class="ui button" onclick="changeButton('cusBut')">Customer Name</button>
        <button id="cusRepBut" class="ui button" onclick="changeButton('cusRepBut')">Customer Representative</button>
    </div>
</div>

<div id="fno">
    <table class="ui sortable orange celled structured table" id="fnoTable" style="margin: 20px; width: 40%;">
        <thead>
        <tr>
            <th rowspan="2" class="center aligned">Flight Number</th>
            <th rowspan="2" class="center aligned">revenue</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>

<div id="city" style="display: none">
    <table class="ui sortable orange celled structured table" id="cityTable" style="margin: 20px; width: 40%; ">
        <thead>
        <tr>
            <th rowspan="2" class="center aligned">City</th>
            <th rowspan="2" class="center aligned">Revenue</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>

<div id="cus" style="display: none">
    <table class="ui sortable orange celled structured table" id="cusTable" style="margin: 20px; width: 40%;">
        <thead>
        <tr>
            <th rowspan="2" class="center aligned">Customer</th>
            <th rowspan="2" class="center aligned">Revenue</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>

<div id="cusRep" style="display: none">
    <table class="ui sortable orange celled structured table" id="cusRepTable" style="margin: 20px; width: 40%;">
        <thead>
        <tr>
            <th rowspan="2" class="center aligned">Customer Representative</th>
            <th rowspan="2" class="center aligned">Revenue</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
</html>
