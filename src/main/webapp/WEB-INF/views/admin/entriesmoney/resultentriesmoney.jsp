<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="entriesmoney" />
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
</div>