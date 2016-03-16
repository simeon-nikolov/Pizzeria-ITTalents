<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf8"%>
<!DOCTYPE html">
<html>
<head>
<title>Pizza-Bug | Начало</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="bootstrap-3.3.6-dist/css/bootstrap.css" rel="stylesheet"
	type="text/css" media="all" />
<script src="js/jquery-2.2.0.min.js"></script>
<script src="js/custom.js"></script>
</head>
<body>
	${isAuthenticated}
	<div id="left_side_content">
		<div id="login" class="login">
			<form name="login">
				<label for="email">E-mail:</label>
				<input id="email" type="text" placeholder="E-mail" value="">
				<label for="password">Парола:</label>
				<input id="password" type="password" placeholder="Password" value="">
				<div>
					<button class="btn btn-success">Вход</button>
					<a class="btn btn-danger" href="register.html">Регистрация</a>
				</div>
			</form>
		</div>
	</div>
	<div class="wrap">
		<div class="wrapper">
			<div class="header">
				<div class="header_top">
					<div class="logo">
						<a href="#"><img src="images/pizza/logo1.png" alt="" /></a>
					</div>
					<div class="clear"></div>
				</div>
				<div class="header_bottom">
					<div class="menu">
						<ul>
							<li><a href="index.html"><img src="images/list_img.png"
									alt="" />Начало</a></li>
							<li><a href="menu.html"><img src="images/list_img.png"
									alt="" />Меню</a></li>
							<li><a href="about.html"><img src="images/list_img.png"
									alt="" />За нас</a></li>
							<li><a href="shops.html"><img src="images/list_img.png"
									alt="" />Магазини</a></li>
						</ul>
					</div>
					<div class="header_img">
						<img src="images/header_img.jpg" alt="" />
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
						<img src="images/pizza/pizza_CherryPepperBombshell.png" alt="" />
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
							<img src="images/box1_img.jpg" alt="" />
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
							<img src="images/pizza/pizzeria1.jpg" alt="" />
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
								<img src="images/delivery.png" alt="" />
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