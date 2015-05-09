<%-- 
    Document   : prompt
    Created on : Jan 22, 2014, 6:13:56 PM
    Author     : Preston
    Note       : As a welcome page for user to input
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project1Task2</title>
    </head>
    <body>     
        <form action="BigCal" method="GET"> 
            <h1>Input two integers and choose the operator.</h1>
            <h1>Then Hit the "Calculate" Button!</h1>
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