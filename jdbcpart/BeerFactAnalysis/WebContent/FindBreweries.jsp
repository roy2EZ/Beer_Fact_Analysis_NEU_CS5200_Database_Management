<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Brewery</title>
</head>
<body>
	<form action="findbreweries" method="post">
		<h1>Search for a Brewery by BreweryId</h1>
		<p>
			<label for="BreweriesId">BreweryId</label>
			<input id="BreweriesId" name="BreweriesId" value="${fn:escapeXml(param.BreweriesId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>

	<h1>Matching Brewery</h1>
        <table border="1">
            <tr>
                <th>BreweryId</th>
                <th>BreweryName</th>
                <th>BreweryCountry</th>
                <th>BreweryState</th>
            </tr>

            <tr>
                <td><c:out value="${brewery.getBreweryId()}" /></td>
                <td><c:out value="${brewery.getBreweryName()}" /></td>
                <td><c:out value="${brewery.getBreweryCountry()}" /></td>
                <td><c:out value="${brewery.getBreweryState()}" /></td>
            </tr>
       </table>
</body>
</html>
