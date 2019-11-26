<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Semesters</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<!-- include the script -->
<script src="../../js/alertify.min.js"></script>
<!-- include the style -->
<link rel="stylesheet" href="../../js/css/alertify.min.css" />
<!-- include a theme -->
<link rel="stylesheet" href="../../js/css/themes/default.min.css" />
</head>
<body>
<jsp:include page="../header/nav_bar.jsp"></jsp:include>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	<input type="hidden" id="fromRequest" value="${alertMessageIdentitfier}">
	

	<div class="container">
		<h1>Semesters List</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Semester No</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${data}" var="sem">
					<tr>
						<td>${sem.getSemesterId()}</td>
						<td>${sem.getSemesterNo()}</td>
						<td><a
							href="${pageContext.request.contextPath}/semester/editSemesterForm?id=${sem.getSemesterId()}">Edit</a></td>
						<td><a href="${pageContext.request.contextPath}/semester/deleteSemester?actionType=delete&id=${sem.getSemesterId()}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br /> <a href="${pageContext.request.contextPath}/views/semester_views/add_semester_form.jsp">Add New Semester</a>
	</div>
	<script type="text/javascript">
		var fromRequest = document.getElementById("fromRequest").value;
		if(fromRequest == "from-create"){
			alertify.success('Semester Added');
			document.getElementById("fromRequest").value = null;
		}else if(fromRequest == "from-create-error"){
			alertify.error('Semester is not Created');
			document.getElementById("fromRequest").value = null;
		}else if( fromRequest == "from-edit"){
			alertify.success('Semester Updated');
			document.getElementById("fromRequest").value = null;
		}else if(fromRequest == "from-edit-error"){
			alertify.error('Semester is not Updated');
			document.getElementById("fromRequest").value = null;
		}else if( fromRequest == "from-delete"){
			alertify.error('Semester Deleted');
			document.getElementById("fromRequest").value = null;
		}else if(fromRequest == "from-delete-error"){
			alertify.error('Semester is not Deleted');
			document.getElementById("fromRequest").value = null;
		}
	</script>

</body>
</html>