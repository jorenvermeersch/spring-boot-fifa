<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/css/style.css" var="urlCss"/>
<link rel="STYLESHEET" href="${urlCss}" type="text/css"/>
<title>Match overview</title>
</head>
<body>
	<h1>FIFA Word Cup Qatar 2022</h1>
	<h2>Stadium: ${stadium.value}</h2>
	<table>
		<tr>
			<th>Nr</th>
			<th>Soccer match</th>
			<th>Date</th>
			<th>Start</th>
			<th>Tickets</th>
		</tr>
		<spring:url value="/fifa/" var="buyTicketsUrl"></spring:url>
		<c:forEach items="${matchList}" var="ticket" >
			<tr>
				<td><a href="${buyTicketsUrl}${ticket.match.id}">${ticket.match.id}</a></td>
				<td>${ticket.match.countries[0]}-${ticket.match.countries[1]}</td>
				<td>${ticket.match.day}</td>
				<td>${ticket.match.hour}</td>
				<td>${ticket.tickets}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>