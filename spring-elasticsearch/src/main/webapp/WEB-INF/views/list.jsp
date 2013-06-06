<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" media="all">
<title>List</title>
</head>
<body>
	<c:if test="${not empty error}">
		<div class="error-container"><c:out value="${error}"/></div>
	</c:if>

	<h2>List</h2>

	<table>
		<c:forEach items="${list}" var="element">
			<tr>
				<td>${element}</td>
			</tr>
		</c:forEach>
		<tr>
			<td>
				<form action="add.do" method="post">
					<input name="element" /><input type="submit" value="Add" />
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
