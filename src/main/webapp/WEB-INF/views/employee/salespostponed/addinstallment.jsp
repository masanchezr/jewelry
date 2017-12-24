<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="salepostponed" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="addinstallment" /></li>
</ol>
<form:form action="saveinstallment" modelAttribute="installment"
	role="form">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="idsale" var="idsalemessage" />
							<form:input class="form-control" path="idsalepostponed"
								placeholder="${idsalemessage}" />
							<p class="text-danger">
								<form:errors path="idsalepostponed" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="amount" var="amountmessage" />
							<form:input class="form-control" path="amount"
								placeholder="${amountmessage}" />
							<div class="form-group has-error">
								<label class="control-label" for="inputSuccess"><form:errors
										path="amount" /></label>
							</div>
						</div>
						<div class="form-group">
							<form:select class="form-control" path="pay.idpayment">
								<form:options items="${payments}" itemValue="idpayment"
									itemLabel="name" />
							</form:select>
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="save" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>