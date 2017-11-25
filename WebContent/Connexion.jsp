<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Connexion" />
</jsp:include>

	<h1>Me connecter</h1>

	<form action="/?page=Connexion" method="POST">
		<input type="hidden" name="formulaire" value="connexion_connexion"/>
		<label>Identifiant</label>
		<input type="text" name="id"/>
		<label>Mot de passe</label>
		<input type="password" name="mdp"/>
		<input type="submit" value="Connexion"/>
	</form>

<jsp:include page="includes/footer.jsp" />
