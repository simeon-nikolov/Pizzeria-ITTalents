<%@page import="servlets.BaseHttpServlet"%>
<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8" %>
<!DOCTYPE html">
<html>
<head>
<title>Pizza-Bug | Начало</title>
<jsp:include page="partials/HeadResources.jsp" />
</head>
<body>
 	<div id="left_side_content">
	<% if (!(Boolean)request.getAttribute("auth")) { %>
		<jsp:include page="partials/LoginForm.jsp" />
	<% } else { %>
		<jsp:include page="partials/LoggedIn.jsp" />
	<% } %>
	</div>
	<div class="wrap">
		<div class="wrapper">
			<div class="header">
				<jsp:include page="partials/HeaderTop.jsp" />
				<div class="header_bottom">
					<jsp:include page="partials/Menu.jsp" />
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
			<jsp:include page="partials/Footer.jsp" />
		</div>
	</div>
</body>
</html>