<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
<c:choose>	
	<c:when test="${empty person.id}"><h2>Lisää uusi käyttäjä</h2></c:when>
	<c:otherwise><h2>Muokkaa käyttäjää</h2></c:otherwise>
</c:choose>
<c:if test="${not empty message}">
	<table>
		<tr>
			<td bgcolor="#BBFFAE">
				<c:out value="${message}" escapeXml="false"/><br/>
			</td>
		</tr>
	</table>
</c:if> 
<br />
<form:form method="POST" action="personController" commandName="person">
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
		<td width="17%" align="right">Etunimi:</td>
		<td width="33%" align="left">
			<form:input path="firstname"/>
		</td>
		<td width="17%" align="right">Sukunimi:</td>
		<td width="33%" align="left">
			<form:input path="lastname"/>
		</td>
	</tr>			
	<tr>
		<td width="17%" align="right">Tehtävänimike:</td>
		<td width="33%" align="left">
			<form:input path="title"/>
		</td>
		<td width="17%" align="right">Sotu:</td>
		<td width="33%" align="left">
			<form:input path="socialSecuritySuffix"/>
		</td>
	</tr>
	<tr>
		<td width="17%" align="right">Sähköposti:</td>
		<td width="33%" align="left">
			<form:input path="email"/>
		</td>
		<td width="17%" align="right">Puhelin:</td>
		<td width="33%" align="left">
			<form:input path="phone"/>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="4" height="10px"/>		 
	</tr>
	<tr>
		<td width="17%" align="right">Osoite:</td>
		<td width="33%" align="left">
			<form:input path="address"/>
		</td>
		<td width="17%" align="right">Postinumero:</td>
		<td width="33%" align="left">
			<form:input path="postalcode"/>
		</td>
	</tr>
	<tr>
		<td width="17%" align="right">Kaupunki:</td>
		<td width="33%" align="left">
			<form:input path="city"/>
		</td>
		<td width="17%" align="right">Maa:</td>
		<td width="33%" align="left">
			<form:input path="country"/>
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