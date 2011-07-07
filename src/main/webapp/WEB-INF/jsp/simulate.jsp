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
					<td>SKU</td>
					<td>Price</td>
				</tr>
				<c:forEach items="${skuList.pageList}" var="item">
					<form>
						<tr>
							<td><c:out value="${item}"></c:out><input type="hidden" name="sku" value="${item}"></td>
							<td><input name="price"></td>
							<td><input type="submit" value="simulate"></td>
						</tr>
					</form>
				</c:forEach>
			</table>
			<tg:paging pagedListHolder="${skuList}" pagedLink="?page=~"/>
		</div>
    </body>
</html>
