<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form method="POST" enctype="multipart/form-data"
	action="upload?${_csrf.parameterName}=${_csrf.token}">
	<spring:message code="fileupload" />
	<input class="btn btn-primary" type="file" name="file"><br />
	<spring:message code="namefile" />
	<input type="text" name="name"><br /> <br /> <input
		class="btn btn-primary" type="submit" value="UPLOAD"> <label><c:out
			value="${message}" /></label>
</form>