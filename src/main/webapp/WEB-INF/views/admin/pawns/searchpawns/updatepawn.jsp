<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="savePawn" commandName="pawnForm">
	<form:hidden path="idpawn" />
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="newpawn" />
				</div>
				<div class="panel-body">
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
								<div class="form-group has-error">
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
							<div class="form-group">
								<spring:message code="town" var="town" />
								<form:input class="form-control" path="town"
									placeholder="${town}" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="town" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="nationality" />
								<form:input class="form-control" path="nationality" />
								<div class="form-group has-error">
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
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="numpawn" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="amountpawn" var="amountpawn" />
								<form:input class="form-control" path="amount"
									placeholder="${amountpawn}" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="amount" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="percent" var="per" />
								<form:input class="form-control" path="percent"
									placeholder="${per}" />
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
								<c:forEach items="${pawnForm.objects}" varStatus="status"
									var="os">
									<tr class="${status.count % 2 == 0 ? 'success' : 'danger'}">
										<td><strong><form:hidden
													path="objects[${status.index}].idobjectpawn" /> <form:select
													class="form-control"
													path="objects[${status.index}].metal.idmetal">
													<form:option value="${os.metal.idmetal}"
														label="${os.metal.description}" />
													<form:options items="${metals}" itemValue="idmetal"
														itemLabel="description" />
												</form:select></strong></td>
										<td><spring:message code="grossgrams" /> <form:input
												class="form-control"
												path="objects[${status.index}].grossgrams" disabled="true" /></td>
										<td><spring:message code="realgrams" /> <form:input
												class="form-control"
												path="objects[${status.index}].realgrams" /></td>
										<td><spring:message code="description" /> <form:input
												class="form-control"
												path="objects[${status.index}].description" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<c:if test="${not empty pawnForm.renovations}">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th><spring:message code="daterenew" /></th>
										<th><spring:message code="daterenovation" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pawnForm.renovations}" var="renovation">
										<tr>
											<td><fmt:formatDate
													value="${renovation.nextrenovationdate}"
													pattern="yyyy-MM-dd" /></td>
											<td><fmt:formatDate value="${renovation.creationdate}"
													pattern="yyyy-MM-dd" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:if>
					<c:if test="${empty pawnForm.renovations}">
						<spring:message code="noresults" />
						<c:out value="${pawnForm.months}" />
					</c:if>
					<c:if test="${not empty pawnForm.renovations}">
						<c:out value="${fn:length(pawnForm.renovations)}" />
					</c:if>
				</div>
			</div>
		</div>
	</div>
</form:form>