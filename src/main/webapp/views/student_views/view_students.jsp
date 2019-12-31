<!DOCTYPE html>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Students</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- include the script -->
	<script src="../resources/js/alertify.min.js"></script>
	<link rel="stylesheet" href="../resources/css/alertify.min.css" />
	
	<!-- include a theme -->
	<link rel="stylesheet" href="../resources/css/themes/default.min.css" />
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	
	<link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,500" rel="stylesheet"/>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
	
	<!-- Include Bootstrap for Searching -->
 	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap_search.css"/>
 	
 	<!-- include jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<!-- include table data scripts -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
	<script src="https://cdn.datatables.net/plug-ins/1.10.15/sorting/stringMonthYear.js"></script>
	
 	<!-- Include Bootstrap for Searching -->
 	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom/pdf_download_tag.css"/>
</head>
<body>
	<jsp:include page="../header/nav_bar.jsp"></jsp:include>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<input type="hidden" id="fromRequest" value="${alertMessageIdentitfier}">
	
	<div class="row">
	<div class="container">
		<h2 style="overflow:hidden;" >All Students		
		<a href="#" class="all_stdnt_link"><img src="../resources/images/download_image.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip"  title="Download Report"/></a>
		<div class="std_dif_formats">
			<a href="../rest-jasper-report/pdf/all_students" target="_blank"><img src="../resources/images/pdf.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip" title="Download PDF"/></a>
			<a href="../rest-jasper-report/csv/all_students" target="_blank"><img src="../resources/images/csv.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip" title="Download CSV"/></a>
			<a href="../rest-jasper-report/html/all_students" target="_blank"><img src="../resources/images/html.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip" title="View HTML"/></a>
		</div>
		</h2>
		<table class="table responsive" id="sort">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Registration No</th>
					<th scope="col">Edit</th>
					<th scope="col">Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${data}" var="std">
					<tr>
						<td data-table-header="Id">${std.getStudentId()}</td>
						<td data-table-header="First Name">${std.getFirstName()}</td>
						<td data-table-header="Last Name">${std.getLastName()}</td>
						<td data-table-header="Registration No">${std.getRegistrationNo()}</td>
						<td data-table-header="Edit"><a href="${pageContext.request.contextPath}/student/editStudentForm?id=${std.getStudentId()}">Edit</a></td>
						<td data-table-header="Delete" id="delete-student" onclick="deleteARecord()")><a href="${pageContext.request.contextPath}/student/deleteStudent?id=${std.getStudentId()}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br /> <a href="${pageContext.request.contextPath}/views/student_views/add_student_form.jsp">Add New Student</a>
	</div></div>
</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom/student/view_students.js"></script>
</html>