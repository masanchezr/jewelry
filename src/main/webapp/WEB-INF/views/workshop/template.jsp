<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<!-- Bootstrap Core CSS -->
<!-- Custom Fonts -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
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
<body id="page-top">
	<c:url value="/workshop/j_spring_security_logout" var="logoutUrl" />
	<!-- csrt support -->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<!-- Navigation-->
	<nav class="navbar navbar-expand navbar-dark bg-dark static-top">
		<a class="navbar-brand mr-1" href="index.html"><spring:message
				code="goldburgos" /></a>
		<!-- Navbar -->
		<ul class="navbar-nav ml-auto ml-md-0">
			<li class="nav-item dropdown no-arrow"><a
				class="nav-link dropdown-toggle" href="#" id="userDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <i class="fas fa-user-circle fa-fw"></i>
			</a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="userDropdown">
					<a class="dropdown-item" href="#" data-toggle="modal"
						data-target="#logoutModal"><spring:message code="logout" /></a>
				</div></li>
		</ul>
		<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
			<li class="nav-item"><spring:url value="/workshop/newadjustment"
					var="adjustment" /> <a class="nav-link" href="${adjustment}"><i
					class="fas fa-archive fa-fw"></i> <span><spring:message
							code="adjustment" /></span></a></li>
			<li class="nav-item"><spring:url value="/workshop/newWorkshop"
					var="workshop" /> <a class="nav-link" href="${workshop}"><i
					class="fas fa-gem fa-fw"></i> <span><spring:message
							code="workshop" /></span></a></li>
			<li class="nav-item"><spring:url value="/workshop/billing"
					var="billing" /> <a class="nav-link" href="${billing}"><i
					class="fas fa-bar-chart fa-fw"></i> <span><spring:message
							code="currentmonthbill" /></span></a></li>
			<li class="nav-item"><spring:url
					value="/workshop/billingprevious" var="billingprevious" /> <a
				class="nav-link" href="${billingprevious}"><i
					class="fas fa-bar-chart fa-fw"></i> <span><spring:message
							code="monthpreviousbill" /></span></a></li>
		</ul>
	</nav>
	<div id="wrapper">
		<div id="content-wrapper">
			<div class="container-fluid">
				<tiles:insertAttribute name="body" />
			</div>
			<!-- Sticky Footer -->
			<footer class="sticky-footer">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright © Your Website 2019</span>
					</div>
				</div>
			</footer>
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
						<span aria-hidden="true">X</span>
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
	<!-- Custom Theme JavaScript -->
	<script src="<spring:url value="/resources/js/jquery.easing.min.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/dataTables.bootstrap4.min.js"/>"></script>
	<script src="<spring:url value="/resources/js/sb-admin.min.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/bootstrap-datepicker.min.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/bootstrap-datepicker.es.min.js"/>"></script>
</body>
</html>