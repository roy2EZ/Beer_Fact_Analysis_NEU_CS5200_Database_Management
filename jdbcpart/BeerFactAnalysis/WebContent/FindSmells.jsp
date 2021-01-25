<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Smell</title>
</head>
<body>
	<form action="findsmells" method="post">
		<h1>Search for a Smell by SmellId</h1>
		<p>
			<label for="smellId">SmellId</label>
			<input id="smellId" name="smellId" value="${fn:escapeXml(param.smellId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Smells</h1>
        <table border="1">
            <tr>
                <th>SmellId</th>
                <th>SmellScore</th>
                <th>UserName </th>
                <th>BeerId</th>
            </tr>
     
           <tr>
               <td><c:out value="${smells.getSmellId()}" /></td>
               <td><c:out value="${smells.getSmellScore()}" /></td>
               <td><c:out value="${smells.getUser().getUserId()}" /></td>
               <td><c:out value="${smells.getBeer().getBeerId()}" /></td>
           </tr>
       </table>
</body>
</html>
