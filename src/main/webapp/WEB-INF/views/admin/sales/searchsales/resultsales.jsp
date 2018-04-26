<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="sales" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="resultsearch" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
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
								<th><spring:message code="payment" /></th>
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
									<td><c:out value="${sale.payment.name}" /></td>
									<td><c:out value="${sale.discount}" /></td>
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
							<i class="fa fa-euro-sign"></i>
						</div>
						<div class="form-group">
							<spring:message code="cost" />
							<c:out value="${cost}" />
							<i class="fa fa-euro-sign"></i>
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