// toggle docs formats
$(document)
		.ready(
				function() {
					$('.all_results_link')
							.click(
									function() {
										$('.result_dif_formats').fadeToggle();

										$('#pdf_report')
												.click(
														function() {
															let std = document
																	.getElementById("studentId");
															let stdId = std.options[std.selectedIndex].value;
															console.log(stdId);
															document
																	.getElementById("pdf_report").href = "../rest-jasper-report/pdf/result/"
																	+ stdId;
														});
										$('#csv_report')
												.click(
														function() {
															let std = document
																	.getElementById("studentId");
															let stdId = std.options[std.selectedIndex].value;
															console.log(stdId);
															document
																	.getElementById('csv_report').href = "../rest-jasper-report/csv/result/"
																	+ stdId;
														});
										$('#html_report')
												.click(
														function() {
															let std = document
																	.getElementById("studentId");
															let stdId = std.options[std.selectedIndex].value;
															console.log(stdId);
															document
																	.getElementById('html_report').href = "../rest-jasper-report/html/result/"
																	+ stdId;
														});
									});
				});

// it is call when any student registration no is choose from select tag
function getStudent() {
	var std = document.getElementById("studentId");
	var stdId = std.options[std.selectedIndex].value;
	const url = "../course-results/getStudentByRegistration?studentID=" + stdId;

	// Populate dropdown with list of students
	$.getJSON(url, function(data) {
		var studentDetails = "Marksheet Of: " + data.firstName + " "
				+ data.lastName;
		$('#studName').text(studentDetails);
		document.getElementById("studName").text = "" + data.firstName + " "
				+ data.lastName;
		console.log(data.firstName + " " + data.lastName);
	});
}

/**
 * This will be called when the registration no is selected from select tag.
 * This method will get studentID and send it with the end-point url and get
 * the whole results. 
 * */
function generateResult() {
	var std = document.getElementById("studentId");
	var stdId = std.options[std.selectedIndex].value;

	// this array is used for the header of the table
	var tableHead = [ "Semester No", "Course Code", "Course Name",
			"Percentage", "Credit Hours", "Grade", "GPA", "Total Points",
			"Semester GPA", "CGPA" ];

	document.getElementById("error").innerHTML = "";

	const url = "../result/getResultByStudent?id=" + stdId;
	$.getJSON(url, function(data) {
		// Show Report Download Button
		$("#downloadAnchorTag").show();
		if (data.results == "") {
			// Hide Report Download Button if there isn't any result for the
			// student
			$("#downloadAnchorTag").hide();
		}
		var table = document.querySelector("table");
		var resultData = data.results;
		console.log(resultData[0]);

		// generating only header of the result's table
		generateTableHead(table, tableHead);

		// generating the body of the result's table 
		generateTable(table, resultData);

	});
}

// this method will only generate header of the result's table
function generateTableHead(table, tableHead) {
	// REMOVE PREVIOUS DATA
	$("#table tr").remove();

	var table = table.createTHead();
	var row = table.insertRow();

	for (var i = 0; i < tableHead.length; i++) {
		let th = document.createElement("th");
		let text = document.createTextNode(tableHead[i]);
		th.appendChild(text);
		row.appendChild(th);
	}
}

// this method will do the actual work of result's table printing
function generateTable(table, data) {
	// this array is used for fetching data from json in below array's data sequence
	var tableSequence = [ "semesterNo", "courseCode", "courseName",
			"percentage", "creditHours", "grade", "gpa", "totalPoints",
			"semesterGPA", "cgpa" ];
	var switched = false;
	for (let i = 0; i < data.length; i++) {
		let row;
		let element = data[i];
		if (switched) {
			switched = false;
			row = table.insertRow();
			for (var a = 0; a < tableSequence.length - 2; a++) {
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

		} else {
			row = table.insertRow();
			let counter = 0;
			for (key in element) {
				if (counter == tableSequence.length)
					break;
				if (counter < 8) {
					let cell = row.insertCell();
					let text = document
							.createTextNode(element[tableSequence[counter]]);
					cell.appendChild(text);
				}
				counter++;
			}
			if (i == data.length - 1
					|| element.semesterNo != data[i + 1].semesterNo) {
				i--;
				switched = true;
			}
		}
	}
}