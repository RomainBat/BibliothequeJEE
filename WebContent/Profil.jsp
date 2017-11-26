<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="biblipackage.Operation, biblipackage.Livre" %>
<%
String userId = null;
if(session.getAttribute("id") == null){
	response.sendRedirect("Connexion");
}else {
	userId = (String) session.getAttribute("id");
}

Operation emprunts[] = (Operation[]) request.getAttribute("emprunts");
Operation reservations[] = (Operation[]) request.getAttribute("reservations");
%>

  <jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Mon compte" />
    <jsp:param name="nom" value="<%= session.getAttribute(\"nom\") %>" />
  </jsp:include>

  <h1>Livres que vous avez réservés :</h1>
  <ul class="liste-reservations">
  <%if (reservations != null){ 
  for(int i = 0; i<reservations.length; i++){
		Operation reservation = reservations[i];
		Livre livre = reservation.getLivre();%>
		<li class="reservation">
			<span class="auteur"><%=livre.getAuteur()%></span>
			<span class="titre"><%=livre.getTitre()%></span>
			<form action="Site?page=Profil" method="POST">
				<input type="hidden" name="formulaire" value="profil_annuler">
				<input type="hidden" name="resa" value="<%=reservation.getId()%>"/>
				<button type="submit">Annuler</button>
			</form>
		</li>
	<% } %>
	<% } %>
  </ul>
  <h1>Livres que vous avez empruntés :</h1>
  <ul class="liste-emprunts">
    <%if (emprunts != null){  
    for(int i = 0; i<emprunts.length; i++){
		Livre livre = emprunts[i].getLivre();%>
		<li class="reservation">
			<span class="auteur"><%=livre.getAuteur()%></span>
			<span class="titre"><%=livre.getTitre()%></span>
		</li>
	<% } %>
	<% } %>
  </ul>

  <jsp:include page="includes/footer.jsp" />
