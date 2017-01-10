<!DOCTYPE html>
<!--[if IE 7]>                  <html class="ie7 no-js" lang="en">     <![endif]-->
<!--[if lte IE 8]>              <html class="ie8 no-js" lang="en">     <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> 
<%@page import="example.domain.Carpool"%>
<%@page import="java.util.List"%>
<%@page import="com.cloudant.client.api.Database"%>
<%@page import="example.nosql.CloudantClientMgr"%>
<%@page import="com.cloudant.client.*"%>
<html class="not-ie no-js" lang="en">  <!--<![endif]-->
    <head>
<%

String sessionid="";
try
{

sessionid =session.getAttribute("session_id").toString();

}
catch(Exception e)
{

}

 %>
        <!-- Basic Page Needs -->
        <meta charset="utf-8">
        <title>My ride</title>
        <meta name="description" content="">
        <meta name="author" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Mobile Specific Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Styles -->

        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Forms -->
        <link href="css/jquery.idealforms.css" rel="stylesheet">
        <!-- Select  -->
        <link href="css/jquery.idealselect.css" rel="stylesheet">
        <!-- Slicknav  -->
        <link href="css/slicknav.css" rel="stylesheet">
        <!-- Main style -->
        <link href="css/style.css" rel="stylesheet">

        <!-- Modernizr -->
        <script src="js/modernizr.js"></script>
		<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <!-- Fonts -->
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>

        <header class="header">

            <div class="top-menu">

                <section class="container">
                    <div class="row">

                        <div class="col-md-4 col-sm-4 col-xs-12">
                            <div class="user-log">
							<%
							
							if((sessionid.equals("")))
							{
							%>
							
							<a data-toggle="modal" data-target="#loginModal">
                                    Log in
                                </a> /
                                <a data-toggle="modal" data-target="#regModal">
                                    Sign up
                                </a>
							<% 
							
							}
							else
							{
							%>
							<a  href="/LogoutServlet" data-toggle="" data-target="#">
                                    <b>(<%=sessionid %>)</b> Logout
                                </a>
							
							<% 
							}
							
							 %>
                                

                            </div><!-- end .user-log -->
                        </div><!-- end .col-sm-4 -->

                        <div class="col-md-8 col-sm-8 col-xs-12">


                        </div><!-- end .col-sm-8 -->

                    </div><!-- end .row -->
                </section><!-- end .container -->

            </div><!-- end .top-menu -->

            <div class="main-baner">

                <div class="fullscreen background parallax clearfix" style="background-image:url('img/tumblr_n7yhhvUQtx1st5lhmo1_1280.jpg');" data-img-width="1600" data-img-height="1064">

                    <div class="main-parallax-content">

                        <div class="second-parallax-content">

                            <section class="container">

                                <div class="row">

                                    <div class="main-header-container clearfix">

                                        <div class="col-md-4 col-sm-12 col-xs-12">

                                            <div class="logo">
                                                <h1>My ride</h1>
                                            </div><!-- end .logo -->

                                        </div><!-- end .col-sm-4 -->

                                        <div class="col-md-8 col-sm-8 col-xs-12">

                                            <nav id="nav" class="main-navigation">

                                                <ul class="navigation">
                                                    <li>
                                                        <a href="index.jsp">Home</a>
                                                    </li>
                                                    <li>
                                                        <a href="rides.jsp">rides</a>
                                                    </li>
                                                    <%
                                                    if(!(sessionid.equals("")))
                                                    {
                                                    	%>
                                                    	
                                                    <li>
                                                        <a href="add-ride.jsp">Add Ride</a>
                                                    </li>
                                                    <li>
                                                        <a href="add-ride.jsp">My Rides</a>
                                                    </li>
                                                    <%
                                                     
                                                    }
                                                    
                                                     %>
                                                    <li>
                                                        <a href="contact-page.jsp">AboutUs</a>
                                                    </li>
                                                   <!--  <li>
                                                        <a href="#">Pages</a>
                                                        <ul class="sub-menu">
                                                            <li>
                                                                <a href="events.html">Events</a>
                                                            </li>
                                                            <li>
                                                                <a href="single-post.html">Single post</a>
                                                            </li>
                                                            <li>
                                                                <a href="single-article.html">Single article</a>
                                                            </li>
                                                        </ul>
                                                    </li>
                                                    <li>
                                                        <a href="blog.html">Blog</a>
                                                    </li>
                                                    <li>
                                                        <a href="contact-page.html">Contact</a>
                                                    </li> -->
                                                </ul>

                                            </nav><!-- end .main-navigation -->

                                        </div><!-- end .col-md-8 -->

                                    </div><!-- end .main-header-container -->

                                </div><!-- end .row -->

                            </section><!-- end .container -->

                        </div><!-- end .second-parallax-content -->

                    </div><!-- end .main-parallax-content -->

                </div><!-- end .background .parallax -->

            </div><!-- end .main-baner -->

        </header><!-- end .header -->
			<%
				//new CloudantClientMgr();
	//	response.getWriter().append(new CloudantClientMgr().getDB().getDBUri().toString());
		Database db= new CloudantClientMgr().getDB();
		List<Carpool> carpoolList = null;
	try{
		List<Carpool> d1  = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Carpool.class);
		 carpoolList = (List)request.getAttribute("CarpoolsList");
			out.println(carpoolList);
				
				 %>
                            <div class="col-md-12 col-sm-12 col-xs-12">

                                <div class="search-content">

                                    <form action="/SearchRide" autocomplete="off" class="idealforms searchtours" method="post">

                                        <div class="row">

                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                <div class="field">
                                                    <select id="searchpickUpPoint" name="startPoint" style="color:#a6a497 !important; height:50px;">
                                                    <option>From</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-3 col-sm-3 col-xs-12">

                                                <div class="field">
                                                    <select id="search_endcity" name="endCity" style="color:#a6a497 !important; height:50px;">
                                                        <option value="default">To</option>
                                                    <%  for(int i=0; i<d1.size();i++){  %>
                                                        <option ><%=d1.get(i).getEndCity()%></option>
                                                    <%} %>
                                                    </select>
                                                </div>

                                            </div>

                                            <div class="col-md-3 col-sm-3 col-xs-12">

                                                <div class="field">
                                                    <input name="carpoolDate" type="text" placeholder="Date" class="datepicker">
                                                </div>


                                            </div>

                                            <div class="col-md-3 col-sm-3 col-xs-12">

                                                <div class="field">
                                                    <select id="destination" name="seatsAvailable" style="color:#a6a497 !important; height:50px;">
                                                        <option value="default">Number of seats</option>
                                                        <option>1</option>
                                                        <option>2</option>
                                                        <option>4</option>
                                                        <option>5</option>
                                                        <option>6</option>
                                                    </select>
                                                </div>

                                            </div>

                                            <div class="col-md-3 col-sm-3 col-xs-12">

                                                <div class="field buttons">
                                                    <button type="submit" class="btn btn-lg green-color">Search</button>
                                                </div>

                                            </div>

                                        </div>


                                    </form>
 <%    }catch(Exception e){
		
	}     %>                       </div><!-- end .search-content -->

                            </div><!-- end .col-sm-12 -->

                        </div><!-- end .second-parallax-content -->

                    </div><!-- end .main-parallax-content -->

                </div><!-- end .background .parallax -->

            </div><!-- end .main-baner -->

        </header><!-- end .header -->

        <section class="main-content">

            <div class="container">
                <div class="row">

                    <div class="col-md-12 col-sm-12 col-xs-12">

                        <div class="page-sub-title textcenter">
                            <h2>All rides</h2>
                            <div class="line"></div>
                        </div><!-- end .page-sub-title -->

                    </div><!-- end .col-md-12 col-sm-12 col-xs-12 -->

	
                    <div class="col-md-12 col-sm-12 col-xs-12">
                    
                    <%
                          try {
			List<Carpool> d = null;
			if(carpoolList != null){
				d= carpoolList;
			}else{
				d  = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Carpool.class);
			}
			for(int i=0; i<d.size();i++){
			//response.getWriter().append((d.get(i).getUserId())); 
			
			                    
                    
                     %>

                        <div class="page-content">

                            <div class="rides-list">

                                <article class="ride-box clearfix">

                                    <div class="ride-content">
                                        <h3><a href="#">From <%=d.get(i).getStartPoint() %> to <%=d.get(i).getEndCity() %></a></h3>ride by <a href="#"><%=d.get(i).getCarpoolerName() %>(<%=d.get(i).getCarpoolerNumber() %>)</a>
                                    </div>

                                    <ul class="ride-meta">

                                        <li class="ride-date">
                                            <a href="#" class="tooltip-link" data-original-title="Date" data-toggle="tooltip">
                                                <i class="fa fa-calendar"></i>
                                                <%=d.get(i).getCarpoolDate() %>,<%=d.get(i).getCarpoolTime() %>
                                            </a>
                                        </li><!-- end .ride-date -->
                                        <li>
                                            <a href="#" class="tooltip-link" data-original-title="Car Information" data-toggle="tooltip">
                                                <i class="fa fa-file"></i>
                                                Car Info:<%=d.get(i).getCarInfo() %>
                                            </a>
                                        </li>
                                        <li class="ride-people">
                                            <a href="#" class="tooltip-link" data-original-title="Number of seats" data-toggle="tooltip">
                                                <i class="fa fa-user"></i>
                                                <%=d.get(i).getSeatsAvailable() %>
                                            </a>
                                        </li><!-- end .ride-people -->
 
                                        <li>
                                            <a href="#" class="tooltip-link" data-original-title="Price" data-toggle="tooltip">
                                                <i class="fa fa-file"></i>
                                                $<%=d.get(i).getPrice() %>
                                            </a>
                                        </li>

                                    </ul><!-- end .ride-meta -->

                                </article><!-- end .ride-box -->
                                
                                <%
                                
                                }


			} catch (Exception e) {
			// TODO Auto-generated catch block
				response.getWriter().append(e.getMessage());
			}
                                
                                 %>

                                <div class="clearfix"></div>

                                <div class="post-pagination pagination-margin clearfix">

                                    <div class="next pull-right">
                                        <a href="#">
                                            Next
                                            <i class="fa fa-chevron-right"></i>
                                        </a>
                                    </div>

                                </div><!-- end .post-pagination -->

                            </div><!-- end .events-list -->

                        </div><!-- end .page-content -->

                    </div><!-- end .col-md-12 col-sm-12 col-xs-12 -->

                </div><!-- end .row -->
            </div><!-- end .container -->

        </section><!-- end .main-content -->

        <footer id="footer">

            <div class="footer-copyright">

                <div class="container">
                    <div class="row">

                        <div class="col-md-12 col-sm-12 col-xs-12">
                            Copyright by My ride
                        </div>

                    </div><!-- end .row -->
                </div><!-- end .container -->

            </div><!-- end .footer-copyright -->

        </footer><!-- end #footer -->

        <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div><!-- end .modal-header -->

                    <div class="modal-body">
                        <form action="NewLoginController" novalidate autocomplete="off" class="idealforms login">

                            <div class="log-header">
                                <span class="log-in">Log in</span>
                            </div>

                            <div class="field">
                                <input name="username" type="text" placeholder="Username">
                                <span class="error"></span>
                            </div>

                            <div class="field">
                                <input type="password" name="password" placeholder="Password">
                                <span class="error"></span>
                            </div>

                            <div class="field buttons">
                                <button type="submit" class="submit btn green-color">Log in</button>
                            </div>


                            <div class="clearfix"></div>

                        </form><!-- end .login -->
                    </div><!-- end .modal-body -->

                </div><!-- end .modal-content -->
            </div><!-- end .modal-dialog -->
        </div><!-- end .modal -->

        <div class="modal fade" id="regModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>

                    <div class="modal-body">
                        <form action="NewRegisterservlet" novalidate autocomplete="off" class="idealforms reg">

                            <div class="log-header">
                                <span class="log-in">Sign up</span>
                            </div>

                            <div class="field">
                                <input name="username" type="text" placeholder="Username">
                                <span class="error"></span>
                            </div>

                            <div class="field">
                                <input name="email" type="email"  placeholder="E-Mail">
                                <span class="error"></span>
                            </div>

                            <div class="field">
                                <input type="password" name="password" placeholder="Password">
                                <span class="error"></span>
                            </div>

                            <div class="field">
                                <input name="confirmpass" type="password"  placeholder="Password">
                                <span class="error"></span>
                            </div>

                            <div class="field buttons">
                                <button type="submit" class="submit btn green-color">Sign up</button>
                            </div>

                            <div class="clearfix"></div>

                        </form><!-- end .reg -->
                    </div><!-- end .modal-body -->

                </div><!-- end .modal-content -->
            </div><!-- end .modal-dialog -->
        </div><!-- end .modal -->
    <!-- Javascript -->
    
    
   <div id="map"></div>
                </div><!-- end .modal-content -->
            </div><!-- end .modal-dialog -->
        </div><!-- end .modal -->

        <!-- Javascript -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
   	<script>
      // This example requires the Places library. Include the libraries=places
      // parameter when you first load the API. For example:
      // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

      var map;
      var infowindow;

      function initMap() {
        var pyrmont = {lat: 42.296 , lng: -82.990 };

        map = new google.maps.Map(document.getElementById('map'), {
          center: pyrmont,
          zoom: 15
        });

        infowindow = new google.maps.InfoWindow();
        var service = new google.maps.places.PlacesService(map);
        service.nearbySearch({
          location: pyrmont,
          radius: 5000,
          type: ['parking']
        }, callback);
      }

      function callback(results, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          for (var i = 0; i < results.length; i++) {
		    var select = $("#searchpickUpPoint");
		   
		    if(results[i].vicinity != "Windsor"){
			    select.append("<option value='" + results[i].vicinity + "'>" + results[i].vicinity + "</option>")
				}
          
            createMarker(results[i]);
          }
        }
      }

      function createMarker(place) {
        var placeLoc = place.geometry.location;
        var marker = new google.maps.Marker({
          map: map,
          position: place.geometry.location
        });

        google.maps.event.addListener(marker, 'click', function() {
          infowindow.setContent(place.name);
          infowindow.open(map, this);
        });
      }
	  
	  
	  
	  
    </script>
        <script src="https://maps.googleapis.com/maps/api/js?key=&libraries=places&callback=initMap" async defer></script>
        
        <!-- Bootstrap -->
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <!-- Main jQuery -->
        <script type="text/javascript" src="js/jquery.main.js"></script>
        <!-- Form -->
        <script type="text/javascript" src="js/jquery.idealforms.min.js"></script>
        <script type="text/javascript" src="js/jquery.idealselect.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.10.4.custom.min.js"></script>

        <!-- Menu -->
        <script type="text/javascript" src="js/hoverIntent.js"></script>
        <script type="text/javascript" src="js/superfish.js"></script>
        <!-- Counter-Up  -->
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/waypoints/2.0.3/waypoints.min.js"></script>
        <script type="text/javascript" src="js/jquery.counterup.min.js"></script>
        <!-- Rating  -->
        <script type="text/javascript" src="js/bootstrap-rating-input.min.js"></script>
        <!-- Slicknav  -->
        <script type="text/javascript" src="js/jquery.slicknav.min.js"></script>

    </body>
</html>
