<%--
  Created by IntelliJ IDEA.
  User: Rahul
  Date: 11/01/17
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<link rel="stylesheet" href="index.css">
<script src="index.js"></script>

<head>
    <title>Welcome!</title>
</head>

<body>

<h1 class="orngHeader">Backpackers</h1>

<div class="ui buttons" name="type">
    <button id="round" class="ui orange button" onclick="changeButton('round')">Roundtrip</button>
    <button id="one" class="ui button" onclick="changeButton('one')">One way</button>
    <button id="multi" class="ui button" onclick="changeButton('multi')">Multi-City</button>
</div>

<br><br>

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

    </div>

    <div id="multiDiv" style="position: relative;top: 10px; display:none;">
        <div id="header" style="height: 15px;width: 200px">
            <h2 class="orngHeader" style="position: relative;float: left;width: 90px">Trips</h2>
            <div class="ui button" style="position: relative;float: left;width: 90px;" onclick="addDiv()">
                <i class="add icon"></i>
            </div>
        </div>
        <div class="four fields">
            <div class="four field">
                <label>Source</label>
                <input type="text" placeholder="Source">
            </div>
            <div class="field">
                <label>Destination</label>
                <input type="text" placeholder="Destination">
            </div>
            <div class="field">
                <label>Departing</label>
                <input type="date" name="departing">
            </div>
            <div class="field">
                <div onclick="removeDiv('multiDivButton0');" class="ui button" style="margin-top: 25px; display: none"
                     id="multiDivButton0">
                    <i style="margin-top: 35px;" class="close icon"></i>
                </div>
            </div>
        </div>

    </div>

    <div class="three fields" style="position: relative;top: 15px">
        <div class="field" id="class">
            <label>Preferred class</label>
            <select class="ui selection dropdown">
                <option value="none">Preferred Class</option>
                <option value="eco">Economy</option>
                <option value="bus">Business</option>
                <option value="fc">First Class</option>
            </select>
        </div>
        <div class="field" style="width: 20%">
            <label>No. of Passengers</label>
            <select class="ui selection dropdown">
                <option value="one">1</option>
                <option value="two">2</option>
                <option value="three">3</option>
                <option value="four">4</option>
                <option value="five">5</option>
                <option value="six">6</option>
            </select>
        </div>
        <div class="field" style="margin-left: 15px">
            <label style="margin-top: 5px;">Flexible dates</label>
            <input id="flex" name="flex" type="checkbox" style="float: left; margin-top: 10px;>">
            <div style="margin-top:10px; margin-left: 25px;">
                (+/- 3 days)
            </div>
        </div>
    </div>

    <br>
    <input id="postButton" class="ui large orange button" type="submit"
           value="Search"/>

</div>

</body>
</html>

