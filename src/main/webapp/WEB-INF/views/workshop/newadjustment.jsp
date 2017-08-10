<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="saveAdjustment" commandName="adjustment">
	<div class="panel panel-default">
		<div class="panel-heading">
			<spring:message code="newadjustment" />
		</div>
		<div class="panel-body">
			<div class="form-group">
				<spring:message code="idadjustment" />
				<form:input class="form-control" path="idadjustment" />
				<form:errors path="idadjustment" />
			</div>
			<div class="form-group">
				<spring:message code="description" />
				<form:input class="form-control" path="description" />
				<form:errors path="description" />
			</div>
			<div class="form-group">
				<spring:message code="gramsused" />
				<form:input class="form-control" path="grams" />
				<form:errors path="grams" />
			</div>
			<div class="form-group">
				<spring:message code="amountwork" />
				<form:input class="form-control" path="amountwork" />
				<form:errors path="amountwork" />
			</div>
			<div class="form-group">
				<spring:message code="recommendedprice" />
				<form:input class="form-control" path="recommendedprice" />
				<form:errors path="recommendedprice" />
			</div>
			<div class="form-group">
				<form:button class="btn btn-success" value="submit">
					<spring:message code="save" />
				</form:button>
			</div>
		</div>
	</div>
</form:form>