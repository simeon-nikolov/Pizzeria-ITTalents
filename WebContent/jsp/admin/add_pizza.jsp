<%@page import="pizzeria.menu.Ingredient"%>
<%@page import="pizzeria.account.User"%>
<%@page import="java.util.List"%>
<%@page import="servlets.BaseHttpServlet"%>
<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8" %>
<!DOCTYPE html">
<html>
<head>
<title>Pizza-Bug | Admin Panel</title>
<jsp:include page="./partials/HeadResources.jsp" />
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
					<h4>Добави пица:</h4>
					<form method="POST" action="../admin/addPizza" enctype="multipart/form-data">
						<table class="table borderless">
							<tr>
								<td>Име: </td>
								<td><input type="text" name="name" value="" /></td>
							</tr>
							<tr>
								<td>Цена: </td>
								<td><input type="text" name="price" value="" /></td>
							</tr>
							<tr>
								<td>Изображение: </td>
								<td><input type="file" name="image" value="" /></td>
							</tr>
							<tr>
								<td>Грамаж: </td>
								<td><input type="text" name="grammage" value="" /></td>
							</tr>
							<tr>
								<td>Размер: </td>
								<td><input type="text" name="size" value="" /></td>
							</tr>
							<tr>
								<td>Съставки: </td>
								<td>
								<% List<Ingredient> ingredients = (List<Ingredient>) request.getAttribute("ingredients"); %>
								<% for (Ingredient ing : ingredients) { %>
								<span style="display:inline-block;"><input type="checkbox" id="<%= ing.getName() %>" name="ingredients" value="<%= ing.getName() %>" style="display:inline-block;" /><label style="display:inline-block; margin-left:0; padding-left: 0; padding-right: 10px;" for="<%= ing.getName() %>"><%= ing.getName() %></label></span>
								<% } %>
								</td>
							</tr>
						</table>
						<input type="submit" value="Добави" class="btn btn-lg btn-warning" />
					</form>
					&nbsp;
					<div class="clear"></div>
				</div>
			</div>
			<jsp:include page="./partials/Footer.jsp" />
		</div>
	</div>
</body>
</html>