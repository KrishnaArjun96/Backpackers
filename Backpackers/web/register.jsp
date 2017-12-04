<%--
  Created by IntelliJ IDEA.
  User: varun
  Date: 12/1/17
  Time: 12:24 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<link rel="stylesheet" href="login.css"/>
<head>
    <title>Register</title>
</head>
<body>

<div>
    <div class="ui middle aligned center aligned grid" style="position: absolute; top:25%;left:50%; transform: translateX(-50%); width: 350px">
        <div class="column">
            <h2 class="ui image header">
                <div class="ui orange header">
                    Create a new account
                </div>
            </h2>
            <form class="ui large form">
                <div class="ui stacked secondary  segment">
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="user icon"></i>
                            <input type="text" id="firstname" placeholder="First Name">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="user icon"></i>
                            <input type="text" id="lastname" placeholder="Last Name">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="inbox icon"></i>
                            <input type="text" id="email" placeholder="Email Address">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="lock icon"></i>
                            <input type="password" id="password" placeholder="Password">
                        </div>
                    </div>
                    <input class="ui orange button" id="register" value="Register"/>
                </div>

                <div class="ui error message"></div>

            </form>

        </div>
    </div>
</div>


<script>

    $(function() {

        $("#register").on('click', function() {
            var cred = {
                firstname:$('#firstname').val(),
                lastname:$('#lastname').val(),
                email: $('#email').val(),
                password: $('#password').val(),
            };
            console.log(cred);
            $.ajax({
                type: 'POST',
                url: '/Register',
                contentType: 'application/json',
                data: JSON.stringify(cred),
                success: function(data){
                    console.log(data.isValid);
                    window.location.href = "login.jsp";
                },
                failure: function (data) {
                    console.log(data.isValid);
                    window.location.href = "register.jsp";
                }
            });

        });
    });



</script>


</body>
</html>

