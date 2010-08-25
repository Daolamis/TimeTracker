<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
	
<h2>Kaikki projektit</h2>
<br />
<table border="0" cellpadding="0" cellspacing="0" width="500px">
	<tr>
		<td bgcolor="#A1A1A1">Nimi</td>
		<td bgcolor=#A1A1A1>Tila</td>		
		<td bgcolor="#A1A1A1">Toiminta</td>		
	</tr>
	<c:forEach items="${projects}" var="project" varStatus="status">
		<c:set var="color" value="#E6ECFF" />	
          <c:if test="${status.count%2==0}">
            <c:set var="color" value="#CDCDCD" />
          </c:if>
    <tr>
    	<td colspan="6" height="2" bgcolor="GRAY"/>
    </tr>
	<tr>
		<td bgcolor="<c:out value="${color}"/>"><c:out value="${project.name}"/></td>		
		<td bgcolor="<c:out value="${color}"/>">
			<c:choose>
				<c:when test="${project.statusCode == 'A'}">Aktiivinen</c:when>				
				<c:otherwise>Suljettu</c:otherwise>			
			</c:choose>		
		</td>		
		<td bgcolor="<c:out value="${color}"/>"><a href="projectController?id=<c:out value="${project.id}"/>">[muokkaa]</a></td>		
	</tr>	
	</c:forEach>	
</table>		
<br/>
<br/>
</div>
<jsp:include page="footer.jsp"></jsp:include>