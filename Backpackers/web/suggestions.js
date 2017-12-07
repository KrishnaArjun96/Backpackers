$(function () {
    $("#cusNameButton").on('click', function () {
        console.log("HRE");
        $.ajax({
            type: 'GET',
            url: '/flightsuggestions?userId=' + $('#name').val() + '&type=cusrep',
            success: function (data) {
                console.log(data);
                $(document).ready(function () {
                    for (var i = 0; i < data.length; i++) {
                        console.log(data[i]);
                        console.log(data[i].destinations);
                        var airports = JSON.parse(data[i].destinations);
                        var dest = '';

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
    )
});
