<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="biblipackage.Livre" %>
<%
String userId = null;
if(session.getAttribute("id") == null){
	response.sendRedirect("Connexion");
}else if (!(boolean)session.getAttribute("isBibliothecaire")) {
	response.sendRedirect("Profil");
} else {
	userId = (String) session.getAttribute("id");
}

Livre[] livresRecherches = (Livre[]) request.getAttribute("livresRecherches");
%>

	<jsp:include page="includes/header.jsp">
		<jsp:param name="title" value="Gestion" />
	</jsp:include>

	<h1>Espace bibliothécaires</h1>
	<h2>Gestion des emprunts</h2>

	<h3>Ajouter un livre</h3>
	<form action="Gestion" method="POST">
		<label for="auteur">Auteur :</label> <input type="text" name="auteur">
		<label for="titre">Titre :</label> <input type="text" name="titre">
		<label for="nombre">Nombre :</label>
		<select name="nombre">
			<% for(int i =1; i<10; i++){ %>
			<option value="<%= i %>"><%= i %></option>
			<% } %>
			<option value="50">50</option>
			<option value="100">100</option>
		</select>
		<button type="submit">Ajouter</button>
	</form>

	<h3>Gérer les exemplaires</h3>

	<form action="Gestion" method="POST">
		<label for="auteur">Auteur :</label> <input type="text" name="auteur">
		<label for="titre">Titre :</label> <input type="text" name="titre">
		<button type="submit">Rechercher</button>
	</form>
	<ul class="liste-match">
	<% if(livresRecherches != null) {
		for(int k = 0; k<livresRecherches.length; k++){
		Livre livre = livresRecherches[k];%>
		<li class="match">
			<span class="auteur"><%=livre.getAuteur()%></span>
			<span class="titre"><%=livre.getTitre()%></span>
			<span class="disponibilite"><%=livre.getNb_restant()%> exemplaire(s) disponible(s)</span>
			<% if (livre.getNb_restant()>0) { %>
			<form action="Gestion" method="POST">
				<input type="hidden" name="livre" value="<%=livre.getId()%>"/>
				<% if (request.getParameter("titre") != null){ %>
				<input type="hidden" name="titre" value="<%=request.getParameter("titre")%>"/>
				<% } if (request.getParameter("auteur") != null){ %> 
				<input type="hidden" name="auteur" value="<%=request.getParameter("auteur")%>"/>
				<label for="difference">Ajouter ou retirer :</label>
				<select name="difference">
					<% for(int j =-10; j<10; j++){ %>
					<option <% if (j==0) out.print("selected"); %> value="<%= j %>"> <%= j %></option>
					<% } %>
					<option value="50">50</option>
					<option value="100">100</option>
				</select>
				<% } %>
				<button type="submit">OK</button>
			</form>
			<% } %>
		</li>
	<% } %>
	<% } %>
	</ul>
	
	<jsp:include page="includes/footer.jsp" />