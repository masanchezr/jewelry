<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<form:form action="resolvedincident" commandName="incident">
	<form:hidden path="idincident" />
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="incident" />
					<form:label path="idincident" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<form:label path="state" />
							</div>
							<div class="form-group">
								<spring:message code="description" />
								<form:textarea path="description" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"> <form:errors
											path="description" /></label>
								</div>
							</div>
							<div class="form-group">
								<form:button class="btn btn-success" value="submit">
									<spring:message code="solve" />
								</form:button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>