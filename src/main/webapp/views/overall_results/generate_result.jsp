<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Semester Results</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>	
<style>
 	.dif_formats {
	    position: absolute;
	    right: 180px;
	    display: none;
	}
 	.dif_formats a{
 		display: inline-block;
 		margin-left: 5px;
 		position: relative;
 		z-index: 1;
 	}
 	.all_stdnt_link{
 	    position: absolute;
    right: 180px;
    top: 90px;
 	}
 </style>
</head>
<body>
<jsp:include page="../header/nav_bar.jsp"></jsp:include>

	<div class="container">
		<form onchange="generateResult()">
			<div class="form-group" style="overflow: hidden">
				<label for="studentId">Student Id:</label> <select class="form-control" style="width: 15%;" name="studentId" id="studentId" onchange="getStudent()">
					<c:forEach items="${studentList}" var="student">
						<option value='<c:out value="${student.getStudentId()}"/>'><c:out
								value="${student.getRegistrationNo()}" /></option>
					</c:forEach>
				</select>
				<a href="#" class="all_stdnt_link" id="downloadAnchorTag"><img src="../resources/images/download_image.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip"  title="Download Report"/></a>
				<div class="dif_formats">
					<a href="" target="_blank" id="pdf_report"><img src="../resources/images/pdf.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip" title="Download PDF"/></a>
					<a href="" target="_blank" id="csv_report"><img src="../resources/images/csv.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip" title="Download CSV"/></a>
					<a href="" target="_blank" id="html_report"><img src="../resources/images/html.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip" title="View HTML"/></a>
				</div>
			</div>
			<span id="studName">Choose Student Registration NO...</span><br>
			<div id="downloadAnchorTag"></div>
		</form>
		<h3 id="error"></h3>
		<div id="result"></div>
		<table class="table table-striped" id="table">
			<!-- here goes our data! -->
		</table>
	</div>
	<script type="text/javascript">
		
		// toggle docs formats
		$(document).ready(function(){
			$('.all_stdnt_link').click(function(){
				$('.dif_formats').fadeToggle();
			
				$('#pdf_report').click(function(){
					let std = document.getElementById("studentId");
					let stdId = std.options[std.selectedIndex].value;
					console.log(stdId);
					document.getElementById("pdf_report").href = "../rest-jasper-report/pdf/result/"+stdId;					
				});
				$('#csv_report').click(function(){
					let std = document.getElementById("studentId");
					let stdId = std.options[std.selectedIndex].value;
					console.log(stdId);
					document.getElementById('csv_report').href = "../rest-jasper-report/csv/result/"+stdId;
				});
				$('#html_report').click(function(){
					let std = document.getElementById("studentId");
					let stdId = std.options[std.selectedIndex].value;
					console.log(stdId);
					document.getElementById('html_report').href = "../rest-jasper-report/html/result/"+stdId;
				});			
			});
		});
		
		function getStudent(){
			var std = document.getElementById("studentId");
			var stdId = std.options[std.selectedIndex].value; 
			const url = "../course-results/getStudentByRegistration?studentID="+stdId;
			
			// Populate dropdown with list of students
			$.getJSON(url, function (data) {
				var studentDetails = "Marksheet Of: "+data.firstName+" "+data.lastName;
				$('#studName').text(studentDetails);
				document.getElementById("studName").text = ""+data.firstName+" "+data.lastName;
				console.log(data.firstName+" "+data.lastName);
			});
		}
		
		function generateResult(){
			var std = document.getElementById("studentId");
			var stdId = std.options[std.selectedIndex].value; 
			var tableHead = ["Semester No", "Course Code", "Course Name", 
				"Percentage", "Credit Hours", "Grade", "GPA", "Total Points", "Semester GPA", "CGPA"];
			
			document.getElementById("error").innerHTML = "";

			const url = "../result/getResultByStudent?id="+stdId;
			$.getJSON(url, function (data) {
				// Show Report Download Button
				$("#downloadAnchorTag").show();
				if(data.results == ""){
					// Hide Report Download Button if there isn't any result for the student
					$("#downloadAnchorTag").hide();
				}
				var table = document.querySelector("table");
				var resultData = data.results;
				console.log(resultData[0]);			
				generateTableHead(table, tableHead);
				generateTable(table, resultData);

			});
		}

		function generateTableHead(table, tableHead){
			// REMOVE PREVIOUS DATA
			$("#table tr").remove();
			
			var table = table.createTHead();
			var row = table.insertRow();
			
			for(var i = 0; i < tableHead.length; i++){
				let th = document.createElement("th");
				let text = document.createTextNode(tableHead[i]);
				th.appendChild(text);
				row.appendChild(th);
			}
		}
		
		function generateTable(table, data) {
			var tableSequence = ["semesterNo", "courseCode", "courseName", 
				"percentage", "creditHours", "grade", "gpa", "totalPoints", "semesterGPA", "cgpa"];
			var switched = false;
			for (let i= 0; i < data.length; i++) {	
				let row;
				let element = data[i];
				if(switched){
					switched = false;
					//i--;
					//element = data[i];
					row = table.insertRow();
					for(var a = 0; a < tableSequence.length-2; a++){
						let cell = row.insertCell();
						let text = document.createTextNode("");
						cell.appendChild(text);
					}
					let cell1 = row.insertCell();
					let text1 = document.createTextNode(element['semesterGPA']);
					cell1.appendChild(text1);
					
					let cell2 = row.insertCell();
					let text2 = document.createTextNode(element['cgpa']);
					cell2.appendChild(text2);
					
				}else{
					//element = data[i];
					row = table.insertRow();
					let counter = 0; 
					//console.log(element.semesterNo);
					for (key in element) {
						//console.log(element.length);
						if(counter == tableSequence.length)
					 		break;
						if(counter < 8){
							let cell = row.insertCell();
							let text = document.createTextNode(element[tableSequence[counter]]);
							cell.appendChild(text);
						}
						counter++;
					}
					if(i == data.length-1 || element.semesterNo != data[i+1].semesterNo){
						
						i--;
						switched = true;
					}
				}
			} 
		}
	</script>
</body>
</html>

