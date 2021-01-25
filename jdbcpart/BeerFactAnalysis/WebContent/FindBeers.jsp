<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Beer</title>
</head>
<body>
	<form action="findbeers" method="post">
		<h1>Search for a Beer by BeerId</h1>
		<p>
			<label for="beerid">BeerId</label>
			<input id="beerid" name="beerid" value="${fn:escapeXml(param.beerid)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Beer</h1>
        <table border="1">
            <tr>
                <th>BeerId</th>
                <th>BeerName</th>
                <th>BeerCountry</th>
                <th>BeerState</th>
                <th>BeerABV</th>
                <th>BreweryId</th>
            </tr>
            <tr>
                <td><c:out value="${beer.getBeerId()}" /></td>
                <td><c:out value="${beer.getBeerName()}" /></td>
                <td><c:out value="${beer.getBeerCountry()}" /></td>
                <td><c:out value="${beer.getBeerState()}" /></td>
                <td><c:out value="${beer.getBeerABV()}" /></td>
                <td><c:out value="${beer.getBrewery().getBreweryId()}" /></td>
            </tr>
       </table>
</body>
</html>
