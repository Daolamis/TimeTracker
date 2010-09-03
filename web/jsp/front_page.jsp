<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
<p>
<c:if test="${not empty message}">
	<div class="message">
		<c:out value="${message}" escapeXml="false"/><br/>
	</div>
</c:if> 
</p>
</div>
<jsp:include page="footer.jsp"></jsp:include>