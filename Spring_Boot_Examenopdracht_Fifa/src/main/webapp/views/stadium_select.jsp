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
	<form action="" method="post">
		<label for="stadium">Stadiums:</label>
		<select name="stadium" id="stadium">
		<c:forEach items="${stadiumList}" var="stadium">
			<option value="${stadium}">${stadium}</option>
		</c:forEach>
		</select>
		<button type="submit">Execute</button>
	</form>
</body>
</html>