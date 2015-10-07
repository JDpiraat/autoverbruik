<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
</head>
<body>
<ul id="menu">
	<li><a href="<c:url value='/'/>">home </a></li> 
	<c:if test="${param.wijze != 'uitgebreid'}"><li><a href="<c:url value='/verbruik/overzicht.htm?wijze=uitgebreid&page=1'/>">uitgebreid overzicht </a></li></c:if>
	<c:if test="${param.wijze != 'beknopt'}"><li><a href="<c:url value='/verbruik/overzicht.htm?wijze=beknopt&page=1'/>">beknopt overzicht </a></li></c:if>
	<c:if test="${param.wijze == 'uitgebreid' || param.wijze == 'beknopt'}"><li><a href="<c:url value='/verbruik/invoer.htm'/>">invoer </a></li></c:if>	
	</ul>
</body>
</html>