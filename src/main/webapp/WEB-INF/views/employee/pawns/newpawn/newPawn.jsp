<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="pawns" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="newpawn" /></li>
</ol>
<form:form action="savePawn" modelAttribute="pawnForm">
	<div class="card-body">
		<div class="row">
			<div class="col-lg-6">
				<div class="form-group">
					<spring:message code="nif" var="dni" />
					<form:input class="form-control" path="nif" placeholder="${dni}" />
					<p class="text-danger">
						<form:errors path="nif" />
					</p>
				</div>
				<div class="form-group">
					<spring:message code="nameclient" var="nameclient" />
					<form:input class="form-control" path="name"
						placeholder="${nameclient}" />
					<p class="text-danger">
						<form:errors path="name" />
					</p>
				</div>
				<div class="form-group">
					<spring:message code="surname" var="surname" />
					<form:input class="form-control" path="surname"
						placeholder="${surname}" />
					<p class="text-danger">
						<form:errors path="surname" />
					</p>
				</div>
				<div id="sandbox-container" class="form-group">
					<spring:message code="datebirth" var="datebirth" />
					<form:input class="form-control" path="datebirth"
						placeholder="${datebirth}" />
					<p class="text-danger">
						<form:errors path="datebirth" />
					</p>
				</div>
				<div class="form-group">
					<form:select class="form-control" path="track.idtrack">
						<form:options items="${tracks}" itemValue="idtrack"
							itemLabel="track" />
					</form:select>
				</div>
				<div class="form-group">
					<spring:message code="addressnif" var="addressdni" />
					<form:input class="form-control" path="address"
						placeholder="${addressdni}" />
					<p class="text-danger">
						<form:errors path="address" />
					</p>
				</div>
				<div class="form-group ">
					<spring:message code="town" var="town" />
					<form:input class="form-control" path="town" placeholder="${town}" />
					<p class="text-danger">
						<form:errors path="town" />
					</p>
				</div>
				<div class="form-group">
					<form:select class="form-control" path="nation.idnation">
						<form:options items="${nations}" itemValue="idnation"
							itemLabel="nation" />
					</form:select>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="form-group">
					<spring:message code="idpawn" />
					<form:input class="form-control" path="numpawn" />
					<p class="text-danger">
						<form:errors path="numpawn" />
					</p>
				</div>
				<div class="form-group">
					<spring:message code="amountpawn" />
					<form:input class="form-control" path="amount" />
					<p class="text-danger">
						<form:errors path="amount" />
					</p>
				</div>
				<div class="form-group">
					<spring:message code="percent" />
					<form:input class="form-control" path="percent" />
					<p class="text-danger">
						<form:errors path="percent" />
					</p>
				</div>
				<div id="sandbox-container" class="form-group">
					<spring:message code="pawndate" />
					<form:input class="form-control" path="creationdate" />
					<p class="text-danger">
						<form:errors path="creationdate" />
					</p>
				</div>
				<div>
					<form:button class="btn btn-primary" value="submit">
						<spring:message code="save" />
					</form:button>
				</div>
			</div>
		</div>
		<!-- fin div row -->
	</div>
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
</form:form>