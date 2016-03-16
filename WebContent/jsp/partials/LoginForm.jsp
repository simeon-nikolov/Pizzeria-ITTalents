<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8" %>
<div id="login" class="login">
	<form name="login" action="./login" method="post">
		<label for="username">Потребител:</label>
		<input id="username" name="username" type="text" placeholder="Username" value="">
		<label for="password">Парола:</label>
		<input id="password" name="password" type="password" placeholder="Password" value="">
		<div>
			<button class="btn btn-success">Вход</button>
			<a class="btn btn-danger" href="./register">Регистрация</a>
		</div>
	</form>
</div>