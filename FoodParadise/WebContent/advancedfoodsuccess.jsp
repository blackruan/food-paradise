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
				<li class="active"><a href="/FoodParadise">Home</a></li>
				<% if (session.getAttribute("username") == null) { %>
					<li><a href="signup.jsp">Signup</a></li>
    				<li><a href="login.jsp">Login</a></li>
				<% } else {%>
   				 <li><a href="managepageaction.action"><s:property value="#session.username" /></a></li>
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
	

		<% if (session.getAttribute("username") == null) { %>
    			<div class="container">
					<div class="jumbotron">
						<div>
							<h1>Welcome to Online Food Paradise!</h1>
							<p>To get started, you need to enter your details to enroll with
								us. Or login to access your details, if you are already enrolled.</p>
						</div>
			
						<a class="btn btn-primary" href="signup.jsp">Signup » </a> <a
							class="btn btn-primary" href="login.jsp">Login » </a>
					</div>
			
					<div></div>
				</div>
				<% } else {%>
   					<div class="container">
						<div class="jumbotron">
							<div>
								<h2>Welcome to Food Paradise, <s:property value="#session.username" /></h2>
							<p>Let's have a tour of the local restaurants, special events, and make some friends.</p>
							<li><a href="advancedsearch.jsp">Advanced Search</a></li>
							</div><br/>
							
							<div align="center">
							<form action="advancedfriendaction" method="post">
						      <input type="submit" value="Search"/><br/>
						      (Search the restaurants that my friends have visited)
						   </form>
						   </div>
						   <div align="center">
							<form action="advancedfoodaction" method="post">
						     <input type="text" name="searchquery"/>
						      <input type="submit" value="Search"/><br/>
						      (Search the restaurants that owns this food)
						   </form>
						   </div>
				
					</div>
				<% } %>
		<div class="container">
			<div class="jumbotron">
			<h2>Matched Restaurants</h2>
				<table border="3">
					<tr>
					    <td width="5%" align="center">Name</td>
					    <td width="5%" align="center">Address</td>
					    <td width="5%" align="center">Latitude</td>
					    <td width="5%" align="center">Longitude</td>
					    <td width="5%" align="center">Rating</td>
					    <td width="5%" align="center">Rating_Number</td>
					    <td width="5%" align="center">Review</td>
					    <td width="5%" align="center">Check In</td>
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
					    <td width="5%" align="center">
					    <s:form action="checkinaction">
					        <s:hidden name="checkin" value="%{restaurant_id}" />
					        <s:submit label="Check In" value="Check In" theme="simple" />
					    </s:form>
					    </td>
					 </tr>
					 </s:iterator>
				 </table>  

	
		</div>
		</div>



</body>
</html>