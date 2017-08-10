<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
	<form:form action="savePayment" commandName="payment">
		<spring:message code="name" />
		<form:input class="form-control" path="name" />
		<spring:message code="active" />
		<form:checkbox path="active" />
		<form:button class="btn btn-success" value="submit">
			<spring:message code="save" />
		</form:button>
	</form:form>
</div>