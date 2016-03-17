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
					<h4>Потребители</h4>
					<div class="search_field">
						<form method="GET" action="../admin/users">
							<input type="text" name="username" value="" placeholder="Search" />
							<input type="submit" class="btn btn-sm btn-primary" value="Търси" />
						</form>
					</div>
					<table class="container table table-bordered">
					<thead>
						<tr class="row table_head">
							<th class="col-sm-1">
								Потребителско име:
							</th>
							<th class="col-sm-1">
								Име:
							</th>
							<th class="col-sm-1">
								E-mail:
							</th>
							<th class="col-sm-1">
								Телефонен номер:
							</th>
							<th class="col-sm-2">
								Адрес:
							</th>
							<th class="col-sm-3">
								Действия:
							</th>
						</tr>
					</thead>
					<tbody>
						<% List<User> users = (List<User>) request.getAttribute("users"); %>
						<% if (users != null) { %>
							<% for (User user : users) { 
									if (user != null) { %>
								<tr class="row">
									<td class="col-sm-1">
										<%= user.getUsername() %>
									</td>
									<td class="col-sm-3">
										<%= user.getFirstName() %> <%= user.getLastName() %>
									</td>
									<td class="col-sm-1">
										<%= user.getEmail() %>
									</td>
									<td class="col-sm-1">
										<%= user.getPhoneNumber() %>
									</td>
									<td class="col-sm-2">
										<%= user.getAddress() %>
									</td>
									<td class="col-sm-4">
										<a href="../admin/user?id=<%= user.getId() %>" class="btn btn-xs btn-success">Прегледай</a>
										<a href="../admin/editUser?id=<%= user.getId() %>" class="btn btn-xs btn-warning">Промени</a>
										<a href="../admin/deleteUser?id=<%= user.getId() %>" class="btn btn-xs btn-danger">Изтрий</a>
									</td>
								</tr>
								<% } %>
							<% } %>
						<% } %>
						<tr class="row">
							<td class="col-lg-12" colspan="6">
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