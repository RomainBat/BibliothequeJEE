<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Gestion de la bibliothèque - Recherche</title>
</head>
<body>
  <h2>Rechercher un livre</h2>
  <form action="Recherche.jsp">
    <label for="auteur">Auteur :</label> <input type="text" name="auteur">
    <label for="titre">Titre :</label> <input type="text" name="titre">
    <button type="submit">Rechercher</button>
  </form>
  <h1>Livres correspondants à  votre recherche</h1>
  <ul class="liste-recherche">
    <li class="livre"><span class="auteur">Georges R. R. Martins</span><span class="titre">A Game of Thrones</span><span class="disponibilite">3 exemplaire(s) disponible(s)</span><form action="Reserver" method="POST"><input type="hidden" name="idLivre" value="10"/><button type="submit">Réserver</button></form></li>
    <li class="livre"><span class="auteur">Georges R. R. Martins</span><span class="titre">A Dance with Dragons</span><span class="disponibilite">Aucun exemplaire disponible</span></li>
    <li class="livre"><span class="auteur">Georges R. R. Martins</span><span class="titre">A Storm of Swords</span><span class="disponibilite">1 exemplaire(s) disponible(s)</span><form action="Reserver" method="POST"><input type="hidden" name="idLivre" value="12"/><button type="submit">Réserver</button></form></li>
  </ul>
</body>
</html>
