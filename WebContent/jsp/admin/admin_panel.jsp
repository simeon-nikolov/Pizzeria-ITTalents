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
					<table class="container table table-bordered">
					<thead>
						<tr class="row table_head">
							<th class="col-sm-2">
								Име:
							</th>
							<th class="col-sm-2">
								E-mail:
							</th>
							<th class="col-sm-4">
								Адрес:
							</th>
							<th class="col-sm-4">
								Действия:
							</th>
						</tr>
					</thead>
					<tbody>
						<tr class="row">
							<td class="col-sm-2">
								Arlie Artman
							</td>
							<td class="col-sm-2">
								email@example.com
							</td>
							<td class="col-sm-4">
								JOHN "GULLIBLE" DOE
							    CENTER FOR FINANCIAL ASSISTANCE TO DEPOSED NIGERIAN ROYALTY
							    421 E DRACHMAN
							    TUCSON AZ 85705-7598
							    USA
							</td>
							<td class="col-sm-4">
								<a class="btn btn-sm btn-success">Прегледай</a>
								<a class="btn btn-sm btn-warning">Промени</a>
								<a class="btn btn-sm btn-danger">Изтрий</a>
							</td>
						</tr>
						<tr class="row">
							<td class="col-lg-12" colspan="4">
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