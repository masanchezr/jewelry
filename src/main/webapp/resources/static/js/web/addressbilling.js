$(document).ready(function() {
	$('#billing_details_drawer').hide();
	$('#billing_same_as_shipping').on('click', function() {
		if (this.checked) {
			$('#billing_details_drawer').hide();
		} else {
			$('#billing_details_drawer').show();
		}
	});
	$('#otherdirection').hide();
	$('#aotherdirection').on('click', function() {
		$('#otherdirection').show();
	});
});