// toggle docs formats
$(document).ready(function() {
	$('.all_semester_results_link').click(function() {
		$('.semester_results_dif_formats').fadeToggle();
	});
});
$(document).ready(function() {
	$("#sort").DataTable({
		columnDefs : [ {
			type : 'date',
			targets : [ 5 ]
		} ],
	});
});
var fromRequest = document.getElementById("fromRequest").value;
if (fromRequest != null) {
	alertify.success('Semester Result Added');
	document.getElementById("fromRequest").value = null;
}