<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="biblipackage.Operation, biblipackage.Utilisateur, biblipackage.Livre" %>
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
Operation emprunts[] = (Operation[]) request.getAttribute("livresEmpruntes");
Operation reservations[] = (Operation[]) request.getAttribute("livresReserves");
Livre livresRecherches[] = (Livre[]) request.getAttribute("livresRecherches");
%>

	<jsp:include page="includes/header.jsp">
		<jsp:param name="title" value="Emprunts" />
	</jsp:include>
	
	<h1>Espace bibliothécaires</h1>
	<h2>Gestion des adhérents</h2>
		<h3>Nom de l'adhérent</h3>
	<form action="?page=Adherents" method="POST">
		<input type="hidden" name="formulaire" value="adherents_selectionner"/>
		<select name="adherent">
			<% if(adherents != null) {
			for(int i = 0; i<adherents.length; i++){
				Utilisateur adherent = adherents[i];%>
			<option <%= adherent.getIdentifiant() == request.getAttribute("selectedAdherent") ? "selected" : ""%> value="<%= adherent.getIdentifiant() %>"><%= adherent.getNom() %></option>
			<% } %>
			<% } %>
		</select>
		<button type="submit">Sélectionner</button>
	</form>
	<% if(request.getParameter("adherent") != null){%>
	<h3>Emprunts et réservations</h3>
	<ul class="liste-emprunts">
	<% if(reservations != null) {
	for(int i = 0; i<reservations.length; i++){
		Livre livre = reservations[i].getLivre();%>
		<li class="reservation">
			<span class="auteur"><%=livre.getAuteur()%></span>
			<span class="titre"><%=livre.getTitre()%></span>
			<form action="?page=Adherents" method="POST">
				<input type="hidden" name="formulaire" value="adherents_emprunter_dereserver"/>
				<input type="hidden" name="adherent" value="<%=request.getParameter("adherent")%>"/>
				<input type="hidden" name="reservation" value="<%=reservations[i].getId()%>"/>
				<input type="hidden" name="typeOperation" value="emprunt"/>
				<input type="hidden" name="livre" value="<%=livre.getId()%>"/>
				<button type="submit">Emprunter</button>
			</form>
		</li>
	<% } %>
	<% } %>
	<% if(emprunts != null) {
	for(int i = 0; i<emprunts.length; i++){
		Livre livre = emprunts[i].getLivre();%>
		<li class="reservation">
			<span class="auteur"><%=livre.getAuteur()%></span>
			<span class="titre"><%=livre.getTitre()%></span>
			<form action="?page=Adherents" method="POST">
				<input type="hidden" name="formulaire" value="adherents_restituer"/>
				<input type="hidden" name="adherent" value="<%=request.getParameter("adherent")%>"/>
				<input type="hidden" name="emprunt" value="<%=emprunts[i].getId()%>"/>
				<input type="hidden" name="typeOperation" value="restitution"/>
				<input type="hidden" name="livre" value="<%=livre.getId()%>"/>
				<button type="submit">Restituer</button>
			</form>
		</li>
	<% } %>
	<% } %>
	</ul>
	<h3>Nouvel emprunt</h3>
	<form action="?page=Adherents" method="POST">
		<input type="hidden" name="formulaire" value="adherents_rechercher"/>
		<input type="hidden" name="adherent" value="<%=request.getParameter("adherent")%>"/>
		<label for="titre">Titre :</label><input type="text" name="titre" value="<%=request.getParameter("titre")%>">
		<label for="auteur">Auteur :</label><input type="text" name="auteur" value="<%=request.getParameter("auteur")%>">
		<button type="submit">Rechercher</button>
	</form>
	<ul class="liste-match">
	<% if(livresRecherches != null) {
	for(int i = 0; i<livresRecherches.length; i++){
		Livre livre = livresRecherches[i];%>
		<li class="match">
			<span class="auteur"><%=livre.getAuteur()%></span>
			<span class="titre"><%=livre.getTitre()%></span>
			<span class="disponibilite"><%=livre.getNb_restant()%> exemplaire(s) disponible(s)</span>
			<% if (livre.getNb_restant()>0) { %>
			<form action="?page=Adherents" method="POST">
				<input type="hidden" name="formulaire" value="adherents_emprunter"/>
				<input type="hidden" name="adherent" value="<%=request.getParameter("adherent")%>"/>
				<input type="hidden" name="typeOperation" value="restitution"/>
				<input type="hidden" name="livre" value="<%=livre.getId()%>"/>
				<% if (request.getParameter("titre") != null){ %>
				<input type="hidden" name="titre" value="<%=request.getParameter("titre") == null ? "" : request.getParameter("titre")%>"/>
				<% } if (request.getParameter("auteur") != null){ %> 
				<input type="hidden" name="auteur" value="<%=request.getParameter("auteur") == null ? "" : request.getParameter("auteur")%>"/>
				<% } %>
				<button type="submit">Emprunter</button>
			</form>
			<% } %>
		</li>
	<% } %>
	<% } %>
	</ul>
	<%}%>
	<jsp:include page="includes/footer.jsp" />
