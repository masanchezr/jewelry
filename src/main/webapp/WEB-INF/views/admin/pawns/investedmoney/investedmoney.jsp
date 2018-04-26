<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-success o-hidden h-100">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-euro-sign"></i>
				</div>
				<div class="mr-5">
					<spring:message code="totalamount" />
					<c:out value="${investedmoney}" />
				</div>
			</div>
		</div>
	</div>
</div>