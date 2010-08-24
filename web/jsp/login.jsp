<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sisään kirjautuminen</title>
</head>
<body>
<center>

<h2>Projektin työaikaseuranta</h2>
<br />
<form:form method="POST" action="loginController" commandName="login">
<table width="25%" border="1">
	<tr>
		<td align="center" bgcolor="lightblue">Kirjaudu</td>
	</tr>
	<tr>
		<td>
		<table border="0" width="100%">
			<tr>
				<td width="33%" align="right">Käyttäjätunnus:</td>
				<td width="66%" align="left">
					<form:input path="userId"/>
				</td>
			</tr>			
			<tr>
				<td width="33%" align="right">Salasana:</td>
				<td width="66%" align="left">
					<form:password path="password"/>
				</td>
			</tr>		
			<tr>
				<td align="center" colspan="2">
					<input type="submit" alignment="center" value="Kirjaudu"> 
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form:form>
</center>
</body>
</html>