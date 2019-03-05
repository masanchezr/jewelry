<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<!-- Bootstrap Core CSS -->
<link
	href="<spring:url value="/resources/styles/admin/bootstrap.min.css"/>"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="<spring:url value="/resources/styles/admin/font-awesome-5.0.10/css/fontawesome-all.css"/>"
	rel="stylesheet" type="text/css">
<link
	href="<spring:url value="/resources/styles/admin/dataTables.bootstrap4.min.css"/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link
	href="<spring:url value="/resources/styles/admin/sb-admin.min.css"/>"
	rel="stylesheet">
<link
	href="<spring:url value="/resources/styles/admin/bootstrap-datepicker.min.css"/>"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<spring:url value="/resources/img/favicon.png"/>"
	type="image/png">
<title><spring:message code="goldburgos" /></title>
<!-- html5
<meta charset="utf-8" />-->
<!-- html4
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />-->
<script>
	function formSubmit() {
		$("#logoutForm").submit();
	}
</script>
<!-- jQuery -->
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- Custom Theme JavaScript -->
<script src="<spring:url value="/resources/js/jquery.easing.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/dataTables.bootstrap4.js"/>"></script>
<script src="<spring:url value="/resources/js/sb-admin.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/sb-admin-datatables.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/bootstrap-datepicker.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/bootstrap-datepicker.es.min.js"/>"></script>
<script>
	$(function() {
		$("#sandbox-container input").datepicker({
			language : "es",
			autoclose : true,
			todayHighlight : true
		});
	});
</script>
</head>