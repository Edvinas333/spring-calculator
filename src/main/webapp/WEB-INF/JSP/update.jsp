<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title>Skaičiaus atnaujinimas</title>
		<jsp:include page="header.jsp"/>
	</head>
	<body>
		<form:form name="number" action="/updateNumber" method="post">
			<!-- id būtina pateikti formoje, kitaip į back-end nueis null. Todėl darome hidden, kad neredaguotų -->
			<input type="hidden" name="id" value="${number.id}"><p>
			Pirmas skaičius:<br>
			<input type="number" name="sk1" value="${number.sk1}"><p>
			Ženklas:<br>
			<input type="text" name="zenklas" value="${number.zenklas}"><p>
			Antras skaičius:<br>
			<input type="number" name="sk2" value="${number.sk2}"><p>
			Rezultatas:<br>
			<input type="number" name="rezultatas" value="${number.rezultatas}"><p>
			<input type="submit" value="Atnaujinti">
		</form:form>
	</body>
</html>
