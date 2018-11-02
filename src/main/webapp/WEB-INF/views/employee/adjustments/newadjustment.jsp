<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<ol class="breadcrumb">
	<li class="breadcrumb-item active"><spring:message
			code="newadjustment" /></li>
</ol>
<form:form action="saveAdjustment" modelAttribute="adjustment">
	<div class="card-body">
		<div class="form-group col-3">
			<spring:message code="idadjustment" var="idadjustmentmessage" />
			<form:input class="form-control" path="idadjustment"
				placeholder="${idadjustmentmessage}" />
			<div class="form-group col-3 has-error">
				<label class="control-label" for="inputSuccess"> <form:errors
						path="idadjustment" /></label>
			</div>
		</div>
		<div class="form-group col-3">
			<spring:message code="description" var="descriptionmessage" />
			<form:input class="form-control" path="description"
				placeholder="${descriptionmessage}" />
			<div class="form-group col-3 has-error">
				<label class="control-label" for="inputSuccess"> <form:errors
						path="description" /></label>
			</div>
		</div>
		<div class="form-group col-3">
			<spring:message code="amount" var="amountmessage" />
			<form:input class="form-control" path="amount"
				placeholder="${amountmessage}" />
			<div class="form-group col-3 has-error">
				<label class="control-label" for="inputSuccess"> <form:errors
						path="amount" /></label>
			</div>
		</div>
		<div class="form-group col-3">
			<spring:message code="payment" />
			<form:select class="form-control" path="payment.idpayment">
				<form:options items="${payments}" itemValue="idpayment"
					itemLabel="name" />
			</form:select>
		</div>
		<div class="form-group col-3">
			<form:button class="btn btn-primary" value="submit">
				<spring:message code="save" />
			</form:button>
		</div>
	</div>
</form:form>