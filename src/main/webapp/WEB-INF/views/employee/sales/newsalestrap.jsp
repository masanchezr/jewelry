<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8"%>
<form:form action="savesalestrap" modelAttribute="strapForm" role="form">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="sales" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="putstrap" /></li>
	</ol>
	<div class="card-body">
		<div class="form-row">
			<div class="col-lg-6">
				<div class="form-group">
					<spring:message code="amount" var="stringamount" />
					<form:input class="form-control" path="amount"
						placeholder="${stringamount}" />
					<p class="text-danger">
						<form:errors path="amount" />
					</p>
				</div>
				<div class="form-group">
					<spring:message code="payment" />
					<form:select class="form-control" path="payment.idpayment">
						<form:options items="${payments}" itemValue="idpayment"
							itemLabel="name" />
					</form:select>
				</div>
				<div class="form-group">
					<spring:message code="numberofsaleph" var="numberofsale" />
					<form:input class="form-control" path="numsale"
						placeholder="${numberofsale}" />
					<form:errors path="numsale" />
				</div>
				<div class="form-group">
					<form:button class="btn btn-primary" value="submit">
						<spring:message code="save" />
					</form:button>
				</div>
			</div>
		</div>
	</div>
</form:form>