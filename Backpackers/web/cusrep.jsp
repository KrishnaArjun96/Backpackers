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
<script src="cusrep.js"></script>
<head>
    <title>Customers</title>
    <link rel="icon" href="tabIcon4.png">
</head>

<div class="ui orange tiered menu">
    <div class="menu">
        <a class="active item">
            <i class="users icon"></i>Customers
        </a>
        <a class="item" href="mailingList.jsp">
            <i class="book icon"></i> Mailing Lists
        </a>
        <a class="item" href="suggestions.jsp">
            <i class="plane icon"></i> Flight Suggestions
        </a>
        <a class="item" href="cusrepResr.jsp">
            <i class="globe icon"></i> Reservation
        </a>
        <a class="item" style="float: right" href="login.jsp">
            <i class="power icon"></i>
        </a>
        <a class="item" style="float: right" href="help.html">
            Help
        </a>
    </div>
</div>


<div style="margin: 20px;">
    <div class="ui buttons" name="type">
        <button id="add" class="ui orange button" onclick="changeButton('add')">Add</button>
        <button id="edit" class="ui button" onclick="changeButton('edit')">Edit</button>
        <button id="delete" class="ui button" onclick="changeButton('delete')">Delete</button>
    </div>

    <div class="ui form" style="margin: 20px">
        <div id="empAdd">

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
                        <label>E-Mail</label>
                        <input type="text" id="email" placeholder="E-Mail">
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
                </div>
            </div>
        </div>

        <div id="empDel" style="display: none">
            <div class="three fields">
                <div class="field">
                    <label>E-Mail</label>
                    <input placeholder="E-Mail" type="text" id="emailDel">
                </div>
            </div>
        </div>

        <br>
        <input id="postButton" class="ui large orange button" type="submit"
               value="Add"/>
    </div>
</div>
</div>

</body>
</html>
