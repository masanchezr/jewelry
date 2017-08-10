<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="saveshop" commandName="shopform">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="newshopping" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="numshop" var="numpawnvar" />
								<form:input class="form-control" path="numshop"
									placeholder="${numpawnvar}" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="numshop" /></label>
								</div>
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
								<spring:message code="nameclient" var="nameclient" />
								<form:input class="form-control" path="name"
									placeholder="${nameclient}" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="name" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="surname" var="surnamevar" />
								<form:input class="form-control" path="surname"
									placeholder="${surnamevar}" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="surname" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="addressnif" var="addressnif" />
								<form:input class="form-control" path="address"
									placeholder="${addressnif}" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="address" /></label>
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="town" var="townvar" />
								<form:input class="form-control" path="town"
									placeholder="${townvar}" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="town" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="nationality" var="nationalityvar" />
								<form:input class="form-control" path="nationality"
									placeholder="${nationalityvar}" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="nationality" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="cashamount" var="amount" />
								<form:input class="form-control" path="cashamount"
									placeholder="${amount}" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="cashamount" /></label>
								</div>
							</div>
							<div id="sandbox-container" class="form-group">
								<spring:message code="date" var="pawndate" />
								<form:input class="form-control" type="text" path="creationdate"
									placeholder="${pawndate}" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="creationdate" /></label>
								</div>
							</div>
							<div>
								<form:button class="btn btn-success" value="submit">
									<spring:message code="save" />
								</form:button>
							</div>
						</div>
					</div>
					<!-- fin div row -->
					<div class="table-responsive">
						<table class="table">
							<tbody>
								<c:forEach items="${shopform.objects}" varStatus="status"
									var="os">
									<tr class="${status.count % 2 == 0 ? 'success' : 'danger'}">
										<td><strong><form:hidden
													path="objects[${status.index}].metal.idmetal" /> <c:out
													value="${os.metal.description}" /></strong></td>
										<td><spring:message code="description" var="description" />
											<form:input class="form-control"
												path="objects[${status.index}].description"
												placeholder="${description}" /></td>
										<td><spring:message code="grossgrams" var="grossgrams" />
											<form:input class="form-control"
												path="objects[${status.index}].grossgrams"
												placeholder="${grossgrams}" /></td>
										<td><spring:message code="amount" var="amountmessage" />
											<form:input class="form-control"
												path="objects[${status.index}].amount"
												placeholder="${amountmessage}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>