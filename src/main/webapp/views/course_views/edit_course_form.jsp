<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Edit Form</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../header/nav_bar.jsp"></jsp:include>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="container">
		<h1>Edit Course</h1>

		<form action="${pageContext.request.contextPath}/course/editCourse" method="POST">
			<input type="hidden" name="courseId" id="courseId"
				value="${course.getCourseId()}" />
			<div class="form-group">
				<label for="courseCode">Course Code:</label> <input type="text"
					class="form-control" id="courseCode"
					value="${course.getCourseCode()}" name="courseCode" onchange="isValidCode()" required>
				<span class="error"><p id="course_code_error"></p></span>
			</div>
			<div class="form-group">
				<label for="name">Course Name:</label> <input type="text"
					class="form-control" id="name" value="${course.getName()}"
					name="name" onchange="isValidCourseName()" required>
				<span class="error"><p id="course_name_error"></p></span>
			</div>
			<div class="form-group">
				<label for="name">Semester No:</label> <select name="semesterId"
					required>
					<c:forEach items="${semesters}" var="semester">
						<option value='<c:out value="${semester.getSemesterId()}"/>'><c:out
								value="${semester.getSemesterNo()}" /></option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-default">Update Course</button>
		</form>
	</div>
</body>
	<!-- include adding course's validation -->
	<script src="${pageContext.request.contextPath}/resources/js/custom/course/course_validation.js"></script>

</html>