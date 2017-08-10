<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="savedsale" />
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-shopping-cart fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">
							<c:out value="${sale.numsale}" />
						</div>
						<div>
							<spring:message code="idsale" />
						</div>
					</div>
				</div>
				<spring:url value="/employee/newsale" var="newsale"></spring:url>
				<a href="${newsale}">
					<div class="panel-footer">
						<span class="pull-left"><spring:message code="newsale" /></span>
						<span class="pull-right"><i
							class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-euro fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">
							<c:out value="${sale.total}" />
						</div>
						<div>
							<spring:message code="totalamount" />
						</div>
					</div>
				</div>
				<spring:url value="/employee/daily" var="daily"></spring:url>
				<a href="${daily}">
					<div class="panel-footer">
						<span class="pull-left"><spring:message code="daily" /></span>
						<span class="pull-right"><i
							class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-yellow">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-gift fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">
							<c:out value="${sale.discount}" />
						</div>
						<div>
							<spring:message code="discount" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message code="jewelrysold" />
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
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
									<td><c:out value="${jewel.price}" /></td>
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
</div>