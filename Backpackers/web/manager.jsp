<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 11/28/17
  Time: 12:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
<script src="manager.js"></script>
<head>
    <title>Manager</title>
</head>

<div class="ui orange tiered menu">
    <div class="menu">
        <a class="active item">
            <i class="users icon"></i>
            Employees
        </a>
        <a class="item" href="sales.jsp">
            <i class="bar chart icon"></i> Sales Report
        </a>
        <a class="item" href="flights.jsp">
            <i class="plane icon"></i> Flights
        </a>
        <a class="item" href="reservations.jsp">
            <i class="book icon"></i> Reservations
        </a>
        <a class="item" href="revenue.jsp">
            <i class="bitcoin icon"></i> Revenue
        </a>
        <a class="item" style="float: right" href="login.jsp">
            <i class="power icon"></i>
        </a>
    </div>
</div>


<div style="margin: 20px;">
    <div class="ui buttons" name="type">
        <button id="all" class="ui orange button" onclick="changeButton('all')">All</button>
        <button id="add" class="ui button" onclick="changeButton('add')">Add</button>
        <button id="edit" class="ui button" onclick="changeButton('edit')">Edit</button>
        <button id="delete" class="ui button" onclick="changeButton('delete')">Delete</button>
    </div>

    <div id="empAll">
        <table class="ui sortable orange celled structured table" id="empTable" style="margin: 20px; width: 90%; ">
            <thead>
            <tr>
                <th rowspan="2" class="center aligned">First Name</th>
                <th rowspan="2" class="center aligned">Last Name</th>
                <th rowspan="2" class="center aligned">Address</th>
                <th rowspan="2" class="center aligned">City</th>
                <th rowspan="2" class="center aligned">State</th>
                <th rowspan="2" class="center aligned">Country</th>
                <th rowspan="2" class="center aligned">Phone</th>
                <th rowspan="2" class="center aligned">Zip</th>
                <th rowspan="2" class="center aligned">SSN</th>
                <th rowspan="2" class="center aligned">Start Date</th>
                <th rowspan="2" class="center aligned">Wage</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>


    <div class="ui form" style="margin: 20px">
        <div id="empAdd" style="display: none">

            <div class="field">
                <div class="three fields">
                    <div class="four wide field">
                        <label>First Name</label>
                        <input type="text" id="firstName" placeholder="First Name">
                    </div>
                    <div class="three wide field">
                        <label>Last Name</label>
                        <input type="text" id="lastName" placeholder="Last Name">
                    </div>
                    <div class="three wide field">
                        <label>SSN</label>
                        <input type="text" id="ssn" placeholder="SSN">
                    </div>
                </div>
            </div>

            <div class="field">
                <label>Address</label>
                <div class="ten wide field">
                    <input type="text" id="address" placeholder="Street">
                </div>
            </div>

            <div class="field">
                <div class="two fields">
                    <div class="three wide field">
                        <input type="text" id="city" placeholder="City">
                    </div>
                    <div class="two wide field">
                        <input type="text" id="state" placeholder="State">
                    </div>
                    <div class="three wide field">
                        <input type="text" id="country" placeholder="Country">
                    </div>
                    <div class="two wide field">
                        <input type="text" id="zip" placeholder="Zip Code">
                    </div>
                </div>
            </div>


            <div class="field">
                <div class="four fields">
                    <div class="three wide field">
                        <label>Phone Number</label>
                        <input type="text" id="phone" placeholder="Phone Number">
                    </div>
                    <div class="three wide field">
                        <div class="field">
                            <label>Start date</label>
                            <input id="startDate" type="date">
                        </div>
                    </div>
                    <div class="two wide field">
                        <div class="field">
                            <label>Wage</label>
                            <div class="ui right labeled input">
                                <label for="wage" class="ui label">$</label>
                                <input type="text" placeholder="Amount" id="wage">
                            </div>
                        </div>
                    </div>
                    <div class="two wide field">
                        <label>Role</label>
                        <input type="text" id="role"/>
                    </div>
                </div>
            </div>
        </div>

            <div id="empDel" style="display: none">
                <div class="three fields">
                    <div class="field">
                        <label>SSN</label>
                        <input type="text" id="ssnDel">
                    </div>
                </div>
            </div>

            <br>
            <input style="display: none" id="postButton" class="ui large orange button" type="submit"
                   value="Add"/>
        </div>
    </div>
</div>

</body>
</html>
