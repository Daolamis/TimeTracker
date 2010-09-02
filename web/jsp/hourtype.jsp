<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
<c:choose>	
	<c:when test="${empty hourtype.id}"><h2>Lis‰‰ uusi tuntityyppi</h2></c:when>
	<c:otherwise><h2>Muokkaa tuntityyppi√§</h2></c:otherwise>
</c:choose>
 
<br />
<form:form method="POST" action="hourTypeController" commandName="hourtype">
<jsp:include page="message_component.jsp"/>
<table border="0" width="400px">
	<tr>
		<td width="33%" align="right">ID:</td>
		<td width="67%" align="left">
			<c:out value="${hourtype.id}"></c:out>
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
		<td width="33%" align="right">
			<form:errors path="branchOfActivity"><span style="color:red"> *</span></form:errors>
			Toimiala:
		</td>
		<td width="66%" align="left">
			<form:input path="branchOfActivity" size="25"/>
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