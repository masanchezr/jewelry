<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="adjustments" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchsumadjustments" /></li>
</ol>
<form:form action="sumadjustments" modelAttribute="searchDateForm">
	<div class="row">
		<div class="col-lg-9">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<form:select class="form-control" path="place.idplace">
								<form:options items="${places}" itemValue="idplace"
									itemLabel="description" />
							</form:select>
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="search" />
							</form:button>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<spring:message code="datefrom" var="datefrommessage" />
							<div id="sandbox-container">
								<form:input class="form-control" path="datefrom"
									placeholder="${datefrommessage}" />
							</div>
							<p class="text-danger">
								<form:errors path="datefrom" />
							</p>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<spring:message code="dateuntil" var="dateuntilmessage" />
							<div id="sandbox-container">
								<form:input class="form-control" path="dateuntil"
									placeholder="${dateuntilmessage}" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>