<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp"></jsp:include>
<div id="maincol">
<h2>Tuntikirjaus</h2>

<table border="1">
<tr><td>
<table border="0"  cellpadding="0"  cellspacing="0" width="500px">
	<tr>
		<td width="25%" align="left"><a href="#">[edellinen]</a><br/></td>
		<td align="center" colspan="4">
			<h1>Viikko 31</h1>
		</td>
		<td align="right"><a href="#">[seuraava]</a><br/></td>
	</tr>
	<tr>
		<td colspan="6" height="2" bgcolor="gray"></td>
	</tr>
	<tr>
		<td align="center">Projekti</td>
		<td align="center">Maanantai</td>
		<td align="center">Tiistai</td>
		<td align="center">Keskiviikko</td>
		<td align="center">Torstai</td>
		<td align="center">Perjantai</td>
	</tr>
	<tr>
		<td colspan="6" height="1" bgcolor="gray"></td>
	</tr>			
	<tr>
		<td>Yleinen</td>
		<td colspan="5">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp; Sairausloma</td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
	</tr>
	<tr>
		<td>&nbsp; Loma</td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
	</tr>
	<tr>
		<td>&nbsp; Koulutus</td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
	</tr>
	<tr>
		<td colspan="6" height="1" bgcolor="gray"></td>
	</tr>
	<tr>
		<td>WebFox2.0</td>
		<td colspan="5">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp; Suunnittelu</td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
	</tr>
	<tr>
		<td>&nbsp; Kehitys</td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
	</tr>
	<tr>
		<td>&nbsp; Testaus</td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
	</tr>
	<tr>
		<td colspan="6" height="1" bgcolor="gray"></td>
	</tr>
	<tr>
		<td>Tsoha</td>
		<td colspan="5">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp; Suunnittelu</td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
	</tr>
	<tr>
		<td>&nbsp; Kehitys</td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text" value="2"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
	</tr>
	<tr>
		<td>&nbsp; Testaus</td>
		<td><input type="text" value="4"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
		<td><input type="text"/></td>
	</tr>
	<tr>
		<td colspan="6" height="1" bgcolor="gray"></td>
	</tr>
	<tr>
		<td>Yhteensä</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
</td>
</tr>	
</table>
</div>
<jsp:include page="footer.jsp"></jsp:include>