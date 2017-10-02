<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="shoppings" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="resultsearch" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty nummissing}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="numshop" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${nummissing}" var="item">
								<tr>
									<td><c:out value="${item}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<c:if test="${empty nummissing}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>