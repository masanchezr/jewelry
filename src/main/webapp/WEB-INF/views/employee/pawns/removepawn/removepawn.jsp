<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="pawns" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="removepawn" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty pawns}">
				<div class="table-responsive">
					<form:form action="removepawn" modelAttribute="pawnForm">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th></th>
									<th><spring:message code="numpawn" /></th>
									<th><spring:message code="datepawn" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pawns}" var="pawn">
									<tr>
										<td><form:radiobutton path="id" value="${pawn.id}" /></td>
										<td><c:out value="${pawn.numpawn}" /></td>
										<td><c:out value="${pawn.creationdate}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<form:button class="btn btn-primary" value="submit">
							<spring:message code="removepawn" />
						</form:button>
					</form:form>
				</div>
			</c:if>
			<c:if test="${empty pawns}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>