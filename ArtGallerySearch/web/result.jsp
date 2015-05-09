<%-- 
    Document   : result
    Created on : Jan 31, 2014, 4:28:29 PM
    Author     : Preston    
    Note       : As a result page to show the outcome based on the user's input
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project1Task4 </title>
    </head>
    <body>
        <h2>"<%= request.getAttribute("NGApictureTitle")%>"</h2><br>
        <img <%= request.getAttribute("NGApictureURL")%>"><br><br>
        <h3>Try to give the last name of an artist to search for a painting</h3>
        <form action="NGAPictureServlet" method="GET"> 
            <input type="text" name="input" value="" /><br><br>
            <input type="submit" value="Search" /><br><br>
        </form>
    </body>
</html>

