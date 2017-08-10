<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="stock" />
		</h1>
	</div>
</div>
<form:form action="updateJewels" commandName="toUpdate">
	<c:if test="${not empty results}">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="selectforedit" />
					</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th><spring:message code="select" /></th>
										<th><spring:message code="reference" /></th>
										<th><spring:message code="description" /></th>
										<th><spring:message code="price" /></th>
										<th><spring:message code="active" /></th>
										<th><spring:message code="image" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${results}" var="jewel">
										<tr>
											<td><form:radiobutton path="idjewel"
													value="${jewel.idjewel}" /></td>
											<td><c:out value="${jewel.reference}" /></td>
											<td><c:out value="${jewel.description}" /></td>
											<td><c:out value="${jewel.price}" /></td>
											<td><c:out value="${jewel.active}"></c:out></td>
											<td><c:out value="${jewel.img}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div>
								<form:button class="btn btn-success" value="submit">
									<spring:message code="edit" />
								</form:button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<div>
		<c:if test="${empty results}">
			<spring:message code="noresults" />
		</c:if>
	</div>
</form:form>