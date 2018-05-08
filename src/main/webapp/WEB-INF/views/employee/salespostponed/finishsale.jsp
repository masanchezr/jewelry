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
					<c:out value="${saleForm.idsale}" />
					<spring:message code="idsale" />
				</div>
			</div>
			<spring:url value="/employee/newsalepostponed" var="newsale"></spring:url>
			<a class="card-footer text-white clearfix small z-1"
				href="${newsale}"> <span class="float-left"><spring:message
						code="newsalepostponed" /></span> <span class="float-right"><i
					class="nav-link-text"></i></span>
			</a>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-success o-hidden">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-euro-sign fa-fw"></i>
				</div>
				<div class="mr-5">
					<c:out value="${saleForm.total}" />
					<spring:message code="totalamount" />
				</div>
			</div>
			<spring:url value="/employee/daily" var="daily"></spring:url>
			<a class="card-footer text-white clearfix small z-1" href="${daily}">
				<span class="float-left"><spring:message code="daily" /></span> <span
				class="float-right"><i class="nav-link-text"></i></span>
			</a>
		</div>
	</div>
	<c:if test="${saleForm.dateretired==null}">
		<div class="col-xl-3 col-sm-6 mb-3">
			<div class="card text-white bg-warning o-hidden">
				<div class="card-body">
					<div class="card-body-icon">
						<i class="fa fa-fw fa-gift"></i>
					</div>
					<div class="mr-5">
						<spring:message code="timedout" />
						<c:out value="${saleForm.timeout}" />
					</div>
				</div>
			</div>
		</div>
	</c:if>
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
							<th><spring:message code="material" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${saleForm.jewels}" var="jewel">
							<tr>
								<td><c:out value="${jewel.reference}" /></td>
								<td><c:out value="${jewel.name}" /></td>
								<td><c:out value="${jewel.description}" /></td>
								<td><c:out value="${jewel.price}" /><i
									class="fa fa-euro-sign"></i></td>
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
							<th><spring:message code="payment" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${saleForm.spayments}" var="jewel">
							<tr>
								<td><fmt:formatDate value="${jewel.creationdate}"
										type="date" /></td>
								<td><c:out value="${jewel.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td><c:out value="${jewel.pay.name}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>