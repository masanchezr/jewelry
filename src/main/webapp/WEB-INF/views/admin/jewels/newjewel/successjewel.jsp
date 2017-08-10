<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="savedjewel" />
		</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-9 text-right">
						<div class="huge">
							<c:out value="${jewel.category.namecategory}" />
						</div>
						<div>
							<spring:message code="category" />
						</div>
					</div>
				</div>
				<spring:url value="/newJewel" var="newjewels"></spring:url>
				<a href="${newjewels}">
					<div class="panel-footer">
						<span class="pull-left"><spring:message code="newjewels" /></span>
						<span class="pull-right"><i
							class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-euro fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">
							<c:out value="${jewel.price}" />
						</div>
						<div>
							<spring:message code="price" />
						</div>
					</div>
				</div>
				<spring:url value="/searchUpdateJewels" var="updatejewels"></spring:url>
				<a href="${updatejewels}">
					<div class="panel-footer">
						<span class="pull-left"><spring:message code="updatejewels" /></span>
						<span class="pull-right"><i
							class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-yellow">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-9 text-right">
						<div class="huge">
							<spring:message code="nameclient" />
						</div>
						<div>
							<c:out value="${jewel.name}" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-red">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-9 text-right">
						<div class="huge">
							<c:out value="${jewel.place.description}" />
						</div>
						<div>
							<c:out value="${jewel.reference}" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--<spring:url value="/image/" var="urlimg" />
	<img
		src="<c:out value="${urlimg}"/><c:out value="${jewel.reference}" />" />-->
</div>
<!-- /.row -->