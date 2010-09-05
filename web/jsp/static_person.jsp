<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
Käyttäjän tiedot
<br />
<table border="0" width="500px">
	<tr>
		<td width="33%" align="right">Rooli:</td>
		<td align="left">
			<c:choose>
				<c:when test="${person.roleCode == 'S'}">Pääkäyttäjä</c:when>
				<c:when test="${person.roleCode == 'M'}">Manager</c:when>
				<c:otherwise>Työntekijä</c:otherwise>			
			</c:choose>			
		</td>
		<td colspan="2">
			<br/> 
		</td>
	</tr>
	<tr>
		<td width="17%" align="right">Etunimi:</td>
		<td width="33%" align="left">
			<c:out value="${person.firstname}"></c:out>						
		</td>
		<td width="17%" align="right">Sukunimi:</td>
		<td width="33%" align="left">
			<c:out value="${person.lastname}"></c:out>			
		</td>
	</tr>			
	<tr>
		<td width="17%" align="right">Tehtävänimike:</td>
		<td width="33%" align="left">
			<c:out value="${person.title}"></c:out>				
		</td>
		<td width="17%" align="right">Sotu:</td>
		<td width="33%" align="left">
			<fmt:formatDate value="${person.dateOfBirth}" pattern="ddMMyy"/>-<c:out value="${person.socialSecuritySuffix}"></c:out>						
		</td>
	</tr>
	<tr>
		<td width="17%" align="right">Sähköposti:</td>
		<td width="33%" align="left">
			<c:out value="${person.email}"></c:out>						
		</td>
		<td width="17%" align="right">puhelin:</td>
		<td width="33%" align="left">
			<c:out value="${person.phone}"></c:out>				
		</td>
	</tr>
	<tr>
		<td align="center" colspan="4" height="10px"/>		 
	</tr>
	<tr>
		<td width="17%" align="right">Osoite:</td>
		<td width="33%" align="left">
			<c:out value="${person.address}"></c:out>			
		</td>
		<td width="17%" align="right">Postinumero:</td>
		<td width="33%" align="left">
			<c:out value="${person.postalcode}"></c:out>			
		</td>
	</tr>
	<tr>
		<td width="17%" align="right">Kaupunki:</td>
		<td width="33%" align="left">
			<c:out value="${person.city}"></c:out>				
		</td>
		<td width="17%" align="right">Maa:</td>
		<td width="33%" align="left">
			<c:out value="${person.country}"></c:out>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<c:if test="${not empty workHours}">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
			    	<td colspan="4">Työtunnit </td>
			    </tr>
				<tr>
					<td bgcolor="#A1A1A1">Pvm</td>
					<td bgcolor=#A1A1A1>Projekti</td>		
					<td bgcolor="#A1A1A1">Tuntityyppi</td>
					<td bgcolor=#A1A1A1>Määrä</td>	
					
				</tr>
				
				<c:forEach items="${workHours}" var="workhour" varStatus="status">
					<c:set var="color" value="#E6ECFF" />	
			          <c:if test="${status.count%2==0}">
			            <c:set var="color" value="#CDCDCD" />
			          </c:if>
			    <tr>
			    	<td colspan="5" height="2" bgcolor="GRAY"/>
			    </tr>
				<tr>
					<td bgcolor="${color}"><fmt:formatDate value="${workhour.workDate}" pattern="dd.MM.yyyy"/></td>		
					<td bgcolor="${color}">${projects[workhour.projectId].name}</td>				
					<td bgcolor="${color}">${hourTypes[workhour.hourTypeId].name}</td>		
					<td bgcolor="${color}">${workhour.amount}</td>							
				</tr>	
				</c:forEach>
			</table>
			</c:if>
		</td>
	</tr>	
</table>
</div>
<br/>
<jsp:include page="footer.jsp"></jsp:include>