<!DOCTYPE html>
<%@page import="com.ihsinformatics.spring.appgpa.model.SemesterResultsPOJO"%>
<%@page import="java.util.List"%>
<%@page import="com.ihsinformatics.spring.appgpa.service.imp.SemesterResultsServiceImp"%>
<%@page import="com.ihsinformatics.spring.appgpa.service.SemesterResultsService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Semester Results</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<!-- include the script -->
<script src="../resources/js/alertify.min.js"></script>
<!-- include the style -->
<link rel="stylesheet" href="../resources/css/alertify.min.css" />
<!-- include a theme -->
<link rel="stylesheet" href="../resources/css/themes/default.min.css" />
<link rel="stylesheet" href="../resources/css/bootstrap_search.css"/>
</head>
<body>
<jsp:include page="../header/nav_bar.jsp"></jsp:include>
	
	<input type="hidden" id="fromRequest" value="${alertMessageIdentifier}">
	

	<div class="container">
		<h1>Semester Results OF ${semesterResults.get(0).getFirstName()}</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Registration No</th>
					<th>Semester</th>
					<th>Semester GPA</th>
					<th>CGPA</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${semesterResults}" var="semRes">
					<tr>
						<td>${semRes.getFirstName()}</td>
						<td>${semRes.getLastName()}</td>
						<td>${semRes.getRegistrationNo()}</td>
						<td>${semRes.getSemesterNo()}</td>
						<td>${semRes.getSemesterGPA()}</td>
						<td>${semRes.getcGPA()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br /> <a href="add_semester_results_form.jsp">Add New Semester Results</a>
		<a href="${pageContext.request.contextPath}/semester-results/viewSemesterResults" style="float:right">View All Semester Results</a>
	</div>
	<script type="text/javascript">
	
		var fromRequest = document.getElementById("fromRequest").value;
		if(fromRequest != null && fromRequest == "from-create"){
			alertify.success('Semester Result Added');
			document.getElementById("fromRequest").value = null;
		}else if(fromRequest != null && fromRequest == "retrieve"){
			alertify.success('Exist');
			document.getElementById("fromRequest").value = null;
		}
		
	</script>

</body>
</html>

