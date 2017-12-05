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
                            <input type="text" id="email" placeholder="Email">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="lock icon"></i>
                            <input type="password" id="password" placeholder="Password">
                        </div>
                    </div>
                    <input class="ui orange button" id="login" value="Login"/>
                </div>


            </form>

                <div class="ui error message" id="errorValue" style="display:none"> </div>

            <div class="ui message">
                New to us? <a href="register.jsp" style="color: #E07B53">Register</a>
            </div>
        </div>
    </div>
</div>

<script>

    $(function() {

        $("#login").on('click', function() {

            var cred = {
                email: $('#email').val(),
                password: $('#password').val()
            };

            $.ajax({
                type: 'POST',
                url: '/Login',
                contentType: 'application/json',
                data: JSON.stringify(cred),
                success: function(data){
                    if(data.isValid)
                        window.location.href = "welcome.jsp?firstname="+data.FirstName;
                    else{
                        var errorDiv = document.getElementById('errorValue');
                        errorDiv.style.display ="block";
                        errorDiv.innerHTML = data.errorValue;
                    }

                }
            });

        });
    });



</script>


</body>
</html>
