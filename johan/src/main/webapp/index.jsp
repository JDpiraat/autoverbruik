<?xml version="1.0" encoding="UTF-8"?>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>auto verbruik</title>
<link rel="stylesheet" type="text/css" href="css/standaard.css" />
</head>

<body>
	<div id="mainpage">
		<h1>Auto verbruik ...</h1>
		<h2>mijne corsa</h2>
		<div>
			<ul>
				<li><a href="<c:url value="/verbruik/invoer.htm" />">invoer</a></li>
				<li><a
					href="<c:url value="/verbruik/overzicht.htm?wijze=beknopt&page=1" />">beknopt
						overzicht</a></li>
				<li><a
					href="<c:url value="/verbruik/overzicht.htm?wijze=uitgebreid&page=1" />">uitgebreid
						overzicht</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
