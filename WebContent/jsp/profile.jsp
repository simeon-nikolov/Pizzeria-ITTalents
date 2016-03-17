<%@page import="pizzeria.account.User"%>
<%@page import="servlets.BaseHttpServlet"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Pizza-Bug | Профил</title>
<jsp:include page="partials/HeadResources.jsp" />
</head>
<body>
	<div id="left_side_content">
		<jsp:include page="partials/LoggedIn.jsp" />
	</div>
	<div class="wrap">
		<div class="wrapper">
			<div class="header">
				<div class="header_top">
					<div class="logo">
						<a href="resources/#"><img
							src="resources/images/pizza/logo1.png" alt="" /></a>
					</div>
					<div class="clear"></div>
				</div>
				<div class="header_bottom">
					<jsp:include page="partials/Menu.jsp" />
				</div>
			</div>
			<div class="main">
				<div class="main_top">
					<h1>Моят профил:</h1>
					<div class="clear"></div>
				</div>
				<div class="register_form" class="table">
					<%User user = (User) session.getAttribute(BaseHttpServlet.LOGGED_USER_ATTRIBUTE_NAME); %>
					<ul>
						<li><span><%=user.getFirstName() %></span>
						<li><span><%=user.getLastName() %></span>
						<li><span><%=user.getUsername() %></span>
						<li><span><%=user.getEmail() %></span>
						<li><span><%=user.getAddress() %></span>
						<li><span><%=user.getPhoneNumber() %></span>
					</ul>
					<div class="clear"></div>
					<span style="float: center;" id="edit"><a href="./edit" class="btn btn-info">Редактиране</a></span>
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