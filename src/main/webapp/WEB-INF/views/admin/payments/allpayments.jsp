<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="payments" />
		</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message code="resultsearch" />
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="number" /></th>
								<th><spring:message code="name" /></th>
								<th><spring:message code="active" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${payments}" var="payment">
								<tr>
									<td><c:out value="${payment.idpayment}" /></td>
									<td><c:out value="${payment.name}" /></td>
									<td><c:out value="${payment.active}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>