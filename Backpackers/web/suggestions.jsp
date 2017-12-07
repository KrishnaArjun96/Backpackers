<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 12/6/17
  Time: 2:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="suggestions.js"></script>
<head>
    <title>Suggestions</title>
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
        <a class="active item">
            <i class="plane icon"></i> Flight Suggestions
        </a>
        <a class="item" style="float: right">
            <i class="power icon"></i>
        </a>
    </div>
</div>

<div style="margin: 20px;">
    <div class="ui form">
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
    <table class="ui sortable orange celled structured table" id="customerTable" style="margin: 20px; width: 50%;">
        <thead>
        <tr>
            <th rowspan="2" class="center aligned">Flight</th>
            <th rowspan="2" class="center aligned">Destinations</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
</html>
