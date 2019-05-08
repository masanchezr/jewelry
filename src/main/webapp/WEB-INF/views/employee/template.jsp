<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<tiles:insertAttribute name="header" />
<body id="page-top">
	<c:url value="/employee/j_spring_security_logout" var="logoutUrl" />
	<!-- csrt support -->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
		<a class="navbar-brand mr-1" href="admin"><spring:message
				code="goldburgos" /></a>
		<button class="btn btn-link btn-sm text-white order-1 order-sm-0"
			id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>
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
	</nav>
	<div id="wrapper">
		<ul class="sidebar navbar-nav">
			<li class="nav-item"><spring:url value="/employee/newshopping"
					var="newshopping" /> <a href="${newshopping}" class="nav-link"><i
					class="fa fa-shopping-basket fa-fw"></i> <span><spring:message
							code="newshopping" /></span></a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseComponents" role="button" aria-haspopup="true"
				aria-expanded="false"> <i class="fa fa-fw fa-gift"></i> <span
					class="nav-link-text"><spring:message code="sales" /></span>
			</a>
				<div class="dropdown-menu" aria-labelledby="collapseComponents">
					<spring:url value="/employee/newsale" var="newsale" />
					<a class="dropdown-item" href="${newsale}"><spring:message
							code="newsale" /></a>
					<spring:url value="/employee/removesale" var="removesale" />
					<a class="dropdown-item" href="${removesale}"><spring:message
							code="removesale" /></a>
					<spring:url value="/employee/removeparcialsale"
						var="removeparcialsale" />
					<a class="dropdown-item" href="${removeparcialsale}"><spring:message
							code="removeparcialsale" /></a>
					<spring:url value="/employee/newsalebattery" var="salebattery" />
					<a class="dropdown-item" href="${salebattery}"><spring:message
							code="putbattery" /></a>
					<spring:url value="/employee/newsalestrap" var="salestrap" />
					<a class="dropdown-item" href="${salestrap}"><spring:message
							code="putstrap" /></a>
					<spring:url value="/employee/newrecording" var="recording" />
					<a class="dropdown-item" href="${recording}"><spring:message
							code="recording" /></a>
					<spring:url value="/employee/newdiscount" var="newdiscount" />
					<a class="dropdown-item" href="${newdiscount}"><spring:message
							code="newdiscount" /></a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseSalesPostPoned" role="button" aria-haspopup="true"
				aria-expanded="false"> <i class="fa fa-fw fa-tasks"></i> <span><spring:message
							code="salespostponed" /></span>
			</a>
				<div class="dropdown-menu" aria-labelledby="collapseSalesPostPoned">
					<spring:url value="/employee/newsalepostponed"
						var="newsalepostponed" />
					<a class="dropdown-item" href="${newsalepostponed}"><spring:message
							code="newsalepostponed" /></a>
					<spring:url value="/employee/addinstallment" var="installment" />
					<a class="dropdown-item" href="${installment}"><spring:message
							code="addinstallment" /></a>
					<spring:url value="/employee/searchsalepostponed"
						var="searchsalepostponed" />
					<a class="dropdown-item" href="${searchsalepostponed}"><spring:message
							code="searchsalepostponed" /></a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapsePawns" role="button" aria-haspopup="true"
				aria-expanded="false"> <i class="fa fa-fw fa-money-bill-alt"></i>
					<span><spring:message code="pawns" /></span>
			</a>
				<div class="dropdown-menu" aria-labelledby="collapsePawns">
					<spring:url value="/employee/searchclientpawn" var="newpawn" />
					<a class="dropdown-item" href="${newpawn}"><spring:message
							code="newpawn" /></a>
					<spring:url value="/employee/renewPawn" var="renewpawn" />
					<a class="dropdown-item" href="${renewpawn}"><spring:message
							code="renewpawn" /></a>
					<spring:url value="/employee/searchrenovations"
						var="searchrenovations" />
					<a class="dropdown-item" href="${searchrenovations}"><spring:message
							code="searchrenovations" /></a>
					<spring:url value="/employee/removePawn" var="removePawn" />
					<strong><a href="${removePawn}" class="text-danger"><spring:message
								code="removepawn" /></a></strong>
				</div></li>

			<li class="nav-item"><spring:url value="/employee/newadjustment"
					var="adjustment" /> <a class="nav-link" href="${adjustment}"><i
					class="fa fa-wrench fa-fw"></i> <span><spring:message
							code="adjustments" /></span></a></li>
			<li class="nav-item"><spring:url value="/employee/newentrymoney"
					var="entrymoney" /> <a href="${entrymoney}" class="nav-link"><i
					class="fa fa-euro-sign fa-fw"></i><span> <spring:message
							code="entrymoney" />
				</span></a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseOConcepts" role="button" aria-haspopup="true"
				aria-expanded="false"> <i class="fa fa-fw fa-tags"></i> <span><spring:message
							code="othersconcepts" /></span>
			</a>
				<div class="dropdown-menu" aria-labelledby="collapseOConcepts">
					<spring:url value="/employee/newpayroll" var="payroll" />
					<a class="dropdown-item" href="${payroll}"><spring:message
							code="payroll" /></a>
					<spring:url value="/employee/localrental" var="localrental" />
					<a class="dropdown-item" href="${localrental}"><spring:message
							code="localrental" /></a>
					<spring:url value="/employee/newconcept" var="newconcept" />
					<a class="dropdown-item" href="${newconcept}"><spring:message
							code="othersconcepts" /></a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseDailies" role="button" aria-haspopup="true"
				aria-expanded="false"> <i class="fa fa-fw fa-calendar"></i> <span><spring:message
							code="dailies" /></span>
			</a>
				<div class="dropdown-menu" aria-labelledby="collapseDailies">
					<spring:url value="/employee/daily" var="daily" />
					<a class="dropdown-item" href="${daily}"><spring:message
							code="daily" /></a>
					<spring:url value="/employee/searchdaily" var="dailies" />
					<a class="dropdown-item" href="${dailies}"><spring:message
							code="dailies" /></a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseincidents" role="button" aria-haspopup="true"
				aria-expanded="false"> <i class="fa fa-exclamation"></i> <span><spring:message
							code="incidents" /></span>
			</a>
				<div class="dropdown-menu" aria-labelledby="collapseincidents">
					<spring:url value="/employee/newincident" var="newincident" />
					<a class="dropdown-item" href="${newincident}"><spring:message
							code="newincident" /></a>
					<spring:url value="/employee/myincidents" var="myincidents" />
					<a class="dropdown-item" href="${myincidents}"><spring:message
							code="myincidents" /></a>
					<spring:url value="/employee/pendingissues" var="pendingissues" />
					<a class="dropdown-item" href="${pendingissues}"><spring:message
							code="pendingissues" /></a>
				</div></li>
		</ul>
		<div id="content-wrapper">
			<div class="container-fluid">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fa fa-angle-up"></i>
	</a>
	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<spring:message code="readyToLeave" />
					</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true"><spring:message code="close" /></span>
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
</body>
</html>