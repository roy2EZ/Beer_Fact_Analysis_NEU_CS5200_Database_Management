<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Taste</title>
</head>
<body>
	<form action="findtastes" method="post">
		<h1>Search for a Taste by TasteId</h1>
		<p>
			<label for="tasteId">TasteId</label>
			<input id="tasteId" name="tasteId" value="${fn:escapeXml(param.tasteId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Tastes</h1>
        <table border="1">
            <tr>
                <th>TasteId</th>
                <th>TasteScore</th>
                <th>UserName </th>
                <th>BeerId</th>
            </tr>
     
           <tr>
               <td><c:out value="${tastes.getTasteId()}" /></td>
               <td><c:out value="${tastes.getTasteScore()}" /></td>
               <td><c:out value="${tastes.getUser().getUserId()}" /></td>
               <td><c:out value="${tastes.getBeer().getBeerId()}" /></td>
           </tr>
       </table>
</body>
</html>
