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
			<c:if test="${not empty resultexpired}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="id" /></th>
								<th><spring:message code="price" /></th>
								<th><spring:message code="creationdate" /></th>
								<th><spring:message code="deadline" /></th>
								<th><spring:message code="place" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${resultexpired}" var="sp">
								<tr>
									<td><c:out value="${sp.idsalepostponed}" /></td>
									<td><c:out value="${sp.totalamount}" /></td>
									<td><c:out value="${sp.creationdate}" /></td>
									<td><c:out value="${sp.deadline}" /></td>
									<td><c:out value="${jewel.place.description}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<c:if test="${empty resultexpired}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>