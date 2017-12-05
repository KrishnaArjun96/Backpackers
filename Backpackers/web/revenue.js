$(window).on('load', getFlights());

function getFlights() {
    $.ajax({
        type: 'GET',
        url: "/revenue?type=flightNo",
        success: function (data) {
            console.log(data);
            $(document).ready(function () {
                for (var i = 0; i < data.length; i++) {

                    $('#fnoTable tbody').append('<tr>\n' +
                        '        <td class="center aligned">' + data[i].flight + '</td>\n' +
                        '        <td class="center aligned"> $' + data[i].revenue + '</td>\n' +
                        '    </tr>');
                }
            });
        }
    });

    $.ajax({
        type: 'GET',
        url: "/revenue?type=city",
        success: function (data) {
            console.log(data);
            $(document).ready(function () {
                for (var i = 0; i < data.length; i++) {

                    $('#cityTable tbody').append('<tr>\n' +
                        '        <td class="center aligned">' + data[i].city + '</td>\n' +
                        '        <td class="center aligned"> $' + data[i].revenue + '</td>\n' +
                        '    </tr>');
                }
            });
        }
    });

    $.ajax({
        type: 'GET',
        url: "/revenue?type=customer",
        success: function (data) {
            console.log(data);
            $(document).ready(function () {
                for (var i = 0; i < data.length; i++) {
                    $('#cusTable tbody').append('<tr>\n' +
                        '        <td class="center aligned">' + data[i].customerName + '</td>\n' +
                        '        <td class="center aligned"> $' + data[i].revenue + '</td>\n' +
                        '    </tr>');
                }
            });
        }
    });

    $.ajax({
        type: 'GET',
        url: "/revenue?type=customerRep",
        success: function (data) {
            console.log(data);
            $(document).ready(function () {
                for (var i = 0; i < data.length; i++) {

                    $('#cusRepTable tbody').append('<tr>\n' +
                        '        <td class="center aligned">' + data[i].rep + '</td>\n' +
                        '        <td class="center aligned"> $' + data[i].revenue + '</td>\n' +
                        '    </tr>');
                }
            });
        }
    });
}


var cur = 'fnoBut';

function changeButton(changeTo) {

    if (cur != changeTo) {
        document.getElementById(cur).className = "ui button"
        document.getElementById(changeTo).className = "ui orange button";
    }

    if (changeTo === "fnoBut") {
        document.getElementById('fno').style.display = "block";
        document.getElementById('city').style.display = "none";
        document.getElementById('cus').style.display = "none";
        document.getElementById('cusRep').style.display = "none";
        cur = "fnoBut";
    }
    else if (changeTo === "cityBut") {
        document.getElementById('fno').style.display = "none";
        document.getElementById('city').style.display = "block";
        document.getElementById('cus').style.display = "none";
        document.getElementById('cusRep').style.display = "none";
        cur = "cityBut";
    }
    else if (changeTo === "cusBut") {
        document.getElementById('fno').style.display = "none";
        document.getElementById('city').style.display = "none";
        document.getElementById('cus').style.display = "block";
        document.getElementById('cusRep').style.display = "none";
        cur = "cusBut";
    }
    else {
        document.getElementById('fno').style.display = "none";
        document.getElementById('city').style.display = "none";
        document.getElementById('cus').style.display = "none";
        document.getElementById('cusRep').style.display = "block";
        cur = "cusRepBut";
    }
}

function filter(inputName, tbName) {
    var input, filter, table, tr;
    input = document.getElementById(inputName);
    filter = input.value.toUpperCase();
    table = document.getElementById(tbName);
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (var i = 0; i < tr.length; i++) {
        var val = tr[i].getElementsByTagName("td")[0];
        if (val) {
            if (val.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

