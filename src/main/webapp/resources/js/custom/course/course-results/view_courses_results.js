//toggle docs formats
$(document).ready(function() {
	$('.all_course_results_link').click(function() {
		$('.course_results_dif_formats').fadeToggle();
	});
});
$(document).ready(function() {
	$("#sort").DataTable({
		columnDefs : [ {
			type : 'date',
			targets : [ 9 ]
		} ],
	});
});
var fromRequest = document.getElementById("fromRequest").value;
if (fromRequest != null && fromRequest == "from-create") {
	alertify.success('Course Result Added');
	document.getElementById("fromRequest").value = null;
} else if (fromRequest == "from-delete") {
	alertify.error('Course Result Deleted');
	document.getElementById("fromRequest").value = null;
}

var elementIsClicked = false; // declare the variable that tracks the state

function clickHandler() { // declare a function that updates the state
	elementIsClicked = true;
	console.log("LKDJLJFL:SDJLJL:KSJ");
}

var element = document.getElementById('delete'); // grab a reference to your element
element.addEventListener('click', clickHandler);

if (elementIsClicked) {
	alertify.confirm("Do you really want to delete this?", function() {
		alertify.success('Ok');
	}, function() {
		alertify.error('Cancel');
	});
}