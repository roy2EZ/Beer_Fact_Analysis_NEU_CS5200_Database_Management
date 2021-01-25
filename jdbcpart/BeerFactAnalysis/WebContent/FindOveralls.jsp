<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Overall</title>
</head>
<body>
	<form action="findoveralls" method="post">
		<h1>Search for a Overall by OverallId</h1>
		<p>
			<label for="overallId">OverallId</label>
			<input id="overallId" name="overallId" value="${fn:escapeXml(param.overallId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Overalls</h1>
        <table border="1">
            <tr>
                <th>OverallId</th>
                <th>OverallScore</th>
                <th>UserName </th>
                <th>BeerId</th>
            </tr>
     
           <tr>
               <td><c:out value="${overalls.getOverallId()}" /></td>
               <td><c:out value="${overalls.getOverallScore()}" /></td>
               <td><c:out value="${overalls.getUser().getUserId()}" /></td>
               <td><c:out value="${overalls.getBeer().getBeerId()}" /></td>
           </tr>
       </table>
</body>
</html>
