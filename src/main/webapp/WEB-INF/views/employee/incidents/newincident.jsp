<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form action="saveincident" commandName="incident">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="incident" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<p class="form-control-static">
									<spring:message code="descriptionincidents" />
								</p>
							</div>
							<div class="form-group">
								<spring:message code="description" />
								<form:textarea class="form-control" path="description" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"> <form:errors
											path="description" /></label>
								</div>
							</div>
							<div class="form-group">
								<form:button class="btn btn-success" value="submit">
									<spring:message code="save" />
								</form:button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>