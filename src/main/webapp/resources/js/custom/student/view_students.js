
$(document).ready(function() {
	// toggle docs formats
	$('.all_stdnt_link').click(function() {
		$('.std_dif_formats').fadeToggle();
	});
	// bootstrap data table
	$("#sort").DataTable({
		columnDefs : [ {
			type : 'date',
			targets : [ 5 ]
		} ],
	});
});

// popup confirmation box for deleting a data
function deleteARecord() {
	alertify.confirm(" Do you want to delete the record.", function() {
		alertify.success('Ok');
	}, function() {
		alertify.error('Cancel');
	});
}

// for alert
var fromRequest = document.getElementById("fromRequest").value;
if (fromRequest == "from-create") {
	alertify.success('Student Added');
	document.getElementById("fromRequest").value = null;
} else if (fromRequest == "from-create-error") {
	alertify.error('Student is not Created');
	document.getElementById("fromRequest").value = null;
} else if (fromRequest == "from-edit") {
	alertify.success('Student Updated');
	document.getElementById("fromRequest").value = null;
} else if (fromRequest == "from-edit-error") {
	alertify.error('Student is not Updated');
	document.getElementById("fromRequest").value = null;
} else if (fromRequest == "from-delete") {
	alertify.error('Student Deleted');
	document.getElementById("fromRequest").value = null;
} else if (fromRequest == "from-delete-error") {
	alertify.error('Student is not Deleted');
	document.getElementById("fromRequest").value = null;
}