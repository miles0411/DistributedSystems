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
        <title>Project1Task1</title>
    </head>
    <body>     
        <form action="ComputeHashes" method="GET"> 
            <p>Select the type for encoding:</p>
            <input type="radio" name="choice" value="MD5"checked> MD5<br>
            <input type="radio" name="choice" value="SHA-1"> SHA-1<br><br>
            <p>Input text for encoding, and hit the "Submit" button.</p>
            <input type="text" name="input" value="" /><br>
            <input type="submit" value="Submit" /><br><br>
        </form>
    </body>
</html>
