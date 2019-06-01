<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>E-Commerce: Bem Vindo</title>
	</head>
<body>
	<%-- comentário JSP aqui: nossa primeira página JSP --%>
	<%
		String mensagem = "Bem vindo ao E-Commerce Escolpi!";
	 %>
	<% out.println(mensagem); %>
	<br>
	<%
		String desenvolvido = "Desenvolvido por Felipe Assis";
	 %>
	<%=desenvolvido %>
	<br>
	<%
		System.out.println("Tudo foi executado!");
	 %>
</body>
</html>