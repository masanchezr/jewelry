<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="sales" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="newsale" /></li>
</ol>
<form:form action="resultsale" modelAttribute="saleForm" role="form">
	<div class="card-body">
		<div class="table-responsive">
			<table class="table">
				<tbody>
					<tr>
						<td><spring:message code="numberofsale" var="numsalemessage" />
							<form:input class="form-control" path="numsale"
								placeholder="${numsalemessage}" /></td>
						<td><form:select class="form-control"
								path="payment.idpayment">
								<form:options items="${payments}" itemValue="idpayment"
									itemLabel="name" />
							</form:select></td>
						<td><form:select class="form-control" path="place.idplace">
								<form:options items="${places}" itemValue="idplace"
									itemLabel="description" />
							</form:select></td>
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
									<form:options items="${materials}" itemValue="idmetal"
										itemLabel="description" />
								</form:select></td>
						</tr>
					</c:forEach>
					<tr>
						<td><spring:message code="saledate" var="datemessage" />
							<div id="sandbox-container">
								<form:input class="form-control" type="text" path="saledate"
									placeholder="${datemessage}" autocomplete="off" />
							</div></td>
						<td><spring:message code="discount" var="discountmessage" />
							<form:input class="form-control" path="discount"
								placeholder="${discountmessage}" /></td>
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