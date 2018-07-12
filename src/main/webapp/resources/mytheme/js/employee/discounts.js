$('#payments').change(function() {
	var selected = $(this).find("option:selected").val();
	if (selected == 4) {
		$('#discount').removeClass('d-none');
		$('#discount').addClass('d-print-none');
		$('#accordion').removeClass('d-print-none');
		$('#accordion').addClass('d-none');
	} else if (selected == 6) {
		$('#discount').removeClass('d-none');
		$('#discount').addClass('d-print-none');
		$('#accordion').removeClass('d-none');
		$('#accordion').addClass('d-print-none');
	} else {
		$('#discount').removeClass('d-print-none');
		$('#discount').addClass('d-none');
		$('#accordion').removeClass('d-print-none');
		$('#accordion').addClass('d-none');
	}
});