<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="shoppings" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="updateshopping" /></li>
</ol>
<form:form action="saveShopping" commandName="shoppingForm" role="form">
	<form:hidden path="id" />
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="table-responsive">
					<table class="table">
						<tbody>
							<tr>
								<td><spring:message code="numshop" /> <form:input
										class="form-control" path="numshop" disabled="true" /></td>
								<td class="form-group has-error"><label
									class="control-label" for="inputSuccess"><form:errors
											path="numshop" /></label></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<c:forEach items="${shoppingForm.objects}" var="os"
								varStatus="status">
								<tr class="${status.count % 2 == 0 ? 'success' : 'danger'}">
									<td><strong><spring:message code="metal" /></strong> <form:hidden
											path="objects[${status.index}].idobjectshop" /> <form:select
											class="form-control"
											path="objects[${status.index}].metal.idmetal">
											<form:option value="${os.metal.idmetal}"
												label="${os.metal.description}" />
											<form:options items="${metals}" itemValue="idmetal"
												itemLabel="description" />
										</form:select></td>
									<td><spring:message code="grossgrams" /> <form:input
											class="form-control"
											path="objects[${status.index}].grossgrams" /></td>
									<td><spring:message code="netgrams" /> <form:input
											class="form-control" path="objects[${status.index}].netgrams"
											disabled="true" /></td>
									<td><spring:message code="realgrams" /> <form:input
											class="form-control"
											path="objects[${status.index}].realgrams" /></td>
									<td><spring:message code="amount" /> <form:input
											class="form-control" path="objects[${status.index}].amount" /></td>
								</tr>
							</c:forEach>
							<tr>
								<td></td>
								<td><spring:message code="amount" /> <form:input
										class="form-control" disabled="true" path="amount" /></td>
								<td class="form-group has-error"><label
									class="control-label" for="inputSuccess"><form:errors
											path="amount" /></label></td>
								<td><form:button class="btn btn-primary" value="submit">
										<spring:message code="save" />
									</form:button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</form:form>