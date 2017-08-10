<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="resultsale" commandName="saleForm" role="form">
	<div class="row">
		<div class="col-lg-8">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="newsale" />
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table">
							<tbody>
								<tr>
									<td></td>
									<td><spring:message code="idsale" var="numsalemessage" />
										<form:input class="form-control" path="numsale"
											placeholder="${numsalemessage}" /></td>
									<td><spring:message code="payment" /> <form:select
											class="form-control" path="payment.idpayment">
											<form:options items="${payments}" itemValue="idpayment"
												itemLabel="name" />
										</form:select></td>
									<td></td>
								</tr>
								<c:forEach items="${saleForm.jewels}" var="jewel"
									varStatus="status">
									<tr class="${status.count % 2 == 0 ? 'success' : 'danger'}">
										<td><strong><spring:message code="jewel" /> <c:out
													value="${status.index}" /></strong></td>
										<td><spring:message code="reference" var="reference" /> <form:input
												class="form-control"
												path="jewels[${status.index}].reference"
												placeholder="${reference}" /></td>
										<td><spring:message code="category" /> <form:select
												class="form-control"
												path="jewels[${status.index}].category.idcategory">
												<form:options items="${categories}" itemValue="idcategory"
													itemLabel="namecategory" />
											</form:select></td>
										<td><spring:message code="metal" /> <form:select
												class="form-control"
												path="jewels[${status.index}].metal.idmetal">
												<form:options items="${metals}" itemValue="idmetal"
													itemLabel="description" />
											</form:select></td>
									</tr>
								</c:forEach>
								<tr>
									<td></td>
									<td><spring:message code="discount" var="discountmessage" />
										<form:input class="form-control" path="discount"
											placeholder="${discountmessage}" /></td>
									<td class="form-group has-error"><label
										class="control-label" for="inputSuccess"> <form:errors
												path="numsale" /></label></td>
									<td><form:button class="btn btn-success" value="submit">
											<spring:message code="save" />
										</form:button></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div id="collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<spring:message code="clientdiscount" />
									<form:input class="form-control" path="iddiscount" />
								</div>
							</div>
						</div>
					</div>
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div id="collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<spring:message code="severalpayments" />
									<form:input class="form-control" path="optionalpayment" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>