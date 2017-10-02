<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item active"><spring:message code="clients" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="clients" />
		</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th><spring:message code="niforcif" /></th>
							<th><spring:message code="name" /></th>
							<th><spring:message code="email" /></th>
							<th><spring:message code="telephone" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${clients}" var="client">
							<tr>
								<td><c:out value="${client.nifclient}" /></td>
								<td><c:out value="${client.nameuser}" /></td>
								<td><c:out value="${client.email}" /></td>
								<td><c:out value="${client.telephone}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>