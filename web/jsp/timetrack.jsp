<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
<table border="0" cellpadding="0" cellspacing="0" width="500px">
	<tr>
		<td colspan="5">
		<form:form method="POST" action="timetrackController" commandName="workhour">
		<jsp:include page="message_component.jsp"/>
			<fieldset>
				<legend>Lis‰‰ tyˆtunti</legend>
				<table border="0" width="500px">
					<tr>						
						<td>Projekti & tuntityyppi</td>						
						<td>Pvm (dd.MM.yyyy)</td>
						<td>M‰‰r‰</td>						
						<td>&nbsp;</td>
					</tr>	
					<tr>
						<td>			
							<form:select path="projectAndHourType" multiple="false">
								<c:forEach items="${sessionScope.loginData.projects}" var="projectId">
									<c:set var="project" value="${projects[projectId]}"/>						
									<optgroup label="${project.name}">
										<c:forEach items="${project.hourtypes}" var="hourTypeId">
											<form:option value="${projectId}_${hourTypeId}">${hourTypes[hourTypeId].name}</form:option>
										</c:forEach>						
									<optgroup/>
								</c:forEach>
							</form:select>
						</td>
						<td>
							<form:errors path="workDate"><span style="color:red"> *</span></form:errors>
							<form:input path="workDate"  cssStyle="width:70px;"/>
						</td>
						<td>
							<form:errors path="amount"><span style="color:red"> *</span></form:errors>
							<form:input path="amount"  cssStyle="width:30px;"/>
						</td>
						<td>
							<input type="submit" value="Lis‰‰ tunti"> 
						</td>
					</tr>
				</table>
			</fieldset>		
			</form:form>		
		</td>
	</tr>
	<tr>
    	<td colspan="5" height="2" bgcolor="GRAY"/>
    </tr>
	<tr>
		<td bgcolor="#A1A1A1">Pvm</td>
		<td bgcolor=#A1A1A1>Projekti</td>		
		<td bgcolor="#A1A1A1">Tuntityyppi</td>
		<td bgcolor=#A1A1A1>M‰‰r‰</td>		
		<td bgcolor="#A1A1A1">Toiminta</td>
	</tr>
	<c:if test="${not empty workHours}">
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
		<td bgcolor="${color}"><a href="personTaskController?method=deleteWorkhour&workHourId=<c:out value="${workhour.id}"/>" onclick="return confirm('Haluatko varmasti poistaa tyˆtunnin')">[poista]</a></td>		
	</tr>	
	</c:forEach>
	</c:if>	
</table>

<br/>
<br/>
</div>
<jsp:include page="footer.jsp"></jsp:include>