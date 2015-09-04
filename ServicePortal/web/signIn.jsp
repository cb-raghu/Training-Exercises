<%-- 
    Document   : signIn
    Created on : Jul 30, 2015, 3:40:36 PM
    Author     : cb-raghu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service Portal</title>
    </head>
    <body>
    <a href="index.html">home</a>
    <center>
        <h1>Sign In</h1>        
        <form method="POST" action="Authenticate">
            <input type="text" name="email" placeholder="email"/><br><br>
            <input type="password" name="password" placeholder="password"/><br> <br>
            <input type="submit" value="SUBMIT"/><br>
        </form>
    </center>
    </body>
</html>
