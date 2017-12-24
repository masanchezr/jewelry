<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="savePayment" modelAttribute="payment">
	<div class="form-group">
		<spring:message code="name" />
		<form:input class="form-control" path="name" />
	</div>
	<div class="form-group">
		<spring:message code="active" />
		<form:checkbox path="active" />
	</div>
	<div class="form-group">
		<form:button class="btn btn-primary" value="submit">
			<spring:message code="save" />
		</form:button>
	</div>
</form:form>