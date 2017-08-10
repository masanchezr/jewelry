<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="sales" />
		</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-shopping-cart fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">
							<c:out value="${numsale}" />
						</div>
						<div>
							<spring:message code="idsale" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>