<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="pawns" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="resultsearch" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<!-- /.card-heading -->
		<div class="card-body">
			<c:if test="${not empty pawns}">
				<div class="table-responsive">
					<form:form action="updatepawn" commandName="pawnForm">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th></th>
									<th><spring:message code="numpawn" /></th>
									<th><spring:message code="percent" /></th>
									<th><spring:message code="amount" /></th>
									<th><spring:message code="datepawn" /></th>
									<th><spring:message code="retired" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pawns}" var="pawn">
									<tr>
										<td><form:radiobutton path="id"
												value="${pawn.id}" /></td>
										<td><c:out value="${pawn.numpawn}" /></td>
										<td><c:out value="${pawn.percent}" /></td>
										<td><c:out value="${pawn.amount}" /></td>
										<td><c:out value="${pawn.creationdate}" /></td>
										<td><fmt:formatDate value="${pawn.dateretired}"
												pattern="yyyy-MM-dd" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<form:button class="btn btn-primary" value="submit">
							<spring:message code="update" />
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