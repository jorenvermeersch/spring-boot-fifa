<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<spring:message code="title.placeOrder" var="placeOrder"/>
<spring:message code="stadium" var="stadium"/>
<spring:message code="on" var="on"/>

<spring:message code="order.match.ticketsAvailable" var="ticketsAvailable"/>
<spring:message code="order.match.form.email" var="email"/>
<spring:message code="order.match.form.numberOfTickets" var="numberOfTickets"/>
<spring:message code="order.match.form.soccerCode1" var="soccerCode1"/>
<spring:message code="order.match.form.soccerCode2" var="soccerCode2"/>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,400;0,500;0,700;1,400;1,500;1,700&display=swap" rel="stylesheet"> 

<spring:url value="/css/style.css" var="urlCss"/>
<link rel="STYLESHEET" href="${urlCss}" type="text/css"/>
<title>${placeOrder}</title>
</head>
<body>
	<fmt:parseDate value="${matchTicket.match.day}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
	
	<h1>FIFA Word Cup Qatar 2022</h1>
	<h2>${stadium}: ${chosenStadium.name}</h2>
	<h3>${matchTicket.match} ${on} <fmt:formatDate type="date" value="${parsedDate}" pattern="dd-MM" /></h3>
	<h3>${ticketsAvailable}: ${matchTicket.tickets}</h3>
	
	<spring:url value="/fifa" var="placeOrderUrl"></spring:url>
	<form:form method="POST" action="${placeOrderUrl}/${matchTicket.match.id}" modelAttribute="order" id="order-form">
		<div>
			<label for="email">${email}:</label>
			<form:input path="email" id="email"/>
			<form:errors path="email" cssClass="error"/>
		</div>
		<div>
			<label for="amount">${numberOfTickets}:</label>
			<form:input path="amount" id="amount"/>
			<form:errors path="amount" cssClass="error"/>
		</div>
		<div>
			<label for="soccerCode1">${soccerCode1}:</label>
			<form:input path="soccerCode1" id="soccerCode1"/>
			<form:errors path="soccerCode1" cssClass="error"/>
		</div>
		<div>
			<label for="soccerCode2">${soccerCode2}:</label>
			<form:input path="soccerCode2" id="soccerCode2"/>
			<form:errors path="soccerCode2" cssClass="error"/>
		</div>
		<div>
			<input type="submit" value="Buy" class="btn"/>
		</div>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
	</form:form>
</body>
</html>