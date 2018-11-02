<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="sales" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchsalescard" /></li>
</ol>
<form:form action="resultSalesCard" modelAttribute="searchSaleForm">
	<div class="row">
		<div class="col-lg-8">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-4">
						<div class="form-group">
							<spring:message code="datefrom" var="from" />
							<div id="sandbox-container">
								<form:input class="form-control" path="sfrom"
									placeholder="${from}" />
							</div>
							<p class="text-danger">
								<form:errors path="sfrom" />
							</p>
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="search" />
							</form:button>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="form-group">
							<spring:message code="dateuntil" var="until" />
							<div id="sandbox-container">
								<form:input class="form-control" path="suntil"
									placeholder="${until}" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>