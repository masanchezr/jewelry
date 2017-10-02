<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="savePawn" commandName="pawnForm">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="pawns" /></a></li>
		<li class="breadcrumb-item active"><spring:message code="newpawn" /></li>
	</ol>
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
					<spring:message code="nameclient" var="nameclient" />
					<form:input class="form-control" path="name"
						placeholder="${nameclient}" />
					<div class="form-group col-3 has-error">
						<label class="control-label" for="inputSuccess"><form:errors
								path="name" /></label>
					</div>
				</div>
				<div class="form-group">
					<spring:message code="surname" var="surname" />
					<form:input class="form-control" path="surname"
						placeholder="${surname}" />
					<div class="form-group has-error">
						<label class="control-label" for="inputSuccess"><form:errors
								path="surname" /></label>
					</div>
				</div>
				<div class="form-group">
					<spring:message code="addressnif" var="addressdni" />
					<form:input class="form-control" path="address"
						placeholder="${addressdni}" />
					<div class="form-group has-error">
						<label class="control-label" for="inputSuccess"><form:errors
								path="address" /></label>
					</div>
				</div>
				<div class="form-group ">
					<spring:message code="town" var="town" />
					<form:input class="form-control" path="town" placeholder="${town}" />
					<div class="form-group has-error">
						<label class="control-label" for="inputSuccess"><form:errors
								path="town" /></label>
					</div>
				</div>
				<div class="form-group">
					<spring:message code="nationality" var="nationality" />
					<form:input class="form-control" path="nationality"
						placeholder="${nationality}" />
					<div class="form-group col-3 has-error">
						<label class="control-label" for="inputSuccess"><form:errors
								path="nationality" /></label>
					</div>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="form-group">
					<spring:message code="idpawn" var="numpawnmessage" />
					<form:input class="form-control" path="numpawn"
						placeholder="${numpawnmessage}" />
					<div class="form-group col-3 has-error">
						<label class="control-label" for="inputSuccess"><form:errors
								path="numpawn" /></label>
					</div>
				</div>
				<div class="form-group">
					<spring:message code="amountpawn" />
					<form:input class="form-control" path="amount" />
					<div class="form-group has-error">
						<label class="control-label" for="inputSuccess"><form:errors
								path="amount" /></label>
					</div>
				</div>
				<div class="form-group">
					<spring:message code="percent" />
					<form:input class="form-control" path="percent" />
					<div class="form-group has-error">
						<label class="control-label" for="inputSuccess"><form:errors
								path="percent" /></label>
					</div>
				</div>
				<div id="sandbox-container" class="form-group">
					<spring:message code="pawndate" var="pawndate" />
					<form:input class="form-control" path="creationdate"
						placeholder="${pawndate}" />
					<div class="form-group has-error">
						<label class="control-label" for="inputSuccess"><form:errors
								path="creationdate" /></label>
					</div>
				</div>
				<div id="sandbox-container" class="form-group">
					<spring:message code="datebirth" var="datebirth" />
					<form:input class="form-control" path="datebirth"
						placeholder="${datebirth}" />
					<div class="form-group has-error">
						<label class="control-label" for="inputSuccess"><form:errors
								path="datebirth" /></label>
					</div>
				</div>
				<div>
					<form:button class="btn btn-primary" value="submit">
						<spring:message code="save" />
					</form:button>
				</div>
			</div>
		</div>
		<!-- fin div row -->
		<div class="table-responsive">
			<table class="table">
				<tbody>
					<c:forEach items="${pawnForm.objects}" varStatus="status" var="os">
						<tr class="${status.count % 2 == 0 ? 'success' : 'danger'}">
							<td><strong><form:hidden
										path="objects[${status.index}].metal.idmetal" /> <c:out
										value="${os.metal.description}" /></strong></td>
							<td><spring:message code="grossgrams" var="grossgrams" /> <form:input
									class="form-control" path="objects[${status.index}].grossgrams"
									placeholder="${grossgrams}" /></td>
							<td><spring:message code="description" var="description" />
								<form:input class="form-control"
									path="objects[${status.index}].description"
									placeholder="${description}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</form:form>