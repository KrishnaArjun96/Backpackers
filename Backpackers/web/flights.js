var cur = 'all';

$(window).on('load', getFlights());

function getFlights() {
    console.log("aking ajaj call");
    $.ajax({
        type: 'GET',
        url: "/flights",
        success: function (data) {
            $(document).ready(function () {
                for (var i = 0; i < data.length; i++) {
                    var obj = data[i];
                    $('#flightTable tbody').append('<tr>\n' +
                        '        <td>EK 203</td>\n' +
                        '        <td>DXB</td>\n' +
                        '        <td>JFK</td>\n' +
                        '        <td>14:00</td>\n' +
                        '        <td>5</td>\n' +
                        '        <td class="center aligned">\n' +
                        '            <i class="large green checkmark icon"></i>\n' +
                        '        </td>\n' +
                        '        <td></td>\n' +
                        '        <td class="center aligned">\n' +
                        '            <i class="large green checkmark icon"></i>\n' +
                        '        </td>\n' +
                        '        <td class="center aligned">\n' +
                        '            <i class="large green checkmark icon"></i>\n' +
                        '        </td>\n' +
                        '        <td></td>\n' +
                        '        <td class="center aligned">\n' +
                        '            <i class="large green checkmark icon"></i>\n' +
                        '        </td>\n' +
                        '        <td></td>\n' +
                        '    </tr>');
                }
                //});
            });
        }
    });

}


