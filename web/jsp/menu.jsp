<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<ul>
<c:if test="${sessionScope.loginData.roleCode == 'M'}">
	<li><a href="personTaskController?method=listPersons">Ty�ntekij�t</a></li>	<%--Managerin linkit --%>
</c:if>
<c:if test="${sessionScope.loginData.roleCode != 'S'}">
	<li><a href="timetrackController">Tuntikirjanpito</a></li><%--Muiden kuin p��k�ytt�j�n linkit --%>
</c:if>
<c:if test="${sessionScope.loginData.roleCode == 'S'}"> <%--P��k�ytt�j�n linkit --%>
	<li><a href="personController">Lis�� uusi k�ytt�j�</a></li>
	<li><a href="findPersonsController">Etsi k�ytt�ji�</a></li>
	<li><a href="projectController">Lis�� projekti</a></li>
	<li><a href="listProjectsController">Listaa projektit</a></li>
	<li><a href="hourTypeController">Lis�� tuntityyppi</a></li>
	<li><a href="listHourTypesController">Listaa tuntityypit</a></li>
</c:if>
<li><a href="passwordController">Vaihda salasana</a></li>
</ul>
