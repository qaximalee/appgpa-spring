<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Edit Form</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>

<body>
<jsp:include page="../header/nav_bar.jsp"></jsp:include>
	<div class="container">
		<h1>Edit Form</h1>
		<form action="${pageContext.request.contextPath}/student/editStudent" method="POST">
			<input type="hidden" name="studentId" value="${student.getStudentId()}" />
			<div class="form-group">
				<label for="firstName">First Name:</label> <input type="text"
					class="form-control" id="firstName" value="${student.getFirstName()}"
					name="firstName" onchange="fnameValidation(this.value)" required>
				<span class="error"><p id="fname_error"></p></span>
			</div>
			<div class="form-group">
				<label for="lastName">Last Name:</label> <input type="text"
					class="form-control" id="lastName" value="${student.getLastName()}"
					name="lastName" onchange="lnameValidation(this.value)" required>
				<span class="error"><p id="lname_error"></p></span>
			</div>
			<div class="form-group">
				<label for="registrationNo">First Name:</label> <input type="text"
					class="form-control" id="registrationNo"
					value="${student.getRegistrationNo()}" name="registrationNo" onchange="return isRegNoValid()" required>
				<span class="error"><p id="reg_no_error"></p></span>
			</div>
			<button type="submit" class="btn btn-default">Edit Student</button>
		</form>
	</div>
</body>
	<!-- include student validation script -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom/student/student_validation.js"></script>
</html>