<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8"%>
<form:form action="savepayroll" modelAttribute="payrollForm" role="form"
	autocomplete="off">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="othersconcepts" /></a></li>
		<li class="breadcrumb-item active"><spring:message code="payroll" /></li>
	</ol>
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="amount" />
							<form:input class="form-control" path="amount" />
							<p class="text-danger">
								<form:errors path="amount" />
							</p>
						</div>
						<div class="form-group">
							<form:select class="form-control"
								path="payrolltype.idpayrolltype">
								<form:options items="${payrolltypes}" itemValue="idpayrolltype"
									itemLabel="name" />
							</form:select>
						</div>
						<div class="form-group">
							<spring:message code="month" var="month" />
							<div id="sandbox-container">
								<form:input class="form-control" type="text" path="payrolldate"
									placeholder="${payrolldate}" />
							</div>
							<p class="text-danger">
								<form:errors path="payrolldate" />
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