var cur = "flightNo";

function changeButton(changeTo) {

    if (cur != changeTo) {
        document.getElementById(cur).className = "ui button"
        document.getElementById(changeTo).className = "ui orange button";
    }

    if (changeTo === "flightNo" || changeTo === "customerOnFlight") {
        document.getElementById('fl').style.display = "block";
        document.getElementById('cus').style.display = "none";
        cur = (changeTo === "flightNo" ) ? "flightNo" : "customerOnFlight";
    } else {
        document.getElementById('fl').style.display = "none";
        document.getElementById('cus').style.display = "block";
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
            url: '/reservations?name=' + $('#name').val() + '&type=' + cur,
            success: function (data) {
                console.log("HERE"+data);
            }
        });
    });

    $("#flightNoButton").on('click', function () {
        $.ajax({
            type: 'GET',
            url: '/reservations?flightNo=' + $('#flightNoInput').val() + '&date=' + $('#date').val() + '&type=' + cur,
            success: function (data) {
                console.log(typeof (data));
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
});

