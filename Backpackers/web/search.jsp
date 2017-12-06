<%@ page import="JavaBeans.Airline" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="JavaBeans.Airport" %>
<%@ page import="JavaBeans.Option" %>
<%@ page import="static webapp.Search.convertTimeFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Rahul
  Date: 11/12/17
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<link rel="stylesheet" href="search.css">
<script src="search.js"></script>
<html>
<head>
    <title>Flight Results</title>
</head>
<body>

<h2 class="ui horizontal divider header" style="margin: 25px">
    JFK -> DXB
</h2>
<div>
    <div class="ui orange segment" style="margin: 10px; width: 60%">
        <div class="ui grid">
            <div class="eight wide column">
                <h3>Etihad Airways, EY 111</h3>
                <h4>10:00pm - 10:00am </h4>
            </div>
            <div class="six wide column">
                <h4>Stops: 1</h4>
                <label>LHW</label>
            </div>
            <div class="two wide column">
                <h3>$ 1,400</h3>
                <label>Select:  <input type="radio" name="from"/></label>
            </div>
        </div>
    </div>
</div>


</body>
</html>
