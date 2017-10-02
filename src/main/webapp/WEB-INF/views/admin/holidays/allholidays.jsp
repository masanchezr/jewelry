<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="holidays" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="resultsearch" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty holidays}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="date" /></th>
								<th><spring:message code="place" /></th>
								<th><spring:message code="description" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${holidays}" var="holiday">
								<tr>
									<td><c:out value="${holiday.place.description}" /></td>
									<td><c:out value="${holiday.holiday}" /></td>
									<td><c:out value="${holiday.description}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<c:if test="${empty holidays}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>