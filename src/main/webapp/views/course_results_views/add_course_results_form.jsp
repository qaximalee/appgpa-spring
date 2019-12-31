<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Course Result</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body onload="getSem(), getStudent(this)">
	<jsp:include page="../header/nav_bar.jsp"></jsp:include>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="container">
		<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}"/>
		<a
			href="${pageContext.request.contextPath}/course-results/viewCourseResults"
			style="float: right">View All Courses Results</a>
		<h1>Add Course Results</h1>

		<form
			action="${pageContext.request.contextPath}/course-results/addCourseResults"
			method="post">
			<div class="form-group">
				<label for="studentId">Student Id:</label> <select
					class="form-control" name="studentId" id="studentId" required>
					<c:forEach items="${studentList}" var="student">
						<option value='<c:out value="${student.getStudentId()}"/>'><c:out
								value="${student.getRegistrationNo()}" /></option>
					</c:forEach>
				</select>
			</div>
			<h3 id="studName">Choose Student Registration NO...</h3>
			<div class="form-group" style="padding-top: 30px">
				<label for="semesterId">Semester Id:</label> <select
					class="form-control" name="semesterId" id="semesterId" required>
					<c:forEach items="${semesterList}" var="semester">
						<option value='<c:out value="${semester.getSemesterId()}"/>'><c:out
								value="${semester.getSemesterNo()}" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="courseId">Course Id:</label> <select
					class="form-control" name="courseId" id="courseId" required>
					<option value=""></option>
				</select>
			</div>
			<div class="form-group">
				<label for="percentage">Percentage:</label> <input type="number"
					min="0" max="100" class="form-control" id="percentage"
					placeholder="Enter Percentage" name="percentage" required>
			</div>
			<button type="submit" class="btn btn-default" id="course_button">Create Course
				Results</button>
		</form>
	</div>
	<script
		src="${pageContext.request.contextPath}/resources/js/custom/course/course-results/add_course_results.js"></script>
</body>
</html>
