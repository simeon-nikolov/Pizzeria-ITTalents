<%@page import="java.nio.channels.SeekableByteChannel"%>
<%@page import="pizzeria.menu.Ingredient"%>
<%@page import="pizzeria.menu.Pizza"%>
<%@page import="servlets.PizzaServlet"%>
<%@page import="java.util.List"%>
<%@page import="servlets.BaseHttpServlet"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Pizza-Bug | Меню</title>
<jsp:include page="partials/HeadResources.jsp" />
</head>
<body>
	<div id="left_side_content">
		<%
			if (!(Boolean)request.getAttribute("auth")) {
		%>
		<jsp:include page="partials/LoginForm.jsp" />
		<%
			} else {
		%>
		<jsp:include page="partials/LoggedIn.jsp" />
		<jsp:include page="partials/ShoppingCart.jsp" />
		<%
			}
		%>
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
					<p>
						<button type="button" class="btn btn btn-warning">Пица</button>
						<button type="button" class="btn btn btn-warning">Салати</button>
						<button type="button" class="btn btn btn-warning">Паста</button>
						<button type="button" class="btn btn btn-warning">Десерти</button>
						<button type="button" class="btn btn btn-warning">Напитки</button>
					</p>
					<div class="clear"></div>
				</div>
				<div class="about_data">
					<%
						List<Pizza> list = (List<Pizza>) request.getAttribute("pizza");
						for (Pizza pizza : list) {
							String ing = "";
							List<Ingredient> ingredients = pizza.getIngredients();
							for (Ingredient ingredient : ingredients) {
								ing += ingredient.getName();
								ing += ", ";
							}
					%>
					<div class="pizza_details">
						<img src=<%= pizza.getImage() %> alt="" />
						<div class="desc">
							<ul>
								<li><span>Име: </span> <%= pizza.getName() %></li>
								<li><span>Грамаж: </span> <%= pizza.getGrammage() %></li>
								<li><span>Цена: </span> <%= pizza.getPrice() %>  лв.</li>
								<li><span>Съставки: </span> <%= ing %></li>
								<% if ((Boolean)request.getAttribute("auth")) { %>
								<li>
									<form method="POST" action="./cart">
										<input type="hidden" name="id" value="<%= pizza.getId() %>" />
										<input type="submit" class="btn btn-success" value="Добави" />
									</form>
								<% } %>
								<li>
							</ul>
						</div>
						<div class="clear"></div>
					</div>
					<%
						}
					%>
					<div class="clear"></div>
				</div>
			</div>
			<jsp:include page="partials/Footer.jsp" />
		</div>
	</div>
</body>
</html>
