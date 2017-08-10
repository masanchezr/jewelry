<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="pawns" />
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
				<c:if test="${not empty pawns}">
					<div class="table-responsive">
						<form:form action="removepawn" commandName="pawnForm">
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
											<td><form:radiobutton path="idpawn"
													value="${pawn.idpawn}" /></td>
											<td><c:out value="${pawn.numpawn}" /></td>
											<td><c:out value="${pawn.creationdate}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<form:button class="btn btn-success" value="submit">
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
</div>