<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="newshop" commandName="shopform">
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="searchClients" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="searchclient" />
							</div>
							<div class="form-group">
								<spring:message code="nif" var="dni" />
								<form:input class="form-control" path="nif" placeholder="${dni}" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="nif" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="numshop" />
								<form:input class="form-control" path="numshop" />
							</div>
							<div class="form-group">
								<spring:message code="user" />
								<form:input class="form-control" path="user" />
							</div>
							<div class="form-group">
								<form:button class="btn btn-success" value="submit">
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