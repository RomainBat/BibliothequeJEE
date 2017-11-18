<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="biblipackage.Utilisateur, biblipackage.Livre" %>
<%
String userId = null;
if(session.getAttribute("id") == null){
	response.sendRedirect("Connexion");
}else if (!(boolean)session.getAttribute("isBibliothecaire")) {
	response.sendRedirect("Profil");
} else {
	userId = (String) session.getAttribute("id");
}

Utilisateur adherents[] = (Utilisateur[]) request.getAttribute("adherents");
Livre livresEmpruntes[] = (Livre[]) request.getAttribute("livresEmpruntes");
Livre livresReserves[] = (Livre[]) request.getAttribute("livresReserves");
Livre livresRecherches[] = (Livre[]) request.getAttribute("livresRecherches");
%>

	<jsp:include page="includes/header.jsp">
		<jsp:param name="title" value="Emprunts" />
	</jsp:include>
	
	<h1>Espace bibliothécaires</h1>
	<h2>Gestion des adhérents</h2>
	<form action="Gestion">
		<h3>Adhérent</h3>
		<select name="adherent">
			<%for(int i = 0; i<adherents.length; i++){
				Utilisateur adherent = adherents[i];%>
			<option value="<%= adherent.getIdentifiant() %>"><%= adherent.getNom() %></option>
			<% } %>
		</select>
		<button type="submit">OK</button>
	</form>
	<% if(request.getParameter("adherent") != null){%>
	<h3>Emprunts et réservations</h3>
	<ul class="liste-emprunts">
	<% for(int i = 0; i<livresReserves.length; i++){
		Livre livre = livresReserves[i];%>
		<li class="reservation">
			<span class="auteur"><%=livre.getAuteur()%></span>
			<span class="titre"><%=livre.getTitre()%></span>
			<form action="Gestion" method="POST">
				<input type="hidden" name="adherent" value="<%=request.getParameter("adherent")%>"/>
				<input type="hidden" name="livre" value="<%=livre.getId()%>"/>
				<button type="submit">Emprunter</button>
			</form>
		</li>
	<% } %>
	<% for(int i = 0; i<livresEmpruntes.length; i++){
		Livre livre = livresEmpruntes[i];%>
		<li class="reservation">
			<span class="auteur"><%=livre.getAuteur()%></span>
			<span class="titre"><%=livre.getTitre()%></span>
			<form action="Gestion" method="POST">
				<input type="hidden" name="adherent" value="<%=request.getParameter("adherent")%>"/>
				<input type="hidden" name="livre" value="<%=livre.getId()%>"/>
				<button type="submit">Restituer</button>
			</form>
		</li>
	<% } %>
	</ul>
	<%}%>
	<%if(request.getParameter("auteur") != null || request.getParameter("titre") != null){%>
	<h3>Nouvel emprunt</h3>
	<form action="Gestion">
		<input type="hidden" name="adherent" value="<%=request.getParameter("adherent")%>"/>
		<label for="titre">Titre :</label><input type="text" name="titre">
		<label for="auteur">Auteur :</label><input type="text" name="auteur">
		<button type="submit">Rechercher</button>
	</form>
	<ul class="liste-match">
	<% for(int i = 0; i<livresRecherches.length; i++){
		Livre livre = livresRecherches[i];%>
		<li class="match">
			<span class="auteur"><%=livre.getAuteur()%></span>
			<span class="titre"><%=livre.getTitre()%></span>
			<span class="disponibilite"><%=livre.getNb_restant()%> exemplaire(s) disponible(s)</span>
			<% if (livre.getNb_restant()>0) { %>
			<form action="Gestion" method="POST">
				<input type="hidden" name="adherent" value="<%=request.getParameter("adherent")%>"/>
				<input type="hidden" name="livre" value="<%=livre.getId()%>"/>
				<% if (request.getParameter("titre") != null){ %>
				<input type="hidden" name="titre" value="<%=request.getParameter("titre")%>"/>
				<% } if (request.getParameter("auteur") != null){ %> 
				<input type="hidden" name="auteur" value="<%=request.getParameter("auteur")%>"/>
				<% } %>
				<button type="submit">Emprunter</button>
			</form>
			<% } %>
		</li>
	<% } %>
	</ul>
	<% } %>
	<jsp:include page="includes/footer.jsp" />
