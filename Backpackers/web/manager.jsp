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
        <a class="item">
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


<div style="margin: 20px;">
    <div class="ui buttons" name="type">
        <button id="add" class="ui orange button" onclick="changeButton('add')">Add</button>
        <button id="edit" class="ui button" onclick="changeButton('edit')">Edit</button>
        <button id="delete" class="ui button" onclick="changeButton('delete')">Delete</button>
    </div>

    <div class="ui form" style="margin: 10px">
        <div id="empAdd">
            <div class="three fields">
                <div class="field">
                    <label>SSN</label>
                    <input type="text" id="ssn">
                </div>
                <div class="field">
                    <label>Name</label>
                    <input type="text" id="name"/>
                </div>
            </div>
            <div class="three fields">
                <div class="field">
                    <label>Start date</label>
                    <input type="date" id="startDate">
                </div>
                <div class="field">
                    <label>Role</label>
                    <input type="text" id="role"/>
                </div>
            </div>
            <div class="three fields">
                <div class="field">
                    <label>Wage</label>
                    <div class="ui right labeled input">
                        <label for="wage" class="ui label">$</label>
                        <input type="text" placeholder="Amount" id="wage">
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
        <input id="postButton" class="ui large orange button" type="submit"
               value="Add"/>
    </div>
</div>

</body>
</html>
