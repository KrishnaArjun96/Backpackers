var cur = 'all';

$(window).on('load', getFlights());

function changeButton(changeTo) {

    if (cur != changeTo) {
        document.getElementById(cur).className = "ui button"
        document.getElementById(changeTo).className = "ui orange button";
        cur = changeTo;
    }
}

function getFlights() {
    console.log("aking ajaj call");
    $.ajax({
        type: 'GET',
        url: "/flights",
        success: function (data) {
            console.log(data);
        }
    });
}
