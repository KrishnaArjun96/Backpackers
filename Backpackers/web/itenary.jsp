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
    <h2>You Itenary for resrvation 444 is</h2>
    <div class="ui raised segment" style="margin:20px;">
        <h4 class="ui header">A header</h4>
        <p>"customer": "Jane Smith"</p>
        <p>"customer": "Jane Smith"</p>
        <p>"customer": "Jane Smith"</p>
        <p>"customer": "Jane Smith"</p>
        <h3>Passengers</h3>
        <div>
            <p>{\"name\":\"Celestino Cremin\",\"seatPref\":\"Aisle\",\"mealPref\":\"Vegan\"}</p>
            <p>{\"name\":\"Elliot Blick\",\"seatPref\":\"window\",\"mealPref\":\"Low Calorie\"}</p>
            <p>{\"name\":\"Jane Smith\",\"seatPref\":\"window\",\"mealPref\":\"Veg\"}</p>
        </div>
        <h3>Legs</h3>
        <div>
            <p>{\"flight\":\"EK 567\",\"origin\":\"BLR: Bengaluru, India\",\"destination\":\"DXB: Dubai, United Arab
                Emirates\",\"departure\":\"20:00:00\",\"arrival\":\"23:10:00\",\"duration\":\"04:00:00\"}</p>
            <p> {\"flight\":\"EK 203\",\"origin\":\"DXB: Dubai, United Arab Emirates\",\"destination\":\"JFK: New York,
                United States of
                America\",\"departure\":\"02:30:00\",\"arrival\":\"08:15:00\",\"duration\":\"14:00:00\"}</p>
            <p> {\"flight\":\"EK 203\",\"origin\":\"DXB: Dubai, United Arab Emirates\",\"destination\":\"JFK: New York,
                United States of
                America\",\"departure\":\"02:30:00\",\"arrival\":\"08:15:00\",\"duration\":\"14:00:00\"},</p>
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#goButton").on('click', function () {
            var month = $('#search-select').val();
            var year = $('#year').val();
            $.ajax({
                type: 'GET',
                url: '/sales?month=' + month + '&year=' + year,
                success: function (data) {
                    $(document).ready(function () {
                        $('#itr').empty();

                        $('#itr').append('<h2>You Itenary for resrvation 444 is</h2>\n' +
                            '    <div class="ui raised segment" style="margin:20px;">\n' +
                            '        <h4 class="ui header">Customer Info</h4>\n' +
                            '        <p>Name:'+ data.customer + '</p>\n' +
                            '        <p>Email:'+ data.userId + '</p>\n' +
                            '        <p>Travel Class:'+ data.class + '</p>\n' +
                            '        <p>Booking Date:'+ data.bookingDate + '</p>\n' +
                            '        <p>Fare: $'+ data.fare + '</p>\n' +
                            '        <h3>Passengers</h3>\n' +
                            '        <div>\n' +
                            '            <p>name:Celestino Cremin seatPref:Aisle mealPref:Vegan</p>\n' +
                            '            <p>name:Celestino Cremin seatPref:Aisle mealPref:Vegan</p>\n' +
                            '            <p>name:Celestino Cremin seatPref:Aisle mealPref:Vegan</p>\n' +
                            '        </div>\n' +
                            '        <h3>Legs</h3>\n' +
                            '        <div>\n' +
                            '            <p>flight:EK 567 origin:BLR destination:DXB: Dubai departure:20:00:00 arrival:23:10:00 duration:04:00:00</p>\n' +
                            '            <p>flight:EK 567 origin:BLR destination:DXB: Dubai departure:20:00:00 arrival:23:10:00 duration:04:00:00</p>\n' +
                            '            <p>flight:EK 567 origin:BLR destination:DXB: Dubai departure:20:00:00 arrival:23:10:00 duration:04:00:00</p>\n' +
                            '    </div>');
                    });
                }
            });
        });
    });

</script>

</body>
</html>
