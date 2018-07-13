<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="deletesale" modelAttribute="removeSaleForm"
	role="form">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="sales" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="removesale" /></li>
	</ol>
	<div class="card-body">
		<div class="row">
			<div class="col-lg-6">
				<div class="form-group">
					<spring:message code="idsale" />
					<form:input class="form-control" path="numsale" />
					<p class="text-danger">
						<form:errors path="numsale" />
					</p>
				</div>
				<div class="form-group" id="payments">
					<spring:message code="repayment" />
					<form:select class="form-control" path="payment.idpayment"
						id="payments">
						<form:options items="${payments}" itemValue="idpayment"
							itemLabel="name" />
					</form:select>
				</div>
				<div id="discount" class="d-none">
					<div class="form-group">
						<spring:message code="iddiscount" />
						<form:input class="form-control" path="iddiscount" />
						<p class="text-danger">
							<form:errors path="numsale" />
						</p>
					</div>
					<div class="form-group">
						<spring:message code="numsalechange" />
						<form:input class="form-control" path="numsalechange" />
					</div>
				</div>
				<div class="d-none" id="accordion">
					<div class="form-group">
						<spring:message code="cancelpayments" />
						<form:input class="form-control" path="optionalpayment" />
						<p class="text-danger">
							<form:errors path="optionalpayment" />
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<form:button class="btn btn-primary" value="submit">
				<spring:message code="cancelsale" />
			</form:button>
		</div>
	</div>
</form:form>