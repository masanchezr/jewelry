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
<!-- MetisMenu CSS -->
<link
	href="<spring:url value="/resources/styles/admin/metisMenu.min.css"/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link
	href="<spring:url value="/resources/styles/admin/sb-admin-2.css"/>"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="<spring:url value="/resources/styles/admin/font-awesome-4.5.0/css/font-awesome.min.css"/>"
	rel="stylesheet" type="text/css">
<!-- Timeline CSS -->
<link href="<spring:url value="/resources/styles/admin/timeline.css"/>"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="<spring:url value="/resources/styles/admin/morris.css"/>"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<spring:url value="/resources/img/favicon.png"/>"
	type="image/png">

<!-- link href="<spring:url value="/resources/styles/admin/stylesAdmin.css" />" rel="stylesheet"-->
<title><spring:message code="goldburgos" /></title>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>
<body>
	<c:url value="/workshop/j_spring_security_logout" var="logoutUrl" />
	<!-- csrt support -->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html"><spring:message
						code="goldburgos" /></a>
			</div>
			<ul class="nav navbar-top-links navbar-right">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="javascript:formSubmit()"><i
								class="fa fa-sign-out fa-fw"></i> <spring:message code="logout" /></a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-header -->
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><spring:url value="/workshop/newadjustment"
								var="adjustment"></spring:url> <a href="${adjustment}"><i
								class="fa fa-archive fa-fw"></i> <spring:message
									code="adjustment" /></a></li>
						<li><spring:url value="/workshop/newWorkshop" var="workshop"></spring:url>
							<a href="${workshop}"><i class="fa fa-diamond fa-fw"></i> <spring:message
									code="workshop" /></a></li>
						<li><spring:url value="/workshop/billing" var="billing"></spring:url>
							<a href="${billing}"><i class="fa fa-bar-chart fa-fw"></i> <spring:message
									code="currentmonthbill" /></a></li>
						<li><spring:url value="/workshop/billingprevious"
								var="billingprevious"></spring:url> <a href="${billingprevious}"><i
								class="fa fa-bar-chart fa-fw"></i> <spring:message
									code="monthpreviousbill" /></a></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>
		<div id="page-wrapper">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<!-- jQuery Version 1.11.0 -->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-2.0.3.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<spring:url value="/resources/js/admin/bootstrap.min.js"/>"></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<spring:url value="/resources/js/admin/metisMenu.min.js"/>"></script>
	<!-- Custom Theme JavaScript -->
	<script src="<spring:url value="/resources/js/admin/sb-admin-2.js"/>"></script>
</body>
</html>