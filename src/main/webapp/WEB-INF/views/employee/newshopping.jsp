<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>
<form:form action="saveShopping" modelAttribute="shoppingForm"
	role="form">
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
						<td><spring:message code="cashamount" var="cashamount" /> <form:input
								class="form-control" path="cashamount"
								placeholder="${cashamount}" /></td>
						<td><spring:message code="description" var="description" />
							<form:input class="form-control" path="description"
								placeholder="${description}" /></td>
					</tr>
					<c:forEach items="${shoppingForm.objects}" var="os"
						varStatus="status" end="4">
						<tr>
							<td><form:hidden
									path="objects[${status.index}].idobjectshop" /> <form:select
									class="form-control"
									path="objects[${status.index}].metal.idmetal">
									<form:option value="${os.metal.idmetal}"
										label="${os.metal.description}" />
									<form:options items="${materials}" itemValue="idmetal"
										itemLabel="description" />
								</form:select></td>
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
				</tbody>
			</table>
		</div>
		<div class="row">
			<div class="col-lg-4">
				<div class="form-group">
					<spring:message code="wiretransfer" var="amount" />
					<form:input class="form-control" path="wiretransfer"
						placeholder="${amount}" />
					<p class="text-danger">
						<form:errors path="wiretransfer" />
					</p>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="form-group">
					<form:button class="btn btn-primary" value="submit">
						<spring:message code="save" />
					</form:button>
				</div>
			</div>
		</div>
	</div>
</form:form>