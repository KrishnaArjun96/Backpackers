<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 12/7/17
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<head>
    <title>Reservations</title>
</head>
<body>

<div class="ui orange tiered menu">
    <div class="menu">
        <a class="item" href="cusrep.jsp">
            <i class="users icon"></i>Customers
        </a>
        <a class="item" href="mailingList.jsp">
            <i class="book icon"></i> Mailing Lists
        </a>
        <a class="item">
            <i class="plane icon"></i> Flight Suggestions
        </a>
        <a class="active item" href="cusrepResr.jsp">
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
    <input id="1" type="text" placeholder="Enter Customer Name...">
</div>

<div class="ui input" style="margin: 20px">
    <input id="2" type="text" placeholder="Enter Customer Name...">
</div>

<div class="ui input" style="margin: 20px">
    <input id="3" type="text" placeholder="Enter Customer Name...">
</div>

<br>
<div class="ui input" style="margin: 20px">
<input id="postButton" class="ui large orange button" type="submit"
       value="Add"/>
</div>


</body>
</html>
