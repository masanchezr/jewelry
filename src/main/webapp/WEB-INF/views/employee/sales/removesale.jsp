<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="deletesale" commandName="removeSaleForm" role="form">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="removesale" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="idsale" />
								<form:input class="form-control" path="numsale" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"> <form:errors
											path="numsale" /></label>
								</div>
							</div>
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
							<div class="hidden" id="accordion">
								<div class="form-group">
									<spring:message code="cancelpayments" />
									<form:input class="form-control" path="optionalpayment" />
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<form:button class="btn btn-success" value="submit">
							<spring:message code="cancelsale" />
						</form:button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>