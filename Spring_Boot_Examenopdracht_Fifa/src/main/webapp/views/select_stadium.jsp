<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">

<spring:url value="/css/style.css" var="urlCss"/>
<link rel="STYLESHEET" href="${urlCss}" type="text/css"/>
<title>Stadium selection</title>
</head>
<body>
	<h1>FIFA Word Cup Qatar 2022</h1>
	<form:form method="post" action="/fifa" modelAttribute="chosenStadium" id="stadium-form">
		<label for="chosenStadium">Stadiums:</label>
		<form:select path="value" id="chosenStadium" name="chosenStadium">
			<form:options items="${stadiumList}"/>
		</form:select>
		<button type="submit" class="btn">Execute</button>
	</form:form>
</body>
</html>