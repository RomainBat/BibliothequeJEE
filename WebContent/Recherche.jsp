<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Recherche" />
</jsp:include>

  <h1>Rechercher un livre</h1>
  <form action="Recherche.jsp">
    <label for="auteur">Auteur :</label> <input type="text" name="auteur">
    <label for="titre">Titre :</label> <input type="text" name="titre">
    <button type="submit">Rechercher</button>
  </form>
  <h1>Livres correspondants à votre recherche</h1>
  <ul class="liste-recherche">
    <li class="livre"><span class="auteur">Georges R. R. Martins</span><span class="titre">A Game of Thrones</span><span class="disponibilite">3 exemplaire(s) disponible(s)</span><form action="Reserver" method="POST"><input type="hidden" name="idLivre" value="10"/><button type="submit">Réserver</button></form></li>
    <li class="livre"><span class="auteur">Georges R. R. Martins</span><span class="titre">A Dance with Dragons</span><span class="disponibilite">Aucun exemplaire disponible</span></li>
    <li class="livre"><span class="auteur">Georges R. R. Martins</span><span class="titre">A Storm of Swords</span><span class="disponibilite">1 exemplaire(s) disponible(s)</span><form action="Reserver" method="POST"><input type="hidden" name="idLivre" value="12"/><button type="submit">Réserver</button></form></li>
  </ul>
  <ul class="liste-match">
    <li class="match">
      <span class="auteur">Georges R. R. Martins</span>
      <span class="titre">A Game of Thrones</span>
      <span class="disponibilite">2 exemplaire(s) disponible(s)</span>
      <form action="Recherche" method="POST">
        <input type="hidden" name="adherent" value="1"/>
        <input type="hidden" name="livre" value="11"/>
        <input type="hidden" name="auteur" value="Georges R. R. Martins"/>
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
        <input type="hidden" name="adherent" value="1"/>
        <input type="hidden" name="livre" value="14"/>
        <input type="hidden" name="auteur" value="Georges R. R. Martins"/>
        <button type="submit">Réserver</button>
      </form>
    </li>
  </ul>

<jsp:include page="includes/footer.jsp" />
