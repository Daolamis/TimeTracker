<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${not empty message}">
	<div class="message">
		<c:out value="${message}" escapeXml="false"/><br/>
	</div>
</c:if> 

<form:errors path="*">
	<div class="errors">
		<p>Syötteessä oli virheitä</p>
		<c:forEach items="${messages}" var="message">
			<div class="error">${message}</div>
		</c:forEach>
	</div>
</form:errors>