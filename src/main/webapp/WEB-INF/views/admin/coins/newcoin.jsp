<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<form:form action="addcoin" modelAttribute="coinForm">
	<div class="row">
		<div class="col-lg-8">
			<div class="panel card-default">
				<div class="card-heading">
					<spring:message code="newcoin" />
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-lg-4">
							<div class="form-group col-3">
								<spring:message code="description" />
								<form:input class="form-control" path="description" />
								<form:errors path="description" />
							</div>
							<div class="form-group col-3">
								<spring:message code="place" />
								<form:select class="form-control" path="place.idplace">
									<form:options items="${places}" itemValue="idplace"
										itemLabel="description" />
								</form:select>
							</div>
							<div class="form-group col-3">
								<spring:message code="price" />
								<form:input class="form-control" path="price" />
								<form:errors path="price" />
							</div>
							<div class="form-group col-3">
								<form:button class="btn btn-primary" value="submit">
									<spring:message code="save" />
								</form:button>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="form-group col-3">
								<spring:message code="material" />
								<form:select class="form-control" path="metal">
									<form:options items="${materials}" itemValue="idmetal"
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