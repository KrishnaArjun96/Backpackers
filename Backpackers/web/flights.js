$(window).on('load', getFlights());

function getFlights() {
    $.ajax({
        type: 'GET',
        url: "/flights",
        success: function (data) {
            $(document).ready(function () {
                for (var i = 0; i < data.length; i++) {
                    var days = [];
                    for(var j=0;j<7;j++){
                        days[j] = parseInt(data[i].days.charAt(j)) ? "\"large green checkmark icon\"" : "\"large red remove icon\"";
                    }

                    $('#flightTable tbody').append('<tr>\n' +
                        '        <td class="center aligned">' + data[i].flightNo + '</td>\n' +
                        '        <td class="center aligned">' + data[i].origin + '</td>\n' +
                        '        <td class="center aligned">' + data[i].destination + '</td>\n' +
                        '        <td class="center aligned">' + data[i].duration + '</td>\n' +
                        '        <td class="center aligned">' + data[i].noOfDays + '</td>\n' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[0] + '></i>\n' +
                        '        </td>\n' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[1] + '></i>\n' +
                        '        </td>\n' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[2] + '></i>\n' +
                        '        </td>\n' + '' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[3] + '></i>\n' +
                        '        </td>\n' + '' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[4] + '></i>\n' +
                        '        </td>\n' + '' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[5] + '></i>\n' +
                        '        </td>\n' + '' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[6] + '></i>\n' +
                        '        </td>\n' +
                        '    </tr>');
                }
            });
        }
    });
}


