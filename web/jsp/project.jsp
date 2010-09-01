<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
<c:choose>	
	<c:when test="${empty project.id}"><h2>Lis‰‰ uusi projekti</h2></c:when>
	<c:otherwise><h2>Muokkaa projektia</h2></c:otherwise>
</c:choose>
<c:if test="${not empty message}">
	<table width="400px">
		<tr>
			<td align="center" bgcolor="#BBFFAE">
				<c:out value="${message}" escapeXml="false"/><br/>
			</td>
		</tr>
	</table>
</c:if> 
<br />
<form:form method="POST" action="projectController" commandName="project">
<jsp:include page="message_component.jsp"/>
<table border="0" width="400px">
	<tr>
		<td width="33%" align="right">ID:</td>
		<td width="67%" align="left">
			<c:out value="${project.id}"></c:out>
			<form:hidden path="id"/>
		</td>		
	</tr>
	<tr>
		<td width="33%" align="right">Nimi:</td>
		<td width="66%" align="left">
			<form:input path="name" size="25"/>
		</td>
	</tr>					
	<tr>
		<td width="33%" align="right">Kuvaus:</td>
		<td width="66%" align="left">
			<form:textarea path="description" rows="5" cols="20"/>
		</td>
	</tr>	
	<tr>
		<td width="33%" align="right">Tila:</td>
		<td width="67%" align="left">
			Aktiivinen <form:radiobutton path="statusCode" value="A"/> 
			Suljettu <form:radiobutton path="statusCode" value="C"/> 
			 
		</td>		
	</tr>
	
	<tr>
		<td align="center" colspan="2">
		<fieldset>
			<legend>Tuntityypit</legend>
			<form:checkboxes items="${allHourTypes}" path="hourtypes" />
		</fieldset>
		</td>		 
	</tr>			
	<tr>
		<td align="center" colspan="4">
			<input type="submit" alignment="center" value="Tallenna"> 
		</td>
	</tr>
</table>		
</form:form>
</div>
<jsp:include page="footer.jsp"></jsp:include>