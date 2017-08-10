<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="summary" />
			<spring:message code="shoppings" />
			<c:out value="${shoppingForm.place.description}" />
			<c:out value="${shoppingForm.datefrom}" />
			<c:out value="${shoppingForm.dateuntil}" />
		</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="realgrams" /></th>
								<th><spring:message code="grossgrams" /></th>
								<th><spring:message code="netgrams" /></th>
								<th><spring:message code="amount" /></th>
								<th><spring:message code="metal" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${quarters}" var="quarter">
								<tr>
									<td><c:out value="${quarter.realgrams}" /></td>
									<td><c:out value="${quarter.grossgrams}" /></td>
									<td><c:out value="${quarter.netgrams}" /></td>
									<td><c:out value="${quarter.amount}" /><i
										class="fa fa-euro"></i></td>
									<td><c:out value="${quarter.metal.description}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>