<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">	
	<c:if test="${not empty message}">
	<table width="400px">
		<tr>
			<td align="center" bgcolor="#BBFFAE">
				<c:out value="${message}" escapeXml="false"/><br/>
			</td>
		</tr>
	</table>
</c:if>
</div>
<jsp:include page="footer.jsp"></jsp:include>