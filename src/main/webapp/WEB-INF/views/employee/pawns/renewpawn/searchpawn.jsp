<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="searchRenewPawn" commandName="searchPawnForm"
	role="form">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="pawns" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="renewpawn" /></li>
	</ol>
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group col-3">
							<spring:message code="numpawn" />
							<form:input class="form-control" path="numpawn" />
							<div class="form-group col-3 has-error">
								<label class="control-label" for="inputSuccess"><form:errors
										path="numpawn" /></label>
							</div>
						</div>
						<div class="form-group col-3">
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