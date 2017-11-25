<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="biblipackage.Livre" %>
<%
String userId = null;
if(session.getAttribute("id") != null){
	userId = (String) session.getAttribute("id");
}

Livre livresRecherches[] = (Livre[]) request.getAttribute("livresRecherches");
%>
    
	<jsp:include page="includes/header.jsp">
   		<jsp:param name="title" value="Recherche" />
	</jsp:include>

	<h1>Rechercher un livre</h1>
	<form action="?page=Recherche" method="POST">
		<input type="hidden" name="formulaire" value="recherche_rechercher"/>
	  	<label for="auteur">Auteur :</label> <input type="text" name="auteur">
	  	<label for="titre">Titre :</label> <input type="text" name="titre">
	  	<button type="submit">Rechercher</button>
	</form>
	<h1>Livres correspondants à votre recherche</h1>
	<ul class="liste-match">
		<% if(livresRecherches != null) {
		for(int i = 0; i<	livresRecherches.length; i++){
		Livre livre = livresRecherches[i];%>
		<li class="match">
			<span class="auteur"><%=livre.getAuteur()%></span>
			<span class="titre"><%=livre.getTitre()%></span>
			<span class="disponibilite"><%=livre.getNb_restant()%> exemplaire(s) disponible(s)</span>
			<% if (livre.getNb_restant()>0 && userId != null) { %>
			<form action="?page=Recherche" method="POST">
				<input type="hidden" name="formulaire" value="recherche_reserver"/>			
				<input type="hidden" name="livre" value="<%=livre.getId()%>"/>
				<% if (request.getParameter("titre") != null){ %>
				<input type="hidden" name="titre" value="<%=request.getParameter("titre")%>"/>
				<% } if (request.getParameter("auteur") != null){ %> 
				<input type="hidden" name="auteur" value="<%=request.getParameter("auteur")%>"/>
				<% } %>
				<button type="submit">Réserver</button>
			</form>
			<% } %>
		</li>
		<% } %>
		<% } %>
	</ul>

	<jsp:include page="includes/footer.jsp" />
