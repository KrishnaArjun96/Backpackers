<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 11/29/17
  Time: 2:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="manager.js"></script>
<head>
    <title>Sales Report</title>
</head>
<body>


<div class="ui orange tiered menu">
    <div class="menu">
        <a class="item" href="manager.jsp">
            <i class="users icon"></i>
            Employees
        </a>
        <a class="active item">
            <i class="bar chart icon"></i> sales report
        </a>
        <a class="item">
            <i class="plane icon"></i> flights
        </a>
        <a class="item">
            <i class="book icon"></i> Reservations
        </a>
        <a class="item">
            <i class="bitcoin icon"></i> Revenue
        </a>
        <a class="item">
            <i class="compass icon"></i> Airport
        </a>
        <a class="item">
            <i class="checked calendar icon"></i> Schedule
        </a>
        <a class="item" style="float: right">
            <i class="power icon"></i>
        </a>
    </div>
</div>

<h1 style="color: #E07B53;margin: 20px">Enter a month and a year.</h1>

<div class="ui grid" style="margin: 20px">
    <div class="two wide column">
        <select class="ui search selection dropdown" id="search-select">
            <option value="">Month</option>
            <option value="AL">January</option>
            <option value="AK">February</option>
            <option value="AZ">March</option>
            <option value="AR">April</option>
            <option value="CA">May</option>
            <option value="CO">June</option>
            <option value="CT">July</option>
            <option value="DE">August</option>
            <option value="DC">September</option>
            <option value="FL">October</option>
            <option value="GA">November</option>
            <option value="HI">December</option>
        </select>
    </div>
    <div class="two wide column">
        <div class="ui input">
            <input type="text" id="year" placeholder="Year">
        </div>
    </div>
    <div class="one wide column">
        <button class="ui orange button" id="goButton">Go</button>
    </div>
</div>


<script>
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
                url: '/salesReport?month='+month+'&year='+year,
                success: function (data) {
                    console.log(data);
                }
            });
        });
    });

</script>

</body>
</html>
