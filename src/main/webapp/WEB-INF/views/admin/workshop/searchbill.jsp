<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="workshop" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchbill" /></li>
</ol>
<form:form action="bill" commandName="billingForm">
	<div class="row">
		<div class="col-lg-6">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="date" var="datemessage" />
							<div id="sandbox-container">
								<form:input class="form-control" type="text" path="date"
									placeholder="${datemessage}" />
							</div>
							<div class="form-group has-error">
								<label class="control-label" for="inputSuccess"><form:errors
										path="date" /></label>
							</div>
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="search" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>