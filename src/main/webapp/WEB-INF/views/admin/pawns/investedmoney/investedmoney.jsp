<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-euro fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">
							<c:out value="${investedmoney}" />
						</div>
						<div>
							<spring:message code="totalamount" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>