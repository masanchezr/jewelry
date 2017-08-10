<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="col-lg-6">
		<h1 class="page-header">
			<spring:message code="shoppings" />
		</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message code="resultsearch" />
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
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
											<td><form:radiobutton path="idshop"
													value="${shopping.idshop}" /></td>
											<td><c:out value="${shopping.numshop}" /></td>
											<td><c:out value="${shopping.totalamount}" /></td>
											<td><c:out value="${shopping.creationdate}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<form:button class="btn btn-success" value="submit">
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
</div>