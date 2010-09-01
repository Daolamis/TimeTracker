<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Tuntikirjanpito</title>
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<link rel="stylesheet" href="/timetracker/css/style.css" type="text/css"/>
</head>
<body>
	<div id="pagewidth">
		<div id="header">
			Tervetuloa ${applicationScope.loginData.firstname} ${applicationScope.loginData.lastname}, 
			olet ollut viimeksi kirjautuneena: <fmt:formatDate value="${applicationScope.loginData.lastlogin}" pattern="dd.MM.yyyy HH:mm"/>
			<br /><a href="logoutController">[kirjaudu ulos]</a><br/>	
		</div>
		<div id="wrapper" class="clearfix">