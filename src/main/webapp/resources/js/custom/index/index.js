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
		const url = "rest-student-statistics/getStudentsCurrentSemester";

		// Populate Students Info
		return new Promise((resolve, reject) => {
			//console.log("called");
			$.getJSON(url,(data) => data ? resolve(data) : reject(data));
		})
	}

	function getNoOfCoursesBySemester(){
		const url = "rest-student-statistics/getTotalCoursesBySemester";

		return new Promise((resolve, reject) => {
			$.getJSON(url,(data) => data ? resolve(data) : reject(data));
		});
	}

	function getStudentsWithHigherMarks(){
		const url = "rest-student-statistics/getCoursesByHigherMarks";

		return new Promise((resolve, reject) => {
			$.getJSON(url,(data) => data ? resolve(data) : reject(data));
		});
	}

	function getTopCgpaHolder(){
		const url = "rest-student-statistics/getTopCgpaHolder";

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