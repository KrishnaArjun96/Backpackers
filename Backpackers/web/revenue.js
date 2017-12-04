$(function () {

    $("#goButton").on('click', function () {
        $.ajax({
            type: 'GET',
            url: '/revenue?value=' + $('#value').val() + '&type=' + $('#search-select').val(),
            success: function (data) {
                console.log(data)
            }
        });
    });

});

