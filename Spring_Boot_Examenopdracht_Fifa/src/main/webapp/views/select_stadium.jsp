<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<spring:message code="title.stadiums" var="stadiums"/>
<spring:message code="stadiums" var="stadiums"/>
<spring:message code="button.execute" var="execute"/>

<spring:message code="confirmation.ticket.purchased" var="ticketPurchased"/>
<spring:message code="confirmation.tickets.purchased" var="ticketsPurchased"/>
<spring:message code="confirmation.ticket.soldout" var="ticketsSoldOut"/>



<spring:message code="button.logout" var="logoutButton"/>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,400;0,500;0,700;1,400;1,500;1,700&display=swap" rel="stylesheet"> 

<spring:url value="/css/style.css" var="urlCss"/>
<link rel="STYLESHEET" href="${urlCss}" type="text/css"/>
<title>${stadiums}</title>
</head>
<body>
	<form action="logout" method="POST">
		<input type="submit" class="btn-logout" value="${logoutButton}"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
		<c:if test="${param.verkocht!=null}">
		<div class="notification">${param.verkocht} ${param.verkocht == 1 ? ticketPurchased : ticketsPurchased}.</div>
	</c:if>
	<c:if test="${param.uitverkocht==true}">
		<div class="notification error">${ticketsSoldOut}.</div>
	</c:if>
	
	<h1>FIFA Word Cup Qatar 2022</h1>
	<form:form method="POST" action="/fifa" modelAttribute="chosenStadium" id="stadium-form">
		<label for="chosenStadium">${stadiums}:</label>
		<form:select path="name" id="chosenStadium" name="chosenStadium">
			<form:options items="${stadiumList}"/>
		</form:select>
		<input type="submit" class="btn" value="${execute}"/>
	</form:form>
</body>
</html>