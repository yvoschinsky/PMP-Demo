<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PMPDemo</title>
    </head>

    <body>
		<div>
			<span><a href="index.htm">Home</a></span>&nbsp;|&nbsp;
			<span><a>New Tracker</a></span>&nbsp;|&nbsp;
			<span><a href="simulate.htm">Simulate</a></span>&nbsp;|&nbsp;
			<span><a href="active.htm">Active Trackers</a></span>&nbsp;|&nbsp;
			<span><a href="audit.htm">All Trackers</a></span>
		</div>
        <div>
			<p>Tracker Information</p>
			<form method="post">
				<table border="1">
					<tr>
						<td>Id</td>
						<td><c:out value="${tracker.id}"></c:out></td>
						<td><input type="hidden" name="id" value="${tracker.id}"/></td>
					</tr>
					<tr>
						<td>Date Created</td>
						<td><c:out value="${tracker.dateCreated}"></c:out></td>
						<td></td>
					</tr>
					<tr>
						<td>Period, days</td>
						<td></td>
						<td><input name="period" value="${tracker.period}"/></td>
					</tr>
					<tr>
						<td>Retailer</td>
						<td><c:out value="${tracker.retailer.name}"></c:out></td>
						<td><input name="retailer" value="${tracker.retailer.id}"/></td>
					</tr>
					<tr>
						<td>SKU</td>
						<td></td>
						<td><input name="product" value="${tracker.sku}"/></td>
					</tr>
					<tr>
						<td>Target Price</td>
						<td></td>
						<td><input name="price" value="${tracker.affordablePrice}"/></td>
					</tr>
					<tr>
						<td><input type="submit" name="save" value="save"/></td>
						<td></td>
						<td><input type="submit" name="delete" value="delete"/></td>
					</tr>
				</table>
			</form>
		</div>
    </body>
</html>
