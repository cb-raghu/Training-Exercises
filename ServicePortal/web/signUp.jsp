<%-- 
    Document   : signUp
    Created on : Jul 30, 2015, 5:32:53 PM
    Author     : cb-raghu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jquery.min.js" type="text/javascript"></script>
    </head>
    <body>
        <a href="index.html">home</a>
        <center>
             <h1>Sign Up</h1>
             
             <form action="SaveSignUp" onsubmit="return validateForm()" method="post"> 
                <input style="margin-right: 10px;" type="text" required name="FirstName" placeholder="first name"/><input type="text" required name="LastName" placeholder="last name"/><br><br>
                <input style="margin-right: 10px;" type="email" required name="email" placeholder="email"/> <input type="email" required name="email-confirm" placeholder="confirm email"/> <span style='position:absolute;display:none;color:red' id="emailAlert">Emails do not match</span><br><br>
                <input style="margin-right: 10px;" type="password" required name="password" placeholder="password"/> <input type="password" required name="password-confirm" placeholder="confirm password"/><span style='position:absolute;display:none;color:red' id="passwordAlert">Passwords do not match</span><br> <br>
                <input type="submit" value="SUBMIT"/><br>
             </form>
        </center>
    </body>
    <script>
        function validateForm(){
            
                var email = $('input[name=email]').val();
                var emailConfirm = $('input[name=email-confirm]').val();
                if(email !== emailConfirm){
                    $('#emailAlert').show();
                    return false;    
                }
                
                var password = $('input[name=password]').val();
                var passwordConfirm = $('input[name=password-confirm]').val();
                if(password !== passwordConfirm){
                    $('#passwordAlert').show();
                    return false;    
                }    
                
                
                return true;
            }  
            
            $(document).ready(function(){
                $('input[name=email], input[name=email-confirm]').focus(function(){
                    $('#emailAlert').hide();
                });
                
                $('input[name=email]').focusout(function(){                    
                    $.post( "EmailValidation", { email: $('input[name=email]').val() })
                     .done(function( data ) {
                     if(data === 'true'){
                         alert("Email Id not available")
                     }
                    });
                });
                
                 $('input[name=password], input[name=password-confirm]').focus(function(){
                    $('#passwordAlert').hide();
                });
            });
    </script>
</html>
