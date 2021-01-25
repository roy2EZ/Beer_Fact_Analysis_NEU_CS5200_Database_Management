<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Feel</title>
</head>
<body>
	<form action="findfeels" method="post">
		<h1>Search for a Feel by FeelId</h1>
		<p>
			<label for="feelId">FeelId</label>
			<input id="feelId" name="feelId" value="${fn:escapeXml(param.feelId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Feels</h1>
        <table border="1">
            <tr>
                <th>FeelId</th>
                <th>FeelScore</th>
                <th>UserName </th>
                <th>BeerId</th>
            </tr>
     
           <tr>
               <td><c:out value="${feels.getFeelId()}" /></td>
               <td><c:out value="${feels.getFeelScore()}" /></td>
               <td><c:out value="${feels.getUser().getUserId()}" /></td>
               <td><c:out value="${feels.getBeer().getBeerId()}" /></td>
           </tr>
       </table>
</body>
</html>
