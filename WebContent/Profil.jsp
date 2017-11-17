<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="biblipackage.Livre" %>
<%
Livre livresEmpruntes[] = (Livre[]) request.getAttribute("livresEmpruntes");
Livre livresReserves[] = (Livre[]) request.getAttribute("livresReserves");
%>

  <jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Mon compte" />
  </jsp:include>

  <h1>Livres que vous avez réservés :</h1>
  <ul class="liste-reservations">
  <% for(int i = 0; i<livresReserves.length; i++){
		Livre livre = livresReserves[i];%>
		<li class="reservation">
			<span class="auteur"><%=livre.getAuteur()%></span>
			<span class="titre"><%=livre.getTitre()%></span>
			<form action="Profil" method="POST">
				<input type="hidden" name="livre" value="<%=livre.getId()%>"/>
				<button type="submit">Annuler la réservation</button>
			</form>
		</li>
	<% } %>
  </ul>
  <h1>Livres que vous avez empruntés :</h1>
  <ul class="liste-emprunts">
    <% for(int i = 0; i<livresEmpruntes.length; i++){
		Livre livre = livresEmpruntes[i];%>
		<li class="reservation">
			<span class="auteur"><%=livre.getAuteur()%></span>
			<span class="titre"><%=livre.getTitre()%></span>
		</li>
	<% } %>
  </ul>

  <jsp:include page="includes/footer.jsp" />
