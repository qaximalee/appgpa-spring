<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Semester Results Form</title>
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
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	<div class="container">
		<a href="${pageContext.request.contextPath}/semester-results/viewSemesterResults" style="float:right">View All Semester Results</a>	
		<h1>Add Semester Results</h1>

		<form action="${pageContext.request.contextPath}/semester-results/addSemesterResults" method="post">
			<div class="form-group">
				<label for="studentId">Student Id:</label> <select name="studentId" id="studentId" onchange="getStudent()"
					required>
					<c:forEach items="${studentList}" var="student">
						<option value='<c:out value="${student.getStudentId()}"/>'><c:out
								value="${student.getRegistrationNo()}" /></option>
					</c:forEach>
				</select>
			</div>
			<h3 id="studName">Choose Student Registration NO...</h3>
			<div class="form-group">
				<label for="semesterId">Semester Id:</label> <select
					name="semesterId" required>
					<c:forEach items="${semesterList}" var="semester">
						<option value='<c:out value="${semester.getSemesterId()}"/>'><c:out
								value="${semester.getSemesterNo()}" /></option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-default">Create Semester Results</button>
		</form>
	</div>
	
	<script>
		function getStudent(){
			var std = document.getElementById("studentId");
			var stdId = std.options[std.selectedIndex].value;
			const url = "course-results/getStudentByRegistration?studentID="+stdId;
	
			// Populate dropdown with list of provinces
			$.getJSON(url, function (data) {
				var studentDetails = "Full Name: "+data.firstName+" "+data.lastName;
				$('#studName').text(studentDetails);
				document.getElementById("studName").text = ""+data.firstName+" "+data.lastName;
				console.log(data.firstName+" "+data.lastName);
			});
			
		}
	</script>
</body>
</html>
