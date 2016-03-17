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
					<h4>Потребител:</h4>
					<% User user = (User) request.getAttribute("user"); %>
					<% if (user != null) { %>
						<form method="POST" action="../admin/editUser">
							<table class="table table-bordered">
								<tr>
									<td>ID: </td>
									<td><%= user.getId() %></td>
								</tr>
								<tr>
									<td>Потребителско име: </td>
									<td><input type="text" name="username" value="<%= user.getUsername() %>" /></td>
								</tr>
								<tr>
									<td>Първо име: </td>
									<td><input type="text" name="firstName" value="<%= user.getFirstName() %>" /></td>
								</tr>
								<tr>
									<td>Фамилно име: </td>
									<td><input type="text" name="lastName" value="<%= user.getLastName() %>" /></td>
								</tr>
								<tr>
									<td>E-mail: </td>
									<td><input type="text" name="email" value="<%= user.getEmail() %>" /></td>
								</tr>
								<tr>
									<td>Телефонен номер: </td>
									<td><input type="text" name="phone" value="<%= user.getPhoneNumber() %>" /></td>
								</tr>
								<tr>
									<td>Адрес: </td>
									<td><input type="text" name="address" value="<%= user.getAddress() %>" /></td>
								</tr>
							</table>
							<input type="hidden" name="id" value="<%= user.getId() %>" />
							<input type="submit" value="Промени" class="btn btn-lg btn-warning" />
						</form>
					<% } %>
					&nbsp;
					<div class="clear"></div>
				</div>
			</div>
			<jsp:include page="./partials/Footer.jsp" />
		</div>
	</div>
</body>
</html>