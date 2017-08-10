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
<title><spring:message code="goldburgos" /></title>
<script>
	function formSubmit() {
		$("#logoutForm").submit();
	}
</script>
</head>
<body>
	<c:url value="/employee/j_spring_security_logout" var="logoutUrl" />
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
						<li><spring:url value="/employee/newshopping"
								var="newshopping"></spring:url> <a href="${newshopping}"><i
								class="fa fa-shopping-basket fa-fw"></i> <spring:message
									code="newshopping" /></a></li>
						<li><a href="#"><i class="fa fa-gift fa-fw"></i> <spring:message
									code="sales" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/employee/newsale" var="newsale"></spring:url><a
									href="${newsale}"><spring:message code="newsale" /></a></li>
								<li><spring:url value="/employee/removesale"
										var="removesale"></spring:url><a href="${removesale}"><spring:message
											code="removesale" /></a></li>
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
						<li><a href="#"><i class="fa fa-money fa-fw"></i> <spring:message
									code="pawns" /><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/employee/searchclientpawn"
										var="newpawn"></spring:url><a href="${newpawn}"><spring:message
											code="newpawn" /></a></li>
								<!--<li><spring:url value="/employee/searchPawn" var="searchpawn"></spring:url><a
									href="${searchpawn}"><spring:message code="searchpawn" /></a></li>-->
								<li><spring:url value="/employee/renewPawn" var="renewpawn"></spring:url><a
									href="${renewpawn}"><spring:message code="renewpawn" /></a></li>
								<li><spring:url value="/employee/removePawn"
										var="removePawn"></spring:url><strong><a
										href="${removePawn}" class="text-danger"><spring:message
												code="removepawn" /></a></strong></li>
							</ul> <!-- /.nav-second-level --></li>
						<li><spring:url value="/employee/newadjustment"
								var="adjustment"></spring:url> <a href="${adjustment}"><i
								class="fa fa-wrench fa-fw"></i> <spring:message
									code="adjustments" /></a></li>
						<li><spring:url value="/employee/newentrymoney"
								var="entrymoney"></spring:url> <a href="${entrymoney}"><i
								class="fa fa-euro fa-fw"></i> <spring:message code="entrymoney" /></a></li>
						<li><a href="#"><i class="fa fa-tags fa-fw"></i> <spring:message
									code="othersconcepts" /><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/employee/newpayroll" var="payroll"></spring:url>
									<a href="${payroll}"><spring:message code="payroll" /></a></li>
								<li><spring:url value="/employee/localrental"
										var="localrental"></spring:url> <a href="${localrental}"><spring:message
											code="localrental" /></a></li>
								<li><spring:url value="/employee/newconcept"
										var="newconcept"></spring:url> <a href="${newconcept}"><spring:message
											code="othersconcepts" /></a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-calendar fa-fw"></i> <spring:message
									code="dailies" /><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/employee/daily" var="daily"></spring:url>
									<a href="${daily}"><spring:message code="daily" /></a></li>
								<li><spring:url value="/employee/searchdaily" var="dailies"></spring:url>
									<a href="${dailies}"><spring:message code="dailies" /></a></li>
							</ul></li>
						<li><a href="#"><i
								class="fa fa-exclamation-triangle fa-fw"></i> <spring:message
									code="incidents" /><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
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
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>
		<div id="page-wrapper">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<!-- jQuery -->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-2.0.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<spring:url value="/resources/js/admin/bootstrap.min.js"/>"></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<spring:url value="/resources/js/admin/metisMenu.min.js"/>"></script>
	<!-- Custom Theme JavaScript -->
	<script src="<spring:url value="/resources/js/admin/sb-admin-2.js"/>"></script>
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
				todayHighlight : true
			});
		});
	</script>
</body>
</html>