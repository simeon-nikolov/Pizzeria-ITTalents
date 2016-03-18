<%@page import="pizzeria.menu.Ingredient"%>
<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@page import="pizzeria.menu.Pizza"%>
<%@page import="pizzeria.menu.IProduct"%>
<%@page import="java.util.List"%>
<%@page import="servlets.BaseHttpServlet"%>
<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8" %>
<!DOCTYPE html">
<html>
<head>
<title>Pizza-Bug | Admin Panel</title>
<jsp:include page="./partials/HeadResources.jsp" />
<style>
	.admin_panel table > tbody > tr > td {
		vertical-align: middle;
	}
</style>
</head>
<body>
 	<div id="left_side_content">
		&nbsp;
	</div>
	<div class="wrap">
		<div class="wrapper">
			<div class="header">
				<jsp:include page="./partials/HeaderTop.jsp" />
				<div class="header_bottom">
					<jsp:include page="./partials/Menu.jsp" />
				</div>
			</div>
			<div class="main">
				<div class="main_top">
					<jsp:include page="./partials/AdminPanelMenu.jsp" />
					<div class="clear"></div>
				</div>
				<div class="admin_panel">
					<h4>Продукти</h4>
					<div class="search_field">
						<form method="GET" action="../admin/products">
							<input type="text" name="title" value="" placeholder="Търси продукт" />
							<input type="submit" class="btn btn-sm btn-primary" value="Търси" />
						</form>
					</div>
					<div style="padding: 10px; text-align:right;">
						<a class="btn btn-sm btn-success" href="../admin/addPizza">Добави пица</a>
					</div>
					<table class="container table table-bordered">
					<thead>
						<tr class="row table_head">
							<th class="col-sm-1">
							</th>
							<th class="col-sm-1">
								Продукт:
							</th>
							<th class="col-sm-1">
								Цена:
							</th>
							<th class="col-sm-1">
								Грамаж:
							</th>
							<th class="col-sm-1">
								Размер:
							</th>
							<th class="col-sm-2">
								Съставки:
							</th>
							<th class="col-sm-3">
								Действия:
							</th>
						</tr>
					</thead>
					<tbody>
						<% List<IProduct> products = (List<IProduct>) request.getAttribute("products"); %>
						<% if (products != null) { %>
							<% for (IProduct product : products) { %>
								<% if (product != null) { %>
								<tr class="row">
									<td class="col-sm-1">
										<img src="../<%= product.getImage() %>" alt="<%= product.getName() %>" />
									</td>
									<td class="col-sm-3">
										<%= product.getName() %>
									</td>
									<td class="col-sm-1">
										<%= String.format("%.2f",product.getPrice()) %>
									</td>
									<td class="col-sm-1">
										<% if (product instanceof Pizza) { %>
										<%= ((Pizza)product).getGrammage() %>
										<% } %>
									</td>
									<td class="col-sm-1">
										<% if (product instanceof Pizza) { %>
										<%= ((Pizza)product).getSize() %>
										<% } %>
									</td>
									<td class="col-sm-2">
										<% if (product instanceof Pizza) { 
											Pizza pizza = (Pizza) product;
											
											for (Ingredient ing : pizza.getIngredients()) { %>
												<%= ing.getName() %>,
											<% }
										} %>
									</td>
									<td class="col-sm-4">
										<a href="../admin/product?id=<%= product.getId() %>" class="btn btn-xs btn-success">Прегледай</a>
										<a href="../admin/editProduct?id=<%= product.getId() %>" class="btn btn-xs btn-warning">Промени</a>
										<a href="../admin/deleteProduct?id=<%= product.getId() %>" class="btn btn-xs btn-danger">Изтрий</a>
									</td>
								</tr>
								<% } %>
							<% } %>
						<% } %>
						<tr class="row">
							<td class="col-lg-12" colspan="7">
								Page: <a>1 2 3 ... 10</a>
							</td>
						</tr>
					<tbody>
					</table>
					&nbsp;
					<div class="clear"></div>
				</div>
			</div>
			<jsp:include page="./partials/Footer.jsp" />
		</div>
	</div>
</body>
</html>