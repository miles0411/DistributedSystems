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
        <title>Project1Task1</title>
    </head>
    <body>
        <h1>Hashes of the string "<%= request.getAttribute("input")%>":</h1>
        <h2><%= request.getAttribute("choice")%> (Hex) : <%= request.getAttribute("outputHex")%></h2>
        <h2><%= request.getAttribute("choice")%> (Base 64) : <%= request.getAttribute("output")%></h2><br>
        <p>Try another text for encoding.</p>
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
