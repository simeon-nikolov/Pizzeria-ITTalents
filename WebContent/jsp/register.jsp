<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Pizza-Bug | Регистрация</title>
<jsp:include page="partials/HeadResources.jsp" />
</head>
<body>
	<div id="left_side_content">&nbsp;</div>
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
					<h1>Регистрация :</h1>
					<div class="clear"></div>
				</div>
				<div class="register_form" class="table">
					<form name="login" action="./register" method="post">
						<div>
							<label for="user_name">Потребителско име: *</label> <input
								id="user_name" name="username" type="text"
								placeholder="User name" value="">
						</div>
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
							<label for="phone">Телефон: *</label> <input id="phone"
								type="text" name="phone" placeholder="Phone number" value="">
						</div>
						<div>
							<input type="submit" value="Регистрирай се"
								class="btn btn-success" />
						</div>
					</form>
					<div class="clear"></div>
				</div>
			</div>
			<jsp:include page="partials/Footer.jsp" />
		</div>
	</div>
</body>
</html>