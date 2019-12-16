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
</head>
<body>
<jsp:include page="../header/nav_bar.jsp"></jsp:include>

	<div class="container">
		<form onchange="generateResult()">
			<div class="form-group">
				<label for="studentId">Student Id:</label> <select class="form-control" style="width: 15%;" name="studentId" id="studentId" onchange="getStudent()">
					<c:forEach items="${studentList}" var="student">
						<option value='<c:out value="${student.getStudentId()}"/>'><c:out
								value="${student.getRegistrationNo()}" /></option>
					</c:forEach>
				</select>
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
		function getStudent(){
			var std = document.getElementById("studentId");
			var stdId = std.options[std.selectedIndex].value; 
			const url = "../course-results/getStudentByRegistration?studentID="+stdId;
			document.getElementById("downloadAnchorTag").innerHTML = "";
			$("div#downloadAnchorTag").append('Download Result <a href="' + "../rest-jasper-report/result_pdf/" +stdId+ '" target="_blank"><img src="../resources/images/download_image.png" width="30px" height="30px" data-toggle="tooltip" title="Download PDF"/></a>');
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
				if(data.message == "NULL"){
					document.getElementById("downloadAnchorTag").innerHTML = "";
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
					 �		break;
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

