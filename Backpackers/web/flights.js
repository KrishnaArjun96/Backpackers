var cur = 'all';

function changeButton(changeTo) {

    if (cur != changeTo) {
        document.getElementById(cur).className = "ui button"
        document.getElementById(changeTo).className = "ui orange button";
        cur = changeTo;
    }

    // if (changeTo === "all") {
    //     document.getElementById('empAdd').style.display = "block";
    //     document.getElementById('empDel').style.display = "none";
    //     document.getElementById('postButton').value = "Add";
    //     cur = "all";
    // } else if (changeTo === "ma") {
    //     document.getElementById('empAdd').style.display = "block";
    //     document.getElementById('empDel').style.display = "none";
    //     document.getElementById('postButton').value = "Edit";
    //     cur = "ma";
    // } else if (changeTo === 'airport') {
    //     document.getElementById('empAdd').style.display = "none";
    //     document.getElementById('empDel').style.display = "block";
    //     document.getElementById('postButton').value = "Delete";
    //     cur = "airport";
    // } else {
    //
    // }
}
