<%-- 
    Document   : endSurvey
    Created on : Nov 21, 2014, 1:14:02 AM
    Author     : satejwagle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>End Survey</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet">


        <style>
            p{background-color: #ff0000;}
        </style>

    </head>
    <body>
        <div class ="container">
            <div class="row">
                <div class="col-md-2">
                    <div>
                        <div class="fileinput fileinput-new" data-provides="fileinput">
                        <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
                            <img data-src="js/holder.js/100%x100%">
                        </div>
                    </div>
                    </div>
                </div>
                <div class="col-md-8">
                   <h2 align="center"> User 1 Sample Surveyor</h2>
                </div>
                <div class="col-md-2">
                    <img src="download.jpg" class="img-responsive" alt="Responsive image" >
                </div>
            </div>
        </div>
                <div class ="container">
                    <h3> Thank you for your time</h3>
                    <div class="col-md-4">
                    <h4>Total Survey Duration = <%= request.getAttribute("totaltime")%></h4>
                    <br/>
                    <h4>Average Survey Duration = <%= request.getAttribute("avgtime")%></h4>
                    </div>
                    <div class="col-md-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">Addresses Completed</div>
                                <div class="panel-body">
                                    <%= request.getAttribute("Visited")%>
                                </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">Addresses Missed</div>
                                <div class="panel-body">
                                    <%= request.getAttribute("Missed")%>
                                </div>
                        </div>
                    </div>
                      <div class="col-md-3"> 
                        <button onclick="location.href = 'index.html'" class="btn btn-lg btn-primary btn-block">logout</button>
                      </div>
     </div>
                     
    </body>
</html>
