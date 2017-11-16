<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
	<jsp:include page="includes/header.jsp">
		<jsp:param name="title" value="Gestion" />
	</jsp:include>

	<h1>Espace bibliothécaires</h1>
	<h2>Gestion des emprunts</h2>

	<h3>Ajouter un livre</h3>
	<form action="Gestion.jsp">
		<label for="auteur">Auteur :</label> <input type="text" name="auteur">
		<label for="titre">Titre :</label> <input type="text" name="titre">
		<label for="nombre">Nombre :</label>
		<select name="nombre">
			<% for(int i =0; i<10; i++){ %>
			<option value="<%= i %>"><%= i %></option>
			<% } %>
			<option value="50">50</option>
			<option value="100">100</option>
		</select>
		<button type="submit">Ajouter</button>
	</form>

	<h3>Gérer les exemplaires</h3>

	<form action="Gestion">
		<label for="auteur">Auteur :</label> <input type="text" name="auteur">
		<label for="titre">Titre :</label> <input type="text" name="titre">
		<button type="submit">Rechercher</button>
	</form>
	<ul class="liste-match">
		<li class="match">
			<span class="auteur">Georges R. R. Martins</span>
			<span class="titre">A Game of Thrones</span>
			<span class="disponibilite">2 exemplaire(s) disponible(s)</span>
			<form action="Gestion" method="POST">
				<input type="hidden" name="livre" value="11"/>
				<input type="hidden" name="auteur" value="Georges R. R. Martins"/>
				<select name="difference">
					<% for(int i =-10; i<10; i++){ %>
					<option <% if (i==0) out.print("selected"); %> value="<%= i %>"> <%= i %></option>
					<% } %>
					<option value="50">50</option>
					<option value="100">100</option>
				</select>
				<button type="submit">Réserver</button>
			</form>
		</li>
		<li class="match">
			<span class="auteur">Georges R. R. Martins</span>
			<span class="titre">A Dance with Dragons</span>
			<span class="disponibilite">Aucun exemplaire disponible</span>
		</li>
		<li class="match">
			<span class="auteur">Georges R. R. Martins</span>
			<span class="titre">A Storm of Swords</span>
			<span class="disponibilite">1 exemplaire(s) disponible(s)</span>
			<form action="Recherche" method="POST">
				<input type="hidden" name="livre" value="12"/>
				<input type="hidden" name="auteur" value="Georges R. R. Martins"/>
				<button type="submit">Réserver</button>
			</form>
		</li>
	</ul>
