<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="saverecording" commandName="recording" role="form">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="sales" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="recording" /></li>
	</ol>
	<div class="card-body">
		<div class="row">
			<div class="col-lg-6">
				<div class="form-group">
					<spring:message code="idsale" />
					<spring:message code="optional" />
					<form:input class="form-control" path="numsale" />
				</div>
				<div class="form-group">
					<spring:message code="payment" />
					<form:select class="form-control" path="pay.idpayment">
						<form:options items="${payments}" itemValue="idpayment"
							itemLabel="name" />
					</form:select>
				</div>
				<div class="form-group">
					<spring:message code="amount" />
					<form:input class="form-control" path="amount" />
				</div>
				<div class="form-group">
					<spring:message code="description" />
					<form:input class="form-control" path="description" />
				</div>
				<div class="form-group">
					<div class="form-group col-3 has-error">
						<label class="control-label" for="inputSuccess"> <form:errors
								path="numsale" /></label>
					</div>
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