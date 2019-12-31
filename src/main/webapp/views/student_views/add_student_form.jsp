<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Student Form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
	<jsp:include page="../header/nav_bar.jsp"></jsp:include>

	<div class="container">
		<a href="${pageContext.request.contextPath}/student/viewStudents" style="float: right">View All Records</a>
		<br />

		<h1>Add New Student</h1>
		<form action="${pageContext.request.contextPath}/student/addStudent" method="post" name="studentForm"
			id="studentForm" onsubmit="return getValidated()">
			<div class="form-group">
				<label for="firstName">First Name:</label> <input type="text"
					class="form-control" id="firstName" placeholder="Enter First Name"
					name="firstName" onchange="fnameValidation(this.value)" required>
				<span class="error"><p id="fname_error"></p></span>
			</div>
			<div class="form-group">
				<label for="lastName">Last Name:</label> <input type="text"
					class="form-control" id="lastName" placeholder="Enter Last Name"
					name="lastName" onchange="lnameValidation(this.value)" required>
					<span class="error"><p id="lname_error"></p></span>
			</div>
			<div class="form-group">
				<label for="registrationNo">Registration No:</label> <input type="text"
					class="form-control" id="registrationNo"
					placeholder="Enter Registration No" name="registrationNo"
					onchange="return isRegNoValid()" required>
					<span class="error"><p id="reg_no_error"></p></span>
			</div>
			<button type="submit" class="btn btn-default">Create Student</button>
		</form>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom/student/student_validation.js"></script>
</body>

</html>
