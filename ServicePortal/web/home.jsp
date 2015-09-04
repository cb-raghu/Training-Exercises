<%-- 
    Document   : home
    Created on : Jul 30, 2015, 6:32:07 PM
    Author     : cb-raghu
--%>

<%@page import="ServicePortal.Login"%>
<%@page import="ServicePortal.Helper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service Portal</title>
        <script src="js/jquery.min.js"></script>
        <%
            HttpServletResponse res = (HttpServletResponse) response;
            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.     
            res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            res.setDateHeader("Expires", 0); // Proxies.
        %>
    </head>
    <body>        
        <%
            Cookie[] cookies = request.getCookies();
            Cookie loginCookie = null;
            for(int i=0; i < cookies.length; i++){
                if(cookies[i].getName().equals("login_token")){
                 loginCookie = cookies[i];
                 break;
                }
            }
            if(loginCookie == null || !loginCookie.getValue().equals(session.getId()))
            {
                response.sendRedirect("signIn.jsp");
                return;
            }
                     
        %>
        <div style="margin:auto; width:900px;height:700px;border: 1px solid teal;">
            <b style="margin-left: 10px;">Welcome :  <%= session.getAttribute("email") %> </b>
            <a id="deActivate" style="float:right;margin-right: 10px;" href="#">Deactivate</a>
            <form style='display:none;' action="DeactivateAccount" method="POST"><input id="deactivate-submit" type="submit"></form>
            <a id="logout" style="float:right;margin-right: 10px;" href="#">Logout</a>    
            <form style='display:none;' action="logout" method="GET"><input id="logout-submit" type="submit"></form>
            <hr>
            <% Login login =  Helper.getLoginDeatils(session.getAttribute("email").toString()); %>
            <form action="UpdateUser"method="POST">
                <table style="width:400px;height:300px;border:1px solid red;margin:70px auto; border-radius: 3px; ">
                    <tr>
                        <td>Email</td><td> <span class="non-edit"><%= login.email %></span><input style="display:none;" name="" type="text" value="<%= login.email %>"></td>
                    </tr>
                    <tr>
                        <td>First Name</td><td><span class="non-edit"><%= login.firstName %></span><input style="display:none;" name="FirstName" class="edit" type="text" value="<%= login.firstName %>"></td>
                    </tr>
                    <tr>
                        <td>Last Name</td><td><span class="non-edit"><%= login.lastName %></span><input style="display:none;" name="LastName"  class="edit" type="text" value="<%= login.lastName %>"></td>
                    </tr>
                    <tr>
                        <td>Address</td><td><span class="non-edit"><%= login.address == null ? "N/A" : login.address %></span><input style="display:none;" name="Address"  class="edit" type="text" value="<%= login.address == null ? "" : login.address %>"></td>
                    <tr>
                    <tr>
                        <td colspan="2">
                            <input id="edit" type="button" value="EDIT"/>
                            <input id="cancel" type="button" value="CANCEL" style="display:none;"/>
                            <input id="submit" type="submit" value="SAVE" style="display:none;" />
                        </td>
                    </tr>    
                </table>      
            </form> 
        <div>
    </body>
    <script>
        $(document).ready(function(){
            
            $("#deActivate").click(function(){                
                $('#deactivate-submit').click();
            });
            $("#logout").click(function(){                
                $('#logout-submit').click();
            });
            
            $('#edit').click(function(){
                $('.non-edit').hide();
                $('.edit').show();
                $('#cancel').show();
                $('#submit').show();
            });
            
            $('#cancel').click(function(){
                $('.edit').hide();
                $('.non-edit').show();                
                $('#cancel').hide();
                $('#submit').hide();
            });
        });
    </script>      
</html>

