<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="assets/css/bootstrap-united.css" rel="stylesheet" />


<style>
.error {
	color: #ff0000;
	font-size: 0.9em;
	font-weight: bold;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}

input[type="text"],input[type="password"] {
	height: 40px;
}
</style>

<title>Food Paradise Login</title>

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
    				<li class="active"><a href="/FoodParadise/login.jsp">Login</a></li>
				<% } else {%>
   				 <li class="active"><a href="/FoodParadise/login.jsp"><s:property value="#session.username" /></a></li>
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
							<h1>Welcome to Online Food Paradise Login</h1>
							<p>Login to explore the complete features!</p>
						</div>
			
						<form action="loginaction" method="post">
					      User:	 <input type="text" name="user"/>
					      Password:<input type="password" name="password"/>
					      <input type="submit" value="Login"/>		
						</form>
					</div>
				</div>
	<% } else {%>
		<div class="container">
			<div class="jumbotron">
				<div>
					<h2>Welcome to Food Paradise, <s:property value="#session.username" /></h2>
				</div>
			</div>
		</div>
	<% } %>
	
</body>
</html>