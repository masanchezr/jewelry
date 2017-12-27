<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="salespostponed" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="newsale" /></li>
	<li class="breadcrumb-item active"><spring:message
			code="savedsale" /></li>
</ol>
<div class="row">
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-primary o-hidden">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-shopping-cart fa-fw"></i>
				</div>
				<div class="mr-5">
					<c:out value="${sale.idsalepostponed}" />
					<spring:message code="idsale" />
				</div>
			</div>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-success o-hidden">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-euro fa-fw"></i>
				</div>
				<div class="mr-5">
					<c:out value="${sale.totalamount}" />
					<spring:message code="totalamount" />
				</div>
			</div>
			<spring:url value="/dailyplace" var="daily"></spring:url>
			<a class="card-footer text-white clearfix small z-1" href="${daily}">
				<span class="float-left"><spring:message code="daily" /></span> <span
				class="float-right"><i class="fa fa-angle-right"></i></span>
			</a>
		</div>
	</div>
</div>
<!-- /.row -->
<div class="row">
	<div class="card mb3">
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
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sale.jewels}" var="jewel">
							<tr>
								<td><c:out value="${jewel.reference}" /></td>
								<td><c:out value="${jewel.name}" /></td>
								<td><c:out value="${jewel.description}" /></td>
								<td><c:out value="${jewel.price}" /><i class="fa fa-euro"></i></td>
								<td><c:out value="${jewel.category.namecategory}" /></td>
								<td><c:out value="${jewel.metal.description}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- /.row -->
<div class="row">
	<div class="card mb3">
		<div class="card-header">
			<spring:message code="installments" />
		</div>
		<!-- /.card-heading -->
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th><spring:message code="date" /></th>
							<th><spring:message code="amount" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sale.spayments}" var="jewel">
							<tr>
								<td><fmt:formatDate value="${jewel.creationdate}"
										type="date" /></td>
								<td><c:out value="${jewel.amount}" /><i class="fa fa-euro"></i></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>