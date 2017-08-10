<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="savesalebattery" commandName="batteryForm"
	role="form">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="putbattery" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="amount" />
								<form:input class="form-control" path="amount" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="amount" /></label>
								</div>
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
								<form:button class="btn btn-success" value="submit">
									<spring:message code="save" />
								</form:button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>