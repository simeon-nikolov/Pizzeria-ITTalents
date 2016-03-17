<%@page import="servlets.BaseHttpServlet"%>
<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8" %>
<div class="header_top">
	<div class="logo">
		<a href="resources/#"><img src="resources/images/pizza/logo1.png" alt="" /></a>
		<% BaseHttpServlet auth = (BaseHttpServlet) request.getAttribute("auth"); %>
		<% if (auth.isAuthenticated(request)) { %>
			<span style="float:right;" id="logout"><a href="./logout" class="btn btn-danger">Logout</a></span>
		<% } %>
	</div>
	<div class="clear"></div>
</div>