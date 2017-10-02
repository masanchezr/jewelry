<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="savelocalrental" commandName="rentalForm" role="form">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="othersconcepts" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="localrental" /></li>
	</ol>
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="amount" />
							<form:input class="form-control" path="amount" />
							<div class="form-group col-3 has-error">
								<label class="control-label" for="inputSuccess"><form:errors
										path="amount" /></label>
							</div>
						</div>
						<div class="form-group">
							<spring:message code="monthrentaldate" var="monthrentaldate" />
							<div id="sandbox-container">
								<form:input class="form-control" type="text" path="rentaldate"
									placeholder="${monthrentaldate}" />
							</div>
							<div class="form-group has-error">
								<label class="control-label" for="inputSuccess"><form:errors
										path="rentaldate" /></label>
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
		</div>
	</div>
</form:form>