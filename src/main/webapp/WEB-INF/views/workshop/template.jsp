<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
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
	href="<spring:url value="/resources/styles/admin/dataTables.bootstrap4.css"/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<spring:url value="/resources/styles/admin/sb-admin.css"/>"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<spring:url value="/resources/img/favicon.png"/>"
	type="image/png">
<title><spring:message code="goldburgos" /></title>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<c:url value="/workshop/j_spring_security_logout" var="logoutUrl" />
	<!-- csrt support -->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<a class="navbar-brand" href="index.html"><spring:message
				code="goldburgos" /></a>
		<!-- Bot�n para dispositivos m�viles -->
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" data-toggle="modal"
					data-target="#exampleModal"> <i
						class="fa fa-fw fa-sign-out-alt"></i> <spring:message
							code="logout" />
				</a></li>
			</ul>
			<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
								code="adjustment" />"><spring:url
						value="/workshop/newadjustment" var="adjustment"></spring:url> <a
					class="nav-link" href="${adjustment}"><i
						class="fa fa-archive fa-fw"></i> <span class="nav-link-text"><spring:message
								code="adjustment" /></span></a></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
								code="workshop" />"><spring:url
						value="/workshop/newWorkshop" var="workshop"></spring:url> <a
					class="nav-link" href="${workshop}"><i class="fa fa-gem fa-fw"></i>
						<span class="nav-link-text"><spring:message code="workshop" /></span></a></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
								code="currentmonthbill" />"><spring:url
						value="/workshop/billing" var="billing"></spring:url> <a
					class="nav-link" href="${billing}"><i
						class="fa fa-bar-chart fa-fw"></i> <span class="nav-link-text"><spring:message
								code="currentmonthbill" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
								code="monthpreviousbill" />"><spring:url
						value="/workshop/billingprevious" var="billingprevious"></spring:url>
					<a class="nav-link" href="${billingprevious}"><i
						class="fa fa-bar-chart fa-fw"></i> <span class="nav-link-text"><spring:message
								code="monthpreviousbill" /></span></a></li>
			</ul>
		</div>
	</nav>
	<div class="content-wrapper">
		<div class="container-fluid">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<spring:message code="readyToLeave" />
					</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">�</span>
					</button>
				</div>
				<div class="modal-body">
					<spring:message code="selectlogout" />
				</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">
						<spring:message code="cancel" />
					</button>
					<a class="btn btn-primary" href="javascript:formSubmit()"><spring:message
							code="logout" /></a>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script type="text/javascript"
		src="<spring:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
	<!-- Custom Theme JavaScript -->
	<script src="<spring:url value="/resources/js/jquery.easing.min.js"/>"></script>
	<script src="<spring:url value="/resources/js/jquery.dataTables.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/dataTables.bootstrap4.js"/>"></script>
	<script src="<spring:url value="/resources/js/sb-admin.min.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/sb-admin-datatables.min.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/bootstrap-datepicker.es.min.js"/>"></script>
</body>
</html>