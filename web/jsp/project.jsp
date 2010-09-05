<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
<c:choose>	
	<c:when test="${empty project.id}"><h2>Lisää uusi projekti</h2></c:when>
	<c:otherwise><h2>Muokkaa projektia</h2></c:otherwise>
</c:choose>
<br />
<form:form method="POST" action="projectController" commandName="project">
<jsp:include page="message_component.jsp"/>
<table border="0" width="500px">
	<tr>
		<td width="33%" align="right">ID:</td>
		<td width="67%" align="left">
			<c:out value="${project.id}"></c:out>
			<form:hidden path="id"/>
		</td>		
	</tr>
	<tr>
		<td width="33%" align="right">
			<form:errors path="name"><span style="color:red"> *</span></form:errors>
			Nimi:
		</td>
		<td width="66%" align="left">
			<form:input path="name" size="25"/>
		</td>
	</tr>					
	<tr>
		<td width="33%" align="right">
			<form:errors path="description"><span style="color:red"> *</span></form:errors>
			Kuvaus:
		</td>
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
<c:if test="${not empty project.workers}">
	<h2>Projektin työntekijät</h2>
	<table border="0" cellpadding="0" cellspacing="0" width="500px">
		<tr>
			<td bgcolor="#A1A1A1">Etunimi</td>
			<td bgcolor=#A1A1A1>Sukunimi</td>
			<td bgcolor="#A1A1A1">Sähköposti</td>
			<td bgcolor="#A1A1A1">Ryhmä</td>
			<td bgcolor="#A1A1A1">Nimike</td>					
		</tr>
		<c:forEach items="${project.workers}" var="person" varStatus="status">
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
		</tr>	
		</c:forEach>	
	</table>
</c:if>
</div>
<br/><br/>
<jsp:include page="footer.jsp"></jsp:include>