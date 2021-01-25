<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Beer</title>
</head>
<body>
	<h1>Create Beer</h1>
	<form action="beercreate" method="post">
		<p>
			<label for="beerId">BeerId</label>
			<input id="beerId" name="beerId" value="">
		</p>
		<p>
			<label for="beername">BeerName</label>
			<input id="beername" name="beername" value="">
		</p>
		<p>
			<label for="beercountry">BeerCountry</label>
			<input id="beercountry" name="beercountry" value="">
		</p>
		<p>
			<label for="beerstate">BeerState</label>
			<input id="beerstate" name="beerstate" value="">
		</p>
		<p>
			<label for="beerabv">BeerABV</label>
			<input id="beerabv" name="beerabv" value="">
		</p>
		<p>
			<label for="breweryId">BreweryId</label>
			<input id="breweryId" name="breweryId" value="">
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