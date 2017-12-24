<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="saveentrymoney" modelAttribute="entrymoneyForm"
	role="form">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item active"><spring:message
				code="entrymoney" /></li>
	</ol>
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group col-3">
							<spring:message code="amount" var="amountmessage" />
							<form:input class="form-control" path="amount"
								placeholder="${amountmessage}" />
							<div class="form-group col-3 has-error">
								<label class="control-label" for="inputSuccess"><form:errors
										path="amount" /></label>
							</div>
						</div>
						<div class="form-group col-3">
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