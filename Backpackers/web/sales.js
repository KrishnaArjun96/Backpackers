$('.ui.orange.tiered.menu')
    .on('click', '.item', function () {
        if (!$(this).hasClass('dropdown')) {
            $(this)
                .addClass('active')
                .siblings('.item')
                .removeClass('active');
        }
    });
$('#search-select').dropdown();


$(function () {
    $("#goButton").on('click', function () {
        var month = $('#search-select').val();
        var year = $('#year').val();
        $.ajax({
            type: 'GET',
            url: '/sales?month=' + month + '&year=' + year,
            success: function (data) {
                $(document).ready(function () {
                    $("#salesTable").find("tr:gt(0)").remove();
                    for (var i = 0; i < data.length ; i++) {
                        $('#salesTable tbody').append('<tr>\n' +
                            '        <td class="center aligned">' + data[i].resr + '</td>\n' +
                            '        <td class="center aligned">' + data[i].rep + '</td>\n' +
                            '        <td class="center aligned">' + data[i].date + '</td>\n' +
                            '        <td class="center aligned">$' + data[i].sale + '</td>\n' +
                            '    </tr>');
                    }
                });
            }
        });
    });
});