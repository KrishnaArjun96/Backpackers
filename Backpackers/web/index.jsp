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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="index.js"></script>
<head>
    <title>Welcome!</title>
    <link rel="icon" href="tabIcon4.png">
</head>

<body>

<div class="ui orange tiered menu">
    <div class="menu">
        <a class="active item">
            <i class="search icon"></i> Search Flights
        </a>
        <a class="item" href="itenary.jsp">
            <i class="suitcase icon"></i> Travel Itenary
        </a>
        <a class="item" href="cusFlightSug.jsp">
            <i class="globe icon"></i> Flight Suggestions
        </a>
        <a class="item" href="curReservations.jsp">
            <i class="browser icon"></i> Current Reservations
        </a>
        <a class="item" href="bestSeller.jsp">
            <i class="tags icon"></i> Best Seller
        </a>
        <a class="item" href="resvrHistory.jsp">
            <i class="archive icon"></i> Reservation History
        </a>
        <a class="item" style="float: right" href="login.jsp">
            <i class="power icon"></i>
        </a>
    </div>
</div>


<div style="margin: 35px">
    <h1 style="color: #e05d0f;">Backpackers</h1>

    <div class="ui buttons" name="type">
        <button id="round" class="ui orange button" onclick="changeButton('round')">Roundtrip</button>
        <button id="one" class="ui button" onclick="changeButton('one')">One way</button>
        <button id="multi" class="ui button" onclick="changeButton('multi')">Multi-City</button>
    </div>

    <br><br>

    <div class="ui form">
        <div id="nonMulti">
            <div class="two fields">
                <div class="field">
                    <label>Source</label>
                    <input id="src" type="text" placeholder="Source" name="source">
                </div>
                <div class="field">
                    <label>Destination</label>
                    <input type="text" id="dest" placeholder="Destination" name="destination">
                </div>
            </div>
            <div class="three fields">
                <div class="field">
                    <label>Departing</label>
                    <input type="date" id="depatureDate" name="departing">
                </div>
                <div class="field" id="retDate">
                    <label>Return Date</label>
                    <input type="date" id="returnDate" name="returning"/>
                </div>

            </div>
        </div>
        <div id="multiDiv" style="position: relative;top: 10px; display:none;">
            <div id="header" style="height: 15px;width: 200px">
                <h2 class="orngHeader" style="position: relative;float: left;width: 90px">Trips</h2>
                <div class="ui orange icon button" style="position: relative;float: left;" onclick="addDiv()">
                    <i class="add icon"></i>
                </div>
            </div>
            <div class="four fields">
                <div class="four field">
                    <label>Source</label>
                    <input type="text" placeholder="Source" id="src0">
                </div>
                <div class="field">
                    <label>Destination</label>
                    <input type="text" placeholder="Destination" id="dst0">
                </div>
                <div class="field">
                    <label>Departing</label>
                    <input type="date" name="departing" id="time0">
                </div>
                <div class="field" style="margin-top: 35px;">
                    <div onclick="removeDiv('multiDivButton0');" class="ui red icon button" style=" display: none"
                         id="multiDivButton0">
                        <i class="close icon"></i>
                    </div>
                </div>
            </div>

        </div>

        <div class="three fields" style="position: relative;top: 15px">
            <div class="field" id="class">
                <label>Preferred class</label>
                <select id="prefClass" class="ui selection dropdown">
                    <option value="eco">Economy</option>
                    <option value="bus">Business</option>
                    <option value="fc">First Class</option>
                </select>
            </div>
            <div class="field" style="width: 20%">
                <label>No. of Passengers</label>
                <select id="noOfPass" class="ui selection dropdown">
                    <option value=1>1</option>
                    <option value=2>2</option>
                    <option value=3>3</option>
                    <option value=4>4</option>
                    <option value=5>5</option>
                    <option value=6>6</option>
                </select>
            </div>
            <div class="field" style="margin-left: 15px">
                <label style="margin-top: 5px;">Flexible dates</label>
                <input id="flex" type="checkbox" style="float: left; margin-top: 10px;">
                <div style="margin-top:10px; margin-left: 25px;">
                    (+/- 3 days)
                </div>
            </div>
        </div>

        <br>
        <input id="postButton" class="ui large orange button" type="submit"
               value="Search"/>

    </div>

</div>

</body>
</html>

