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

                        for(var j =0 ;j<airports.length;j++){
                            if(j !== airports.length-1)
                                dest += airports[i];
                            else
                                dest += airports[i]+', ';
                        }

                        $('#customerTable tbody').append('<tr>\n' +
                            '        <td class="center aligned">' + data[i].flight + '</td>\n' +
                            '        <td class="center aligned">' + dest + '</td>\n' +
                            '    </tr>');
                    }
                });
            }
        });
    });
});