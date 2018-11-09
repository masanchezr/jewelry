<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item active"><spring:message code="clients" /></li>
</ol>
<form:form action="showoperations" modelAttribute="clientModel">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<c:if test="${not empty clients}">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th></th>
									<th><spring:message code="niforcif" /></th>
									<th><spring:message code="name" /></th>
									<th><spring:message code="surname" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${clients}" var="client">
									<tr>
										<td><form:radiobutton path="nif" value="${client.nif}" /></td>
										<td><c:out value="${client.nif}" /></td>
										<td><c:out value="${client.name}" /></td>
										<td><c:out value="${client.surname}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div>
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="showoperations" />
							</form:button>
						</div>
					</div>
				</c:if>
				<c:if test="${empty clients}">
					<spring:message code="noresults" />
				</c:if>
			</div>
		</div>
	</div>
</form:form>