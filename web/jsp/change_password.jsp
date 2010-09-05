<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
<h2>Vaihda salasana</h2>
<br />
<form:form method="POST" action="passwordController" commandName="passwordCommand">
<jsp:include page="message_component.jsp"/>
<table border="0" width="500px">	
	<tr>
		<td width="33%" align="right">
			<form:errors path="oldPassword"><span style="color:red"> *</span></form:errors>
			Vanha salasana:
		</td>
		<td width="66%" align="left">
			<form:password path="oldPassword" size="25"/>
		</td>
	</tr>					
	<tr>
		<td width="33%" align="right">
			<form:errors path="password"><span style="color:red"> *</span></form:errors>
			Uusi salasana:
		</td>
		<td width="66%" align="left">
			<form:password path="password" size="25"/>
		</td>
	</tr>	
	<tr>
		<td width="33%" align="right">
			<form:errors path="retypePassword"><span style="color:red"> *</span></form:errors>
			Uusi salasana uudestaan:
		</td>
		<td width="66%" align="left">
			<form:password path="retypePassword" size="25"/>
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