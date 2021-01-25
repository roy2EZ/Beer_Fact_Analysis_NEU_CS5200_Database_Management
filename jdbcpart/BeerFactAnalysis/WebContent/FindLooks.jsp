<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Look</title>
</head>
<body>
	<form action="findlooks" method="post">
		<h1>Search for a Look by LookId</h1>
		<p>
			<label for="lookId">LookId</label>
			<input id="lookId" name="lookId" value="${fn:escapeXml(param.lookId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Look</h1>
        <table border="1">
            <tr>
                <th>LookId</th>
                <th>LookScore</th>
                <th>UserName </th>
                <th>BeerId</th>
            </tr>
     
           <tr>
               <td><c:out value="${looks.getLookId()}" /></td>
               <td><c:out value="${looks.getLookScore()}" /></td>
               <td><c:out value="${looks.getUser().getUserId()}" /></td>
               <td><c:out value="${looks.getBeer().getBeerId()}" /></td>
           </tr>
       </table>
</body>
</html>
