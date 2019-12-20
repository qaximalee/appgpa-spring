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
<style>
	body{ background-color: #f1f1f1; }
	/* width */
::-webkit-scrollbar {
  width: 5px;
  
}

/* Track */
::-webkit-scrollbar-track {
  background: #f1f1f1; 
}
 
/* Handle */
::-webkit-scrollbar-thumb {
  background: #777; 
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background:#555; 
}
	.list-group-item { margin-bottom: 10px; margin-right: 5px;  }
	.list-group{ margin-bottom: 10px; }
	.crnt_smstr_list{ height: 320px; overflow-x: hidden; }
.dif_formats,.dif_formats2{
	display: none;
}
.dif_formats a{ display: inline-block; width:32%; }
.dif_formats2 a{ display: inline-block; width:32%; }
</style>
<body>
	<h2>CGPA Calculator System</h2>
	<jsp:include page="views/header/nav_bar.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-3">
				<div class="list-group-item list-group-item-info" style="font-weight: 600; font-size: 16px; margin-bottom: 10px; overflow:hidden;">	    	
			    	Top Marks By Courses
			    	<a href="#" class="top_marks_by_courses"><img src="resources/images/download_image.png" width="30px" height="30px" data-toggle="tooltip" class="pull-right " title="Download Report"/></a>
			  	</div>
			  	<div class="list-group-item list-group-item-danger dif_formats text-center">
					<a href="rest-jasper-report/pdf/top_high_course_marks" target="_blank"><img src="resources/images/pdf.png" width="30px" height="30px" data-toggle="tooltip" class=" " title="Download PDF"/></a>
					<a href="rest-jasper-report/csv/top_high_course_marks" target="_blank"><img src="resources/images/csv.png" width="30px" height="30px" data-toggle="tooltip" class=" " title="Download CSV"/></a>
					<a href="rest-jasper-report/html/top_high_course_marks" target="_blank"><img src="resources/images/html.png" width="30px" height="30px" data-toggle="tooltip" class=" " title="View HTML"/></a>
				</div>
			  	<div class="crnt_smstr_list" id="students_with_higher_marks">
					<!-- <div class="list-group">
						<a href="#" class="list-group-item ">
						    <h4 class="list-group-item-heading">Qasim<span class="badge pull-right">43434</span></h4>
						    <p class="list-group-item-text">Course Name: Lorem Ipsum</p>
						    <p class="list-group-item-text">Couse Code: 03212</p>
						</a>
					</div> -->
				</div>
			</div>
			
			<div class="col-xs-3">
				<div class="list-group list-group-item list-group-item-success" style="font-weight: 600; font-size: 16px;">
			    	<span class="badge" style="font-size: 16px;">No of Courses</span>
			    	Semester By Courses
			  	</div>
				<ul class="list-group smster_list crnt_smstr_list" id="courses_by_smster">
				  	<!-- <li class="list-group-item">
				    	<span class="badge">14</span>
				    	1st Semester
				  	</li> -->
				</ul>
			</div>
			<div class="col-xs-3">
				<div class="list-group-item list-group-item-warning" style="font-weight: 600; font-size: 16px; margin-bottom: 10px;">	    	Students By Current Semester
				  	</div>
				<div class="crnt_smstr_list" id="curr_std_info">
					
					<!-- <div class="list-group" >
						<a href="#" class="list-group-item ">
						    <h4 class="list-group-item-heading">Qasim<span class="badge pull-right">43434</span></h4>
						    <p class="list-group-item-text">Course Name: Lorem Ipsum</p>
						    <p class="list-group-item-text">Couse Code: 03212</p>
						</a>
					</div> -->
				</div>
			</div>
			<div class="col-xs-3">
				<div class="list-group-item list-group-item-warning" style="font-weight: 600; font-size: 16px; margin-bottom: 10px; overflow: hidden;">	Top CGPA Holders
					  	<a href="#" class="top_marks_by_courses2"><img src="resources/images/download_image.png" width="30px" height="30px" data-toggle="tooltip" title="Download Report" class="pull-right" /></a>
				  	</div>
				<div class="list-group-item list-group-item-danger dif_formats2 text-center">
					<a href="rest-jasper-report/pdf/top_cgpa_holder" target="_blank"><img src="resources/images/pdf.png" width="30px" height="30px" data-toggle="tooltip" class=" " title="Download PDF"/></a>
					<a href="rest-jasper-report/csv/top_cgpa_holder" target="_blank"><img src="resources/images/csv.png" width="30px" height="30px" data-toggle="tooltip" class=" " title="Download CSV"/></a>
					<a href="rest-jasper-report/html/top_cgpa_holder" target="_blank"><img src="resources/images/html.png" width="30px" height="30px" data-toggle="tooltip" class=" " title="View HTML"/></a>
				</div>			  	
				<div class="crnt_smstr_list" id="top_cgpa_of_students">
					
					<!-- <div class="list-group" >
						<a href="#" class="list-group-item ">
						    <h4 class="list-group-item-heading">Qasim<span class="badge pull-right">43434</span></h4>
						    <p class="list-group-item-text">Course Name: Lorem Ipsum</p>
						    <p class="list-group-item-text">Couse Code: 03212</p>
						</a>
					</div> -->
				</div>
			</div>
		</div>
	</div>
	
	
</body>
<script type="text/javascript">
	
	$(document).ready(function(){
		var noOfSemesters = ['1st', '2nd', '3rd', '4th', '5th', '6th', '7th', '8th'];
		 getStudentBySemester()
		 .then(function (data) {
		 	console.log('data',data);
		 	 let studentInfoDiv = $('#curr_std_info');
		 	for(var i = 0; i<data.length ;i++ ){
				 studentInfoDiv.append('<a href="#" class="list-group-item "><h4 class="list-group-item-heading">'+data[i].student.firstName+' '+data[i].student.lastName+'<span class="badge pull-right">'+noOfSemesters[data[i].currentSemester]+'</span></h4><p class="list-group-item-text">Registration No: '+data[i].student.registrationNo+'</p></a>');

		 	}
					 })
		 .catch(function(e) {
			 console.log("error",e);
		 });

		 getNoOfCoursesBySemester()
		 .then(function (data) {
			console.log('data',data);
			let coursesBySemester = $('#courses_by_smster');
			for(var i = 0; i < data.length; i++){
				coursesBySemester.append('<li class="list-group-item"><span class="badge">'+data[i].totalCourse+' Course(s)</span>'+noOfSemesters[i]+' Semester</li>');
			}	
		 })
		 .catch(function (e) {
			 console.log("error",e);
		 });

		 getStudentsWithHigherMarks()
		 .then((data) => {
			//console.log('data',data);
			let studentsWithHigherMarks = $('#students_with_higher_marks');
			for(var i = 0; i < data.length; i++){
				studentsWithHigherMarks.append('<div class="list-group"><a href="#" class="list-group-item "><h4 class="list-group-item-heading">'+data[i].students.firstName+' '+data[i].students.lastName+'<span class="badge pull-right">'+data[i].marks+'%</span></h4><p class="list-group-item-text">Registration No: '+data[i].students.registrationNo+'</p><p class="list-group-item-text">Course Name: '+data[i].course.name+'</p><p class="list-group-item-text">Couse Code: '+data[i].course.courseCode+'</p></a></div>');
			}
		 })
		 .catch((e) => {
			 console.log("error",e);
		 });
		
		 getTopCgpaHolder()
		 .then((data) =>{
			//console.log('data',data);
			let studentsWithHigherMarks = $('#top_cgpa_of_students');
			for(var i = 0; i < data.length; i++){
				studentsWithHigherMarks.append('<div class="list-group"><a href="#" class="list-group-item "><h4 class="list-group-item-heading">'+data[i].student.firstName+' '+data[i].student.lastName+'<span class="badge pull-right">'+data[i].cGpa+'</span></h4><p class="list-group-item-text">Registration No: '+data[i].student.registrationNo+'</p><p class="list-group-item-text">Semester: '+data[i].semester.semesterNo+'</p><p class="list-group-item-text">Semester GPA: '+data[i].semesterGPA+'</p></a></div>');
			} 
		 })

	});

	function getStudentBySemester(){
		const url = "student-statistics/getStudentsCurrentSemester";

		// Populate Students Info
		return new Promise((resolve, reject) => {
			//console.log("called");
			$.getJSON(url,(data) => data ? resolve(data) : reject(data));
		})
	}

	function getNoOfCoursesBySemester(){
		const url = "student-statistics/getTotalCoursesBySemester";

		return new Promise((resolve, reject) => {
			$.getJSON(url,(data) => data ? resolve(data) : reject(data));
		});
	}

	function getStudentsWithHigherMarks(){
		const url = "student-statistics/getCoursesByHigherMarks";

		return new Promise((resolve, reject) => {
			$.getJSON(url,(data) => data ? resolve(data) : reject(data));
		});
	}

	function getTopCgpaHolder(){
		const url = "student-statistics/getTopCgpaHolder";

		return new Promise((resolve, reject) => {
			$.getJSON(url,(data) => data ? resolve(data) : reject(data));
		});
	}

	// toggle 
	$(document).ready(function(){
		$('.top_marks_by_courses').click(function(){
			$('.dif_formats').slideToggle();
		});
		$('.top_marks_by_courses2').click(function(){
			$('.dif_formats2').slideToggle();
		});
	});

</script>
</html>