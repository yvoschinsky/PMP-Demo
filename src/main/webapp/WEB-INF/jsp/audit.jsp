<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${title}"></c:out></title>
    </head>

    <body>
		<div>
			<span><a href="index.htm">Home</a></span>&nbsp;|&nbsp;
			<span><a href="save.htm">New Tracker</a></span>&nbsp;|&nbsp;
			<span><a href="simulate.htm">Simulate</a></span>&nbsp;|&nbsp;
			<c:if test="${'Active Trackers' eq title}">
				<span><a>Active Trackers</a></span>&nbsp;|&nbsp;
				<span><a href="audit.htm">All Trackers</a></span>
			</c:if>
			<c:if test="${'All Trackers' eq title}">
				<span><a href="active.htm">Active Trackers</a></span>&nbsp;|&nbsp;
				<span><a>All Trackers</a></span>
			</c:if>
		</div>
        <div>
			<table border="1">
				<tr>
					<td>Id</td>
					<td>Created</td>
					<td>Target Price</td>
					<td>SKU</td>
					<td>Retailer Name</td>
					<td>Period, days</td>
				</tr>
				<c:forEach items="${trackerList.pageList}" var="item">
					<tr>
						<td><a href="save.htm?id=${item.id}"><c:out value="${item.id}"></c:out></a></td>
						<td><c:out value="${item.dateCreated}"></c:out></td>
						<td><c:out value="${item.affordablePrice}"></c:out></td>
						<td><c:out value="${item.sku}"></c:out></td>
						<td><c:out value="${item.retailer.name}"></c:out></td>
						<td><c:out value="${item.period}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
			<tg:paging pagedListHolder="${trackerList}" pagedLink="?page=~"/>
		</div>
    </body>
</html>
