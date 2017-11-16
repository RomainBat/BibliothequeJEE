<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				<title>Bibliothèque - <%=request.getParameter("title")%></title>
				<link rel="stylesheet" href="style.css">
				</head>
				<body>
					<header>
						<div>Auxane Thouary</div>
						<nav>
							<ul>
								<li><a href="Recherche.jsp">Recherche</a></li>
								<li><a href="Connexion.jsp">Connexion</a></li>
								<li><a href="Profil.jsp">Mon compte</a></li>
								<li><a href="Gestion.jsp">Gestion des adhérents</a></li>
								<li><a href="Emprunts.jsp">Gestion des emprunts</a></li>
							</ul>
						</nav>
					</header>
