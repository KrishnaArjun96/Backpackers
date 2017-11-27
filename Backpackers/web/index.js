var cur = "round";
var index = 0;
var items_list = ["multiDivButton0"];

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
    else if(changeTo == "multi"){
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
    index+=1;
    var newID = "multiDivButton" + index;
    if (items_list.length != 0){
        $("#" + items_list[0]).css('display','inline');
    }
    $("#multiDiv").append("<div class=\"four fields\">\n" +
        "        <div class=\"four field\">\n" +
        "        <label>Source</label>\n" +
        "        <input type=\"text\" placeholder=\"Source\">\n" +
        "        </div>\n" +
        "        <div class=\"field\">\n" +
        "        <label>Destination</label>\n" +
        "        <input type=\"text\" placeholder=\"Destination\">\n" +
        "        </div>\n" +
        "        <div class=\"field\">\n" +
        "        <label>Departing</label>\n" +
        "        <input type=\"date\" name=\"departing\">\n" +
        "        </div>\n" +
        "        <div class=\"field\" style=\"margin-top: 25px;\">\n" +
        "        <div class=\"ui red icon button\" id='" + newID  + "' onclick=\"removeDiv('" + newID  + "');\">" +
        "        <i class=\"close icon\"></i>\n" +
        "        </div>\n" +
        "        </div>\n" +
        "        </div>");
    items_list.push(newID);
}


function removeDiv(idToRemove) {
    var x = items_list.indexOf(idToRemove.toString());
    items_list.splice(x,1);
    $("#" +idToRemove).parent().parent().remove();
    if (items_list.length == 1){
        $("#" + items_list[0]).css('display','none');
    }
}
