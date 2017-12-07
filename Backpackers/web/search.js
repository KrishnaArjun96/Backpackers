/**
 * Created by varun on 12/6/17.
 */

var search = JSON.parse(sessionStorage.getItem('results'));

$(window).on('load', getFlights());

function getFlights() {
    for (var i = 0; i < search.length; i++) {

        $('#result').append('<h2 class="ui horizontal divider header" style="margin: 25px">\n' +
            '    JFK -> DXB' +
            '</h2>');

        var way = search[i];

        for (var j = 0; j < search[i].length; j++) {

            var trip = way[j];

            var airline = (trip.airlines.length === 1) ? trip.airlines[0].name : 'Multiple Airlines';
            var time = trip.time;

            $('#result').append('<div class="ui orange segment" style="margin: 10px; width: 60%">\n' +
                '    <div class="ui grid">\n' +
                '        <div class="eight wide column">\n' +
                '            <h3>' + airline + '</h3>\n' +
                '            <h4>' + time + '</h4>\n' +
                '        </div>\n' +
                '        <div class="six wide column">\n' +
                '            <h4>Stops: 1</h4>\n' +
                '            <label>LHW</label>\n' +
                '        </div>\n' +
                '        <div class="two wide column">\n' +
                '            <h3>$ 1,400</h3>\n' +
                '            <label>Select:  <input type="radio" name="from"/></label>\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</div>');
        }
    }
}
