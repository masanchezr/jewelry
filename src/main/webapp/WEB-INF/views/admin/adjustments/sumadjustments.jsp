<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="adjustments" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="resultsearch" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th><spring:message code="chargedadjusments" /></th>
							<th><spring:message code="totalwork" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><c:out value="${amount}" /></td>
							<td><c:out value="${amountwork}" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>