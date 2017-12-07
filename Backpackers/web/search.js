/**
 * Created by varun on 12/6/17.
 */

var search = JSON.parse(sessionStorage.getItem('results'));

$(window).on('load', getFlights());

function getFlights() {

    $(document).ready(function () {

        for (var i = 0; i < search.length; i++) {

            var way = search[i];
            var lengthOfLegs = way[0].legs.length;
            $('#result').append('<h2 class="ui horizontal divider header" style="margin: 25px">\n' +
                '    ' + way[0].legs[0].origin.id + ' -> ' + way[0].legs[lengthOfLegs-1].destination.id +'  '+
                '</h2>');

            for (var j = 0; j < way.length; j++) {

                var trip = way[j];

                var airline = (trip.airlines.length === 1) ? trip.airlines[0].name : 'Multiple Airlines';
                var time = trip.time;
                var stops = trip.stops.length;

                var stopNames = '';
                for (var k = 0; k < trip.stops.length; k++) {
                    if (k === trip.stops.length - 1)
                        stopNames += trip.stops[k];
                    else
                        stopNames += trip.stops[k] + ', ';
                }
                var price = trip.totalFare;

                $('#result').append('<div class="ui orange segment" style="margin: 10px; width: 60%">\n' +
                    '    <div class="ui grid">\n' +
                    '        <div class="eight wide column">\n' +
                    '            <h3>' + airline + '</h3>\n' +
                    '            <h4>' + time + '</h4>\n' +
                    '        </div>\n' +
                    '        <div class="six wide column">\n' +
                    '            <h4>Stops: ' + stops + '</h4>\n' +
                    '            <label>' + stopNames + '</label>\n' +
                    '        </div>\n' +
                    '        <div class="two wide column">\n' +
                    '            <h3>$ ' + price + '</h3>\n' +
                    '            <label>Select:  <input type="radio" name="from"/></label>\n' +
                    '        </div>\n' +
                    '    </div>\n' +
                    '</div>');
            }
        }

    });
}
