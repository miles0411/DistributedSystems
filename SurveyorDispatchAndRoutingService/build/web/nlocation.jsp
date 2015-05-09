<%-- 
    Document   : nlocation
    Created on : Nov 11, 2014, 8:00:24 PM
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
        <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css" />

        <title>Next Location</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
 
    <!-- Bootstrap core CSS -->
    <link href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.1.1/css/bootstrap.css" rel="stylesheet" media="screen">
 
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7/html5shiv.js"></script>
      <script src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.3.0/respond.js"></script>
    <![endif]-->
 
    <style>
      #map-container { height: 300px }
    </style>
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
                    <div class="col-md-6">
                    <h3>Current Location = <%= request.getAttribute("Address1")%></h3>
                    <br/>
                    <h3>Next Location = <%= request.getAttribute("Address2")%></h3>
                    </div>
                    <div id="map-container" class="col-md-6"></div>
                    
                    <form class="form-inline" role="form">
                    
                      <div class="col-md-6">  
                        <div class="form-group">
                          <label class="sr-only">Survey Duration</label>
                          <input type="text" class="form-control" name="hours" placeholder="Survey finish time">
                        </div>
                        <div class="form-group">
                          <label class="sr-only">Leaving Time</label>
                          <input type="text" class="form-control" name="leave_hours" placeholder=<%= request.getAttribute("time_to_leave")%>
                        </div>
                      </div>
                      <div class="col-md-6"> 
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Calculate Route</button>
                      </div>
                    </form>
     </div> 
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script src="http://maps.google.com/maps/api/js?sensor=false"></script>
     <script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
    <script>	
 
     // function init_map() {
          
          
          ///new code
        // var address = "<%= request.getAttribute("Address1") %>";
        //
       // var address2 = "<%= request.getAttribute("Address2") %>";
         var coordinates = "<%= request.getAttribute("Coordinates") %>";
        
         var map = L.map('map-container').setView([38.8951,-77.0367],13);
         L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
           }).addTo(map);
           var geojsonMarkerOptions = {
                radius: 8,
                fillColor: "#ff7800",
                color: "#000",
                weight: 1,
                opacity: 1,
                fillOpacity: 0.8
            };
        var geoData = $.parseJSON(coordinates);
        var pathLayer = L.geoJson(geoData, {
                pointToLayer: function (feature, latlng) {
                return L.circleMarker(latlng, geojsonMarkerOptions);
            }
        }).addTo(map);
       map.fitBounds(pathLayer.getBounds());
  /* var map = new google.maps.Map(document.getElementById('map-container'), { 
       mapTypeId: google.maps.MapTypeId.TERRAIN,
       zoom: 6
   });

   var geocoder = new google.maps.Geocoder();

   geocoder.geocode({
      'address': address
   }, 
   function(results, status) {
      if(status == google.maps.GeocoderStatus.OK) {
         new google.maps.Marker({
            position: results[0].geometry.location,
            map: map
         });
         map.setCenter(results[0].geometry.location);
      }
   });
          
 	
 
      }
 
      google.maps.event.addDomListener(window, 'load', init_map);*/
 
    </script>
    </body>
</html>
