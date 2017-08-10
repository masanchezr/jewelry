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
				<spring:message code="idadjustment" var="idadjustmentmessage" />
				<form:input class="form-control" path="idadjustment"
					placeholder="${idadjustmentmessage}" />
				<div class="form-group has-error">
					<label class="control-label" for="inputSuccess"> <form:errors
							path="idadjustment" /></label>
				</div>
			</div>
			<div class="form-group">
				<spring:message code="description" var="descriptionmessage" />
				<form:input class="form-control" path="description"
					placeholder="${descriptionmessage}" />
				<div class="form-group has-error">
					<label class="control-label" for="inputSuccess"> <form:errors
							path="description" /></label>
				</div>
			</div>
			<div class="form-group">
				<spring:message code="amount" var="amountmessage" />
				<form:input class="form-control" path="amount"
					placeholder="${amountmessage}" />
				<div class="form-group has-error">
					<label class="control-label" for="inputSuccess"> <form:errors
							path="amount" /></label>
				</div>
			</div>
			<div class="form-group">
				<spring:message code="payment" />
				<form:select class="form-control" path="payment.idpayment">
					<form:options items="${payments}" itemValue="idpayment"
						itemLabel="name" />
				</form:select>
			</div>
			<div class="form-group">
				<form:button class="btn btn-success" value="submit">
					<spring:message code="save" />
				</form:button>
			</div>
		</div>
	</div>
</form:form>