<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="pawns" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="renovations" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty renovations}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="daterenew" /></th>
								<th><spring:message code="daterenovation" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${renovations}" var="renovation">
								<tr>
									<td><fmt:formatDate
											value="${renovation.nextrenovationdate}" pattern="yyyy-MM-dd" /></td>
									<td><fmt:formatDate value="${renovation.creationdate}"
											pattern="yyyy-MM-dd" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div>${fn:length(renovations)}</div>
			</c:if>
			<c:if test="${empty renovations}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>