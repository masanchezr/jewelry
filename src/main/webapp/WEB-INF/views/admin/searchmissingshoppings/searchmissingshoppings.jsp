<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="resultmissingshoppings"
	commandName="searchmissingshoppings">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="shoppings" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="numfrom" />
								<form:input class="form-control" path="numfrom" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="numfrom" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="numuntil" />
								<form:input class="form-control" path="numuntil" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="numuntil" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="year" />
								<form:input class="form-control" path="year" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="year" /></label>
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
								<form:button class="btn btn-success" value="submit">
									<spring:message code="search" />
								</form:button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>