<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Register</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
	<div class="container">
		<h1>Add New User</h1>
		<form action="${pageContext.request.contextPath}/register" method="post" name="userForm"
			id="userForm">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="username" placeholder="Enter User Name"
					name="username" required>
			</div>
			<div class="form-group">
				<label for="password">Last Name:</label> <input type="password"
					class="form-control" id="password" placeholder="Enter Password"
					name="password" required>
			</div>
			<button type="submit" class="btn btn-default">Create User</button>
		</form>
	</div>
</body>

</html>
