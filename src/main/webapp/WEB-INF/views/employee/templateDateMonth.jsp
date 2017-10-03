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
	href="<spring:url value="/resources/styles/admin/font-awesome-4.5.0/css/font-awesome.min.css"/>"
	rel="stylesheet" type="text/css">
<!-- MetisMenu CSS -->
<link
	href="<spring:url value="/resources/styles/admin/dataTables.bootstrap4.css"/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<spring:url value="/resources/styles/admin/sb-admin.css"/>"
	rel="stylesheet">
<link
	href="<spring:url value="/resources/styles/admin/bootstrap-datepicker.css"/>"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<spring:url value="/resources/img/favicon.png"/>"
	type="image/png">
<title><spring:message code="goldburgos" /></title>
<script>
	function formSubmit() {
		$("#logoutForm").submit();
	}
</script>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<c:url value="/employee/j_spring_security_logout" var="logoutUrl" />
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
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" data-toggle="modal"
					data-target="#exampleModal"> <i class="fa fa-fw fa-sign-out"></i>
						<spring:message code="logout" />
				</a></li>
			</ul>
			<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
								code="newshopping" />"><spring:url
						value="/employee/newshopping" var="newshopping"></spring:url> <a
					href="${newshopping}" class="nav-link"><i
						class="fa fa-shopping-basket fa-fw"></i> <span
						class="nav-link-text"><spring:message code="newshopping" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="sales" />"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseComponents" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-gift"></i> <span class="nav-link-text"><spring:message
								code="sales" /></span>
				</a>
					<ul class="sidenav-second-level collapse" id="collapseComponents">
						<li><spring:url value="/employee/newsale" var="newsale"></spring:url><a
							href="${newsale}"><spring:message code="newsale" /></a></li>
						<li><spring:url value="/employee/removesale" var="removesale"></spring:url><a
							href="${removesale}"><spring:message code="removesale" /></a></li>
						<li><spring:url value="/employee/removeparcialsale"
								var="removeparcialsale"></spring:url><a
							href="${removeparcialsale}"><spring:message
									code="removeparcialsale" /></a></li>
						<li><spring:url value="/employee/newsalebattery"
								var="salebattery"></spring:url> <a href="${salebattery}"><spring:message
									code="putbattery" /></a></li>
						<li><spring:url value="/employee/newsalestrap"
								var="salestrap"></spring:url> <a href="${salestrap}"><spring:message
									code="putstrap" /></a></li>
						<li><spring:url value="/employee/newrecording"
								var="recording"></spring:url> <a href="${recording}"><spring:message
									code="recording" /></a></li>
						<li><spring:url value="/employee/newdiscount"
								var="newdiscount"></spring:url> <a href="${newdiscount}"><spring:message
									code="newdiscount" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="pawns" />"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapsePawns" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-money"></i> <span class="nav-link-text"><spring:message
								code="pawns" /></span>
				</a>
					<ul class="sidenav-second-level collapse" id="collapsePawns">
						<li><spring:url value="/employee/searchclientpawn"
								var="newpawn"></spring:url><a href="${newpawn}"><spring:message
									code="newpawn" /></a></li>
						<li><spring:url value="/employee/renewPawn" var="renewpawn"></spring:url><a
							href="${renewpawn}"><spring:message code="renewpawn" /></a></li>
						<li><spring:url value="/employee/removePawn" var="removePawn"></spring:url><strong><a
								href="${removePawn}" class="text-danger"><spring:message
										code="removepawn" /></a></strong></li>
					</ul></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
								code="adjustments" />"><spring:url
						value="/employee/newadjustment" var="adjustment"></spring:url> <a
					class="nav-link" href="${adjustment}"><i
						class="fa fa-wrench fa-fw"></i> <span class="nav-link-text"><spring:message
								code="adjustments" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
								code="entrymoney" />"><spring:url
						value="/employee/newentrymoney" var="entrymoney"></spring:url> <a
					href="${entrymoney}" class="nav-link"><i
						class="fa fa-euro fa-fw"></i><span class="nav-link-text"> <spring:message
								code="entrymoney" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="otherconcepts" />"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseOConcepts" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-tags"></i> <span class="nav-link-text"><spring:message
								code="othersconcepts" /></span>
				</a>
					<ul class="sidenav-second-level collapse" id="collapseOConcepts">
						<li><spring:url value="/employee/newpayroll" var="payroll"></spring:url>
							<a href="${payroll}"><spring:message code="payroll" /></a></li>
						<li><spring:url value="/employee/localrental"
								var="localrental"></spring:url> <a href="${localrental}"><spring:message
									code="localrental" /></a></li>
						<li><spring:url value="/employee/newconcept" var="newconcept"></spring:url>
							<a href="${newconcept}"><spring:message code="othersconcepts" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="dailies"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseDailies" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-calendar"></i> <span class="nav-link-text"><spring:message
								code="dailies" /></span>
				</a>
					<ul class="sidenav-second-level collapse" id="collapseDailies">
						<li><spring:url value="/employee/daily" var="daily"></spring:url>
							<a href="${daily}"><spring:message code="daily" /></a></li>
						<li><spring:url value="/employee/searchdaily" var="dailies"></spring:url>
							<a href="${dailies}"><spring:message code="dailies" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="incidents"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseincidents" data-parent="#exampleAccordion"> <i
						class="fa fa-exclamation"></i> <span class="nav-link-text"><spring:message
								code="incidents" /></span>
				</a>
					<ul class="sidenav-second-level collapse" id="collapseincidents">
						<li><spring:url value="/employee/newincident"
								var="newincident"></spring:url> <a href="${newincident}"><spring:message
									code="newincident" /></a></li>
						<li><spring:url value="/employee/myincidents"
								var="myincidents"></spring:url> <a href="${myincidents}"><spring:message
									code="myincidents" /></a></li>
						<li><spring:url value="/employee/pendingissues"
								var="pendingissues" /> <a href="${pendingissues}"><spring:message
									code="pendingissues" /></a></li>
					</ul></li>
			</ul>
			<ul class="navbar-nav sidenav-toggler">
				<li class="nav-item"><a class="nav-link text-center"
					id="sidenavToggler"> <i class="fa fa-fw fa-angle-left"></i>
				</a></li>
			</ul>
		</div>
	</nav>
	<div class="content-wrapper">
		<div class="container-fluid">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fa fa-angle-up"></i>
	</a>
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
						<span aria-hidden="true">×</span>
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
		src="<spring:url value="/resources/js/admin/jquery.min.js"/>"></script>
	<script src="<spring:url value="/resources/js/admin/popper.min.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/admin/bootstrap.min.js"/>"></script>
	<!-- Custom Theme JavaScript -->
	<script
		src="<spring:url value="/resources/js/admin/jquery.easing.min.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/admin/jquery.dataTables.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/admin/dataTables.bootstrap4.js"/>"></script>
	<script src="<spring:url value="/resources/js/admin/sb-admin.min.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/admin/sb-admin-datatables.min.js"/>"></script>
	<script src="<spring:url value="/resources/js/employee/discounts.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/bootstrap-datepicker.es.min.js"/>"></script>
	<script>
		$(function() {
			$("#sandbox-container input").datepicker({
				language : "es",
				autoclose : true,
				todayHighlight : true,
				startView : 2,
				minViewMode : 1,
				format : "mm/yyyy"
			});
		});
	</script>
</body>
</html>