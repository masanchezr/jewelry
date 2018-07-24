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
	href="<spring:url value="/resources/styles/font-awesome-5.0.10/css/font-awesome.min.css"/>"
	rel="stylesheet" type="text/css">
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
<title><spring:message code="titleAdmin" /></title>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<!-- csrt support -->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<a class="navbar-brand" href="admin"><spring:message
				code="goldburgos" /></a>
		<!-- Botón para dispositivos móviles -->
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<form class="form-inline my-2 my-lg-0 mr-lg-2">
						<div class="input-group">
							<spring:message code="find" var="find" />
							<form:form action="searchJewels" modelAttribute="adminForm">
								<form:input class="form-control" path="search"
									placeholder="${find}" />
								<span class="input-group-btn">
									<button class="btn btn-primary" type="button">
										<i class="fa fa-search"></i>
									</button>
								</span>
							</form:form>
						</div>
					</form>
				</li>
				<li class="nav-item"><a class="nav-link" data-toggle="modal"
					data-target="#exampleModal"> <i
						class="fa fa-fw fa-sign-out-alt"></i> <spring:message
							code="logout" />
				</a></li>
			</ul>
			<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
								code="searchentries" />"><spring:url
						value="/searchEntries" var="searchEntries"></spring:url> <a
					href="${searchEntries}" class="nav-link"><i
						class="fa fa-euro-sign fa-fw"></i> <span class="nav-link-text"><spring:message
								code="searchentries" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="adjustments"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseAdjustments" data-parent="#exampleAccordion"><i
						class="fa fa-wrench fa-fw"></i> <span class="nav-link-text"><spring:message
								code="adjustments" /></span></a>
					<ul class="sidenav-second-level collapse" id="collapseAdjustments">
						<li><spring:url value="/searchadjustment"
								var="searchadjustment" /> <a href="${searchadjustment}"
							class="nav-link"><spring:message code="searchadjustment" /></a></li>
						<li><spring:url value="/searchsumadjustments"
								var="searchsumadjustments" /> <a href="${searchsumadjustments}"
							class="nav-link"><spring:message code="searchsumadjustments" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="jewels"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseJewels" data-parent="#exampleAccordion"><i
						class="fa fa-gem fa-fw"></i> <span class="nav-link-text"><spring:message
								code="jewels" /></span></a>
					<ul class="sidenav-second-level collapse" id="collapseJewels">
						<li><spring:url value="/newJewel" var="newjewels"></spring:url>
							<a href="${newjewels}"><spring:message code="newjewels" /></a></li>
						<li><spring:url value="/newSet" var="newset"></spring:url> <a
							href="${newset}"><spring:message code="newset" /></a></li>
						<li><spring:url value="/searchUpdateJewels"
								var="updatejewels"></spring:url> <a href="${updatejewels}"><spring:message
									code="updatejewels" /></a></li>
						<li><spring:url value="/allSets" var="allsets"></spring:url>
							<a href="${allsets}"><spring:message code="allsets" /></a></li>
						<li><spring:url value="/searchByReference"
								var="searchbyreference"></spring:url> <a
							href="${searchbyreference}"><spring:message
									code="searchbyreference" /></a></li>
						<li><spring:url value="/searchjewelsactive"
								var="searchjewelsactive"></spring:url> <a
							href="${searchjewelsactive}"><spring:message
									code="jewelsactive" /></a></li>
						<li><spring:url value="/checkinventory" var="checkinventory"></spring:url>
							<a href="${checkinventory}"><spring:message
									code="checkinventory" /></a></li>
						<li><spring:url value="/upload" var="uploadFile"></spring:url>
							<a href="${uploadFile}"><spring:message code="uploadfile" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="shoppings"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseShop" data-parent="#exampleAccordion"><i
						class="fa fa-shopping-cart fa-fw"></i> <span class="nav-link-text"><spring:message
								code="shoppings" /></span> </a>
					<ul class="sidenav-second-level collapse" id="collapseShop">
						<li><spring:url value="/searchShoppings"
								var="searchshoppings" /> <a href="${searchshoppings}"> <spring:message
									code="searchshoppings" /></a></li>
						<li><spring:url value="/searchquartermaterial"
								var="searchquartermaterial" /> <a
							href="${searchquartermaterial}"> <spring:message
									code="gramsformaterial" />
						</a></li>
						<li><spring:url value="/searchgramsnull"
								var="searchgramsnull" /> <a href="${searchgramsnull}"> <spring:message
									code="searchgramsnull" />
						</a></li>
						<li><spring:url value="/tomelloso" var="tomelloso" /> <a
							href="${tomelloso}">Tomelloso </a></li>
						<li><spring:url value="/exceltomelloso" var="exceltomelloso" />
							<a href="${exceltomelloso}">Generar Excel Tomelloso </a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="pawns"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapsePawns" data-parent="#exampleAccordion"><i
						class="fa fa-money-bill-alt fa-fw"></i> <span
						class="nav-link-text"><spring:message code="pawns" /></span> </a>
					<ul class="sidenav-second-level collapse" id="collapsePawns">
						<li><spring:url value="/searchPawns" var="searchpawns" /><a
							href="${searchpawns}"><spring:message code="searchpawns" /></a></li>
						<li><spring:url value="/searchrenovations"
								var="searchrenovations" /><a href="${searchrenovations}"><spring:message
									code="searchrenovations" /></a></li>
						<li><spring:url value="/searchquarterpawns"
								var="searchquarterpawns" /> <a href="${searchquarterpawns}">
								<spring:message code="quarters" />
						</a></li>
						<li><spring:url value="/outofdate" var="outofdate" /> <a
							href="${outofdate}"> <spring:message code="outofdate" />
						</a></li>
						<li><spring:url value="/searchcommissions"
								var="searchcommissions" /> <a href="${searchcommissions}">
								<spring:message code="commissions" />
						</a></li>
						<li><spring:url value="/investedmoney" var="investedmoney" />
							<a href="${investedmoney}"> <spring:message
									code="investedmoney" />
						</a></li>
						<li><spring:url value="/searchPosibleRepeated"
								var="searchposiblerepeated" /><a
							href="${searchposiblerepeated}"><spring:message
									code="searchposiblerepeated" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="dailies"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseDailies" data-parent="#exampleAccordion"><i
						class="fa fa-calendar fa-fw"></i> <span class="nav-link-text"><spring:message
								code="dailies" /></span></a>
					<ul class="sidenav-second-level collapse" id="collapseDailies">
						<li><spring:url value="/dailyplace" var="dailies" /><a
							href="${dailies}"><spring:message code="dailies" /></a></li>
						<li><spring:url value="/searchcalculatedailies"
								var="searchcalculatedailies" /><a
							href="${searchcalculatedailies}"><spring:message
									code="calculatedailies" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="searchbill"/>"><spring:url
						value="/searchbill" var="searchbill" /><a href="${searchbill}"
					class="nav-link"><i class="fa fa-bug fa-fw"></i> <span
						class="nav-link-text"><spring:message code="searchbill" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="holidays"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseHolidays" data-parent="#exampleAccordion"><i
						class="fa fa-calendar fa-fw"></i> <span class="nav-link-text"><spring:message
								code="holidays" /></span> </a>
					<ul class="sidenav-second-level collapse" id="collapseHolidays">
						<li><spring:url value="/newHoliday" var="newholiday" /><a
							href="${newholiday}"><spring:message code="newholiday" /></a></li>
						<li><spring:url value="/allHolidays" var="allholidays" /><a
							href="${allholidays}"><spring:message code="allholidays" /></a></li>
						<li><spring:url value="/searchHolidays" var="searchholidays" /><a
							href="${searchholidays}"><spring:message
									code="searchholidays" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="salescard" />"><spring:url
						value="/searchSalesCard" var="searchsalescard" /> <a
					href="${searchsalescard}" class="nav-link"><i
						class="fa fa-credit-card fa-fw"></i> <span class="nav-link-text">
							<spring:message code="salescard" />
					</span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="sales"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseSales" data-parent="#exampleAccordion"><i
						class="fa fa-tags fa-fw"></i> <span class="nav-link-text"><spring:message
								code="sales" /></span> </a>
					<ul class="sidenav-second-level collapse" id="collapseSales">
						<li><spring:url value="/searchSales" var="searchsales" /> <a
							href="${searchsales}"> <spring:message code="sales" /></a></li>
						<li><spring:url value="/searchNumMissing"
								var="searchNumMissing" /> <a href="${searchNumMissing}"> <spring:message
									code="searchnummissing" />
						</a></li>
						<li><spring:url value="/newsale" var="newsale" /> <a
							href="${newsale}"> <spring:message code="newsale" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="salespostponed" />"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseSalesPostPoned" data-parent="#exampleAccordion">
						<i class="fa fa-fw fa-tasks"></i> <span class="nav-link-text"><spring:message
								code="salespostponed" /></span>
				</a>
					<ul class="sidenav-second-level collapse"
						id="collapseSalesPostPoned">
						<li><spring:url value="/searchsalepostponed"
								var="searchsalepostponed"></spring:url><a
							href="${searchsalepostponed}"><spring:message
									code="searchsalepostponed" /></a></li>
						<li><spring:url value="/searchNumMissingSalePost"
								var="searchmissingsalepostponed" /> <a
							href="${searchNumMissingSalePost}"> <spring:message
									code="searchnummissing" /></a>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="users"/>"><spring:url
						value="/newuser" var="newuser" /> <a href="${newuser}"
					class="nav-link"><i class="fa fa-users fa-fw"></i><span
						class="nav-link-text"> <spring:message code="newuser" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
									code="pendingissues" />"><spring:url
						value="/pendingissues" var="pendingissues" /> <a
					href="${pendingissues}" class="nav-link"><i
						class="fa fa-exclamation-triangle fa-fw"></i> <span
						class="nav-link-text"><spring:message code="pendingissues" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
									code="searchclients" />"><spring:url
						value="/searchclients" var="searchclients" /> <a
					href="${searchclients}" class="nav-link"><i
						class="fa fa-users fa-fw"></i> <span class="nav-link-text"><spring:message
								code="searchclients" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="newcategory"/>"><spring:url
						value="/newCategory" var="newcategory"></spring:url> <a
					href="${newcategory}" class="nav-link"><i
						class="fa fa-archive fa-fw"></i> <span class="nav-link-text"><spring:message
								code="newcategory" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="payments"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapsePayments" data-parent="#exampleAccordion"><i
						class="fa fa-credit-card fa-fw"></i> <span class="nav-link-text"><spring:message
								code="payments" /></span> </a>
					<ul class="sidenav-second-level collapse" id="collapsePayments">
						<li><spring:url value="/newPayment" var="newpayment"></spring:url><a
							href="${newpayment}"> <spring:message code="newpayment" /></a></li>
						<li><spring:url value="/allpayments" var="allpayments"></spring:url><a
							href="${allpayments}"> <spring:message code="allpayments" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="newcoin"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseCoins" data-parent="#exampleAccordion"><i
						class="fa fa-database fa-fw"></i> <span class="nav-link-text"><spring:message
								code="newcoin" /></span></a>
					<ul class="sidenav-second-level collapse" id="collapseCoins">
						<li class="nav-item" data-toggle="tooltip" data-placement="right"
							title="<spring message code="search"/>"><spring:url
								value="/newcoin" var="newcoin"></spring:url><a href="${newcoin}"><spring:message
									code="addcoin" /></a></li>
						<li class="nav-item" data-toggle="tooltip" data-placement="right"
							title="<spring message code="search"/>"><spring:url
								value="/allcoins" var="allcoins"></spring:url><a
							href="${allcoins}"><spring:message code="allcoins" /></a></li>
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
	<script type="text/javascript"
		src="<spring:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<spring:url value="/resources/js/popper.min.js"/>"></script>
	<script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
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
	<script>
		$(function() {
			$("#sandbox-container .input-daterange").datepicker({
				language : "es",
				autoclose : true,
				todayHighlight : true
			});
		});
	</script>
</body>
</html>