var cur = "add";

function changeButton(changeTo){

    if (cur != changeTo) {
        document.getElementById(cur).className = "ui button"
        document.getElementById(changeTo).className = "ui orange button";
    }

    if(changeTo === "add"){
        document.getElementById('empAdd').style.display = "block";
        document.getElementById('empDel').style.display = "none";
        document.getElementById('postButton').value = "Add";
        cur = "add";
    } else if(changeTo === "edit"){
        document.getElementById('empAdd').style.display = "block";
        document.getElementById('empDel').style.display = "none";
        document.getElementById('postButton').value = "Edit";
        cur = "edit";
    } else{
        document.getElementById('empAdd').style.display = "none";
        document.getElementById('empDel').style.display = "block";
        document.getElementById('postButton').value = "Delete";
        cur = "delete";
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

    $("#postButton").on('click', function () {

        var details = {};
        var reqType = 'POST';

        if (cur === 'add' || cur === 'edit') {
            details.ssn = $('#ssn').val();
            details.name = $('#name').val();
            details.startDate = $('#startDate').val();
            details.role = $('#role').val();
            details.wage = $('#wage').val();

            if(cur === 'edit'){
                reqType = "PUT";
            }

        } else {
            details.ssn = $('#ssnDel').val();
            reqType = 'DELETE';
        }

        console.log(details);

        $.ajax({
            type: reqType,
            url: '/employee',
            contentType: 'application/json',
            data: JSON.stringify(details),
            success: function(data){
                console.log(data);
            }
        });

    });
});
