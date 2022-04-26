<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stadium selection</title>
</head>
<body>
	<h1>FIFA Word Cup Qatar 2022</h1>
	<form:form method="post" action="/fifa" modelAttribute="stadium">
		<label for="chosenStadium">Stadiums:</label>
		<form:select path="value" id="chosenStadium" name="chosenStadium">
			<c:forEach items="${stadiumList}" var="stadium">
				<option value="${stadium}">${stadium}</option>
			</c:forEach>
		</form:select>
		<button type="submit">Execute</button>
	</form:form>
</body>
</html>