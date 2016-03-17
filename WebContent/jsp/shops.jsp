<%@page import="java.nio.channels.SeekableByteChannel"%>
<%@page import="pizzeria.menu.Ingredient"%>
<%@page import="pizzeria.menu.Pizza"%>
<%@page import="servlets.PizzaServlet"%>
<%@page import="java.util.List"%>
<%@page import="servlets.BaseHttpServlet"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML >
<html>
<head>
<title>Pizza-Bug | Меню</title>
<jsp:include page="partials/HeadResources.jsp" />
<script src="js/shops.js"></script>
</head>
<body>
	<%
		BaseHttpServlet auth = (BaseHttpServlet) request.getAttribute("auth");
	%>
	<div id="left_side_content">
		<%
			if (!auth.isAuthenticated(request)) {
		%>
		<jsp:include page="partials/LoginForm.jsp" />
		<%
			} else {
		%>
		<jsp:include page="partials/LoggedIn.jsp" />
		<%
			}
		%>
	</div>
	<div class="wrap">
		<div class="wrapper">
			<div class="header">
				<div class="header_top">
					<div class="logo">
						<a href="index.html"><img src="images/pizza/logo1.png" alt="" /></a>
					</div>
					<div class="clear"></div>
				</div>
				<div class="header_bottom">
					<jsp:include page="partials/Menu.jsp" />
					<div class="header_img">
						<img src="resources/images/header_img.jpg" alt="" />
					</div>
				</div>
			</div>
			<div class="main">
				<div id="map_wrapper" class="map">
					<h4>Нашите магазини:</h4>
					<div id="map_canvas"></div>
				</div>
				<div id="store_names">
					<h4>Списък с нашите магазини:</h4>
					<div class="Store_L" rel="0" onclick="GetCenter(0);">София -
						Борово</div>
					<div class="Store_L" rel="1" onclick="GetCenter(1);">София -
						Гео Милев</div>
					<div class="Store_L" rel="2" onclick="GetCenter(2);">София -
						Красна Поляна</div>
					<div class="Store_L" rel="3" onclick="GetCenter(3);">София -
						Люлин</div>
					<div class="Store_L" rel="4" onclick="GetCenter(4);">София -
						Младост</div>
					<div class="Store_L" rel="5" onclick="GetCenter(5);">София -
						Студентски Град</div>
					<div class="Store_L" rel="6" onclick="GetCenter(6);">София -
						Център</div>
					<div class="Store_L" rel="7" onclick="GetCenter(7);">София -
						Южен парк</div>
				</div>
				<div class="contact_top">
					<div class="contact_address">
						<h4>Адрес за връзка:</h4>
						<p>500 Lorem Ipsum Dolor Sit,</p>
						<p>22-56-2-9 Sit Amet, Lorem</p>
						<div class="address_data">
							<p>Phone:(00) 222 666 444</p>
							<p>Fax: (000) 000 00 00 0</p>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="feedback">
					<div class="feed">
						<div class="main_top">
							<h2>Пишете ни:</h2>
							<div class="clear"></div>
						</div>
						<form action="send" method="post">
							<label>Име: </label> <input type="text" name="name" value="">
							<label>E-MAIL: </label><input type="text" name="email" value="">
							<label>Съобщение: </label>
							<textarea name="message"></textarea>
							<input type="submit" value="Изпрати">
						</form>
						<div class="contact_img">
							<img src="images/contact_img.jpg" alt="" />
						</div>
						<div class="clear"></div>
					</div>
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
