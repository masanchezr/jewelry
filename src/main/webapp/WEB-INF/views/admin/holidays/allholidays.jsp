<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="holidays" />
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
</div>