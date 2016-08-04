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

<title>Food Paradise Signup</title>

</head>

<body>

	<script src="bootstrap/js/bootstrap.js">
		
	</script>

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
				<li class="active"><a href="signup.jsp">Signup</a></li>
    			<li><a href="login.jsp">Login</a></li>

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
				<h1>Welcome to Online Food Paradise Signup</h1>
				<p>Its absolutely quick!</p>
			</div>
		</div>

		<div></div>
	</div>
	
	<% if (session.getAttribute("errorsignup") == null) { %>
		<div class="container">
			<div class="jumbotron">
				<p>Fill all the information in the form</p>
				<form action="signupaction" method="post">
		      		UserId:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="user"/><br>
		      		Password:&nbsp;<input type="password" name="password"/><br>
		      		Nickname:&nbsp;<input type="text" name="nickname"/><br>
		      		<input type="submit" value="Signup"/>		
				</form>
			</div>
		</div>
	<% } else {%>
		<div class="container">
			<div class="jumbotron">
				<font color="red">Incorrect userid or password, try again!</font>
				<p>Fill all the information in the form</p>
				<form action="signupaction" method="post">
		      		UserId:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="user"/><br>
		      		Password:&nbsp;<input type="password" name="password"/><br>
		      		Nickname:&nbsp;<input type="text" name="nickname"/><br>
		      		<input type="submit" value="Signup"/>		
				</form>
			</div>
		</div>
	<% } %>

</body>
</html>