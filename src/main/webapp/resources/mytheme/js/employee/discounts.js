$('#payments').change(function() {
	var selected = $(this).find("option:selected").val();
	if (selected == 4) {
		$('#discount').removeClass('hidden');
		$('#discount').addClass('show');
		$('#accordion').removeClass('show');
		$('#accordion').addClass('hidden');
	} else if (selected == 6) {
		$('#discount').removeClass('hidden');
		$('#discount').addClass('show');
		$('#accordion').removeClass('hidden');
		$('#accordion').addClass('show');
	} else {
		$('#discount').removeClass('show');
		$('#discount').addClass('hidden');
		$('#accordion').removeClass('show');
		$('#accordion').addClass('hidden');
	}
});