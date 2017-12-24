<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="resultsale" modelAttribute="saleForm" role="form">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item active"><spring:message code="newsale" /></li>
	</ol>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table">
				<tbody>
					<tr>
						<td><spring:message code="idsale" var="numsalemessage" /> <form:input
								class="form-control" path="numsale"
								placeholder="${numsalemessage}" /></td>
						<td><form:select class="form-control"
								path="payment.idpayment">
								<form:options items="${payments}" itemValue="idpayment"
									itemLabel="name" />
							</form:select></td>
						<td></td>
					</tr>
					<c:forEach items="${saleForm.jewels}" var="jewel"
						varStatus="status">
						<tr class="${status.count % 2 == 0 ? 'success' : 'danger'}">
							<td><spring:message code="referencejewel" var="referencej" />
								<form:input class="form-control"
									path="jewels[${status.index}].reference"
									placeholder="${referencej} ${status.index}" /></td>
							<td><form:select class="form-control"
									path="jewels[${status.index}].category.idcategory">
									<form:options items="${categories}" itemValue="idcategory"
										itemLabel="namecategory" />
								</form:select></td>
							<td><form:select class="form-control"
									path="jewels[${status.index}].metal.idmetal">
									<form:options items="${metals}" itemValue="idmetal"
										itemLabel="description" />
								</form:select></td>
						</tr>
					</c:forEach>
					<tr>
						<td><spring:message code="discount" var="discountmessage" />
							<form:input class="form-control" path="discount"
								placeholder="${discountmessage}" /></td>
						<td><p class="text-danger">
								<form:errors path="numsale" />
							</p></td>
						<td><form:button class="btn btn-primary" value="submit">
								<spring:message code="save" />
							</form:button></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<div class="form-group">
					<spring:message code="clientdiscount" />
					<form:input class="form-control" path="iddiscount" />
				</div>
				<div class="form-group">
					<spring:message code="severalpayments" />
					<form:input class="form-control" path="optionalpayment" />
				</div>
			</div>
		</div>
	</div>
</form:form>