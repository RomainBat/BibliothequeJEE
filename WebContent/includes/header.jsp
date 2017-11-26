<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
  			<meta charset="utf-8"> 
			<title>Bibliothèque - <%=request.getParameter("title")%></title>
			<link rel="stylesheet" type="text/css" href="css/main.css" />
		</head>
				
		<body>
			<header>
				<div><%= session.getAttribute("nom") == null ? "Mode déconnecté" : session.getAttribute("nom")%></div>
				<nav>
					<ul>
						<li><a href="/BibliothequeJEE/Site?page=Recherche">Recherche</a></li>
						<% if (session.getAttribute("id") == null){ %>
						<li><a href="/BibliothequeJEE/Site?page=Connexion">Connexion</a></li>
						<% } else { %>
							<% if ((boolean)session.getAttribute("isBibliothecaire")) { %>
						<li><a href="/BibliothequeJEE/Site?page=Gestion">Gestion des livres</a></li>
						<li><a href="/BibliothequeJEE/Site?page=Adherents">Gestion des adhérents</a></li>
							<% } else { %>
						<li><a href="/BibliothequeJEE/Site?page=Profil">Mon compte</a></li>
							<% } %>
						<li><a href="/BibliothequeJEE/Site?page=Deconnexion">Déconnexion</a></li>
						<% } %>
					</ul>
				</nav>
			</header>
