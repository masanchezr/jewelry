<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<form:form action="saveConcept" commandName="otherconcept">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="othersconcepts" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="othersconcepts" /></li>
	</ol>
	<div class="card-body">
		<div class="form-group">
			<spring:message code="description" />
			<form:input class="form-control" path="description" />
			<div class="form-group has-error">
				<label class="control-label" for="inputSuccess"><form:errors
						path="description" /></label>
			</div>
		</div>
		<div class="form-group">
			<spring:message code="amountotherconcept" />
			<form:input class="form-control" path="amount" />
			<div class="form-group has-error">
				<label class="control-label" for="inputSuccess"><form:errors
						path="amount" /></label>
			</div>
		</div>
		<div class="form-group">
			<form:button class="btn btn-primary" value="submit">
				<spring:message code="save" />
			</form:button>
		</div>
	</div>
</form:form>