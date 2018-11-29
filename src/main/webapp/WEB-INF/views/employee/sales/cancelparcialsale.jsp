<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>
<form:form action="savecancelparcial" modelAttribute="saleForm"
	role="form">
	<form:hidden path="idsale" />
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="sales" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="removeparcialsale" /></li>
	</ol>
	<div class="row">
		<div class="col-lg-3">
			<div class="form-group">
				<spring:message code="idsale" />
				<form:input class="form-control" path="numsale" disabled="true" />
			</div>
			<div class="form-group has-error">
				<label class="control-label" for="inputSuccess"> <form:errors
						path="idsale" /></label>
			</div>
			<div class="form-group">
				<spring:message code="selectjeweltocancel" />
			</div>
			<form:checkboxes items="${saleForm.jewels}" path="jewelstocancel"
				itemValue="idjewel" itemLabel="reference" />
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3">
			<div class="form-group" id="payments">
				<spring:message code="repayment" />
				<form:select class="form-control" path="payment.idpayment"
					id="payments">
					<form:options items="${payments}" itemValue="idpayment"
						itemLabel="name" />
				</form:select>
			</div>
			<div id="discount" class="hidden">
				<div class="form-group">
					<spring:message code="iddiscount" />
					<form:input class="form-control" path="iddiscount" />
				</div>
				<div class="form-group">
					<spring:message code="numsalechange" />
					<form:input class="form-control" path="numsalechange" />
				</div>
			</div>
			<div class="form-group">
				<form:button class="btn btn-primary" value="submit">
					<spring:message code="cancelsale" />
				</form:button>
			</div>
		</div>
	</div>
</form:form>