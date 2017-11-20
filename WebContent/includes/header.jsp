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
						<div><%=session.getAttribute("nom")%></div>
						<nav>
							<ul>
								<li><a href="Recherche">Recherche</a></li>
								<li><a href="Connexion">Connexion</a></li>
								<li><a href="Profil">Mon compte</a></li>
								<li><a href="Gestion">Gestion des adhérents</a></li>
								<li><a href="Emprunts">Gestion des emprunts</a></li>
							</ul>
						</nav>
					</header>
