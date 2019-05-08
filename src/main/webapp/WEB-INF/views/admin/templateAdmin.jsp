<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<tiles:insertAttribute name="header" />
<body id="page-top">
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<!-- csrt support -->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<!-- Navigation-->
	<nav class="navbar navbar-expand navbar-dark bg-dark static-top">
		<a class="navbar-brand mr-1" href="admin"><spring:message
				code="goldburgos" /></a>
		<button class="btn btn-link btn-sm text-white order-1 order-sm-0"
			id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar Search -->
		<form:form action="searchJewels" modelAttribute="adminForm"
			class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
			<div class="input-group">
				<spring:message code="find" var="find" />
				<form:input class="form-control" type="text" path="search"
					aria-describedby="basic-addon2" aria-label="Search"
					placeholder="${find}" />
				<div class="input-group-append">
					<form:button class="btn btn-primary" type="submit" value="submit">
						<i class="fas fa-search"></i>
					</form:button>
				</div>
			</div>
		</form:form>
		<!-- End Navbar Search -->
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
		<!-- Sidebar -->
		<ul class="sidebar navbar-nav">
			<li class="nav-item"><spring:url value="/searchEntries"
					var="searchEntries" /> <a href="${searchEntries}" class="nav-link"><i
					class="fas fa-euro-sign fa-fw"></i> <span><spring:message
							code="searchentries" /></span></a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseAdjustments" role="button" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-wrench fa-fw"></i> <span><spring:message
							code="adjustments" /></span></a>
				<div class="dropdown-menu" aria-labelledby="collapseAdjustments">
					<spring:url value="/searchadjustment" var="searchadjustment" />
					<a class="dropdown-item" href="${searchadjustment}"
						class="nav-link"><spring:message code="searchadjustment" /></a>
					<spring:url value="/searchsumadjustments"
						var="searchsumadjustments" />
					<a class="dropdown-item" href="${searchsumadjustments}"
						class="nav-link"><spring:message code="searchsumadjustments" /></a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseJewels" role="button" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-gem fa-fw"></i> <span><spring:message
							code="jewels" /></span></a>
				<div class="dropdown-menu" aria-labelledby="collapseJewels">
					<spring:url value="/newJewel" var="newjewels" />
					<a class="dropdown-item" href="${newjewels}"><spring:message
							code="newjewels" /></a>
					<spring:url value="/newSet" var="newset" />
					<a class="dropdown-item" href="${newset}"><spring:message
							code="newset" /></a>
					<spring:url value="/searchUpdateJewels" var="updatejewels" />
					<a class="dropdown-item" href="${updatejewels}"><spring:message
							code="updatejewels" /></a>
					<spring:url value="/allSets" var="allsets" />
					<a class="dropdown-item" href="${allsets}"><spring:message
							code="allsets" /></a>
					<spring:url value="/searchByReference" var="searchbyreference" />
					<a class="dropdown-item" href="${searchbyreference}"><spring:message
							code="searchbyreference" /></a>
					<spring:url value="/searchjewelsactive" var="searchjewelsactive" />
					<a class="dropdown-item" href="${searchjewelsactive}"><spring:message
							code="jewelsactive" /></a>
					<spring:url value="/checkinventory" var="checkinventory" />
					<a class="dropdown-item" href="${checkinventory}"><spring:message
							code="checkinventory" /></a>
					<spring:url value="/upload" var="uploadFile" />
					<a class="dropdown-item" href="${uploadFile}"><spring:message
							code="uploadfile" /></a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseShop" role="button" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-shopping-cart fa-fw"></i>
					<span><spring:message code="shoppings" /></span> </a>
				<div class="dropdown-menu" aria-labelledby="collapseShop">
					<spring:url value="/searchShoppings" var="searchshoppings" />
					<a class="dropdown-item" href="${searchshoppings}"> <spring:message
							code="searchshoppings" /></a>
					<spring:url value="/searchquartermaterial"
						var="searchquartermaterial" />
					<a class="dropdown-item" href="${searchquartermaterial}"> <spring:message
							code="gramsformaterial" />
					</a>
					<spring:url value="/searchgramsnull" var="searchgramsnull" />
					<a class="dropdown-item" href="${searchgramsnull}"> <spring:message
							code="searchgramsnull" />
					</a>
					<spring:url value="/tomelloso" var="tomelloso" />
					<a class="dropdown-item" href="${tomelloso}">Tomelloso </a>
					<spring:url value="/exceltomelloso" var="exceltomelloso" />
					<a class="dropdown-item" href="${exceltomelloso}">Generar Excel
						Tomelloso </a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapsePawns" role="button" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-money-bill-alt fa-fw"></i>
					<span><spring:message code="pawns" /></span> </a>
				<div class="dropdown-menu" aria-labelledby="collapsePawns">
					<spring:url value="/searchPawns" var="searchpawns" />
					<a class="dropdown-item" href="${searchpawns}"><spring:message
							code="searchpawns" /></a>
					<spring:url value="/searchrenovations" var="searchrenovations" />
					<a class="dropdown-item" href="${searchrenovations}"><spring:message
							code="searchrenovations" /></a>
					<spring:url value="/searchquarterpawns" var="searchquarterpawns" />
					<a class="dropdown-item" href="${searchquarterpawns}"> <spring:message
							code="quarters" />
					</a>
					<spring:url value="/outofdate" var="outofdate" />
					<a class="dropdown-item" href="${outofdate}"> <spring:message
							code="outofdate" />
					</a>
					<spring:url value="/searchcommissions" var="searchcommissions" />
					<a class="dropdown-item" href="${searchcommissions}"> <spring:message
							code="commissions" />
					</a>
					<spring:url value="/investedmoney" var="investedmoney" />
					<a class="dropdown-item" href="${investedmoney}"> <spring:message
							code="investedmoney" />
					</a>
					<spring:url value="/searchPosibleRepeated"
						var="searchposiblerepeated" />
					<a class="dropdown-item" href="${searchposiblerepeated}"><spring:message
							code="searchposiblerepeated" /></a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseDailies" role="button" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-calendar fa-fw"></i> <span><spring:message
							code="dailies" /></span></a>
				<div class="dropdown-menu" aria-labelledby="collapseDailies">
					<spring:url value="/dailyplace" var="dailies" />
					<a class="dropdown-item" href="${dailies}"><spring:message
							code="dailies" /></a>
					<spring:url value="/searchcalculatedailies"
						var="searchcalculatedailies" />
					<a class="dropdown-item" href="${searchcalculatedailies}"><spring:message
							code="calculatedailies" /></a>
				</div></li>
			<li class="nav-item"><spring:url value="/searchbill"
					var="searchbill" /><a href="${searchbill}" class="nav-link"><i
					class="fas fa-bug fa-fw"></i> <span><spring:message
							code="searchbill" /></span></a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseHolidays" role="button" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-calendar fa-fw"></i> <span><spring:message
							code="holidays" /></span> </a>
				<div class="dropdown-menu" aria-labelledby="collapseHolidays">
					<spring:url value="/newHoliday" var="newholiday" />
					<a class="dropdown-item" href="${newholiday}"><spring:message
							code="newholiday" /></a>
					<spring:url value="/allHolidays" var="allholidays" />
					<a class="dropdown-item" href="${allholidays}"><spring:message
							code="allholidays" /></a>
					<spring:url value="/searchHolidays" var="searchholidays" />
					<a class="dropdown-item" href="${searchholidays}"><spring:message
							code="searchholidays" /></a>
				</div></li>
			<li class="nav-item"><spring:url value="/searchSalesCard"
					var="searchsalescard" /> <a href="${searchsalescard}"
				class="nav-link"><i class="fas fa-credit-card fa-fw"></i> <span>
						<spring:message code="salescard" />
				</span></a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseSales" role="button" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-tags fa-fw"></i> <span><spring:message
							code="sales" /></span> </a>
				<div class="dropdown-menu" aria-labelledby="collapseSales">
					<spring:url value="/searchSales" var="searchsales" />
					<a class="dropdown-item" href="${searchsales}"> <spring:message
							code="sales" /></a>
					<spring:url value="/newsale" var="newsale" />
					<a class="dropdown-item" href="${newsale}"> <spring:message
							code="newsale" /></a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseSalesPostPoned" role="button" aria-haspopup="true"
				aria-expanded="false"> <i class="fas fa-fw fa-tasks"></i> <span><spring:message
							code="salespostponed" /></span>
			</a>
				<div class="dropdown-menu" aria-labelledby="collapseSalesPostPoned">
					<spring:url value="/searchsalepostponed" var="searchsalepostponed" />
					<a class="dropdown-item" href="${searchsalepostponed}"><spring:message
							code="searchsalepostponed" /></a>
					<spring:url value="/searchmissingsalepostponed"
						var="searchNumMissingSalePost" />
					<a class="dropdown-item" href="${searchNumMissingSalePost}"> <spring:message
							code="searchnummissing" /></a>
					<spring:url value="/searchexpired" var="searchexpired" />
					<a class="dropdown-item" href="${searchexpired}"> <spring:message
							code="searchexpired" /></a>
				</div></li>
			<li class="nav-item"><spring:url value="/newuser" var="newuser" />
				<a class="nav-link" href="${newuser}"><i
					class="fas fa-users fa-fw"></i><span> <spring:message
							code="newuser" /></span></a></li>
			<li class="nav-item dropdown"><spring:url value="/pendingissues"
					var="pendingissues" /> <a href="${pendingissues}" class="nav-link"><i
					class="fas fa-exclamation-triangle fa-fw"></i> <span><spring:message
							code="pendingissues" /></span></a></li>
			<li class="nav-item"><spring:url value="/searchclients"
					var="searchclients" /> <a href="${searchclients}" class="nav-link"><i
					class="fas fa-users fa-fw"></i> <span><spring:message
							code="searchclients" /></span></a></li>
			<li class="nav-item"><spring:url value="/newCategory"
					var="newcategory" /> <a href="${newcategory}" class="nav-link"><i
					class="fas fa-archive fa-fw"></i> <span><spring:message
							code="newcategory" /></span></a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapsePayments" role="button" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-credit-card fa-fw"></i> <span><spring:message
							code="payments" /></span> </a>
				<div class="dropdown-menu" aria-labelledby="collapsePayments">
					<spring:url value="/newPayment" var="newpayment" />
					<a class="dropdown-item" href="${newpayment}"> <spring:message
							code="newpayment" /></a>
					<spring:url value="/allpayments" var="allpayments" />
					<a class="dropdown-item" href="${allpayments}"> <spring:message
							code="allpayments" /></a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				id="collapseCoins" role="button" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-database fa-fw"></i> <span><spring:message
							code="newcoin" /></span></a>
				<div class="dropdown-menu" aria-labelledby="collapseCoins">
					<spring:url value="/newcoin" var="newcoin" />
					<a class="dropdown-item" href="${newcoin}"><spring:message
							code="addcoin" /></a>
					<spring:url value="/allcoins" var="allcoins" />
					<a class="dropdown-item" href="${allcoins}"><spring:message
							code="allcoins" /></a>
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
		class="fas fa-angle-up"></i>
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