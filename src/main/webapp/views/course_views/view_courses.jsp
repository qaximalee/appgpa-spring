<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>View Courses</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- include the script -->
	<script
		src="${pageContext.request.contextPath}/resources/js/alertify.min.js"></script>
	<!-- include the style -->
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/alertify.min.css" />
	<!-- include a theme -->
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/themes/default.min.css" />
	
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
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/bootstrap_search.css" />
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
	<script
		src="https://cdn.datatables.net/plug-ins/1.10.15/sorting/stringMonthYear.js"></script>
	
	<!-- Custom CSS Importing -->
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/custom/pdf_download_tag.css">

</head>
<body>
	<jsp:include page="../header/nav_bar.jsp"></jsp:include>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<input type="hidden" id="fromRequest"
		value="${alertMessageIdentitfier}">

	<div class="row">
		<div class="container">
			<h2 style="overflow: hidden;">
				Courses List <a href="#" class="all_course_link"><img
					src="${pageContext.request.contextPath}/resources/images/download_image.png"
					width="30px" height="30px" class="pull-right" data-toggle="tooltip"
					title="Download Report" /></a>
				<div class="course_dif_formats">
					<a
						href="${pageContext.request.contextPath}/rest-jasper-report/pdf/all_courses"
						target="_blank"><img
						src="${pageContext.request.contextPath}/resources/images/pdf.png"
						width="30px" height="30px" class="pull-right"
						data-toggle="tooltip" title="Download PDF" /></a> <a
						href="${pageContext.request.contextPath}/rest-jasper-report/csv/all_courses"
						target="_blank"><img
						src="${pageContext.request.contextPath}/resources/images/csv.png"
						width="30px" height="30px" class="pull-right"
						data-toggle="tooltip" title="Download CSV" /></a> <a
						href="${pageContext.request.contextPath}/rest-jasper-report/html/all_courses"
						target="_blank"><img
						src="${pageContext.request.contextPath}/resources/images/html.png"
						width="30px" height="30px" class="pull-right"
						data-toggle="tooltip" title="View HTML" /></a>
				</div>
			</h2>
			<table class="table responsive" id="sort">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Course No</th>
						<th scope="col">Course Name</th>
						<th scope="col">Semester</th>
						<th scope="col">Edit</th>
						<th scope="col">Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${courseList}" var="std">
						<tr>
							<td data-table-header="Id">${std.getCourseId()}</td>
							<td data-table-header="Course No">${std.getCourseCode()}</td>
							<td data-table-header="Course Name">${std.getName()}</td>
							<td data-table-header="Semester">${std.getSemester().getSemesterNo()}</td>
							<td data-table-header="Edit"><a
								href="${pageContext.request.contextPath}/course/editCourseForm?id=${std.getCourseId()}">Edit</a></td>
							<td data-table-header="Delete"><a
								href="${pageContext.request.contextPath}/course/deleteCourse?id=${std.getCourseId()}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br /> <a href="${pageContext.request.contextPath}/course/">Add
				New Course</a>
		</div>
	</div>
</body>
<!-- include view courses script -->
<script
	src="${pageContext.request.contextPath}/resources/js/custom/course/view_courses.js"></script>
</html>