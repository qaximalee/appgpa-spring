<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Semester Results</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>

<link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,500" rel="stylesheet"/>

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- include the script -->
<script src="../resources/js/alertify.min.js"></script>
<!-- include the style -->
<link rel="stylesheet" href="../resources/css/alertify.min.css" />
<!-- include a theme -->
<link rel="stylesheet" href="../resources/css/themes/default.min.css" />
<link rel="stylesheet" href="../resources/css/bootstrap_search.css"/>
<style>
 	.dif_formats {
	    position: absolute;
	    right: 104px;
	    display: none;
	}
 	.dif_formats a{
 		display: inline-block;
 		margin-left: 5px;
 		position: relative;
 		z-index: 1;
 	}
 </style>
</head>
<body>
<jsp:include page="../header/nav_bar.jsp"></jsp:include>
	
	<input type="hidden" id="fromRequest" value="${pageContext.request.contextPath}">
	
	<div class="row">
	<div class="container">
		<h2 style="overflow:hidden;">Semester Results List
			<a href="#" class="all_stdnt_link"><img src="../resources/images/download_image.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip"  title="Download Report"/></a>
			<div class="dif_formats">
				<a href="../rest-jasper-report/pdf/semester_results" target="_blank"><img src="../resources/images/pdf.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip" title="Download PDF"/></a>
				<a href="../rest-jasper-report/csv/semester_results"" target="_blank"><img src="../resources/images/csv.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip" title="Download CSV"/></a>
				<a href="../rest-jasper-report/html/semester_results"" target="_blank"><img src="../resources/images/html.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip" title="View HTML"/></a>
			</div>
		</h2><!-- <a href="../rest-jasper-report/semester_results_pdf" target="_blank"><img src="../resources/images/download_image.png" width="30px" height="30px" data-toggle="tooltip" title="Download PDF"/></a> -->
		<table class="table responsive" id="sort">
			<thead>
				<tr>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Registration No</th>
					<th scope="col">Semester</th>
					<th scope="col">Semester GPA</th>
					<th scope="col">CGPA</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${semesterResults}" var="semRes">
					<tr>
						<td data-table-header="First Name">${semRes.getFirstName()}</td>
						<td data-table-header="Last Name">${semRes.getLastName()}</td>
						<td data-table-header="Registration No">${semRes.getRegistrationNo()}</td>
						<td data-table-header="Semester">${semRes.getSemesterNo()}</td>
						<td data-table-header="Semester GPA">${semRes.getSemesterGPA()}</td>
						<td data-table-header="CGPA">${semRes.getcGPA()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br /> <a href="${pageContext.request.contextPath}/semester-results/">Add New Semester Results</a>
	</div></div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
	<script src="https://cdn.datatables.net/plug-ins/1.10.15/sorting/stringMonthYear.js"></script>
	<script type="text/javascript">
		// toggle docs formats
		$(document).ready(function(){
			$('.all_stdnt_link').click(function(){
				$('.dif_formats').fadeToggle();
			});
		});
		$(document).ready(function() {
		   $("#sort").DataTable({
		      columnDefs : [
		    			{ type : 'date', targets : [5] }
						],  
		   					});
		});
		var fromRequest = document.getElementById("fromRequest").value;
		if(fromRequest != null){
			alertify.success('Semester Result Added');
			document.getElementById("fromRequest").value = null;
		}
		
	</script>

</body>
</html>

