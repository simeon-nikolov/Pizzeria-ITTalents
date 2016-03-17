<%@page import="pizzeria.menu.IProduct"%>
<%@page import="java.util.List"%>
<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<% List<IProduct> products = (List<IProduct>) request.getAttribute("cartProducts"); %>
<% if (products != null) { %>
<div id="shopping_cart">
	<div class="heading">
		<h4>Кошница</h4>
	</div>
	<div id="shopping_cart_container">
		<% for (IProduct product : products) { %>
		<div class="product">
			<img src="<%= product.getImage() %>" alt="<%= product.getName() %>" width="75px" height="75px" />
			<div class="desc">
				<span><%= product.getName() %><br />
				Цена : <%= product.getPrice() %> лв.</span><br />
				<form method="POST" action="./cart/remove">
					<input type="hidden" name="id" value="<%= product.getId() %>" />
					<input type="submit" value="Премахни" class="btn btn-xs btn-danger" />
				</form>
			</div>
		</div>
		<% } %>
	</div>
	<div class="bottom">
		<a class="btn btn-success">Поръчай</a>
	</div>
</div>
<% } %>