<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8"%>
<form:form action="savelocalrental" modelAttribute="rentalForm"
	role="form" autocomplete="off">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="othersconcepts" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="localrental" /></li>
	</ol>
	<div class="form-row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="form-row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="amount" var="amountph" />
							<form:input class="form-control" path="amount"
								placeholder="${amountph}" />
							<p class="text-danger">
								<form:errors path="amount" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="monthrentaldate" var="monthrentaldate" />
							<div id="sandbox-container">
								<form:input class="form-control" type="text" path="rentaldate"
									placeholder="${monthrentaldate}" />
							</div>
							<p class="text-danger">
								<form:errors path="rentaldate" />
							</p>
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