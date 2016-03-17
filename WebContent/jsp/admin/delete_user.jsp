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
					<h4>Внимание:</h4>
					<% User user = (User) request.getAttribute("user"); %>
					<% if (user != null) { %>
					<div class="container">
						<p>Наистина ли искате да изтриете потребител с потребителско име: <%= user.getUsername() %>?</p>
						<form method="POST" action="../admin/deleteUser">
							<input type="hidden" name="id" value="<%= user.getId() %>" />
							<input type="submit" value="Да, изтрий!" class="btn btn-md btn-warning" />
							<a href="../admin/users" class="btn btn-md btn-danger">Не, не изтривай!</a>
						</form>
					</div>
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