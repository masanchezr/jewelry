<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="pawns" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="resultsearch" /></li>
</ol>
<div class="form-row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty numberpawns}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="numsale" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${numberpawns}" var="item">
								<tr>
									<td><c:out value="${item}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<c:if test="${empty numberpawns}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>