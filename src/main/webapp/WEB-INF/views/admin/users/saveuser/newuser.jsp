<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="saveuser" commandName="user">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="newuser" />
				</div>
				<div class="panel-body">
					<div class="col-lg-6">
						<div class="row">
							<div class="form-group">
								<spring:message code="username" />
								<form:input class="form-control" path="username" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"> <form:errors
											path="username" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="password" />
								<form:input class="form-control" path="password" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"> <form:errors
											path="password" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="place" />
								<form:select class="form-control" path="place.idplace">
									<form:options items="${places}" itemValue="idplace"
										itemLabel="description" />
								</form:select>
							</div>
							<div class="form-group">
								<spring:message code="active" />
								<form:checkbox path="enabled" />
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