<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="shoppings" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="resultsearch" /></li>
</ol>
<div class="row">
	<div class="col-lg-6">
		<div class="card-body">
			<c:if test="${not empty shoppings}">
				<div class="table-responsive">
					<form:form action="updateShoppings" commandName="shoppingForm">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th></th>
									<th><spring:message code="numshop" /></th>
									<th><spring:message code="totalamount" /></th>
									<th><spring:message code="date" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${shoppings}" var="shopping">
									<tr>
										<td><form:radiobutton path="id" value="${shopping.id}" /></td>
										<td><c:out value="${shopping.numshop}" /></td>
										<td><c:out value="${shopping.amount}" /></td>
										<td><c:out value="${shopping.creationdate}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<form:button class="btn btn-primary" value="submit">
							<spring:message code="update" />
						</form:button>
					</form:form>
				</div>
			</c:if>
			<c:if test="${empty shoppings}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>