<%@page import="servlets.BaseHttpServlet"%>
<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8" %>
<!DOCTYPE html">
<html>
<head>
<title>Pizza-Bug | Начало</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="resources/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="resources/bootstrap-3.3.6-dist/css/bootstrap.css" rel="stylesheet"
	type="text/css" media="all" />
<script src="resources/js/jquery-2.2.0.min.js"></script>
<<<<<<< HEAD:WebContent/index.jsp
<script href="resources/js/custom.js"></script>
=======
<script src="resources/js/custom.js"></script>
>>>>>>> 4bc2b99d3a64c676718f0940b69c7535e5b8ad05:WebContent/home.jsp
</head>
<body>
	<div id="left_side_content">
	<% BaseHttpServlet auth = (BaseHttpServlet) request.getAttribute("auth"); %>
	<% if (!auth.isAuthenticated(request)) { %>
		<jsp:include page="partials/LoginForm.jsp" />
	<% } else { %>
		&nbsp;
	<% } %>
	</div>
	<div class="wrap">
		<div class="wrapper">
			<div class="header">
				<div class="header_top">
					<div class="logo">
						<a href="resources/#"><img src="resources/images/pizza/logo1.png" alt="" /></a>
					</div>
					<div class="clear"></div>
				</div>
				<div class="header_bottom">
					<div class="menu">
						<ul>
							<li><a href=""><img src="resources/images/list_img.png"
									alt="" />Начало</a></li>
							<li><a href="resources/menu.html"><img src="resources/images/list_img.png"
									alt="" />Меню</a></li>
							<li><a href="resources/about.html"><img src="resources/images/list_img.png"
									alt="" />За нас</a></li>
							<li><a href="resources/shops.html"><img src="resources/images/list_img.png"
									alt="" />Магазини</a></li>
						</ul>
					</div>
					<div class="header_img">
						<img src="resources/images/header_img.jpg" alt="" />
					</div>
				</div>
			</div>
			<div class="main">
				<div class="main_top">
					<h1>Добре дошли в PIZZA BUG</h1>
					<div class="clear"></div>
				</div>
				<div class="content_top">
					<div class="content_top_img">
						<img src="resources/images/pizza/pizza_CherryPepperBombshell.png" alt="" />
					</div>
					<div class="content_top_data">
						<h3>Специално при нас</h3>
						<p>Направете пица по ваш вкус: изберете си тесто, съставки и размер, и получете вашата пица веднага на най-ниски цени.
						</p>
					</div>
					<div class="clear"></div>
				</div>
				<div class="boxes">
					<div class="box1">
						<div class="box_top">
							<h2>Пица</h2>
							<div class="clear"></div>
						</div>
						<div class="box1_img">
							<img src="resources/images/box1_img.jpg" alt="" />
						</div>
						<div class="box1_data">
						
							<h3>Неотразимият вкус</h3>
							<p>Нашите пици се приготвят по специална италианска рецепта, която се предава от 300 години в семейните традиции.</p>
							<p>
								<span>повече</span>
							</p>
						</div>
						<div class="clear"></div>
					</div>
					<div class="box2">
						<div class="box_top">
							<h2>За нас</h2>
							<div class="clear"></div>
						</div>
						<div class="box2_data">
							<h3>Прекрасно място за прекрасни хора</h3>
						</div>
						<div class="box1_img">
							<img src="resources/images/pizza/pizzeria1.jpg" alt="" />
						</div>
					</div>
					<div class="box3">
						<div class="box_top">
							<h2>Доставки</h2>
							<div class="clear"></div>
						</div>
						<div class="box1_data">
							<h3>Поръчайте тук</h3>
							<p>Може да поръчате по всяко време в работното ни време.</p>
							<p>Доставката е безплатна</p>
							<div class="box3_img">
								<img src="resources/images/delivery.png" alt="" />
							</div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
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