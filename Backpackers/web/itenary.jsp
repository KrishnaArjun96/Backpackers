<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 12/7/17
  Time: 4:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="index.js"></script>
<head>
    <title>Travel Itenary</title>
    <link rel="icon" href="tabIcon4.png">
</head>
<body>

<div class="ui orange tiered menu">
    <div class="menu">
        <a class="item" href="index.jsp">
            <i class="search icon"></i> Search Flights
        </a>
        <a class="active item" href="itenary.jsp">
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
        <a class="item" style="float: right">
            <i class="power icon"></i>
        </a>
    </div>
</div>

<div class="ui form" style="margin: 20px;">
    <div class="field">
        <div class=" two fields">
            <div class="two wide field">
                <input type="text" id="resr" placeholder="Reservation Number">
            </div>
            <div class="one wide field">
                <input id="goButton" class="ui medium orange button" type="submit"
                       value="Go"/>
            </div>
        </div>
    </div>
</div>

<div style="margin:20px;" id="itr">
    <h2>Your Itenary for resrvation 444 is</h2>
    <div class="ui raised segment" style="margin:20px;">
        
    </div>
</div>

<script>
    $(function () {
        $("#goButton").on('click', function () {
            var resr = $('#resr').val();

            $.ajax({
                type: 'GET',
                url: '/itinerary?resrNo=' + resr,
                success: function (data) {
                    $(document).ready(function () {
                        $('#itr').empty();

                        var p = JSON.parse(data.passengers);
                        var pData = '';
                        for (var j = 0; j < p.length; j++) {
                            pData += '<p>Name:' + p[j].name + ' Seat Preference: ' + p[j].seatPref + ' Meal Preference: ' + p[j].mealPref + '</p>\n';
                        }

                        var l = JSON.parse(data.legs);
                        var lData = '';
                        for (var j = 0; j < l.length; j++) {
                            console.log(l[j]);
                            lData += '<p>Flight:' + l[j].flight + ' Origin: ' + l[j].origin + ' Destination: ' + l[j].destination + ' Departure: ' + l[j].departure + ' Arrival: ' + l[j].arrival + ' Duration ' + l[j].duration + '</p>\n';
                        }

                        $('#itr').append('<h2>Your Itinerary for reservation ' + resr + ' is</h2>\n' +
                            '    <div class="ui raised segment" style="margin:20px;">\n' +
                            '        <h4 class="ui header">Customer Info</h4>\n' +
                            '        <p>Name:' + data.customer + '</p>\n' +
                            '        <p>Email:' + data.userId + '</p>\n' +
                            '        <p>Travel Class:' + data.classTravel + '</p>\n' +
                            '        <p>Booking Date:' + data.bookingDate + '</p>\n' +
                            '        <p>Fare: $' + data.fare + '</p>\n' +
                            '        <h3>Passengers</h3>\n' +
                            '        <div>\n' +
                            pData +
                            '        </div>\n' +
                            '        <h3>Legs</h3>\n' +
                            '        <div>\n' +
                            lData +
                            '    </div>');
                    });
                }
            });
        });
    });

</script>

</body>
</html>
