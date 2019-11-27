<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">CGPA SYS</a>
		</div>
		<ul class="nav navbar-nav">	
			<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>		
			<li class="active"><a href="index.jsp"> Home</a>
			</li>
			<li><a
				href="${pageContext.request.contextPath}/student/">Students</a></li>
			<li><a
				href="${pageContext.request.contextPath}/semester/">Semesters</a></li>
			<li><a
				href="${pageContext.request.contextPath}/course/">Courses</a></li>
			<li><a
				href="${pageContext.request.contextPath}/course-results/">Courses Results</a></li>
			<li><a
				href="${pageContext.request.contextPath}/semester-results/">Semesters
					Results</a></li>
			<li><a
				href="${pageContext.request.contextPath}/result/">Generate
					Results</a></li>
		</ul>
	</div>
</nav>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('li.active').removeClass('active');
				$('a[href="' + location.pathname + '"]').closest('li')
						.addClass('active');
			});
</script>
