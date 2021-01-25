<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Review</title>
</head>
<body>
	<form action="findreviews" method="post">
		<h1>Search for a Review by ReviewId</h1>
		<p>
			<label for="reviewId">ReviewId</label>
			<input id="reviewId" name="reviewId" value="${fn:escapeXml(param.reviewId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Review</h1>
        <table border="1">
            <tr>
                <th>ReviewId</th>
                <th>ReviewDate</th>
                <th>ReviewText</th>
                <th>BeerId</th>
                <th>UserName</th>
            </tr>
            <tr>
                <td><c:out value="${reviews.getReviewId()}" /></td>
                <td><fmt:formatDate value="${reviews.getReviewDate()}" pattern="yyyy-MM-dd"/></td>
                <td><c:out value="${reviews.getReviewText()}" /></td>
                <td><c:out value="${reviews.getUser().getUserId()}" /></td>
                <td><c:out value="${reviews.getBeer().getBeerId()}" /></td>

            </tr>
       </table>
</body>
</html>
