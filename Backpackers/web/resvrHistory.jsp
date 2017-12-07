<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 12/7/17
  Time: 4:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="index.js"></script>
<head>
    <title>Reservation History</title>
</head>
<body>

<div class="ui orange tiered menu">
    <div class="menu">
        <a class="item" href="index.jsp">
            <i class="search icon"></i>
            Search Flights
        </a>
        <a class="item" href="itenary.jsp">
            <i class="bar chart icon"></i> Travel Itenary
        </a>
        <a class="item" href="cusFlightSug.jsp">
            <i class="plane icon"></i> Flight Suggestions
        </a>
        <a class="item" href="curReservations.jsp">
            <i class="book icon"></i> Current Reservations
        </a>
        <a class="item" href="bestSeller.jsp">
            <i class="bitcoin icon"></i> Best Seller
        </a>
        <a class="active item" href="resvrHistory.jsp">
            <i class="book icon"></i> Reservation History
        </a>
        <a class="item" style="float: right">
            <i class="power icon"></i>
        </a>
    </div>
</div>

</body>
</html>
