<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="holidays" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="newholiday" /></li>
</ol>
<form:form action="addHoliday" commandName="holiday">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="date" var="date" />
							<div id="sandbox-container">
								<form:input class="form-control" type="text" path="holiday"
									placeholder="${date}" />
							</div>
							<div class="form-group has-error">
								<label class="control-label" for="inputSuccess"> <form:errors
										path="holiday" /></label>
							</div>
						</div>
						<div class="form-group">
							<spring:message code="description" var="desc" />
							<form:input class="form-control" path="description"
								placeholder="${desc}" />
							<div class="form-group has-error">
								<label class="control-label" for="inputSuccess"> <form:errors
										path="description" /></label>
							</div>
						</div>
						<div class="form-group">
							<spring:message code="allplaces" />
							<form:checkbox path="allplaces" />
						</div>
						<div class="form-group">
							<spring:message code="place" />
							<form:select class="form-control" path="place.idplace">
								<form:options items="${places}" itemValue="idplace"
									itemLabel="description" />
							</form:select>
						</div>
					</div>
				</div>
				<div class="form-group">
					<form:button class="btn btn-primary" value="submit">
						<spring:message code="save" />
					</form:button>
				</div>
			</div>
		</div>
	</div>
</form:form>