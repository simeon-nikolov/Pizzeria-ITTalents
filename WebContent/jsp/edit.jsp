<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Pizza-Bug | Редактиране на профил</title>
<jsp:include page="partials/HeadResources.jsp" />
</head>
<body>
	<div id="left_side_content">
		<jsp:include page="partials/LoggedIn.jsp" /></div>
	<div class="wrap">
		<div class="wrapper">
			<div class="header">
				<jsp:include page="partials/HeaderTop.jsp" />
				<div class="header_bottom">
					<jsp:include page="partials/Menu.jsp" />
				</div>
			</div>
			<div class="main">
				<div class="main_top">
					<h1>Редактиране :</h1>
					<div class="clear"></div>
				</div>
				<div class="register_form" class="table">
					<form name="login" action="./edit" method="post">
						<div>
							<label for="first_name">Име: *</label> <input id="first_name"
								name="firstName" type="text" placeholder="First name" value="">
						</div>
						<div>
							<label for="last_name">Фамилно име: *</label> <input
								id="last_name" name="lastName" type="text"
								placeholder="Last name" value="">
						</div>
						<div>
							<label for="user_name">Потребителско име: *</label> <input
								id="user_name" name="username" type="text"
								placeholder="User name" value="">
						</div>
						<div>
							<label for="email">E-mail: *</label> <input id="email"
								type="text" name="e-mail" placeholder="E-mail" value="">
						</div>
						<div>
							<label for="password">Парола: *</label> <input id="password"
								type="password" name="first_password" placeholder="Password"
								value="">
						</div>
						<div>
							<label for="password2">Потвърди паролата: *</label> <input
								id="password2" name="second_password" type="password"
								placeholder="Confirm Password" value="">
						</div>
						<div>
							<label for="address">Адрес: *</label> <input id="address"
								type="text" name="addres" placeholder="Address" value="">
						</div>
						<div>
							<label for="phone">Телефон: *</label> <input id="Phone"
								type="text" name="phoneto" placeholder="Phone" value="">
						</div>
						<div>
							<input type="submit" value="Промени" class="btn btn-success" />
						</div>
					</form>
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