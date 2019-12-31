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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/custom/pdf_download_tag.css">

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
				<a href="#" class="all_results_link" id="downloadAnchorTag"><img src="${pageContext.request.contextPath}/resources/images/download_image.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip"  title="Download Report"/></a>
				<div class="result_dif_formats">
					<a href="" target="_blank" id="pdf_report"><img src="${pageContext.request.contextPath}/resources/images/pdf.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip" title="Download PDF"/></a>
					<a href="" target="_blank" id="csv_report"><img src="${pageContext.request.contextPath}/resources/images/csv.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip" title="Download CSV"/></a>
					<a href="" target="_blank" id="html_report"><img src="${pageContext.request.contextPath}/resources/images/html.png" width="30px" height="30px" class="pull-right" data-toggle="tooltip" title="View HTML"/></a>
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
	<script src="${pageContext.request.contextPath}/resources/js/custom/overall-results/overall_results.js"></script>
</body>
</html>

