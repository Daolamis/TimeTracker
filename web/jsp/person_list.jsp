<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
	
<h2>Kaikki käyttäjät</h2>
<br />
<table border="0" cellpadding="0" cellspacing="0" width="500px">
	<tr>
		<td bgcolor="#A1A1A1">Etunimi</td>
		<td bgcolor=#A1A1A1>Sukunimi</td>
		<td bgcolor="#A1A1A1">Sähköposti</td>
		<td bgcolor="#A1A1A1">Ryhmä</td>
		<td bgcolor="#A1A1A1">Nimike</td>
		<td bgcolor="#A1A1A1">Toiminta</td>		
	</tr>
	<c:forEach items="${persons}" var="person" varStatus="status">
		<c:set var="color" value="#E6ECFF" />	
          <c:if test="${status.count%2==0}">
            <c:set var="color" value="#CDCDCD" />
          </c:if>
    <tr>
    	<td colspan="6" height="2" bgcolor="GRAY"/>
    </tr>
	<tr>
		<td bgcolor="<c:out value="${color}"/>"><c:out value="${person.firstname}"/></td>
		<td bgcolor="<c:out value="${color}"/>"><c:out value="${person.lastname}"/></td>
		<td bgcolor="<c:out value="${color}"/>"><c:out value="${person.email}"/></td>
		<td bgcolor="<c:out value="${color}"/>">
			<c:choose>
				<c:when test="${person.roleCode == 'S'}">Pääkäyttäjä</c:when>
				<c:when test="${person.roleCode == 'M'}">Manager</c:when>
				<c:otherwise>Työntekijä</c:otherwise>			
			</c:choose>		
		</td>
		<td bgcolor="<c:out value="${color}"/>"><c:out value="${person.title}"></c:out> </td>
		<td bgcolor="<c:out value="${color}"/>"><a href="personController?id=<c:out value="${person.id}"/>">[muokkaa]</a></td>		
	</tr>	
	</c:forEach>	
</table>		
<br/>
<br/>
</div>
<jsp:include page="footer.jsp"></jsp:include>