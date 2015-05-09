<%-- 
    Document   : result
    Created on : Jan 22, 2014, 6:19:18 PM
    Author     : Preston
    Note       : As a result page to show the outcome based on the user's input
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project1Task2</title>
    </head>
    <body>     
        <h1><%= request.getAttribute("result")%></h1>
        <form action="BigCal" method="GET"> 
           <h2>Continue to Input another two integers and choose the operator.</h2>
           <h2>Then Hit the "Calculate" Button!</h2>
            X = <input type="text" name="x" value="" /><br><br>
            <select name="operator" onChange="changetext()">
            <option value="add" />add</option><br>
            <option value="multiply" />multiply</option><br>
            <option value="relativelyPrime" />relativelyPrime</option><br>
            <option value="mod" />mod</option><br>
            <option value="modInverse" />modInverse</option><br>
            <option value="power" />power</option><br>
            </select><br><br>
            Y = <input type="text" name="y" value="" /><br><br>
            <input type="submit" value="Calculate" /><br><br>
        </form>
    </body>
</html>