<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Courses Results</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- include the script -->
<script src="../resources/js/alertify.min.js"></script>
<!-- include the style -->
<link rel="stylesheet" href="../resources/css/alertify.min.css" />
<!-- include a theme -->
<link rel="stylesheet" href="../resources/css/themes/default.min.css" />

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" />

<link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,500"
	rel="stylesheet" />

<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
<script
	src="https://cdn.datatables.net/plug-ins/1.10.15/sorting/stringMonthYear.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap_search.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/custom/pdf_download_tag.css" />

</head>
<body>
	<jsp:include page="../header/nav_bar.jsp"></jsp:include>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div class="row">
		<div class="container">
			<h2 style="overflow: hidden;">
				Courses Results List <a href="#" class="all_course_results_link"><img
					src="${pageContext.request.contextPath}/resources/images/download_image.png"
					width="30px" height="30px" class="pull-right" data-toggle="tooltip"
					title="Download Report" /></a>
				<div class="course_results_dif_formats">
					<a
						href="${pageContext.request.contextPath}/rest-jasper-report/pdf/course_results"
						target="_blank"><img
						src="${pageContext.request.contextPath}/resources/images/pdf.png"
						width="30px" height="30px" class="pull-right"
						data-toggle="tooltip" title="Download PDF" /></a> <a
						href="${pageContext.request.contextPath}/rest-jasper-report/csv/course_results"
						target="_blank"><img
						src="${pageContext.request.contextPath}/resources/images/csv.png"
						width="30px" height="30px" class="pull-right"
						data-toggle="tooltip" title="Download CSV" /></a> <a
						href="${pageContext.request.contextPath}/rest-jasper-report/html/course_results"
						target="_blank"><img
						src="${pageContext.request.contextPath}/resources/images/html.png"
						width="30px" height="30px" class="pull-right"
						data-toggle="tooltip" title="View HTML" /></a>
				</div>
			</h2>

			<input type="hidden" id="fromRequest"
				value="${alertMessageIdentifier}">

			<table class="table responsive" id="sort">
				<thead>
					<tr>
						<th scope="col">First Name</th>
						<th scope="col">Last Name</th>
						<th scope="col">Registration No</th>
						<th scope="col">Course Name</th>
						<th scope="col">Semester</th>
						<th scope="col">Percentage</th>
						<th scope="col">GPA</th>
						<th scope="col">Grade</th>
						<th scope="col">Total Points</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${courseResultsList}" var="courseResults">
						<tr>
							<td data-table-header="First Name">${courseResults.getFirstName()}</td>
							<td data-table-header="Last Name">${courseResults.getLastName()}</td>
							<td data-table-header="Registration No">${courseResults.getRegistrationNo()}</td>
							<td data-table-header="Course Name">${courseResults.getCourseName()}</td>
							<td data-table-header="Semester">${courseResults.getSemesterNo()}</td>
							<td data-table-header="Percentage">${courseResults.getPercentage()}</td>
							<td data-table-header="GPA">${courseResults.getGpa()}</td>
							<td data-table-header="Grade">${courseResults.getGrade()}</td>
							<td data-table-header="Total Points">${courseResults.getTotalPoints()}</td>

							<td data-table-header="Actions"><a
								href="${pageContext.request.contextPath}/course-results/deleteCourseResult?id=${courseResults.getCourseResultId()}"
								id="delete">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br /> <a href="${pageContext.request.contextPath}/course-results/">Add
				New Course Result</a>
		</div>
	</div>
</body>
<script
	src="${pageContext.request.contextPath}/resources/js/custom/course/course-results/view_courses_results.js"></script>
</html>