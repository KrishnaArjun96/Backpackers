var cur = "round";
var index = 0;
var items_list = ["multiDivButton0"];
var source_list = ["src0"];
var dst_list = ["dst0"];
var time_list = ["time0"];

function changeButton(changeTo) {
    if (cur != changeTo) {
        document.getElementById(cur).className = "ui button"
        document.getElementById(changeTo).className = "ui orange button";
        changeUI(changeTo);
    }
}

function changeUI(changeTo) {
    if (changeTo == "one") {
        document.getElementById('retDate').style.display = "none";
        document.getElementById('multiDiv').style.display = "none";
        document.getElementById('nonMulti').style.display = "block";
    }
    else if (changeTo == "multi") {
        document.getElementById('nonMulti').style.display = "none";
        document.getElementById('retDate').style.display = "block";
        document.getElementById('multiDiv').style.display = "block";
    }
    else {
        document.getElementById('retDate').style.display = "block";
        document.getElementById('multiDiv').style.display = "none";
        document.getElementById('nonMulti').style.display = "block";
    }
    cur = changeTo;
}

function addDiv() {
    index += 1;
    var newID = "multiDivButton" + index;
    var dstID = "dst" + index;
    var srcID = "src" + index;
    var timeID = "time" + index;
    if (items_list.length != 0) {
        $("#" + items_list[0]).css('display', 'inline');
    }
    $("#multiDiv").append("<div class=\"four fields\">\n" +
        "        <div class=\"four field\">\n" +
        "        <label>Source</label>\n" +
        "        <input type=\"text\" id='" + srcID + "' placeholder=\"Source\">\n" +
        "        </div>\n" +
        "        <div class=\"field\">\n" +
        "        <label>Destination</label>\n" +
        "        <input type=\"text\" id='" + dstID + "' placeholder=\"Destination\">\n" +
        "        </div>\n" +
        "        <div class=\"field\">\n" +
        "        <label>Departing</label>\n" +
        "        <input type=\"date\" id='" + timeID + "' name=\"departing\">\n" +
        "        </div>\n" +
        "        <div class=\"field\" style=\"margin-top: 25px;\">\n" +
        "        <div class=\"ui red icon button\" id='" + newID + "' onclick=\"removeDiv('" + newID + "');\">" +
        "        <i class=\"close icon\"></i>\n" +
        "        </div>\n" +
        "        </div>\n" +
        "        </div>");
    items_list.push(newID);
    source_list.push(srcID);
    dst_list.push(dstID);
    time_list.push(timeID);
}


function removeDiv(idToRemove) {
    // var number = idToRemove.toString().charAt(idToRemove.toString().length - 1);
    var x = items_list.indexOf(idToRemove.toString());
    items_list.splice(x, 1);
    source_list.splice(x, 1);
    dst_list.splice(x, 1);
    time_list.splice(x, 1);
    $("#" + idToRemove).parent().parent().remove();
    if (items_list.length == 1) {
        $("#" + items_list[0]).css('display', 'none');
    }
}


$(function () {

    $("#postButton").on('click', function () {

        var details = {};

        if (cur === 'one') {
            details.type = cur;
            details.source = $('#src').val();
            details.destination = $('#dest').val();
            details.depatureDate = $('#depatureDate').val();
            details.prefClass = $('#prefClass').val();
            details.noOfPass = $('#noOfPass').val();
            details.flexible = $('#flex').is(":checked");
        } else if (cur === 'round') {
            details.type = cur;
            details.source = $('#src').val();
            details.destination = $('#dest').val();
            details.depatureDate = $('#depatureDate').val();
            details.returnDate = $('#returnDate').val();
            details.prefClass = $('#prefClass').val();
            details.noOfPass = $('#noOfPass').val();
            details.flexible = $("#flex").is(":checked");
        } else {
            details.type = 'multi';
            details.prefClass = $('#prefClass').val();
            details.noOfPass = $('#noOfPass').val();
            details.flexible = $("#flex").is(":checked");
            details.trips = [];
            for (var i = 0; i < source_list.length; i++) {
                details.trips.push({
                    source: $('#' + source_list[i]).val(),
                    destination: $('#' + dst_list[i]).val(),
                    date: $('#' + time_list[i]).val()
                });
            }
        }

        //console.log(details);

        $.ajax({
            type: 'POST',
            url: '/Search',
            contentType: 'application/json',
            data: JSON.stringify(details),
            success: function (data) {
                sessionStorage.setItem('results', JSON.stringify(data));
                window.location.href = "search.jsp";
            }
        });

    });
});