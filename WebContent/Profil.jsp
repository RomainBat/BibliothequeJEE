<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Espace adhérent</title>
  
</head>
<body>
  <h1>Espace adhérent</h1>
  <h2>Livres que vous avez réservés :</h2>
  <ul class="liste-reservation">
    <li class="livre"><span class="auteur">Georges Washington</span><span class="titre">Déclaration d'indépendance</span><span class="date">10 Nov 2017 - 10:12</span><form action="Dereserver" method="POST"><button type="submit">Annuler la réservation</button></form></li>
    <li class="livre"><span class="auteur">Stephen King</span><span class="titre">Misery</span><span class="date">10 Nov 2017 - 10:21</span><form action="Dereserver" method="POST"><button type="submit">Annuler la réservation</button></form></li>
    <li class="livre"><span class="auteur">Georges R. R. Martins</span><span class="titre">A Game of Thrones</span><span class="date">12 Nov 2017 - 12:50</span><form action="Dereserver" method="POST"><button type="submit">Annuler la réservation</button></form></li>
    <li class="livre"><span class="auteur">Georges R. R. Martins</span><span class="titre">A Dance with Dragons</span><span class="date">12 Nov 2017 - 12:52</span><form action="Dereserver" method="POST"><button type="submit">Annuler la réservation</button></form></li>
    <li class="livre"><span class="auteur">Georges R. R. Martins</span><span class="titre">A Storm of Swords</span><span class="date">12 Nov 2017 - 12:55</span><form action="Dereserver" method="POST"><button type="submit">Annuler la réservation</button></form></li>
  </ul>
  <h2>Livres que vous avez empruntés :</h2>
  <ul class="liste-emprunts">
    <li class="livre"><span class="auteur">J. K. Rowling</span><span class="titre">L'apprenti sorcier</span><span class="date">10 Oct 2017 - 10:12</span></li>
    <li class="livre"><span class="auteur">J. K. Rowling</span><span class="titre">La chambre des secrets</span><span class="date">10 Oct 2017 - 10:21</span></li>
    <li class="livre"><span class="auteur">J. K. Rowling</span><span class="titre">Le prisonnier d'Azkaban</span><span class="date">12 Oct 2017 - 12:50</span></li>
    <li class="livre"><span class="auteur">J. K. Rowling</span><span class="titre">La coupe de feu</span><span class="date">12 Oct 2017 - 12:52</span></li>
    <li class="livre"><span class="auteur">J. K. Rowling</span><span class="titre">L'ordre du phoenix</span><span class="date">12 Oct 2017 - 12:55</span></li>
  </ul>
</body>
</html>
