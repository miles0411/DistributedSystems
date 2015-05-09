<%-- 
    Document   : user.jsp
    Created on : Nov 11, 2014, 12:14:26 AM
    Author     : satejwagle
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>User Page</title>

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
                            <img src="satty.jpg" class="img-responsive" alt="Responsive image" >
                    <!--    <div class="fileinput fileinput-new" data-provides="fileinput">
                        <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
                            <img data-src="js/holder.js/100%x100%">
                        </div>
                    </div>-->
                    </div>
                </div>
                <div class="col-md-8">
                   <h2 align="center"> User 1 
                       Sample Surveyor</h2>
                </div>
                <div class="col-md-2">
                    <img src="download.jpg" class="img-responsive" alt="Responsive image" >
                </div>
            </div>
        </div>
        <div class ="container">
             <div class="panel panel-default">
                <div class="panel-heading">List Of Addresses</div>
                    <div class="panel-body">
                        <%= request.getAttribute("Address")%>
                    </div>
     </div>
                    
                    <div class="container">
                        <div class ="row">
                        <div class ="col-md-2">
                      <form action="Controller" method="GET">

                        <div class="form-group">
                          <label class="sr-only">Survey Duration</label>
                          <label class="sr-only">Has to return survey time</label>
                        </div>
                        <div class="form-group">
                          Time to leave
                          <label class="sr-only">Time to leave</label>
                          <input type="text" class="form-control" name="leave_hours" placeholder="21:00">
                        </div>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Calculate Route</button>
                      </form>
                        </div>
                    </div>
                    </div>  
    </body>
</html>
