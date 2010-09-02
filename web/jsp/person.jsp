<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
<c:choose>	
	<c:when test="${empty person.id}"><h2>Lisää uusi käyttäjä</h2></c:when>
	<c:otherwise><h2>Muokkaa käyttäjää</h2></c:otherwise>
</c:choose>
<br />
<form:form method="POST" action="personController" commandName="person">
<jsp:include page="message_component.jsp"/>
<table border="0" width="400px">
	<tr>
		<td width="33%" align="right">ID:</td>
		<td align="left">
			<c:out value="${person.id}"></c:out>
			<form:hidden path="id"/>
		</td>
		<td colspan="2">
			<br/> 
		</td>
	</tr>
	<tr>
		<td width="17%" align="right">
		<form:errors path="firstname"><span style="color:red"> *</span></form:errors>
		Etunimi:</td>
		<td width="33%" align="left">
			<form:input path="firstname"/>			
		</td>
		<td width="17%" align="right">
		<form:errors path="lastname"><span style="color:red"> *</span></form:errors>
		Sukunimi:</td>
		<td width="33%" align="left">
			<form:input path="lastname"/>			
		</td>
	</tr>			
	<tr>
		<td width="17%" align="right">
		<form:errors path="title"><span style="color:red"> *</span></form:errors>
		Tehtävänimike:</td>
		<td width="33%" align="left">
			<form:input path="title"/>			
		</td>
		<td width="17%" align="right">
		<form:errors path="dateOfBirth"><span style="color:red"> *</span></form:errors> 
		<form:errors path="socialSecuritySuffix"><span style="color:red"> *</span></form:errors>
		Sotu:</td>
		<td width="33%" align="left">
			<form:input path="dateOfBirth" cssStyle="width:50px;"/> -
			<form:input path="socialSecuritySuffix" cssStyle="width:30px;"/>			
		</td>
	</tr>
	<tr>
		<td width="17%" align="right">
		<form:errors path="email"><span style="color:red"> *</span></form:errors>
		Sähköposti:</td>
		<td width="33%" align="left">
			<form:input path="email"/>			
		</td>
		<td width="17%" align="right">
		<form:errors path="phone"><span style="color:red"> *</span></form:errors>Puhelin:</td>
		<td width="33%" align="left">
			<form:input path="phone"/>			
		</td>
	</tr>
	<tr>
		<td align="center" colspan="4" height="10px"/>		 
	</tr>
	<tr>
		<td width="17%" align="right">
		<form:errors path="address"><span style="color:red"> *</span></form:errors>
		Osoite:</td>
		<td width="33%" align="left">
			<form:input path="address"/>
			
		</td>
		<td width="17%" align="right">
		<form:errors path="postalcode"><span style="color:red"> *</span></form:errors>
		Postinumero:</td>
		<td width="33%" align="left">
			<form:input path="postalcode"/>			
		</td>
	</tr>
	<tr>
		<td width="17%" align="right">
		<form:errors path="city"><span style="color:red"> *</span></form:errors>
		Kaupunki:</td>
		<td width="33%" align="left">
			<form:input path="city"/>			
		</td>
		<td width="17%" align="right">
		<form:errors path="country"><span style="color:red"> *</span></form:errors>
		Maa:</td>
		<td width="33%" align="left">
			<form:input path="country"/>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="4" height="10px"/>		 
	</tr>
	<tr>
		<td width="17" align="right">Tila:</td>
		<td align="left" colspan="3">
			Aktiivinen <form:radiobutton path="statusCode" value="A"/> 
			Suljettu <form:radiobutton path="statusCode" value="C"/>			 
		</td>		
	</tr>
	<tr>
		<td width="17%" align="right">Rooli:</td>
		<td align="left" colspan="3">
			<form:radiobutton path="roleCode" value="W"/>Työntekijä 
			<form:radiobutton path="roleCode" value="M"/>Manager 
			<form:radiobutton path="roleCode" value="S"/>Pääkäyttäjä 
		</td>		
	</tr>
	<tr>
		<td align="center" colspan="4">
		<fieldset>
			<legend>Projektit </legend>			
			<form:checkboxes items="${allProjects}" path="projects" />
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