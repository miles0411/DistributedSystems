<%-- 
    Document   : prompt
    Created on : Apr 8, 2014
    Author     : Preston
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project4Task2 </title>
    </head>
    <body>
        <h1>Give me your currently living city, find a coffee shop in the city!</h1>
        <h1>The format can be: 1) New York or 2) New York, NY</h1>
        <form action="YoutubeDataAPIServlet" method="GET"> 
            <input type="text" name="input" value="" /><br><br>
            <input type="submit" value="Search" /><br><br>
        </form>
    </body>
</html>
