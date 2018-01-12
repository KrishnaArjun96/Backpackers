<%--
  Created by IntelliJ IDEA.
  User: krishna
  Date: 11/7/17
  Time: 10:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.8/semantic.min.css"/>
<link rel="stylesheet" href="login.css"/>
<head>
    <title>Login</title>
</head>
<body>

<div>
    <div class="ui middle aligned center aligned grid" style="position: absolute; top:25%;left:50%; transform: translateX(-50%); width: 350px">
        <div class="column">
            <h2 class="ui image header">
                <div class="ui orange header">
                    Log-in to your account
                </div>
            </h2>
            <form class="ui large form">
                <div class="ui stacked secondary  segment">
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="user icon"></i>
                            <input type="text" id="username" placeholder="Username">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="lock icon"></i>
                            <input type="password" id="password" placeholder="Password">
                        </div>
                    </div>
                    <div class="ui orange button" id="login" value="Login">Login</div>
                </div>


            </form>

                <div class="ui error message" id="errorValue" style="display:none"> </div>

        </div>
    </div>
</div>

<script>

    $(function() {

        $("#login").on('click', function() {
            var cred = {
                username: $('#username').val(),
                password: $('#password').val()
            };

            $.ajax({
                type: 'POST',
                url: '/Login',
                contentType: 'application/json',
                data: JSON.stringify(cred),
                success: function(data){
                    if(data.isValid) {
                        console.log(data);
                        sessionStorage.setItem('username',data.username)
                        if (data.isManager)
                                window.location.href = "manager.jsp";
                        else if(data.isEmployee)
                                window.location.href = "cusrep.jsp";
                        else
                            window.location.href = "index.jsp";
                    }
                    else{
                        var errorDiv = document.getElementById('errorValue');
                        errorDiv.style.display ="block";
                        errorDiv.innerHTML = data.errorValue;
                        console.log(data.errorValue);
                    }

                }
            });

        });
    });



</script>


</body>
</html>
