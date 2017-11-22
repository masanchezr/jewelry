<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="incidents" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="incident" /></li>
</ol>
<form:form action="saveincident" commandName="incident">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<p class="form-control-static">
								<spring:message code="descriptionincidents" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="description" />
							<form:textarea class="form-control" class="form-control" path="description" />
							<div class="form-group col-3 has-error">
								<label class="control-label" for="inputSuccess"> <form:errors
										path="description" /></label>
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
		</div>
	</div>
</form:form>