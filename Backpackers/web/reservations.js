var cur = "flightNo";

function changeButton(changeTo) {

    if (cur != changeTo) {
        document.getElementById(cur).className = "ui button"
        document.getElementById(changeTo).className = "ui orange button";
    }

    if (changeTo === "flightNo") {
        $(document).ready(function() {
            $('#flightTable').find("tr:gt(0)").remove();
        });
        document.getElementById('fl').style.display = "block";
        document.getElementById('cn').style.display = "none";
        document.getElementById('cof').style.display = "none";
        cur = "flightNo";
    }
    else if (changeTo === "customerOnFlight") {
        $(document).ready(function() {
            $('#cusFlightTable').find("tr:gt(0)").remove();
        });
        document.getElementById('fl').style.display = "none";
        document.getElementById('cn').style.display = "none";
        document.getElementById('cof').style.display = "block";
        cur = "customerOnFlight";
    }
    else {
        $(document).ready(function() {
            $("#customerTable").find("tr:gt(0)").remove();
        });
        document.getElementById('fl').style.display = "none";
        document.getElementById('cn').style.display = "block";
        document.getElementById('cof').style.display = "none";
        cur = "customerName";
    }
}


$('.ui.orange.tiered.menu')
    .on('click', '.item', function () {
        if (!$(this).hasClass('dropdown')) {
            $(this)
                .addClass('active')
                .siblings('.item')
                .removeClass('active');
        }
    });


$(function () {

    $("#cusNameButton").on('click', function () {
        $.ajax({
            type: 'GET',
            url: '/reservationListing?name=' + $('#name').val() + '&type=' + cur,
            success: function (data) {
                $(document).ready(function () {
                    for (var i = 0; i < data.length; i++) {
                        $('#customerTable tbody').append('<tr>\n' +
                            '        <td class="center aligned">' + data[i].resrNo + '</td>\n' +
                            '        <td class="center aligned">' + data[i].flight + '</td>\n' +
                            '        <td class="center aligned">' + data[i].rep + '</td>\n' +
                            '        <td class="center aligned">' + data[i].bookingDate + '</td>\n' +
                            '    </tr>');
                    }
                });
            }
        });
    });

    $("#flightNoButton").on('click', function () {
        $.ajax({
            type: 'GET',
            url: '/reservationListing?flightNo=' + $('#flightNoInput').val() + '&date=' + $('#datefl').val() + '&type=' + cur,
            success: function (data) {
                $(document).ready(function () {
                    for (var i = 0; i < data.length; i++) {
                        $('#flightTable tbody').append('<tr>\n' +
                            '        <td class="center aligned">' + data[i].resr + '</td>\n' +
                            '        <td class="center aligned">' + data[i].bookingId + '</td>\n' +
                            '        <td class="center aligned">' + data[i].bookingDate + '</td>\n' +
                            '        <td class="center aligned">' + data[i].rep + '</td>\n' +
                            '    </tr>');
                    }
                });
            }
        });
    });

    $("#cusOnFlButton").on('click', function () {
        console.log($('#cusFlightNoInput').val());
        $.ajax({
            type: 'GET',
            url: '/reservationListing?flightNo=' + $('#cusFlightNoInput').val() + '&date=' + $('#date').val() + '&type=' + cur,
            success: function (data) {

                $(document).ready(function () {
                    for (var i = 0; i < data.length; i++) {
                        $('#cusFlightTable tbody').append('<tr>\n' +
                            '        <td class="center aligned">' + data[i].resrNo + '</td>\n' +
                            '        <td class="center aligned">' + data[i].passenger + '</td>\n' +
                            '        <td class="center aligned">' + data[i].travelDate + '</td>\n' +
                            '        <td class="center aligned">' + data[i].travelClass + '</td>\n' +
                            '    </tr>');
                    }
                });
            }
        });
    });
});

