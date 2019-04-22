<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="jewels" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="resultsearch" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<c:if test="${not empty jewels}">
			<form:form action="updatejewel" modelAttribute="toUpdateForm">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="reference" /></th>
								<th><spring:message code="nameclient" /></th>
								<th><spring:message code="description" /></th>
								<th><spring:message code="price" /></th>
								<th><spring:message code="place" /></th>
								<th><spring:message code="active" /></th>
								<th><spring:message code="category" /></th>
								<th><spring:message code="material" /></th>
								<th><spring:message code="saledate" /></th>
								<th><spring:message code="cost" /></th>
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
									<td><c:out value="${jewel.place.description}" /></td>
									<td><c:out value="${jewel.active}" /></td>
									<td><c:out value="${jewel.category.namecategory}" /></td>
									<td><c:out value="${jewel.metal.description}" /></td>
									<td><c:out value="${jewel.saledate}" /></td>
									<td><c:out value="${jewel.cost}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="form-group">
					<form:button class="btn btn-primary" value="submit">
						<spring:message code="update" />
					</form:button>
				</div>
			</form:form>
		</c:if>
		<c:if test="${empty jewels}">
			<spring:message code="noresults" />
		</c:if>
	</div>
</div>