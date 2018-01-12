/**
 * Created by varun on 12/6/17.
 */
var search = JSON.parse(sessionStorage.getItem('selectedFlights'));


var isAuction=false;
var numPassengers = search[0].passengers;

$(window).on('load', printItenary());

$(document).ready(function () {

    $('#flex').change(function () {

        var ele = document.getElementsByClassName('bidprompt');

        if ($(this).is(":checked")) {

            isAuction=true;
            for(var i=0;i<ele.length;i++){
                document.getElementsByClassName('bidprompt')[i].style.display = 'block';
            }

        } else {

            for(var i=0;i<ele.length;i++){
                document.getElementsByClassName('bidprompt')[i].style.display = 'none';
            }

        }
    });





});

function bookFlight(){
    var userName = sessionStorage.getItem('username');
    var auctions =[]
    var passengers=[];
    for(var i = 1;i<=numPassengers;i++) {
        var fullname = document.getElementById("fullname"+i).value;
        var seat = document.getElementById("seat"+i).value;
        var mealpref = document.getElementById("mealpref"+i).value;
        passengers[i-1]={"name":fullname,"seatPref":seat,"mealPref":mealpref};
    }
    var index = 0;
    if(isAuction==true){
        for(var i =0;i<search.length;i++){
            var bid = document.getElementById("bid"+i).value;
            if(search[i].stops.length>0){
                for(var j=0;j<search[i].stops.length;j++){
                    var splitBid=bid/search[i].stops.length;
                    auctions[index]=splitBid;
                    index++;
                }
            }else{
                auctions[index++]=bid;

            }

        }

    }
    var options = search;
    var result;
    if(isAuction==true)
        result = {"userName":userName,"isAuction":isAuction,"bids":auctions,"passengers":passengers,"options":options};
    else
        result = {"userName":userName,"isAuction":isAuction,"passengers":passengers,"options":options};
    console.log(result);
    $.ajax({
        type: 'POST',
        url: '/Book',
        contentType: 'application/json',
        data: JSON.stringify(result),
        success: function(data){
            console.log(data);
        }
    });

}



function printItenary() {
    $(document).ready(function () {

        for(var j=0; j<search.length;j++){

            var way = search[j];

            var lengthOfLegs = way.legs.length;

            $('#result').append('<h2 class="ui horizontal divider header" style="margin: 25px; width:90%">\n' +
                '    ' + way.legs[0].origin.id + ' -> ' + way.legs[lengthOfLegs - 1].destination.id + '  ' +
                '</h2>');


            var trip = search[j];

            var airline = (trip.airlines.length === 1) ? trip.airlines[0].name : 'Multiple Airlines';
            var time = trip.time;
            var stops = trip.stops.length;

            var stopNames = '';

            for (var k = 0; k < trip.stops.length; k++) {
                if (k === trip.stops.length - 1)
                    stopNames += trip.stops[k];
                else
                    stopNames += trip.stops[k] + ', ';
            }

            var price = trip.totalFare;

            var checked = (j === 0) ? 'checked' : '';

            var val = i + ',' + j;

            $('#result').append('<div class="ui orange segment" style="margin: 10px; width: 60%;">\n' +
                '    <div class="ui grid">\n' +
                '        <div class="eight wide column">\n' +
                '            <h3>' + airline + '</h3>\n' +
                '            <h4>' + time + '</h4>\n' +
                '        </div>\n' +
                '        <div class="six wide column">\n' +
                '            <h4>Stops: ' + stops + '</h4>\n' +
                '            <label>' + stopNames + '</label>\n' +
                '        </div>\n' +
                '        <div class="one wide column">\n' +
                '         <h3>$' + price +'</h3>\n' +
                '        <div class=\"bidprompt\" style=display:none;">\n' +
                '            <h4>Bid</h4>' +
                '            <input type="text" id='+ 'bid' +j +' style="width: 55px" />\n' +
                '        </div>\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</div>');
        }



        for(var i = 1;i<=numPassengers;i++){
            $('#passengerForm').append(
                '<h4 class="ui image header">'+
                '<div class="ui black header">'+
                'Passenger '+i+
                '</div>'+
                '   </h4>'+
                '<div class="field">'+
                '<div class="three fields">'+
                '<div class="four wide field">'+
                '  <label>Full Name</label>'+
                '<input type="text" id='+ 'fullname' + i +' placeholder="Full Name">'+
                '</div>'+
                '<div class="three wide field">'+
                '   <label>Seat</label>'+
                '<input type="text" id='+ 'seat' + i +' placeholder="Seat">'+
                '</div>'+
                '<div class="three wide field">'+
                '  <label>Meal Preference</label>'+
                '<input type="text" id='+ 'mealpref' + i +' placeholder="Meal Preference">'+
                '</div>'+
                '</div>'+
                '</div>');
        }

    });




}


