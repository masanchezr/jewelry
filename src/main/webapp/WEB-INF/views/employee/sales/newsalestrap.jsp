<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="savesalestrap" commandName="strapForm" role="form">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="sales" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="putstrap" /></li>
	</ol>
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
					<spring:message code="payment" />
					<form:select class="form-control" path="payment.idpayment">
						<form:options items="${payments}" itemValue="idpayment"
							itemLabel="name" />
					</form:select>
				</div>
				<div class="form-group">
					<spring:message code="idsale" />
					<form:input class="form-control" path="numsale" />
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