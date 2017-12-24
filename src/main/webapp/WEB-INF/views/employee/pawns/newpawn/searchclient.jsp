<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="newPawn" modelAttribute="pawnForm">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="newpawn" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="searchclient" /></li>
	</ol>
	<div class="card-body">
		<div class="row">
			<div class="col-lg-6">
				<div class="form-group">
					<spring:message code="nif" />
					<form:input class="form-control" path="nif" />
					<div class="form-group col-3 has-error">
						<label class="control-label" for="inputSuccess"><form:errors
								path="nif" /></label>
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
</form:form>