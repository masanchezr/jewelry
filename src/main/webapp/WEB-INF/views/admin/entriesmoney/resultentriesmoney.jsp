<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="entriesmoney" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="resultsearch" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty entries}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="amount" /></th>
								<th><spring:message code="place" /></th>
								<th><spring:message code="date" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${entries}" var="entry">
								<tr>
									<td><c:out value="${entry.amount}" /></td>
									<td><c:out value="${entry.place.description}" /></td>
									<td><c:out value="${entry.creationdate}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<c:if test="${empty entries}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>