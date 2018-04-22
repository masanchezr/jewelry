<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="salespostponed" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="salespostponedtimeout" /></li>
</ol>
<c:if test="${not empty salespostponed}">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="number" /></th>
								<th><spring:message code="totalamount" /></th>
								<th><spring:message code="creationdate" /></th>
								<th><spring:message code="deadline" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${salespostponed}" var="jewel">
								<tr>
									<td><c:out value="${jewel.idsalepostponed}" /></td>
									<td><c:out value="${jewel.totalamount}" /></td>
									<td><c:out value="${jewel.creationdate}" /></td>
									<td><c:out value="${jewel.deadline}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${empty salespostponed}">
	<spring:message code="noresults" />
</c:if>