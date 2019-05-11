<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<form:form action="saveworkshop" modelAttribute="workshop">
	<div class="panel card-default">
		<div class="card-heading">
			<spring:message code="newworkshop" />
		</div>
		<div class="card-body">
			<div class="form-group">
				<spring:message code="description" />
				<form:input class="form-control" path="description" />
				<form:errors path="description" />
			</div>
			<div class="form-group">
				<spring:message code="grams" />
				<form:input class="form-control" path="grams" />
				<form:errors path="grams" />
			</div>
			<div class="form-group">
				<spring:message code="material" />
				<form:select class="form-control" path="metal.idmetal">
					<form:options items="${materials}" itemValue="idmetal"
						itemLabel="description" />
				</form:select>
			</div>
			<div class="form-group">
				<spring:message code="amount" />
				<form:input class="form-control" path="amount" />
				<form:errors path="amount" />
			</div>
			<div class="form-group">
				<form:button class="btn btn-primary" value="submit">
					<spring:message code="save" />
				</form:button>
			</div>
		</div>
	</div>
</form:form>