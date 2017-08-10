<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="stocksets" />
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
					<table class="table table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="reference" /></th>
								<th><spring:message code="description" /></th>
								<th><spring:message code="price" /></th>
								<th><spring:message code="category" /></th>
								<th><spring:message code="metal" /></th>
								<th><spring:message code="place" /></th>
								<th><spring:message code="active" /></th>
								<th><spring:message code="saledate" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sets}" var="set">
								<tr class="success">
									<td><spring:message code="set" /></td>
									<td><spring:message code="num" /></td>
									<td><c:out value="${set.idset}" /></td>
								</tr>
								<c:forEach items="${set.jewels}" var="jewel">
									<tr>
										<td><c:out value="${jewel.reference}" /></td>
										<td><c:out value="${jewel.description}" /></td>
										<td><c:out value="${jewel.price}" /></td>
										<td><c:out value="${jewel.category.namecategory}" /></td>
										<td><c:out value="${jewel.metal.description}" /></td>
										<td><c:out value="${jewel.place.description}" /></td>
										<td><c:out value="${jewel.active}" /></td>
										<td><c:out value="${jewel.saledate}" /></td>
									</tr>
								</c:forEach>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>