<%@page import="servlets.BaseHttpServlet"%>
<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8" %>
<div class="header_top">
	<div class="logo">
		<a href="./"><img src="../resources/images/pizza/logo1.png" alt="Pizzeria Logo" /></a>
		<% if (BaseHttpServlet.isAdmin(request)) { %>
		<span style="float:right;"><a href="./admin/" class="btn btn-success">Admin Panel</a></span>
		<% } %>
	</div>
	<div class="clear"></div>
</div>