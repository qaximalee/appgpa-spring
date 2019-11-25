<!DOCTYPE html>
<%@page import="com.ihsinformatics.spring.appgpa.model.Student"%>
<%@page import="com.ihsinformatics.spring.appgpa.service.imp.StudentServiceImp"%>
<%@page import="com.ihsinformatics.spring.appgpa.service.StudentService"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
					name="firstName">
			</div>
			<div class="form-group">
				<label for="lastName">Last Name:</label> <input type="text"
					class="form-control" id="lastName" value="${student.getLastName()}"
					name="lastName">
			</div>
			<div class="form-group">
				<label for="registrationNo">First Name:</label> <input type="text"
					class="form-control" id="registrationNo"
					value="${student.getRegistrationNo()}" name="registrationNo">
			</div>
			<button type="submit" class="btn btn-default">Edit Student</button>
		</form>
	</div>
</body>
</html>