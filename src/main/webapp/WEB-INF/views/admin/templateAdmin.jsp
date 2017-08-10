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
<body>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
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
						code="titleAdmin" /></a>
			</div>
			<ul class="nav navbar-top-links navbar-right">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="#"><i class="fa fa-user fa-fw"></i> User
								Profile</a></li>
						<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
						</li>
						<li class="divider"></li>
						<li><a href="javascript:formSubmit()"><i
								class="fa fa-sign-out fa-fw"></i> Logout</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-header -->
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li class="sidebar-search">
							<div class="input-group custom-search-form">
								<spring:message code="find" var="find" />
								<form:form action="searchJewels" commandName="adminForm">
									<form:input class="form-control" path="search"
										placeholder="${find}" />
									<span class="input-group-btn"><form:button
											class="btn btn-default" value="submit">
											<i class="fa fa-search"></i>
										</form:button> </span>
								</form:form>
							</div> <!-- /input-group -->
						</li>
						<li><spring:url value="/searchEntries" var="searchEntries"></spring:url>
							<a href="${searchEntries}"><i class="fa fa-eur fa-fw"></i> <spring:message
									code="searchentries" /></a></li>
						<li><spring:url value="/searchsumadjustments"
								var="searchsumadjustments" /> <a href="${searchsumadjustments}"><i
								class="fa fa-wrench fa-fw"></i> <spring:message
									code="adjustments" /></a></li>
						<li><a href="#"><i class="fa fa-diamond fa-fw"></i> <spring:message
									code="jewels" /><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/newJewel" var="newjewels"></spring:url>
									<a href="${newjewels}"><spring:message code="newjewels" /></a></li>
								<li><spring:url value="/newSet" var="newset"></spring:url>
									<a href="${newset}"><spring:message code="newset" /></a></li>
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
								<li><spring:url value="/checkinventory"
										var="checkinventory"></spring:url> <a href="${checkinventory}"><spring:message
											code="checkinventory" /></a></li>
								<li><spring:url value="/upload" var="uploadFile"></spring:url>
									<a href="${uploadFile}"><spring:message code="uploadfile" /></a></li>
							</ul> <!--<li><spring:url value="/searchClients" var="searchClients"></spring:url><a
							href="${searchClients}"><i class="fa fa-smile-o fa-fw"></i> <spring:message
									code="searchClients" /></a></li>-->
						<li><a href="#"><i class="fa fa-shopping-cart fa-fw"></i>
								<spring:message code="shoppings" /><span class="fa arrow"></span>
						</a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/searchShoppings"
										var="searchshoppings" /> <a href="${searchshoppings}"> <spring:message
											code="searchshoppings" /></a></li>
								<li><spring:url value="/searchquarter" var="searchquarter" />
									<a href="${searchquarter}"> <spring:message code="quarters" /></a></li>
								<li><spring:url value="/searchquartermetal"
										var="searchquartermetal" /> <a href="${searchquartermetal}">
										<spring:message code="gramsformetal" />
								</a></li>
								<li><spring:url value="/searchgramsnull"
										var="searchgramsnull" /> <a href="${searchgramsnull}"> <spring:message
											code="searchgramsnull" />
								</a></li>
								<li><spring:url value="/tomelloso" var="tomelloso" /> <a
									href="${tomelloso}">Tomelloso </a></li>
								<li><spring:url value="/exceltomelloso"
										var="exceltomelloso" /> <a href="${exceltomelloso}">Generar
										Excel Tomelloso </a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-money fa-fw"></i> <spring:message
									code="pawns" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
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
						<li><a href="#"><i class="fa fa-calendar fa-fw"></i> <spring:message
									code="dailies" /><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/dailyplace" var="dailies" /><a
									href="${dailies}"><spring:message code="dailies" /></a></li>
								<li><spring:url value="/searchcalculatedailies"
										var="searchcalculatedailies" /><a
									href="${searchcalculatedailies}"><spring:message
											code="calculatedailies" /></a></li>
							</ul></li>
						<li><spring:url value="/searchbill" var="searchbill" /><a
							href="${searchbill}"><i class="fa fa-bug fa-fw"></i> <spring:message
									code="searchbill" /></a></li>
						<li><a href="#"><i class="fa fa-calendar fa-fw"></i> <spring:message
									code="holidays" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/newHoliday" var="newholiday" /><a
									href="${newholiday}"><spring:message code="newholiday" /></a></li>
								<li><spring:url value="/allHolidays" var="allholidays" /><a
									href="${allholidays}"><spring:message code="allholidays" /></a></li>
								<li><spring:url value="/searchHolidays"
										var="searchholidays" /><a href="${searchholidays}"><spring:message
											code="searchholidays" /></a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-tags fa-fw"></i> <spring:message
									code="sales" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/searchSalesCard"
										var="searchsalescard" /> <a href="${searchsalescard}"> <spring:message
											code="salescard" /></a></li>
								<li><spring:url value="/searchSales" var="searchsales" />
									<a href="${searchsales}"> <spring:message code="sales" /></a></li>
								<li><spring:url value="/searchNumMissing"
										var="searchNumMissing" /> <a href="${searchNumMissing}">
										<spring:message code="searchnummissing" />
								</a></li>
								<li><spring:url value="/newsale" var="newsale" /> <a
									href="${newsale}"> <spring:message code="newsale" /></a></li>
								<li><spring:url value="/searchsalepostponed"
										var="searchsalepostponed" /> <a href="${searchsalepostponed}"><spring:message
											code="searchsalepostponed" /></a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-users fa-fw"></i> <spring:message
									code="users" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/enabledisableuser"
										var="enabledisableuser" /> <a href="${enabledisableuser}">
										<spring:message code="enabledisable" />
								</a></li>
								<li><spring:url value="/newuser" var="newuser" /> <a
									href="${newuser}"> <spring:message code="newuser" />
								</a></li>
							</ul></li>
						<li><a href="#"><i
								class="fa fa-exclamation-triangle fa-fw"></i> <spring:message
									code="incidents" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/allincidents" var="allincidents" />
									<a href="${allincidents}"><spring:message
											code="allincidents" /></a></li>
								<li><spring:url value="/pendingissues" var="pendingissues" />
									<a href="${pendingissues}"><spring:message
											code="pendingissues" /></a></li>
							</ul></li>
						<li><spring:url value="/newCategory" var="newcategory"></spring:url>
							<a href="${newcategory}"><i class="fa fa-archive fa-fw"></i>
								<spring:message code="newcategory" /></a></li>
						<li><a href="#"><i class="fa fa-credit-card fa-fw"></i> <spring:message
									code="payments" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/newPayment" var="newpayment"></spring:url><a
									href="${newpayment}"> <spring:message code="newpayment" /></a></li>
								<li><spring:url value="/allpayments" var="allpayments"></spring:url><a
									href="${allpayments}"> <spring:message code="allpayments" /></a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-database fa-fw"></i> <spring:message
									code="newcoin" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/newcoin" var="newcoin"></spring:url><a
									href="${newcoin}"><spring:message code="addcoin" /></a></li>
								<li><spring:url value="/allcoins" var="allcoins"></spring:url><a
									href="${allcoins}"><spring:message code="allcoins" /></a></li>
							</ul></li>
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
	<script
		src="<spring:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
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
</body>
</html>