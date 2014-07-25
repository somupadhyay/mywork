<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p> Connection Status ${constatus}</p>
<c:forEach items="${adaptors}" var="adapter">
	<c:if test="${adapter.key != 'shared'}" >
		<c:if test="${adapter.value.status =='true' }">
			${adapter.value.id},${adapter.value.name}
		</c:if>
	</c:if>
</c:forEach>
<form action="/security/validateJndi" method="post" name="validator">
	<input name="jndiName" type="text">
	<input type="submit" value="check">
</form>

</body>
</html>
