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
	    <span><a href="save.htm">New Tracker</a></span>&nbsp;|&nbsp;
	    <span><a href="simulate.htm">Simulate</a></span>&nbsp;|&nbsp;
	    <span><a href="active.htm">Active Trackers</a></span>&nbsp;|&nbsp;
	    <span><a href="audit.htm">All Trackers</a></span>
	</div>
        <div style="border:lightgray solid; padding: 10px;">
	    <p>Get Price</p>
	    <form action="price.htm" target="blank">
				sku:<input name="sku"><br/>
		<input type="submit">
	    </form>
			example: <a href="price.htm?sku=1418515" target="blank">price.htm?sku=1418515</a>
	</div>
        <div style="border:lightgray solid; padding: 10px;">
	    <p>Set Order</p>
	    <form action="placeOrder.htm" target="blank">
				sku:<input name="sku"><br/>
				email:<input name="email"><br/>
				price:<input name="price"><br/>
				period:<input name="period"><br/>
		<input type="submit">
	    </form>
			example: <a href="placeOrder.htm?sku=1418515&email=some_email@sometestdomain.com&price=13&period=10" target="blank">placeOrder.htm?sku=1418515&email=someemail@sometestdomain.com&price=13&period=10</a>
	</div>
        <div style="border:lightgray solid; padding: 10px;">
	    <p>Set Coupon</p>
	    <form action="sentcoupon.htm" target="blank">
				email:<input name="email"><br/>
				retailerId:<input name="retailerId"><br/>
				discount:<input name="discount"><br/>
		<input type="submit">
	    </form>
			example: <a href="sentcoupon.htm?retailerId=1&email=some_email@sometestdomain.com&discount=10" target="blank">sentcoupon.htm?retailerId=1&email=some_email@sometestdomain.com&discount=10</a>
	</div>	
    </body>
</html>
