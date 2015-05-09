<%-- 
    Document   : result
    Created on : Jan 26, 2014, 7:23:17 PM
    Author     : Preston
    Note       : As a result page to show the outcome based on the user's input
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project1Task3</title>
    </head>
    <body>  
            <h1>"<%= request.getAttribute("s")%>" <%= request.getAttribute("pali")%></h1>
            <form action="Palin" method="GET"> 
            <h1>Try another string to evaluate it is a palindrome!</h1>
            <input type="text" name="input" value="" /><br><br>
            <input type="submit" value="Evaluate" /><br><br>
            </form>
    </body>
</html>
