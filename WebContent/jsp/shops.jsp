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
<script src="resources/js/shops.js"></script>
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
						<p>Черни връх 55,</p>
						<p>ет.10 ап 50</p>
						<div class="address_data">
							<p>Phone:(02) 936 82 01</p>
							<p>Fax: (007) 003 20 33 0</p>
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
							<img src="resources/images/contact_img.jpg" alt="" />
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
			<jsp:include page="partials/Footer.jsp" />
		</div>
	</div>
</body>
</html>
