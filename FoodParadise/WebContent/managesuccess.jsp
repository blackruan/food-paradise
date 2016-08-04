<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="assets/css/bootstrap-united.css" rel="stylesheet" />
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
<style>
/* body {
	height: 100%;
	margin: 0;
	background: url(assets/img/books.jpg);
	background-size: 1440px 800px;
	background-repeat: no-repeat;
	display: compact;
} */
</style>
</head>
<body>
	<div class="navbar navbar-default">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search">
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/FoodParadise">Home</a></li>
				<% if (session.getAttribute("username") == null) { %>
					<li><a href="signup.jsp">Signup</a></li>
    				<li><a href="login.jsp">Login</a></li>
				<% } else {%>
   				<li class="active"><a href="managepageaction.action"><s:property value="#session.username" /></a></li>
				<li><a href="logoutaction.action">Log Out</a></li>
				<% } %>
				
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Explore<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">Contact us</a></li>
						<li class="divider"></li>
						<li><a href="#">Further Actions</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	
		<div class="container">
			<div class="jumbotron">
				<div>
					<h2>Welcome to Food Paradise, <s:property value="#session.username" /></h2>
				</div><br/>
	
		</div>
		</div>
		<div class="container">
			<div class="jumbotron">
			<h2>Restaurants Reviewed</h2>
				<table border="3">
					<tr>
					    <td width="5%" align="center">Name</td>
					    <td width="5%" align="center">Address</td>
					    <td width="5%" align="center">Latitude</td>
					    <td width="5%" align="center">Longitude</td>
					    <td width="5%" align="center">Rating</td>
					    <td width="5%" align="center">Rating_Number</td>
					    <td width="5%" align="center">Review</td>
					 </tr>
					 <s:iterator value="allRestaurants">
					 <tr>
					    <td width="5%" align="center"><s:property value="restaurant_name"/></td>
					    <td width="5%" align="center"><s:property value="restaurant_address"/></td>
					    <td width="5%" align="center"><s:property value="restaurant_lat"/></td>
					    <td width="5%" align="center"><s:property value="restaurant_lon"/></td>
					    <td width="5%" align="center"><s:property value="restaurant_rating"/></td>
					    <td width="5%" align="center"><s:property value="restaurant_rating_num"/></td>
					    <td width="5%" align="center">
					    <s:form action="reviewaction">
					        <s:hidden name="review" value="%{restaurant_id}" />
					        <s:submit label="Review" value="Review" theme="simple" />
					    </s:form>
					    </td>
					 </tr>
					 </s:iterator>
				 </table>  
			<h2>Restaurants Checked In</h2>
				<table border="3">
					<tr>
					    <td width="5%" align="center">Name</td>
					    <td width="5%" align="center">Address</td>
					    <td width="5%" align="center">Latitude</td>
					    <td width="5%" align="center">Longitude</td>
					    <td width="5%" align="center">Rating</td>
					    <td width="5%" align="center">Rating_Number</td>
					    <td width="5%" align="center">Check In Number</td>
					    <td width="5%" align="center">Check In</td>
					 </tr>
					 <s:iterator value="allCheckinRestaurants">
					 <tr>
					    <td width="5%" align="center"><s:property value="restaurant_name"/></td>
					    <td width="5%" align="center"><s:property value="restaurant_address"/></td>
					    <td width="5%" align="center"><s:property value="restaurant_lat"/></td>
					    <td width="5%" align="center"><s:property value="restaurant_lon"/></td>
					    <td width="5%" align="center"><s:property value="restaurant_rating"/></td>
					    <td width="5%" align="center"><s:property value="restaurant_rating_num"/></td>
					    <td width="5%" align="center"><s:property value="restaurant_checkin_num"/></td>
					    <td width="5%" align="center">
					    <s:form action="checkinaction">
					        <s:hidden name="checkin" value="%{restaurant_id}" />
					        <s:submit label="Check In" value="Check In" theme="simple" />
					    </s:form>
					    </td>
					 </tr>
					 </s:iterator>
				 </table>
			<h2>Matched Events</h2>
				<table border="3">
					<tr>
					    <td width="5%" align="center">Name</td>
					    <td width="5%" align="center">Restaurant</td>
					    <td width="8%" align="center">Time</td>
					    <td width="5%" align="center">Event Cost</td>
					    <td width="5%" align="center">Description</td>
					    <td width="5%" align="center">Attend</td>
					 </tr>
					 <s:iterator value="allEvents">
					 <tr>
					    <td width="5%" align="center"><s:property value="event_name"/></td>
					    <td width="5%" align="center"><s:property value="restaurant_name"/></td>
					    <td width="8%" align="center"><s:property value="event_timestamp"/></td>
					    <td width="5%" align="center"><s:property value="event_cost"/></td>
					    <td width="5%" align="center"><s:property value="event_description"/></td>
					    <td width="5%" align="center">
					    <s:form action="attendaction">
					        <s:hidden name="attend" value="%{event_id}" />
					        <s:submit label="Attend" value="Attend" theme="simple" />
					    </s:form>
					    </td>
					 </tr>
					 </s:iterator>
				 </table>
				   
				 <h2>Matched Users</h2>
				<table border="3">
					<tr>
					    <td width="5%" align="center">Nickname</td>
					    <td width="5%" align="center">Average Rating</td>
					    <td width="5%" align="center">Member_Since</td>
					    <td width="5%" align="center">Friend</td>
					    <td width="5%" align="center">Unfriend</td>
					 </tr>
					 <s:iterator value="allUsers">
					 <tr>
					    <td width="5%" align="center"><s:property value="user_nickname"/></td>
					    <td width="5%" align="center"><s:property value="average_ratings"/></td>
					    <td width="5%" align="center"><s:property value="member_since"/></td>
					    <td width="5%" align="center">
					    <s:form action="friendaction">
					        <s:hidden name="friend" value="%{user_id}" />
					        <s:submit label="Friend" value="Friend" theme="simple" />
					    </s:form>
					    </td>
					    <td width="5%" align="center">
					    <s:form action="unfriendaction">
					        <s:hidden name="unfriend" value="%{user_id}" />
					        <s:submit label="Unfriend" value="Unfriend" theme="simple" />
					    </s:form>
					    </td>
					    
					 </tr>
					 </s:iterator>
				 </table>  
	
		</div>
		</div>



</body>
</html>