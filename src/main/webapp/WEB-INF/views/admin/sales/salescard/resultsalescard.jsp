<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="salescard" />
			<c:out value="${numsales}" />
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
				<c:if test="${not empty sales}">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th><spring:message code="idsale" /></th>
									<th><spring:message code="total" /></th>
									<th><spring:message code="place" /></th>
									<th><spring:message code="saledate" /></th>
									<th><spring:message code="discount" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${sales}" var="sale">
									<tr>
										<td><c:out value="${sale.numsale}" /></td>
										<td><c:out value="${sale.total}" /></td>
										<td><c:out value="${sale.place.description}" /></td>
										<td><c:out value="${sale.saledate}" /></td>
										<td><c:out value="${sale.discount}" /></td>
									</tr>
								</c:forEach>
								<c:forEach items="${adjustments}" var="adjustment">
									<tr>
										<td><c:out value="${adjustment.idadjustment}" /></td>
										<td><c:out value="${adjustment.amount}" /></td>
										<td><c:out value="${adjustment.user}" /></td>
										<td></td>
										<td></td>
									</tr>
								</c:forEach>
								<c:forEach items="${recordings}" var="recording">
									<tr>
										<td><c:out value="${recording.numsale}" /></td>
										<td><c:out value="${recording.amount}" /></td>
										<td><c:out value="${recording.place.description}" /></td>
										<td></td>
										<td></td>
									</tr>
								</c:forEach>
								<c:forEach items="${straps}" var="strap">
									<tr>
										<td><c:out value="${strap.numsale}" /></td>
										<td><c:out value="${strap.amount}" /></td>
										<td><c:out value="${strap.place.description}" /></td>
										<td></td>
										<td></td>
									</tr>
								</c:forEach>
								<c:forEach items="${batteries}" var="battery">
									<tr>
										<td><c:out value="${battery.numsale}" /></td>
										<td><c:out value="${battery.amount}" /></td>
										<td><c:out value="${battery.place.description}" /></td>
										<td></td>
										<td></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group">
								<spring:message code="totalamount" />
								<c:out value="${total}" />
								<i class="fa fa-euro"></i>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${empty sales}">
					<spring:message code="noresults" />
				</c:if>
			</div>
		</div>
	</div>
</div>