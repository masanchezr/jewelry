<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="saveShopping" commandName="shoppingForm" role="form">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item active"><spring:message
				code="newshopping" /></li>
	</ol>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table">
				<tbody>
					<tr>
						<td></td>
						<td><spring:message code="numshop" var="numshopmessage" /> <form:input
								class="form-control" path="numshop"
								placeholder="${numshopmessage}" /></td>
						<td class="form-group has-error"><label class="control-label"
							for="inputSuccess"><form:errors path="numshop" /></label></td>
						<td></td>
						<td><spring:message code="description" var="description" />
							<form:input class="form-control" path="description"
								placeholder="${description}" /></td>
					</tr>
					<c:forEach items="${shoppingForm.objects}" var="os"
						varStatus="status">
						<tr class="${status.count % 2 == 0 ? 'success' : 'danger'}">
							<td><strong><form:hidden
										path="objects[${status.index}].metal.idmetal" /> <c:out
										value="${os.metal.description}" /></strong></td>
							<td><spring:message code="grossgrams" var="grossgrams" /> <form:input
									class="form-control" path="objects[${status.index}].grossgrams"
									placeholder="${grossgrams}" /></td>
							<td><spring:message code="netgrams" var="netgrams" /> <form:input
									class="form-control" path="objects[${status.index}].netgrams"
									placeholder="${netgrams}" /></td>
							<td><spring:message code="amount" var="amount" /> <form:input
									class="form-control" path="objects[${status.index}].amount"
									placeholder="${amount}" /></td>
							<td><spring:message code="description" var="description" />
								<form:input class="form-control"
									path="objects[${status.index}].description"
									placeholder="${description}" /></td>
						</tr>
					</c:forEach>
					<tr>
						<td><form:button class="btn btn-primary" value="submit">
								<spring:message code="save" />
							</form:button></td>
						<td><spring:message code="wiretransfer" /> <form:input
								class="form-control" path="wiretransfer" /></td>
						<td><spring:message code="cashamount" /> <form:input
								class="form-control" path="cashamount" /></td>
						<td class="form-group has-error"><label class="control-label"
							for="inputSuccess"><form:errors path="cashamount" /></label></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</form:form>