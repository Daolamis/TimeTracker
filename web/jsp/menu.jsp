<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<ul>
<c:if test="${sessionScope.loginData.roleCode == 'M'}">
	<li><a href="personTaskController?method=listPersons">Työntekijät</a></li>	<%--Managerin linkit --%>
</c:if>
<c:if test="${sessionScope.loginData.roleCode != 'S'}">
	<li><a href="timetrackController">Tuntikirjanpito</a></li><%--Muiden kuin pääkäyttäjän linkit --%>
</c:if>
<c:if test="${sessionScope.loginData.roleCode == 'S'}"> <%--Pääkäyttäjän linkit --%>
	<li><a href="personController">Lisää uusi käyttäjä</a></li>
	<li><a href="findPersonsController">Etsi käyttäjiä</a></li>
	<li><a href="projectController">Lisää projekti</a></li>
	<li><a href="listProjectsController">Listaa projektit</a></li>
	<li><a href="hourTypeController">Lisää tuntityyppi</a></li>
	<li><a href="listHourTypesController">Listaa tuntityypit</a></li>
</c:if>
<li><a href="passwordController">Vaihda salasana</a></li>
</ul>
