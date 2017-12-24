<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<form:form action="resolvedincident" modelAttribute="incident">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="incident" /></a></li>
		<li class="breadcrumb-item active"><form:label path="idincident" /></li>
	</ol>
	<form:hidden path="idincident" />
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<form:label path="state" />
						</div>
						<div class="form-group">
							<spring:message code="description" />
							<form:textarea class="form-control" path="description" />
							<div class="form-group has-error">
								<label class="control-label" for="inputSuccess"> <form:errors
										path="description" /></label>
							</div>
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="solve" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>