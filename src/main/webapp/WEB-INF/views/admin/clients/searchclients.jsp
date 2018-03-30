<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="shoppings" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchClients" /></li>
</ol>
<form:form action="resultclients" modelAttribute="client">
	<div class="row">
		<div class="col-lg-6">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="nif" var="dni" />
							<form:input class="form-control" path="nif" placeholder="${dni}" />
							<div class="form-group has-error">
								<label class="control-label" for="inputSuccess"><form:errors
										path="nif" /></label>
							</div>
						</div>
						<div class="form-group">
							<spring:message code="name" var="name" />
							<form:input class="form-control" path="name"
								placeholder="${name}" />
						</div>
						<div class="form-group">
							<spring:message code="surname" var="surname" />
							<form:input class="form-control" path="surname"
								placeholder="${surname}" />
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