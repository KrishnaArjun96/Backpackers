var cur = "all";

$(window).on('load', getEmployees());

function getEmployees() {
    $.ajax({
        type: 'GET',
        url: "/employee",
        success: function (data) {
            $(document).ready(function () {
                for (var i = 0; i < data.length; i++) {
                    $('#empTable tbody').append('<tr>\n' +
                        '        <td class="center aligned">' + data[i].fName + '</td>\n' +
                        '        <td class="center aligned">' + data[i].lName + '</td>\n' +
                        '        <td class="center aligned">' + data[i].address + '</td>\n' +
                        '        <td class="center aligned">' + data[i].city + '</td>\n' +
                        '        <td class="center aligned">' + data[i].state + '</td>\n' +
                        '        <td class="center aligned">' + data[i].country + '</td>\n' +
                        '        <td class="center aligned">' + data[i].phone + '</td>\n' +
                        '        <td class="center aligned">' + data[i].zip + '</td>\n' +
                        '        <td class="center aligned">' + data[i].ssn + '</td>\n' +
                        '        <td class="center aligned">' + data[i].startDate + '</td>\n' +
                        '        <td class="center aligned"> $' + data[i].wage + '</td>\n' +
                        '    </tr>');
                }
            });
        }
    });
}


function changeButton(changeTo){

    if (cur != changeTo) {
        document.getElementById(cur).className = "ui button"
        document.getElementById(changeTo).className = "ui orange button";
    }


    if(changeTo === "all"){
        document.getElementById('empAll').style.display = "block";
        document.getElementById('empAdd').style.display = "none";
        document.getElementById('empDel').style.display = "none";
        document.getElementById('postButton').style.display = "none";
        cur = "all";
    }
    else if(changeTo === "add"){
        document.getElementById('empAll').style.display = "none";
        document.getElementById('empAdd').style.display = "block";
        document.getElementById('empDel').style.display = "none";
        document.getElementById('postButton').style.display = "block";
        document.getElementById('postButton').value = "Add";
        cur = "add";
    } else if(changeTo === "edit"){
        document.getElementById('empAll').style.display = "none";
        document.getElementById('empAdd').style.display = "block";
        document.getElementById('empDel').style.display = "none";
        document.getElementById('postButton').style.display = "block";
        document.getElementById('postButton').value = "Edit";
        cur = "edit";
    } else{
        document.getElementById('empAll').style.display = "none";
        document.getElementById('empAdd').style.display = "none";
        document.getElementById('empDel').style.display = "block";
        document.getElementById('postButton').style.display = "block";
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
            details.firstName = $('#firstName').val();
            details.lastName = $('#lastName').val();
            details.startDate = $('#startDate').val();
            details.role = $('#role').val();
            details.wage = $('#wage').val();
            details.address = $('#address').val();
            details.city = $('#city').val();
            details.state = $('#state').val();
            details.country = $('#country').val();
            details.zip = $('#zip').val();
            details.phone = $('#phone').val();

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
                if(data.success){
                    alert("Success: "+details.firstName+" has been "+ cur +"ed.");
                }else{
                    alert("Failed: "+data.error);
                }
            }
        });

    });
});





