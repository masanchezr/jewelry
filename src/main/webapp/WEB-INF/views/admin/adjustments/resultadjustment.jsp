<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="adjustments" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="adjustment" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th><spring:message code="id" /></th>
							<th><spring:message code="grams" /></th>
							<th><spring:message code="recommendedprice" /></th>
							<th><spring:message code="description" /></th>
							<th><spring:message code="price" /></th>
							<th><spring:message code="amountwork" /></th>
							<th><spring:message code="payment" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><c:out value="${adjustment.idadjustment}" /></td>
							<td><c:out value="${adjustment.grams}" /></td>
							<td><c:out value="${adjustment.recommendedprice}" /></td>
							<td><c:out value="${adjustment.description}" /></td>
							<td><c:out value="${adjustment.amount}" /></td>
							<td><c:out value="${adjustment.amountwork}" /></td>
							<td><c:out value="${adjustment.payment.name}" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>