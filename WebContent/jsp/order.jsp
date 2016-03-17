<%@page import="java.math.RoundingMode"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="pizzeria.menu.IProduct"%>
<%@page import="java.util.List"%>
<%@page import="pizzeria.account.Account"%>
<%@page import="pizzeria.account.User"%>
<%@page import="servlets.BaseHttpServlet"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Pizza-Bug | Количка</title>
<jsp:include page="partials/HeadResources.jsp" />
<script type="text/javascript">
	function myFunction() {
		var d = new Date();
		d.setMinutes(d.getMinutes() + 20);
		alert("Поръчката ще пристигне след: " + d.toLocaleTimeString()+ " " + d.toDateString() );
	}
</script>
</head>
<body>
	<div id="left_side_content">
		<jsp:include page="partials/LoggedIn.jsp" />
	</div>
	<div class="wrap">
		<div class="wrapper">
			<div class="header">
				<jsp:include page="partials/HeaderTop.jsp" />
				<div class="header_bottom">
					<jsp:include page="partials/Menu.jsp" />
				</div>
			</div>
			<div class="main">
				<div class="main_top">
					<h1>Количка:</h1>
					<div class="clear"></div>
				</div>

				<div class="about_data">
					<%
						Account acc = (Account) session.getAttribute(BaseHttpServlet.LOGGED_USER_ATTRIBUTE_NAME);
						DecimalFormat df = new DecimalFormat("#.##");
						df.setRoundingMode(RoundingMode.FLOOR);
						double sum = 0;
					%>
					<%
						if (acc instanceof User) {
					%>
					<%
						User user = (User) acc;
							List<IProduct> products = user.getShoppingCart().getProducts();
							for (IProduct product : products) {
								sum += product.getPrice();
					%>
					<div class="pizza_details">
						<img src=<%=product.getImage()%> alt="" />
						<div class="desc">
							<ul>
								<li><span>Име: </span> <%=product.getName()%></li>
								<li><span>Цена: </span> <%=product.getPrice()%> лв.</li>
								<li>
									<form method="POST" action="./cart/remove">
										<input type="hidden" name="id" value="<%=product.getId()%>" />
										<input type="submit" value="Премахни"
											class="btn btn-xs btn-danger" />
									</form>
								<li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					<%
						}

						}
					%>
					<div class="clear"></div>
				</div>
				<div class="register_form" class="table">
					<span style="font-family: 16px"> Дължите <%= df.format(sum) %>
						лв.
					</span>
					<form onclick="myFunction()" method="POST" action="./order">
						<input type="submit" value="Поръчай" class="btn btn-success" />
					</form>
				</div>
			</div>
			<div class="footer">
				<div class="copy_right">
					<p>©2016 rights Reserved</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>