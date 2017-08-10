<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="addcoin" commandName="coinForm">
	<div class="row">
		<div class="col-lg-8">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="newcoin" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-4">
							<div class="form-group">
								<spring:message code="description" />
								<form:input class="form-control" path="description" />
								<form:errors path="description" />
							</div>
							<div class="form-group">
								<spring:message code="place" />
								<form:select class="form-control" path="place.idplace">
									<form:options items="${places}" itemValue="idplace"
										itemLabel="description" />
								</form:select>
							</div>
							<div class="form-group">
								<spring:message code="price" />
								<form:input class="form-control" path="price" />
								<form:errors path="price" />
							</div>
							<div class="form-group">
								<form:button class="btn btn-success" value="submit">
									<spring:message code="save" />
								</form:button>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="form-group">
								<spring:message code="metal" />
								<form:select class="form-control" path="idmetal">
									<form:options items="${metals}" itemValue="idmetal"
										itemLabel="description" />
								</form:select>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>