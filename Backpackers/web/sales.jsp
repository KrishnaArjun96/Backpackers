<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 11/29/17
  Time: 2:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="sales.js"></script>
<head>
    <title>Sales Report</title>
</head>
<body>


<div class="ui orange tiered menu">
    <div class="menu">
        <a class="item" href="manager.jsp">
            <i class="users icon"></i>
            Employees
        </a>
        <a class="active item">
            <i class="bar chart icon"></i> Sales Report
        </a>
        <a class="item" href="flights.jsp">
            <i class="plane icon"></i> Flights
        </a>
        <a class="item" href="reservations.jsp">
            <i class="book icon"></i> Reservations
        </a>
        <a class="item" href="revenue.jsp">
            <i class="bitcoin icon"></i> Revenue
        </a>
        <a class="item" style="float: right" href="login.jsp">
            <i class="power icon"></i>
        </a>
    </div>
</div>

<h1 style="color: #E07B53;margin: 20px">Enter a month and a year.</h1>

<div class="ui form" style="margin: 20px">
    <div class="field">
        <div class="three fields">
            <div class="three wide field">
                <select class="ui search selection dropdown" id="search-select">
                    <option value="1">January</option>
                    <option value="2">February</option>
                    <option value="3">March</option>
                    <option value="4">April</option>
                    <option value="5">May</option>
                    <option value="6">June</option>
                    <option value="7">July</option>
                    <option value="8">August</option>
                    <option value="9">September</option>
                    <option value="10">October</option>
                    <option value="11">November</option>
                    <option value="12">December</option>
                </select>
            </div>
            <div class="two wide field">
                <input type="text" id="year" placeholder="Year">
            </div>
            <div class="one wide field">
                <input id="goButton" class="ui medium orange button" type="submit"
                       value="Go"/>
            </div>
        </div>
    </div>
</div>

<table class="ui sortable orange celled structured table" id="salesTable" style="margin: 20px; width: 70%;">
    <thead>
    <tr>
        <th rowspan="2" class="center aligned">Reservation number</th>
        <th rowspan="2" class="center aligned">Representative</th>
        <th rowspan="2" class="center aligned">Date</th>
        <th rowspan="2" class="center aligned">Sale</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

</body>
</html>
