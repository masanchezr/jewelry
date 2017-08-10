<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="jewels" />
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
				<c:if test="${not empty jewels}">
					<div class="table-responsive">
						<form:form action="revised" commandName="toUpdateForm">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th></th>
										<th><spring:message code="reference" /></th>
										<th><spring:message code="nameclient" /></th>
										<th><spring:message code="description" /></th>
										<th><spring:message code="price" /></th>
										<th><spring:message code="category" /></th>
										<th><spring:message code="metal" /></th>
										<th><spring:message code="place" /></th>
										<th><spring:message code="active" /></th>
										<th><spring:message code="saledate" /></th>
										<th><spring:message code="cost" /></th>
										<th><spring:message code="revised" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${jewels}" var="jewel">
										<tr>
											<td><form:radiobutton path="idjewel"
													value="${jewel.idjewel}" /></td>
											<td><c:out value="${jewel.reference}" /></td>
											<td><c:out value="${jewel.name}" /></td>
											<td><c:out value="${jewel.description}" /></td>
											<td><c:out value="${jewel.price}" /></td>
											<td><c:out value="${jewel.category.namecategory}" /></td>
											<td><c:out value="${jewel.metal.description}" /></td>
											<td><c:out value="${jewel.place.description}" /></td>
											<td><c:out value="${jewel.active}" /></td>
											<td><c:out value="${jewel.saledate}" /></td>
											<td><c:out value="${jewel.cost}" /></td>
											<td><c:out value="${jewel.revised}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<form:button class="btn btn-success" value="submit">
								<spring:message code="revised" />
							</form:button>
						</form:form>
					</div>
				</c:if>
				<c:if test="${empty jewels}">
					<spring:message code="noresults" />
				</c:if>
			</div>
		</div>
	</div>
</div>