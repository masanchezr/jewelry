<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="shoppings" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="newshopping" /></li>
</ol>
<form:form action="saveshop" modelAttribute="shopform">
	<div class="card-body">
		<div class="row">
			<div class="col-lg-6">
				<div class="form-group">
					<spring:message code="numshop" var="numpawnvar" />
					<form:input class="form-control" path="numshop"
						placeholder="${numpawnvar}" />
					<p class="text-danger">
						<form:errors path="numshop" />
					</p>
				</div>
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
					<spring:message code="surname" var="surnamevar" />
					<form:input class="form-control" path="surname"
						placeholder="${surnamevar}" />
					<p class="text-danger">
						<form:errors path="surname" />
					</p>
				</div>
				<div class="form-group">
					<form:select class="form-control" path="track.idtrack">
						<form:options items="${tracks}" itemValue="idtrack"
							itemLabel="track" />
					</form:select>
				</div>
				<div class="form-group">
					<spring:message code="addressnif" var="addressnif" />
					<form:input class="form-control" path="address"
						placeholder="${addressnif}" />
					<p class="text-danger">
						<form:errors path="address" />
					</p>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="form-group">
					<spring:message code="town" var="townvar" />
					<form:input class="form-control" path="town"
						placeholder="${townvar}" />
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
				<div class="form-group">
					<spring:message code="cashamount" var="amount" />
					<form:input class="form-control" path="cashamount"
						placeholder="${amount}" />
					<p class="text-danger">
						<form:errors path="cashamount" />
					</p>
				</div>
				<div id="sandbox-container" class="form-group">
					<spring:message code="date" var="pawndate" />
					<form:input class="form-control" type="text" path="creationdate"
						placeholder="${pawndate}" />
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
	</div>
	<!-- fin div row -->
	<div class="table-responsive">
		<table class="table">
			<tbody>
				<c:forEach items="${shopform.objects}" varStatus="status" var="os">
					<tr>
						<td><strong><form:hidden
									path="objects[${status.index}].material.idmaterial" /> <c:out
									value="${os.material.description}" /></strong></td>
						<td><spring:message code="description" var="description" />
							<form:input class="form-control"
								path="objects[${status.index}].description"
								placeholder="${description}" /></td>
						<td><spring:message code="grossgrams" var="grossgrams" /> <form:input
								class="form-control" path="objects[${status.index}].grossgrams"
								placeholder="${grossgrams}" /></td>
						<td><spring:message code="netgrams" var="netgrams" /> <form:input
								class="form-control" path="objects[${status.index}].netgrams"
								placeholder="${netgrams}" /></td>
						<td><spring:message code="amount" var="amountmessage" /> <form:input
								class="form-control" path="objects[${status.index}].amount"
								placeholder="${amountmessage}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</form:form>