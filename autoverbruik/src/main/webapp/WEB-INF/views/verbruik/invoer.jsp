<%-- 
    Document   : invoer
    Created on : 16-sep-2012, 8:07:17
    Author     : Johan Dullaert
--%>

<?xml version="1.0" encoding="UTF-8"?>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML><!-- PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd" -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>verbruik invoeren</title>
<link rel="stylesheet" type="text/css" href="../css/standaard.css"/>
</head>
<body>
<div id="mainpage">
	<h2>Voer het nieuwe verbruik in:</h2>
	<c:if test="${not empty verbruikfouten}">
		<ul>
			<c:forEach items="${verbruikfouten}" var="verbruikfout">
				<li>${verbruikfout}</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${not empty datumfout}">
		<ul>
			<c:forEach items="${datumfout}" var="datumfout">
				<li>${datumfout}</li>
			</c:forEach>
		</ul>
	</c:if>
	<form action="<c:url value="/verbruik/invoer.htm"/>" method="POST">
		<div class="onArow">
			<label >Aantal km: <input class="field" type="number" name="aantalKm"
				value="${param.aantalKm}" required min="0.1" step="0.1"/>
			</label> 
			<label >Aantal liter: <input class="field" type="number" name="aantalLiter"
				value="${param.aantalLiter}" required min="0.01" step="0.01"/>
			</label>
			<label >Prijs per liter: <input class="field" type="number" name="literPrijs"
				value="${param.literPrijs}" required min="0.001" step="0.001"/>
			</label>
			<label >Datum: <input class="field" type="date" name="datum" value="${vandaag}"/>
			</label>
		</div>
		<div class="bottomRoom">
			<INPUT type="reset" value="opnieuw" /> 			
			<INPUT type="submit" value="invoeren" />
		</div>

	</form>
	<c:if test="${not empty fouten}">
		<ul>
			<c:forEach items="${fouten}" var="fout">
				<li>${fout}</li>
			</c:forEach>
		</ul>
	</c:if>
	<jsp:include page="../jspf/linkMenu.jsp"/>
	</div>
</body>
</html>
