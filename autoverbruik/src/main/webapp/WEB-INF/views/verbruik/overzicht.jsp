<%-- 
    Document   : overzicht
    Created on : 16-sep-2012, 8:17:56
    Autdor     : Johan Dullaert
--%>

<?xml version="1.0" encoding="UTF-8"?>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>overzicht ${param.wijze}</title>
<link rel="stylesheet" type="text/css" href="../css/standaard.css" />
</head>
<body>
<div id="mainpage">
	<h1>Het ${param.wijze} overzicht:</h1>
	<c:if test="${not empty verbruik}">
		<div class="bold">
			Aantal tankbeurten:
			<fmt:formatNumber value="${totaalAantalTankbeurten}" />			
		</div>
		<div class="bold">
			Gemiddeld aantal liter per 100km:
			<fmt:formatNumber value="${gemiddeldAantalLiterPer100km}"
				minFractionDigits="2" maxFractionDigits="2" />
			liter
		</div>
		<div class="bold">
			Gemiddelde prijs per km:
			<fmt:formatNumber value="${gemiddeldePrijsPerKm}" minFractionDigits="2"
				maxFractionDigits="2" type="currency" currencySymbol="€" />
		</div>
		<div>
			Totaal aantal km (buiten als ik het vergeten noteren heb ...): 
			<fmt:formatNumber value="${totaalAantalKm}" minFractionDigits="2"
				maxFractionDigits="2" /> km
		</div>
		<div>
			Totaal aantal centjes (buiten als ik het vergeten noteren heb ...): 
			<fmt:formatNumber value="${totaalPrijs}" minFractionDigits="2"
				maxFractionDigits="2" type="currency" currencySymbol="€" />
		</div>
		<div>
			<table>
				<thead>
					<tr>
						<th>datum</th>
						<c:if test="${param.wijze == 'uitgebreid'}">
							<th>aantal km</th>
							<th>aantal liter</th>
							<th>literprijs</th>
							<th>totaal</th>
						</c:if>
						<th>liter per 100km</th>
						<th>prijs per km</th>
					</tr>
				</thead>
				<c:forEach items="${verbruik}" var="verbruikDetail">
					<tr class="borderClass">						
						<td id="datum"><fmt:formatDate value="${verbruikDetail.datum}" type="DATE" /></td>
						<c:if test="${param.wijze == 'uitgebreid'}">
							<td><fmt:formatNumber value="${verbruikDetail.aantalKilometer}"
									minFractionDigits="1" maxFractionDigits="1" /></td>
							<td><fmt:formatNumber value="${verbruikDetail.aantalLiter}"
									minFractionDigits="2" maxFractionDigits="2" /></td>
							<td><fmt:formatNumber value="${verbruikDetail.literPrijs}"
									minFractionDigits="3" maxFractionDigits="3" /></td>
							<td><fmt:formatNumber value="${verbruikDetail.totaal}"
									minFractionDigits="2" maxFractionDigits="2" /></td>
						</c:if>
						<td><fmt:formatNumber value="${verbruikDetail.aantalLiterPerKilometer}"
								minFractionDigits="2" maxFractionDigits="2" /></td>
						<td><fmt:formatNumber value="${verbruikDetail.prijsPerKilometer}"
								minFractionDigits="3" maxFractionDigits="3" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
	<c:if test="${not empty fouten}">
		<div>
			<ul>
				<c:forEach items="${fouten}" var="fout">
					<li>${fout}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<table id="paging">
	<tr>
	<%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td id="pagenumbers"><a id="pagenumberslink" href="../verbruik/overzicht.htm?wijze=${param.wijze}&page=${currentPage - 1}">Previous</a></td>
    </c:if> 
    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>          
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td id="pagenumbers">${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td id="pagenumbers"><a id="pagenumberslink" href="../verbruik/overzicht.htm?wijze=${param.wijze}&page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach> 
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a id="pagenumberslink" href="../verbruik/overzicht.htm?wijze=${param.wijze}&page=${currentPage + 1}">Next</a></td>
    </c:if>
    </tr>
    </table>
	<jsp:include page="../jspf/linkMenu.jsp" />
	</div>
</body>
</html>
