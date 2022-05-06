<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<spring:message code="title.login" var="login"/>
<spring:message code="login.header" var="loginHeader"/>
<spring:message code="login.username" var="username"/>
<spring:message code="login.password" var="password"/>

<spring:message code="login.error" var="loginError"/>
<spring:message code="logout.message" var="logoutMessage"/>

<spring:message code="button.login" var="loginButton"/>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,400;0,500;0,700;1,400;1,500;1,700&display=swap" rel="stylesheet"> 

<spring:url value="/css/style.css" var="urlCss"/>
<link rel="STYLESHEET" href="${urlCss}" type="text/css"/>
<title>${login}</title>
</head>
<body onload='document.loginForm.username.focus();'>
	<h1>${loginHeader}</h1>
	<div id="login">
		<form action="login" method="POST">
			 <c:if test="${error==true}">
                <div class="notification error">${loginError}!</div>
            </c:if>    
            <c:if test="${logout==true}">
                <div class="notification info">${logoutMessage}.</div>
            </c:if>
            
			<div>
				<label for="username">${username}:</label> 
				<input type="text" name="username" id="username"> 
			</div>
			<div>
				<label for="password">${password}:</label>
				<input type="password" name="password" id="password"> 
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
			<input type="submit" value="${loginButton}" class="btn"/>
		</form>
	</div>
</body>
</html>