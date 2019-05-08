<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<form:form action="searchRemovePawn" modelAttribute="searchPawnForm"
	role="form">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="pawns" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="removepawn" /></li>
	</ol>
	<div class="form-row">
		<div class="col-lg-12">
			<div class="panel card-default">
				<div class="card-heading">
					<spring:message code="removepawn" />
				</div>
				<div class="card-body">
					<div class="form-row">
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="numpawnph" var="numpawnholder" />
								<form:input class="form-control" path="numpawn"
									placeholder="${newpawnholder}" />
								<div class="form-group col-3 has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="numpawn" /></label>
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
	</div>
</form:form>