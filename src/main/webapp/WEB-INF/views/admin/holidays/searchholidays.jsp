<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="holidays" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchholidays" /></li>
</ol>
<form:form action="resultHolidays" commandName="holiday">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="datefrom" var="datefrommessage" />
							<div id="sandbox-container">
								<form:input class="form-control" path="holiday"
									placeholder="${datefrommessage}" />
							</div>
							<div class="form-group has-error">
								<label class="control-label" for="inputSuccess"> <form:errors
										path="holiday" /></label>
							</div>
						</div>
						<div class="form-group">
							<spring:message code="dateuntil" var="dateuntilmessage" />
							<div id="sandbox-container">
								<form:input class="form-control" path="untildate"
									placeholder="${dateuntilmessage}" />
							</div>
							<div class="form-group has-error">
								<label class="control-label" for="inputSuccess"> <form:errors
										path="untildate" /></label>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<form:button class="btn btn-primary" value="submit">
						<spring:message code="search" />
					</form:button>
				</div>
			</div>
		</div>
	</div>
</form:form>