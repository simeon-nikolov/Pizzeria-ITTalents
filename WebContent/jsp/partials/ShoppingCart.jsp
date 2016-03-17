<%@page import="pizzeria.menu.IProduct"%>
<%@page import="java.util.List"%>
<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<div id="shopping_cart">
	<div class="heading">
		<h4>Кошница</h4>
	</div>
	<% List<IProduct> pizzas = (List<IProduct>) request.getAttribute("cartProducts"); %>
	<div id="shopping_cart_container">
		<% for (IProduct product : pizzas) { %>
		<div class="product">
			<img src="<%= product.getImage() %>" alt="<%= product.getName() %>" width="75px" height="75px" />
			<div class="desc">
				<span><%= product.getName() %><br />
				Цена : <%= product.getPrice() %>лв</span><br />
				<a href="javascript:;">Премахни</a>
			</div>
		</div>
		<% } %>
	</div>
	<div class="bottom">
		<a class="btn btn-success">Поръчай</a>
	</div>
</div>