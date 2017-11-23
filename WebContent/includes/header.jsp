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
						<div><%= session.getAttribute("nom") == null ? "Mode déconnecté" : session.getAttribute("nom")%></div>
						<nav>
							<ul>
								<li><a href="Recherche">Recherche</a></li>
								<% if (session.getAttribute("id") == null){ %>
								<li><a href="Connexion">Connexion</a></li>
								<% } else { %>
									<% if ((boolean)session.getAttribute("isBibliothecaire")) { %>
								<li><a href="Gestion">Gestion des emprunts</a></li>
								<li><a href="Adherents">Gestion des adhérents</a></li>
									<% } else { %>
								<li><a href="Profil">Mon compte</a></li>
									<% } %>
								<li><a href="Deconnexion">Déconnexion</a></li>
								<% } %>
							</ul>
						</nav>
					</header>
