<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 12/7/17
  Time: 4:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="index.js"></script>
<head>
    <title>Flight Suggestions</title>
    <link rel="icon" href="tabIcon4.png">
</head>
<body>

<div class="ui orange tiered menu">
    <div class="menu">
        <a class="item" href="index.jsp">
            <i class="search icon"></i>
            Search Flights
        </a>
        <a class="item" href="itenary.jsp">
            <i class="suitcase icon"></i> Travel Itenary
        </a>
        <a class="active item" href="cusFlightSug.jsp">
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

<div style="margin: 20px;">
    <h1 style="color: #e05d0f">Flight Suggestions.</h1>
    <table class="ui sortable orange celled structured table" id="bstTable" style="margin: 20px; width: 40%; ">
        <thead>
        <tr>
            <th rowspan="2" class="center aligned">Flight</th>
            <th rowspan="2" class="center aligned">Airport</th>
            <th rowspan="2" class="center aligned">City</th>
            <th rowspan="2" class="center aligned">Country</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>


<script>

    $(window).on('load', getFlightSuggestions());

    function getFlightSuggestions() {
        $.ajax({
            type: 'GET',
            url: "/flightsuggestions?userId=awesomejane@ftw.com",
            success: function (data) {
                $(document).ready(function () {

                    for (var i = 0; i < data.length; i++) {

                        var dests = JSON.parse(data[i].destinations);
                        var rs = dests.length;

                        $('#bstTable tbody').append('<tr>\n' +
                            '        <td rowspan=' + rs + 'class="center aligned">' + data[i].flight + '</td>\n' +
                            '        <td class="center aligned">' + dests[0].airport + '</td>\n' +
                            '        <td class="center aligned">' + dests[0].city + '</td>\n' +
                            '        <td class="center aligned">' + dests[0].country + '</td>\n' +
                            '    </tr>');

                        if (rs > 1) {
                            for (var j = 1; j < rs; j++) {
                                $('#bstTable tbody').append('<tr>\n' +
                                    '        <td class="center aligned">' + dests[j].airport + '</td>\n' +
                                    '        <td class="center aligned">' + dests[j].city + '</td>\n' +
                                    '        <td class="center aligned">' + dests[j].country + '</td>\n' +
                                    '    </tr>');
                            }
                        }
                    }
                });
            }
        });
    }

</script>


</body>
</html>
