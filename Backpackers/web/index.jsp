<%@ page import="Classes.Data" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="JavaBeans.Airport" %>
<%@ page import="com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl" %><%--
  Created by IntelliJ IDEA.
  User: Rahul
  Date: 11/01/17
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<link rel="stylesheet" href="index.css">
<script src="index.js"></script>
<head>
    <title>Welcome!</title>
</head>

<body>

<%
    try {
        Data.Refresh();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
%>


<%--for(Airport airport: Data.AIRPORTS) {--%>
<%--System.out.println(airport.toString());--%>
<%--}--%>

<h1>Backpackers</h1>

<div class="ui buttons">
    <button id="round" class="ui orange button" onclick="changeButton('round')">Roundtrip</button>
    <button id="one" class="ui button" onclick="changeButton('one')">One way</button>
    <button id="multi" class="ui button" onclick="changeButton('multi')">Multi-City</button>
</div>

<br><br>

<form action="/Search" method="post">
    <div class="ui form">
        <div class="two fields">
            <div class="field">
                <label>Source</label>
                <input type="text" placeholder="Source" name="source">
            </div>
            <div class="field">
                <label>Destination</label>
                <input type="text" placeholder="Destination" name="destination">
            </div>
        </div>
        <div class="three fields">
            <div class="field">
                <label>Departing</label>
                <input type="date" name="departing">
            </div>
            <div class="field" id="retDate">
                <label>Return Date</label>
                <input type="date" name="returning"/>
            </div>
            <div class="field" id="class">
                <label>Preferred class</label>
                <select class="ui selection dropdown">
                    <option value="none">Preferred Class</option>
                    <option value="eco">Economy</option>
                    <option value="bus">Business</option>
                    <option value="fc">First Class</option>
                </select>
            </div>
        </div>

        <br>

        <div class="field">
            <div class="ui toggle checkbox">
                <input type="checkbox" name="flex">
                <label>Flexible dates</label>
            </div>
        </div>
        <div class="field">
            <input style="position: relative; top:20px;" class="ui large orange button" type="submit"
                   value="Search"/>
        </div>
    </div>
</form>


<%--<div class="outerbox">--%>
<%--<form action="/Search" method="post">--%>
<%--source: <input type="text" name="source" width="25"/>--%>
<%--destination: <input type="text" name="destination" width="25"/>--%>
<%--departing: <input type="date" name="departing" width="25"/>--%>
<%--<div id="retDate">--%>
<%--returning: <input type="date" name="returning" width="25"/>--%>
<%--</div>--%>
<%--<input type="submit" value="Search"/>--%>
<%--</form>--%>
<%--</div>--%>

</body>
</html>

