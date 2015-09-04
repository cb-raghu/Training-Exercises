<%-- 
    Document   : Info
    Created on : Jul 30, 2015, 6:02:36 PM
    Author     : cb-raghu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="index.html">home</a>
        <h1><%= request.getAttribute("error-info") %></h1>
    </body>
</html>
