<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="savecancelparcial" commandName="saleForm" role="form">
	<form:hidden path="idsale" />
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="removeparcialsale" />
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table">
							<tbody>
								<tr>
									<td></td>
									<td></td>
									<td><spring:message code="selectjeweltocancel" /></td>
									<td class="form-group has-error"><label
										class="control-label" for="inputSuccess"> <form:errors
												path="idsale" /></label></td>
									<td></td>
									<td><spring:message code="idsale" /> <form:input
											class="form-control" path="numsale" disabled="true" /></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<c:forEach items="${saleForm.jewels}" var="jewel"
									varStatus="status">
									<tr class="success">
										<td><form:checkbox
												path="jewelstocancel[${status.index}].idjewel"
												value="${jewel.idjewel}" /></td>
										<td><c:out value="${jewel.reference}" /></td>
										<td><c:out value="${jewel.name}" /></td>
										<td><c:out value="${jewel.description}" /></td>
										<td><c:out value="${jewel.price}" /></td>
										<td><c:out value="${jewel.category.namecategory}" /></td>
										<td><c:out value="${jewel.metal.description}" /></td>
										<td><c:out value="${jewel.place.description}" /></td>
										<td><c:out value="${jewel.active}" /></td>
										<td><c:out value="${jewel.saledate}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3">
			<div class="form-group" id="payments">
				<spring:message code="repayment" />
				<form:select class="form-control" path="payment.idpayment"
					id="payments">
					<form:options items="${payments}" itemValue="idpayment"
						itemLabel="name" />
				</form:select>
			</div>
			<div id="discount" class="hidden">
				<div class="form-group">
					<spring:message code="iddiscount" />
					<form:input class="form-control" path="iddiscount" />
				</div>
				<div class="form-group">
					<spring:message code="numsalechange" />
					<form:input class="form-control" path="numsalechange" />
				</div>
			</div>
			<div class="form-group">
				<form:button class="btn btn-success" value="submit">
					<spring:message code="cancelsale" />
				</form:button>
			</div>
		</div>
	</div>
</form:form>