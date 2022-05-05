<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">

<spring:url value="/css/style.css" var="urlCss"/>
<link rel="STYLESHEET" href="${urlCss}" type="text/css"/>
<title>Order</title>
</head>
<body>
	<fmt:parseDate value="${matchTicket.match.day}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
	
	<h1>FIFA Word Cup Qatar 2022</h1>
	<h2>Stadium: ${chosenStadium.value}</h2>
	<h3>${matchTicket.match} on <fmt:formatDate type="date" value="${parsedDate}" pattern="dd-MM" /></h3>
	<h3>Amount of tickets available: ${matchTicket.tickets}</h3>
	<form:form method="POST" action="/fifa/order" modelAttribute="order" id="order-form">
		<div>
			<label for="email">email:</label>
			<form:input path="email" id="email"/>
			<form:errors path="email" />
		</div>
		<div>
			<label for="amount">Amount of tickets:</label>
			<form:input path="amount" id="amount"/>
			<form:errors path="amount" />
		</div>
		<div>
			<label for="soccerCode1">soccerCode1:</label>
			<form:input path="soccerCode1" id="soccerCode1"/>
			<form:errors path="soccerCode1" />
		</div>
		<div>
			<label for="soccerCode2">soccerCode2:</label>
			<form:input path="soccerCode2" id="soccerCode2"/>
			<form:errors path="soccerCode2" />
		</div>
		<div>
			<input type="submit" value="Buy" class="btn"/>
		</div>
		
	</form:form>
</body>
</html>