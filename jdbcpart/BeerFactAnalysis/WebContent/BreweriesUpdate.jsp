<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a Brewery</title>
</head>
<body>
	<h1>Update Brewery</h1>
	<form action="breweryupdate" method="post">
		<p>
			<label for="breweryId">BreweryId</label>
			<input id="breweryId" name="breweryId" value="${fn:escapeXml(param.breweryId)}">
		</p>
		<p>
			<label for="breweryname">New Brewery Name</label>
			<input id="breweryname" name="breweryname" value="">
		</p>
		<p>
			<label for="brewerycountry">New Brewery Country</label>
			<input id="brewerycountry" name="brewerycountry" value="">
		</p>
		<p>
			<label for="brewerystate">New Brewery State</label>
			<input id="brewerystate" name="brewerystate" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>