<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
<form:form method="POST" action="timetrackController" commandName="workhour">
<jsp:include page="message_component.jsp"/>
<fieldset>
	<legend>Lisää työtunti</legend>
	<table border="0" width="400px">	
		<tr>
			<td>			
				<form:select path="projectId" multiple="false">						
					<form:option value="-">Projekti</form:option>
					<c:forEach items="${sessionScope.loginData.projects}" var="projectId">
						<c:set var="project" value="${projects[projectId]}"/>
						<form:option value="${projectId}">${project.name}</form:option>
					</c:forEach>
				</form:select>
			</td>		
			<td>
				<form:select path="hourTypeId" multiple="false">						
					<form:option value="-">Tuntityyppi</form:option>
					<c:forEach items="${hourTypes}" var="mapEntry">
						<form:option value="${mapEntry.key}">${mapEntry.value.name}</form:option>
					</c:forEach>
				</form:select>
			</td>
			<td>			
				<form:input path="workDate"/>
			</td>
		
			<td>			
				<form:input path="amount"/>
			</td>				
		
			<td>
				<input type="submit" value="Lisää tunti"> 
			</td>
		</tr>
	</table>
</fieldset>		
</form:form>
<br />	
<h2>Työtunnit</h2>

<c:if test="${not empty workHours}">
	<table border="0" cellpadding="0" cellspacing="0" width="500px">
		<tr>
			<td bgcolor="#A1A1A1">Pvm</td>
			<td bgcolor=#A1A1A1>Projekti</td>		
			<td bgcolor="#A1A1A1">Tuntityyppi</td>		
			<td bgcolor="#A1A1A1">Ylityö</td>
			<td bgcolor=#A1A1A1>Määrä</td>		
			<td bgcolor="#A1A1A1">Toiminta</td>
		</tr>
		<c:forEach items="${workHours}" var="workhour" varStatus="status">
			<c:set var="color" value="#E6ECFF" />	
	          <c:if test="${status.count%2==0}">
	            <c:set var="color" value="#CDCDCD" />
	          </c:if>
	    <tr>
	    	<td colspan="6" height="2" bgcolor="GRAY"/>
	    </tr>
		<tr>
			<td bgcolor="${color}"><fmt:formatDate value="${workhour.workDate}" pattern="dd.MM.yyyy"/></td>		
			<td bgcolor="${color}">${projects[workhour.projectId].name}</td>				
			<td bgcolor="${color}">${hourTypes[workhour.hourTypeId].name}</td>
			<td bgcolor="${color}">${workhour.overtime}</td>
			<td bgcolor="${color}">${workhour.amount}</td>
			<td bgcolor="${color}"><a href="#">[poista]</a></td>		
		</tr>	
		</c:forEach>	
	</table>
</c:if>
<br/>
<br/>
</div>
<jsp:include page="footer.jsp"></jsp:include>