<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${sessionScope.loginData.roleCode == 'M'}">
	<a href="personTaskController?method=listPersons">Työntekijät</a><br/>	
</c:if>
<c:if test="${sessionScope.loginData.roleCode != 'S'}">
	<a href="timetrackController">Tuntikirjanpito</a><br/>
</c:if>
<c:if test="${sessionScope.loginData.roleCode == 'S'}">
	<a href="personController">Lisää uusi käyttäjä</a><br/>
	<a href="findPersonsController">Etsi käyttäjiä</a><br/>
	<a href="projectController">Lisää projekti</a><br/>
	<a href="listProjectsController">Listaa projektit</a><br/>
	<a href="hourTypeController">Lisää tuntityyppi</a><br/>
	<a href="listHourTypesController">Listaa tuntityypit</a><br/>
</c:if>
<a href="passwordController">Vaihda salasana</a><br/>