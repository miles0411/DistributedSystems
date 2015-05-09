<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>Registration Page</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet">


        <style>
            p{background-color: #ff0000;}
        </style>

    </head>

    <body>


        <div class="container"> 

            <div class="row">


             <div class ="col-md-2">
                    <div class="fileinput fileinput-new" data-provides="fileinput">
                        <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
                            <img data-src="js/holder.js/100%x100%" alt="...">
                        </div>
                         <div>
                            <span class="btn btn-default btn-file"><span class="fileinput-new">Select image</span>
                            <!--    <span class="fileinput-exists">Change</span><input type="file" name="..."></span>-->

                        </div>
                    </div>



             </div>     

                <div class ="col-md-8"><h2 align="center">Registration</h2></div>
                <div class="col-md-2"><img src="download.jpg" class="img-responsive" alt="Responsive image" ></div>
            </div>


        </div>



        <div class="container">

            <form class="form-signin" role="form">


                <div class="row">
                    <div class="col-md-6"><input type="name" class="form-control" placeholder="First Name" required autofocus></div>
                        
                    <div class="col-md-6"> <input type="name" class="form-control" placeholder="Last Name" required autofocus></div>
                </div>



                <div class="container"><h2></h2></div>
                <input type="name" class="form-control" placeholder="Contact Number" required>
                <div class="container"><h2></h2></div>
                <input type="name" class="form-control" placeholder="Current Address" required>
                <div class="container"><h2></h2></div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            </form>

        </div> <!-- /container -->


        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>