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
	

		<div class="container">
			<div class="jumbotron">
				<div>
					<h2>Welcome to Food Paradise, <s:property value="#session.username" /></h2>
				<p>Make a review.</p>
				</div><br/>
				<div align="center">
				<s:iterator value="singleReviews">
			        <form action="revieweditaction" method="post">
			        	Restaurant:&nbsp;&nbsp;<s:property value="restaurant_name"/>&nbsp;&nbsp;
			        	Rating:&nbsp;&nbsp;<s:textfield name = "ratings"
				            value = "%{ratings}"/>&nbsp;&nbsp;
			        	Comments:&nbsp;&nbsp;<s:textfield name = "comments" 
				            value = "%{comments}"/><br/>
				    <s:hidden name="restaurant_id" value="%{restaurant_id}" />
			        <input type="submit" value="Save Review & Return"/>
			        </form>
			    </s:iterator> 
				<!-- <form action="revieweditaction" method="post">
			     <input type="text" name="searchquery"/>
			      <input type="submit" value="Search"/><br/>
			      restaurants<input type="checkbox" name="checkres" value="true" id="checkres" checked="checked"/>
			      &nbsp;&nbsp;events<input type="checkbox" name="checkeve" value="true" id="checkeve" checked="checked"/>
			      &nbsp;&nbsp;user<input type="checkbox" name="checkuser" value="true" id="checkuser"/>
			      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;		&nbsp;&nbsp;
			   </form> -->
			   </div>
	
		</div>


<!--    <form action="loginaction" method="post">
      User:<br/><input type="text" name="user"/><br/>
      <input type="submit" value="Login"/>		
   </form> -->
</body>
</html>