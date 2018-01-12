<%--
  Created by IntelliJ IDEA.
  User: varun
  Date: 12/6/17
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="confirm.js"></script>
<link rel="stylesheet" href="confirm.css">

<html>
<head>
    <title>Confirm your booking</title>
    <div>
        <h2 class="ui image header" style="margin: 20px">
            <div class="ui orange header">
                Confirm your booking
            </div>
        </h2>
        <div id="result" style="margin: 20px">
        </div>

        <form class="ui form" style="margin: 20px" >
            <h3 class="ui image header">
                <div class="ui orange header">
                    Passenger Information
                </div>
            </h3>
            <div id="passengerForm" >
            </div>

        </form>

        <h3><div>
            <div class="field" style="float: right; top: 30%; right: 20%; overflow-y:auto; position:fixed; margin: 20px">
                <label style="margin-top: 5px;">Reverse Auction</label>
                <input id="flex" type="checkbox">
            </div>
        </div></h3>



        <button class="ui orange button" id="goButton" onclick="bookFlight()" style="float: right; top: 40%; right: 25%; overflow-y:auto; position:fixed; margin: 20px">Book</button>



    </div>

</head>
<body>

</body>
</html>
