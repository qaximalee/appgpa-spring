$(document).ready(function() {
	// toggle docs formats
	$('.all_course_link').click(function() {
		$('.course_dif_formats').fadeToggle();
	});
	// sorting by bootstrap
	$("#sort").DataTable({
		columnDefs : [ {
			type : 'date',
			targets : [ 5 ]
		} ],
	});
});

// alert notification for CRUD operation
var fromRequest = document.getElementById("fromRequest").value;
if (fromRequest == "from-create") {
	alertify.success('Course Added');
	document.getElementById("fromRequest").value = null;
} else if (fromRequest == "from-create-error") {
	alertify.error('Course is not Created');
	document.getElementById("fromRequest").value = null;
} else if (fromRequest == "from-edit") {
	alertify.success('Course Updated');
	document.getElementById("fromRequest").value = null;
} else if (fromRequest == "from-edit-error") {
	alertify.error('Course is not Updated');
	document.getElementById("fromRequest").value = null;
} else if (fromRequest == "from-delete") {
	alertify.error('Course Deleted');
	document.getElementById("fromRequest").value = null;
} else if (fromRequest == "from-delete-error") {
	alertify.error('Course is not Deleted');
	document.getElementById("fromRequest").value = null;
}