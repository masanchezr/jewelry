<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="jewels" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="savedjewel" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="savedjewel" />
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-primary o-hidden h-100">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-comments"></i>
				</div>
				<div class="mr-5">
					<c:out value="${jewel.category.namecategory}" />
				</div>
			</div>
			<spring:url value="/newJewel" var="newjewels"></spring:url>
			<a class="card-footer text-white clearfix small z-1"
				href="${newjewels}"> <span class="float-left"><spring:message
						code="newjewel" /></span> <span class="float-right"> <i
					class="fa fa-angle-right"></i>
			</span>
			</a>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-success o-hidden h-100">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-shopping-cart"></i>
				</div>
				<div class="mr-5">
					<c:out value="${jewel.price}" />
				</div>
			</div>
			<spring:url value="/searchUpdateJewels" var="updatejewels"></spring:url>
			<a class="card-footer text-white clearfix small z-1"
				href="${updatejewels}"> <span class="float-left"><spring:message
						code="updatejewels" /></span> <span class="float-right"> <i
					class="fa fa-angle-right"></i>
			</span>
			</a>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-warning o-hidden h-100">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-list"></i>
				</div>
				<div class="mr-5">
					<spring:message code="nameclient" />
					<c:out value="${jewel.name}" />
				</div>
			</div>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-danger o-hidden h-100">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-support"></i>
				</div>
				<div class="mr-5">
					<c:out value="${jewel.place.description}" />
					<c:out value="${jewel.reference}" />
				</div>
			</div>
		</div>
	</div>
	<!--<spring:url value="/image/" var="urlimg" />
	<img
		src="<c:out value="${urlimg}"/><c:out value="${jewel.reference}" />" />-->
</div>
<!-- /.row -->