<%-- 
    Document   : successfullogin
    Created on : Nov 15, 2014, 4:18:26 PM
    Author     : satejwagle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Successful Login</title>
    </head>
    <body>
        <h1>Successful Login ... Click the button below to get all addresses</h1>
       
        
        <form method="POST" action="ListOfAddresses" enctype="multipart/form-data" >
            File:
            <input type="file" name="file" id="file" /> <br/>
            </br>
            <input type="submit" value="Upload" name="upload" id="upload" />
        </form>
        
      </body>
    
    
</html>
