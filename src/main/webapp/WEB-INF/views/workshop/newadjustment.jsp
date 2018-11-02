<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<form:form action="saveAdjustment" modelAttribute="adjustment">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item active"><spring:message
				code="newadjustment" /></li>
	</ol>
	<div class="card-body">
		<div class="form-group col-3">
			<spring:message code="idadjustment" />
			<form:input class="form-control" path="idadjustment" />
			<form:errors path="idadjustment" />
		</div>
		<div class="form-group col-3">
			<spring:message code="description" />
			<form:input class="form-control" path="description" />
			<form:errors path="description" />
		</div>
		<div class="form-group col-3">
			<spring:message code="gramsused" />
			<form:input class="form-control" path="grams" />
			<form:errors path="grams" />
		</div>
		<div class="form-group col-3">
			<spring:message code="amountwork" />
			<form:input class="form-control" path="amountwork" />
			<form:errors path="amountwork" />
		</div>
		<div class="form-group col-3">
			<spring:message code="recommendedprice" />
			<form:input class="form-control" path="recommendedprice" />
			<form:errors path="recommendedprice" />
		</div>
		<div class="form-group col-3">
			<form:button class="btn btn-primary" value="submit">
				<spring:message code="save" />
			</form:button>
		</div>
	</div>
</form:form>