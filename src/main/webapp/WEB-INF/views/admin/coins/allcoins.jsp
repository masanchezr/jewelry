<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="coins" />
		</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel card-default">
			<div class="card-heading"><spring:message code="resultsearch"/></div>
			<!-- /.card-heading -->
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="description" /></th>
								<th><spring:message code="price" /></th>
								<th><spring:message code="metal" /></th>
								<th><spring:message code="place" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${coins}" var="jewel">
								<tr>
									<td><c:out value="${jewel.description}" /></td>
									<td><c:out value="${jewel.price}" /></td>
									<td><c:out value="${jewel.metal.description}" /></td>
									<td><c:out value="${jewel.place.description}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>