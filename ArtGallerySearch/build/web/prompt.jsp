<%-- 
    Document   : prompt
    Created on : Jan 31, 2014, 2:12:24 PM
    Author     : Preston
    Note       : As a welcome page for user to input
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project1Task4 </title>
    </head>
    <body>
        <h1>Give the last name of an artist to search for a painting</h1>
        <form action="NGAPictureServlet" method="GET"> 
            <input type="text" name="input" value="" /><br><br>
            <input type="submit" value="Search" /><br><br>
        </form>
    </body>
</html>
