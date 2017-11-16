<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Emprunts" />
</jsp:include>

	<h1>Espace bibliothécaires</h1>
	<h2>Gestion des adhérents</h2>
	<form action="Gestion">
	<h3>Adhérent</h3>
		<select name="adherent">
			<option value="1">Auxane Thouary</option>
			<option value="2">Romain Baticle</option>
			<option value="3">Rick Sanchez</option>
			<option value="4">Eric Cartman</option>
			<option value="5">Bart Simpson</option>
		</select>
		<button type="submit">OK</button>
	</form>
	<h3>Nouvel emprunt</h3>
	<form action="Gestion">
		<input type="hidden" name="adherent" value="1"/>
		<label for="titre">Titre :</label><input type="text" name="titre">
		<label for="auteur">Auteur :</label><input type="text" name="auteur">
		<button type="submit">Rechercher</button>
	</form>
	<h3>Emprunts et réservations</h3>
	<ul class="liste-emprunts">
		<li class="emprunt">
			<span class="auteur">Georges Washington</span>
			<span class="titre">Déclaration d'indépendance</span>
			<form action="Gestion" method="POST">
				<input type="hidden" name="adherent" value="1"/>
				<input type="hidden" name="livre" value="6"/>
				<button type="submit">Restituer</button>
			</form>
		</li>
		<li class="reservation">
			<span class="auteur">Stephen King</span>
			<span class="titre">Misery</span>
			<form action="Gestion" method="POST">
				<input type="hidden" name="adherent" value="1"/>
				<input type="hidden" name="livre" value="8"/>
				<button type="submit">Emprunter</button>
			</form>
		</li>
	</ul>
	<h3>Nouvel emprunt</h3>
	<ul class="liste-match">
		<li class="match">
			<span class="auteur">Georges R. R. Martins</span>
			<span class="titre">A Game of Thrones</span>
			<span class="disponibilite">2 exemplaire(s) disponible(s)</span>
			<form action="Gestion" method="POST">
				<input type="hidden" name="adherent" value="1"/>
				<input type="hidden" name="livre" value="11"/>
				<input type="hidden" name="auteur" value="Georges R. R. Martins"/>
				<button type="submit">Emprunter</button>
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
			<form action="Gestion" method="POST">
				<input type="hidden" name="adherent" value="1"/>
				<input type="hidden" name="livre" value="14"/>
				<input type="hidden" name="auteur" value="Georges R. R. Martins"/>
				<button type="submit">Emprunter</button>
			</form>
		</li>
	</ul>

	<jsp:include page="includes/footer.jsp" />
