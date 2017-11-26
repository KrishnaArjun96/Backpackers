<%@ page import="Classes.Data" %>
<%@ page import="java.sql.SQLException" %><%--
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

<script>
    var index = 0;
    var items_list = ["multiDivButton0"];
    function addDiv() {
        index+=1;
        var newID = "multiDivButton" + index;
        if (items_list.length != 0){
                $("#" + items_list[0]).css('display','inline');
        }
        $("#multiDiv").append("<div class=\"four fields\">\n" +
            "        <div class=\"four field\">\n" +
            "        <label>Source</label>\n" +
            "        <input type=\"text\" placeholder=\"Source\">\n" +
            "        </div>\n" +
            "        <div class=\"field\">\n" +
            "        <label>Destination</label>\n" +
            "        <input type=\"text\" placeholder=\"Destination\">\n" +
            "        </div>\n" +
            "        <div class=\"field\">\n" +
            "        <label>Departing</label>\n" +
            "        <input type=\"date\" name=\"departing\">\n" +
            "        </div>\n" +
            "        <div class=\"field\">\n" +
            "        <div class=\"ui button\" style=\"margin-top: 25px\" id='" + newID  + "' onclick=\"removeDiv('" + newID  + "');\">" +
            "        <i class=\"close icon\"></i>\n" +
            "        </div>\n" +
            "        </div>\n" +
            "        </div>");
        items_list.push(newID);
    }
    function removeDiv(idToRemove) {
//        alert(items_list.toString());
//        alert(idToRemove);
        var x = items_list.indexOf(idToRemove.toString());
//        alert(x);
        items_list.splice(x,1);
        $("#" +idToRemove).parent().parent().remove();
        if (items_list.length == 1){
                $("#" + items_list[0]).css('display','none');
        }
//        alert(items_list.toString());
//        alert(items_list.length);
    }

</script>

<head>
    <title>Welcome!</title>
</head>

<body>

<h1 class="orngHeader">Backpackers</h1>

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
                <input type="text" placeholder="Source">
            </div>
            <div class="field">
                <label>Destination</label>
                <input type="text" placeholder="Destination">
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

        <div id="multiDiv" style="position: relative;top: 10px;">
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
                    <div onclick="removeDiv('multiDivButton0');" class="ui button" style="margin-top: 25px; display: none" id="multiDivButton0">
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
                <input id="flex" type="checkbox" style="float: left; margin-top: 10px;>">
                <div style="margin-top:10px; margin-left: 25px;">
                    (+/- 3 days)
                </div>
            </div>
        </div>

        <br>
        <input class="ui large orange button" type="submit"
               value="Search"/>

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

