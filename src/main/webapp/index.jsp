<html>
<title>Main Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<!-- Custom CSS Importing -->
<link rel="stylesheet" type="text/css"
	href="resources/css/custom/index/index.css">
<body>
	<h2>CGPA Calculator System</h2>
	<jsp:include page="views/header/nav_bar.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-3">
				<div class="list-group-item list-group-item-info"
					style="font-weight: 600; font-size: 16px; margin-bottom: 10px; overflow: hidden;">
					Top Marks By Courses <a href="#" class="top_marks_by_courses"><img
						src="resources/images/download_image.png" width="30px"
						height="30px" data-toggle="tooltip" class="pull-right "
						title="Download Report" /></a>
				</div>
				<div
					class="list-group-item list-group-item-danger dif_formats text-center">
					<a href="rest-jasper-report/pdf/top_high_course_marks"
						target="_blank"><img src="resources/images/pdf.png"
						width="30px" height="30px" data-toggle="tooltip" class=" "
						title="Download PDF" /></a> <a
						href="rest-jasper-report/csv/top_high_course_marks"
						target="_blank"><img src="resources/images/csv.png"
						width="30px" height="30px" data-toggle="tooltip" class=" "
						title="Download CSV" /></a> <a
						href="rest-jasper-report/html/top_high_course_marks"
						target="_blank"><img src="resources/images/html.png"
						width="30px" height="30px" data-toggle="tooltip" class=" "
						title="View HTML" /></a>
				</div>
				<div class="crnt_smstr_list" id="students_with_higher_marks">
				</div>
			</div>

			<div class="col-xs-3">
				<div class="list-group list-group-item list-group-item-success"
					style="font-weight: 600; font-size: 16px;">
					<span class="badge" style="font-size: 16px;">No of Courses</span>
					Semester By Courses
				</div>
				<ul class="list-group smster_list crnt_smstr_list"
					id="courses_by_smster">
				</ul>
			</div>
			<div class="col-xs-3">
				<div class="list-group-item list-group-item-warning"
					style="font-weight: 600; font-size: 16px; margin-bottom: 10px;">
					Students By Current Semester</div>
				<div class="crnt_smstr_list" id="curr_std_info"></div>
			</div>
			<div class="col-xs-3">
				<div class="list-group-item list-group-item-warning"
					style="font-weight: 600; font-size: 16px; margin-bottom: 10px; overflow: hidden;">
					Top CGPA Holders <a href="#" class="top_marks_by_courses2"><img
						src="resources/images/download_image.png" width="30px"
						height="30px" data-toggle="tooltip" title="Download Report"
						class="pull-right" /></a>
				</div>
				<div
					class="list-group-item list-group-item-danger dif_formats2 text-center">
					<a href="rest-jasper-report/pdf/top_cgpa_holder" target="_blank"><img
						src="resources/images/pdf.png" width="30px" height="30px"
						data-toggle="tooltip" class=" " title="Download PDF" /></a> <a
						href="rest-jasper-report/csv/top_cgpa_holder" target="_blank"><img
						src="resources/images/csv.png" width="30px" height="30px"
						data-toggle="tooltip" class=" " title="Download CSV" /></a> <a
						href="rest-jasper-report/html/top_cgpa_holder" target="_blank"><img
						src="resources/images/html.png" width="30px" height="30px"
						data-toggle="tooltip" class=" " title="View HTML" /></a>
				</div>
				<div class="crnt_smstr_list" id="top_cgpa_of_students"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="resources/js/custom/index/index.js"></script>
</html>