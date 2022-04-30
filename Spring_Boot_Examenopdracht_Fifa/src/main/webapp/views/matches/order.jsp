<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/css/style.css" var="urlCss"/>
<link rel="STYLESHEET" href="${urlCss}" type="text/css"/>
<title>Order</title>
</head>
<body>
	<fmt:parseDate value="${ticket.match.day}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
	
	<h1>FIFA Word Cup Qatar 2022</h1>
	<h2>Stadium:</h2>
	<h3>${ticket.match} on <fmt:formatDate type="date" value="${parsedDate}" pattern="dd-MM" /></h3>
	<h3>Amount of tickets available: ${ticket.tickets}</h3>
	<form:form method="get" modelAttribute="order">
	
	
		<div>
			<label>email:</label>
			<form:input path="email"/>
		</div>
		<div>
			<label>Amount of tickets:</label>
			<form:input path="amount"/>
		</div>
		<div>
			<label>soccerCode1:</label>
			<form:input path="soccerCode1"/>
		</div>
		<div>
			<label>soccerCode2:</label>
			<form:input path="soccerCode2"/>
		</div>
		<input type="submit" value="Buy"/>
	</form:form>
</body>
</html>