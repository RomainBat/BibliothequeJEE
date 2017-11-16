<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Mon compte" />
  </jsp:include>

  <h1>Livres que vous avez réservés :</h1>
  <ul class="liste-reservation">
    <li class="livre"><span class="auteur">Georges Washington</span><span class="titre">Déclaration d'indépendance</span><form action="Dereserver" method="POST"><input type="hidden" name="idLivre" value="10"/><button type="submit">Annuler la réservation</button></form></li>
    <li class="livre"><span class="auteur">Stephen King</span><span class="titre">Misery</span><form action="Dereserver" method="POST"><input type="hidden" name="idLivre" value="12"/><button type="submit">Annuler la réservation</button></form></li>
    <li class="livre"><span class="auteur">Georges R. R. Martins</span><span class="titre">A Game of Thrones</span><form action="Dereserver" method="POST"><input type="hidden" name="idLivre" value="20"/><button type="submit">Annuler la réservation</button></form></li>
    <li class="livre"><span class="auteur">Georges R. R. Martins</span><span class="titre">A Dance with Dragons</span><form action="Dereserver" method="POST"><input type="hidden" name="idLivre" value="21"/><button type="submit">Annuler la réservation</button></form></li>
    <li class="livre"><span class="auteur">Georges R. R. Martins</span><span class="titre">A Storm of Swords</span><form action="Dereserver" method="POST"><input type="hidden" name="idLivre" value="22"/><button type="submit">Annuler la réservation</button></form></li>
  </ul>
  <h1>Livres que vous avez empruntés :</h1>
  <ul class="liste-emprunts">
    <li class="livre"><span class="auteur">J. K. Rowling</span><span class="titre">L'apprenti sorcier</span></li>
    <li class="livre"><span class="auteur">J. K. Rowling</span><span class="titre">La chambre des secrets</span></li>
    <li class="livre"><span class="auteur">J. K. Rowling</span><span class="titre">Le prisonnier d'Azkaban</span></li>
    <li class="livre"><span class="auteur">J. K. Rowling</span><span class="titre">La coupe de feu</span></li>
    <li class="livre"><span class="auteur">J. K. Rowling</span><span class="titre">L'ordre du phoenix</span></li>
  </ul>

  <jsp:include page="includes/footer.jsp" />
