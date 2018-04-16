<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title><spring:message code="goldburgos" /></title>
<link
	href="<spring:url value="/resources/styles/web/bootstrap.min.css"/>"
	rel='stylesheet' type='text/css' />
<link href="<spring:url value="/resources/styles/web/bootstrap.css"/>"
	rel='stylesheet' type='text/css' />
<link href="<spring:url value="/resources/styles/web/theme.css"/>"
	rel='stylesheet' type='text/css' />
<link href="<spring:url value="/resources/styles/web/etalage.css"/>"
	rel='stylesheet' type='text/css' />
<link href="<spring:url value="/resources/styles/web/form.css"/>"
	rel='stylesheet' type='text/css' />
<link href="<spring:url value="/resources/styles/web/fwslider.css"/>"
	rel='stylesheet' type='text/css' />
<link href="<spring:url value="/resources/styles/web/jquery-ui.css"/>"
	rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript">
	
								
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
	 function hideURLbar(){ window.scrollTo(0,1); }


</script>

<!----webfonts---->
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800,300'
	rel='stylesheet' type='text/css'>
<!----//webfonts---->
<script type="text/javascript"
	src="<spring:url value="/resources/js/web/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/web/bootstrap.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/web/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/web/jquery-ui.min.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/web/jquery.etalage.min.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/web/css3-mediaqueries.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/web/jquery.flexisel.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/web/cookie-warn.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/web/addressbilling.js"/>"></script>
<link rel="shortcut icon"
	href="<spring:url value="/resources/img/favicon.png"/>"
	type="image/png">
</head>
<body>
	<div id="overbox3">
		<div id="infobox3" class="alert alert-warning">
			<p>
				<spring:message code="cookiealert" />
				<a href="<spring:url value="/terms"/>"><spring:message
						code="moreinformation" /></a><a onclick="aceptar_cookies();"
					style="cursor: pointer;"><spring:message code="close" /></a>
			</p>
		</div>
	</div>
	<!----start-container----->
	<div class="header-bg">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="logo">
						<a href="index.html"><img
							src="<spring:url value="/resources/img/web/logo.png"/>" alt="" /></a>
					</div>
				</div>
				<div class="col-md-8">
					<nav class="navbar navbar-default" role="navigation">
						<div class="container-fluid">
							<!-- Brand and toggle get grouped for better mobile display -->
							<div class="navbar-header">
								<span class="text-left"><a href="#">MENU</a></span>
								<button type="button" class="navbar-toggle"
									data-toggle="collapse"
									data-target="#bs-example-navbar-collapse-1">
									<span class="sr-only">Toggle navigation</span> <span
										class="icon-bar"></span> <span class="icon-bar"></span> <span
										class="icon-bar"></span>
								</button>
							</div>
							<!-- Collect the nav links, forms, and other content for toggling -->
							<div class="collapse navbar-collapse"
								id="bs-example-navbar-collapse-1">
								<ul class="nav navbar-nav">
									<li>
										<div class="btn-group show-on-hover">
											<button type="button" class="btn btn-default dropdown-toggle"
												data-toggle="dropdown">
												<spring:message code="categories" />
												<span class="caret"></span>
											</button>
											<ul class="dropdown-menu" role="menu">
												<c:forEach items="${categories}" var="category">
													<li><a
														href="<spring:url value="${category.keyword}" />"><c:out
																value="${category.namecategory}" /></a></li>
												</c:forEach>
											</ul>
										</div>
									</li>
									<li><a href="<spring:url value="/contacto" />"><spring:message
												code="contact" /></a></li>
								</ul>
							</div>
							<!-- /.navbar-collapse -->
						</div>
						<!-- /.container-fluid -->
					</nav>
					<div class="right">
						<ul class="list-unstyled">
							<li class="a text-left"><a
								href="<spring:url value="/checkout"/>"><span
									class="glyphicon glyphicon-shopping-cart"></span></a>
								${fn:length(cart)}</li>
							<li><a href="<spring:url value="/checkout"/>"><spring:message
										code="checkout" /></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<tiles:insertAttribute name="body" />
	<div class="footer">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="list-inline pull-left">
						<li><a href="<spring:url value="/terms"/>"><spring:message
									code="terms" /></a></li>
						<li><a href="<spring:url value="/faqs"/>"><spring:message
									code="faqs" /></a></li>
						<li><a href="<spring:url value="/contacto" />"><spring:message
									code="contact" /></a></li>
					</ul>
					<form:form action='busqueda' modelAttribute="searchDateForm"
						class="navbar-form pull-right" role="search">
						<div class="form-group col-3">
							<spring:message code="find" var="find" />
							<form:input path="searchname" class="form-control"
								placeholder="${find}" />
						</div>
					</form:form>
				</div>
			</div>
			<div class="copy-right text-center">
				<p>
					<spring:message code="copyright" />
					<a href="www.numisgoldsl.com/"> www.numisgoldsl.com</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>