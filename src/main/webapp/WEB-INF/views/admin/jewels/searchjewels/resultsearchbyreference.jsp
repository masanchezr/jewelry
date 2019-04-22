<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="jewels" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="resultsearch" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty jewels}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="id" /></th>
								<th><spring:message code="reference" /></th>
								<th><spring:message code="nameclient" /></th>
								<th><spring:message code="description" /></th>
								<th><spring:message code="price" /></th>
								<th><spring:message code="place" /></th>
								<th><spring:message code="active" /></th>
								<th><spring:message code="category" /></th>
								<th><spring:message code="material" /></th>
								<th><spring:message code="saledate" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${jewels}" var="jewel">
								<tr>
									<td><c:out value="${jewel.idjewel}" /></td>
									<td><c:out value="${jewel.reference}" /></td>
									<td><c:out value="${jewel.name}" /></td>
									<td><c:out value="${jewel.description}" /></td>
									<td><c:out value="${jewel.price}" /></td>
									<td><c:out value="${jewel.place.description}" /></td>
									<td><c:out value="${jewel.active}" /></td>
									<td><c:out value="${jewel.category.namecategory}" /></td>
									<td><c:out value="${jewel.metal.description}" /></td>
									<td><c:out value="${jewel.saledate}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<c:if test="${empty jewels}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>