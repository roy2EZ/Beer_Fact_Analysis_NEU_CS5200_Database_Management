<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Score</title>
</head>
<body>
	<form action="findscores" method="post">
		<h1>Search for a Score by ScoreId</h1>
		<p>
			<label for="scoreId">ScoreId</label>
			<input id="scoreId" name="scoreId" value="${fn:escapeXml(param.scoreId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Scores</h1>
        <table border="1">
            <tr>
                <th>ScoreId</th>
                <th>ScoreScore</th>
                <th>UserName </th>
                <th>BeerId</th>
            </tr>
     
           <tr>
               <td><c:out value="${scores.getScoreId()}" /></td>
               <td><c:out value="${scores.getScore()}" /></td>
               <td><c:out value="${scores.getUser().getUserId()}" /></td>
               <td><c:out value="${scores.getBeer().getBeerId()}" /></td>
           </tr>
       </table>
</body>
</html>
