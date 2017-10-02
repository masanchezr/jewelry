<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="workshop" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="bill" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty billing }">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="number" /></th>
								<th><spring:message code="concept" /></th>
								<th><spring:message code="amount" /></th>
								<th><spring:message code="date" /></th>
								<th><spring:message code="metal" /></th>
								<th><spring:message code="grams" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${billing}" var="bill">
								<tr>
									<td><c:out value="${bill.idadjustment}" /></td>
									<td><c:out value="${bill.description}" /></td>
									<td><c:out value="${bill.amount}" /></td>
									<td><c:out value="${bill.creationdate}" /></td>
									<td><c:out value="${bill.metal.description}" /></td>
									<td><c:out value="${bill.grams}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group col-3">
							<spring:message code="totalamount" />
							<c:out value="${totalamount}" />
							<i class="fa fa-euro"></i>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty grams }">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="grams" /></th>
								<th><spring:message code="metal" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${grams}" var="item">
								<tr>
									<td><c:out value="${item.grams}" /></td>
									<td><c:out value="${item.metal.description}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<c:if test="${empty billing }">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>