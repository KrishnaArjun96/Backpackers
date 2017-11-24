var cur = "round";

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
    }
    else {
        document.getElementById('retDate').style.display = "block";
    }
    cur = changeTo;
}