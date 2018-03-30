<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div style="opacity: 1;" class="wrapper">
	<header class="page-header">
		<figure class="responsive"
			data-media240="<spring:url value="/resources/img/web/DDHeaderContact.jpg"/>"
			data-media481="<spring:url value="/resources/img/web/DDHeaderContact.jpg"/>"
			data-media769="<spring:url value="/resources/img/web/DDHeaderContact.jpg"/>"
			data-media1025="<spring:url value="/resources/img/web/DDHeaderContact.jpg"/>"
			data-title="Contacto">
			<img
				src="<spring:url value="/resources/img/web/DDHeaderContact.jpg"/>"
				class="img-responsive">
		</figure>
		<hgroup class="text-center">
			<h1>
				<spring:message code="visitus" />
			</h1>
		</hgroup>
	</header>
	<section class="text-content white">
		<div class="container">
			<header class="row noborder">
				<h3>
					<spring:message code="goldburgos" />
				</h3>
			</header>
			<!--/.row -->
			<div class="row noborder">
				<div class="col-md-12">
					<h4>Explore la elegancia, calidad y personalidad de nuestra
						colección.</h4>
				</div>
				<div class="col-md-4">
					<article class="copy">
						<h3>
							<spring:message code="contact" />
						</h3>
						<p>
							Email: <a href="mailto:numisgoldsl@gmail.com">numisgoldsl@gmail.com</a><br>
							Tel: 987224666
						</p>
						<p>
							<small><spring:message code="contactdetails" /></small>
						</p>
					</article>
				</div>
				<div class="col-md-4">
					<article class="copy">
						<h3>
							<spring:message code="findus" />
						</h3>
						<p>
							Avenida Gran Vía de San Marcos 4<br>24002 León<br> <a
								target="_blank" title="<spring:message code="findus" />"
								href="https://goo.gl/maps/bi35o">Google Map</a>
						</p>
					</article>
				</div>
				<div class="col-md-4">
					<article class="copy">
						<h3>
							<spring:message code="workinghours" />
						</h3>
						<p>
							Lunes - Viernes 10.00 - 21:00<br> Sábados 10.00 - 14.00
						</p>
					</article>
				</div>
			</div>
			<!--/.row -->
		</div>
		<!--/.container -->
	</section>
	<!--/.main-content -->
</div>
<!--/.wrapper -->