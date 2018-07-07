<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="pawns" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="summary" /></li>
	<li class="breadcrumb-item active"><c:out
			value="${searchDateForm.datefrom}" /></li>
	<li class="breadcrumb-item active"><c:out
			value="${searchDateForm.dateuntil}" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th><spring:message code="realgrams" /></th>
							<th><spring:message code="grossgrams" /></th>
							<th><spring:message code="amount" /></th>
							<th><spring:message code="metal" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><c:out value="${quarter.gramsreal}" /></td>
							<td><c:out value="${quarter.grossgrams}" /></td>
							<td><c:out value="${quarter.amount}" /><i
								class="fa fa-euro-sign"></i></td>
							<td><c:out value="${quarter.metal.description}" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>