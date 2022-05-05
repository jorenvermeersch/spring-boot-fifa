<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Match overview</title>
</head>
<body>
	<h1>FIFA Word Cup Qatar 2022</h1>
	<h2>Stadium: ${chosenStadium.value}</h2>
	<table>
		<tr>
			<th>Nr</th>
			<th>Soccer match</th>
			<th>Date</th>
			<th>Start</th>
			<th>Tickets</th>
		</tr>
		<spring:url value="/fifa/" var="buyTicketsUrl"></spring:url>
		<c:forEach items="${matchList}" var="matchTicket" >
			<!-- LocalDate needs to be parsed before it can be formatted using JSTL -->
			<fmt:parseDate value="${matchTicket.match.day}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
			<tr>
				<td><a href="${buyTicketsUrl}${matchTicket.match.id}">${matchTicket.match.id}</a></td>
				<td>${matchTicket.match.countries[0]}-${matchTicket.match.countries[1]}</td>
				<td><fmt:formatDate type="date" value="${parsedDate}" pattern="dd MMMM" /></td>
				<td>${matchTicket.match.hour}</td>
				<td>${matchTicket.tickets}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>