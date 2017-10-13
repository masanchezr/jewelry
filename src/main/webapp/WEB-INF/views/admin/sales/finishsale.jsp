<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="sales" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="resultsale" /></li>
</ol>
<div class="row">
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-primary o-hidden">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-comments"></i>
				</div>
				<div class="mr-5">
					<c:out value="${sale.numsale}" />
					<spring:message code="idsale" />
				</div>
			</div>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-success o-hidden">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-shopping-cart"></i>
				</div>
				<div class="mr-5">
					<c:out value="${sale.total}" />
					<spring:message code="totalamount" />
				</div>
			</div>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-warning o-hidden">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-gift"></i>
				</div>
				<div class="mr-5">
					<spring:message code="discount" />
					<c:out value="${sale.discount}" />
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /.row -->
<div class="card mb-3">
	<div class="card-header">
		<spring:message code="jewelrysold" />
	</div>
	<!-- /.card-heading -->
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-striped table-bordered table-hover"
				id="dataTables-example">
				<thead>
					<tr>
						<th><spring:message code="reference" /></th>
						<th><spring:message code="nameclient" /></th>
						<th><spring:message code="description" /></th>
						<th><spring:message code="price" /></th>
						<th><spring:message code="category" /></th>
						<th><spring:message code="metal" /></th>
						<th><spring:message code="place" /></th>
						<th><spring:message code="cost" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sale.jewels}" var="jewel">
						<tr>
							<td><c:out value="${jewel.reference}" /></td>
							<td><c:out value="${jewel.name}" /></td>
							<td><c:out value="${jewel.description}" /></td>
							<td><c:out value="${jewel.price}" /></td>
							<td><c:out value="${jewel.category.namecategory}" /></td>
							<td><c:out value="${jewel.metal.description}" /></td>
							<td><c:out value="${jewel.place.description}" /></td>
							<td><c:out value="${jewel.cost}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="list-group">
			<a href="#" class="list-group-item"><spring:message
					code="principalpay" /><span class="pull-right text-muted small"><em><c:out
							value="${sale.payment.name}" /></em> </span> </a>
		</div>
		<div class="list-group">
			<a href="#" class="list-group-item"><spring:message
					code="optionalpayment" /><span class="pull-right text-muted small"><em><c:out
							value="${sale.optionalpayment}" /></em> </span> </a>
		</div>
	</div>
</div>