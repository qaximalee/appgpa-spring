<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Semester Form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- include the script -->
<script src="../../resources/alertify.min.js"></script>
<!-- include the style -->
<link rel="stylesheet" href="../../resources/css/alertify.min.css" />
<!-- include a theme -->
<link rel="stylesheet" href="../../resources/css/themes/default.min.css" />
</head>
<body>

<jsp:include page="../header/nav_bar.jsp"></jsp:include>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="container">
		<a href="${pageContext.request.contextPath}/course/viewCourses" style="float:right">View All Courses</a>
		<h1>Add Course</h1>

		<form action="${pageContext.request.contextPath}/course/addCourse" method="post">
			<div class="form-group">
				<label for="courseCode">Course Code:</label> <input type="text"
					class="form-control" id="courseCode" placeholder="Enter CourseCode"
					name="courseCode" required onchange="isValidCode(this.value)">
			</div>
			<div class="form-group">
				<label for="name">Semester No:</label> <select name="semester"
					required>
					<c:forEach items="${semesterList}" var="sem">
						<option value='<c:out value="${sem.getSemesterId()}"/>'><c:out
								value="${sem.getSemesterNo()}" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="name">Course Name:</label> <input type="text"
					class="form-control" id="name" placeholder="Enter Course Name"
					name="name" onchange="isValidCourseName(this.value)" required>
			</div>
			<button type="submit" class="btn btn-default">Create Course</button>
		</form>
	</div>
	<script>
		function isValidCode(value){
			var regName = /^\d{4}$/;
			if(!regName.test(value) && value != ""){
				alertify.alert("Please Enter Valid Course Code (ie 4 digits code)");
			}
		}
		
		function isValidCourseName(value){
			var regName = /^[a-zA-Z]+/;
			if(!regName.test(value) && value != ""){
				alertify.alert("Please Enter Valid Course Name");
			}
		}
	</script>
	
</body>
</html>
