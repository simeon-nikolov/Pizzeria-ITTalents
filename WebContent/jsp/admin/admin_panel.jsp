<%@page import="servlets.BaseHttpServlet"%>
<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8" %>
<!DOCTYPE html">
<html>
<head>
<title>Pizza-Bug | Начало</title>
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
					<p>
						<button type="button" class="btn btn btn-warning">Потребители</button>
						<button type="button" class="btn btn btn-warning">Поръчки</button>
						<button type="button" class="btn btn btn-warning">Продукти</button>
						<button type="button" class="btn btn btn-warning">Съставки</button>
					</p>
					<div class="clear"></div>
				</div>
				<div class="admin_panel">
					<h4>Потребители</h4>
					<div class="search_field"><input type="text" value="" placeholder="Search" /><button class="btn btn-sm btn-primary">Търси</button></div>
					<div class="container table">
						<div class="row table_head">
							<div class="col-sm-2">
								Име:
							</div>
							<div class="col-sm-2">
								E-mail:
							</div>
							<div class="col-sm-4">
								Адрес:
							</div>
							<div class="col-sm-4">
								Действия:
							</div>
						</div>
						<div class="row">
							<div class="col-sm-2">
								Arlie Artman
							</div>
							<div class="col-sm-2">
								email@example.com
							</div>
							<div class="col-sm-4">
								JOHN "GULLIBLE" DOE
							    CENTER FOR FINANCIAL ASSISTANCE TO DEPOSED NIGERIAN ROYALTY
							    421 E DRACHMAN
							    TUCSON AZ 85705-7598
							    USA
							</div>
							<div class="col-sm-4">
								<a class="btn btn-sm btn-success">Прегледай</a>
								<a class="btn btn-sm btn-warning">Промени</a>
								<a class="btn btn-sm btn-danger">Изтрий</a>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-2">
								Isela Licata
							</div>
							<div class="col-sm-2">
								email@example.com
							</div>
							<div class="col-sm-4">
								JOHN "GULLIBLE" DOE
							    CENTER FOR FINANCIAL ASSISTANCE TO DEPOSED NIGERIAN ROYALTY
							    421 E DRACHMAN
							    TUCSON AZ 85705-7598
							    USA
							</div>
							<div class="col-sm-4">
								<a class="btn btn-sm btn-success">Прегледай</a>
								<a class="btn btn-sm btn-warning">Промени</a>
								<a class="btn btn-sm btn-danger">Изтрий</a>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-2">
								Olene Oliverio
							</div>
							<div class="col-sm-2">
								email@example.com
							</div>
							<div class="col-sm-4">
								JOHN "GULLIBLE" DOE
							    CENTER FOR FINANCIAL ASSISTANCE TO DEPOSED NIGERIAN ROYALTY
							    421 E DRACHMAN
							    TUCSON AZ 85705-7598
							    USA
							</div>
							<div class="col-sm-4">
								<a class="btn btn-sm btn-success">Прегледай</a>
								<a class="btn btn-sm btn-warning">Промени</a>
								<a class="btn btn-sm btn-danger">Изтрий</a>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								Page: <a>1 2 3 ... 10</a>
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<jsp:include page="./partials/Footer.jsp" />
		</div>
	</div>
</body>
</html>