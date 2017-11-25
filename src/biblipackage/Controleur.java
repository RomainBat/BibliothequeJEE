package biblipackage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controleur
 */
@WebServlet("/")
public class Controleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String page = "/";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controleur() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch(request.getParameter("page")) {
		case "Recherche" :
			page = "/Recherche.jsp";
			break;
		case "Connexion" :
			page = "/Connexion.jsp";
			break;
		case "Profil" :
			page = "/Profil.jsp";
			break;
		case "Adherents" :
			page = "/Adherents.jsp";
			break;
		case "Gestion" :
			page = "/Gestion.jsp";
			break;
		case "Deconnexion" :
			page = "/Connexion.jsp";
			break;
		default :
			break;
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		switch(request.getParameter("formulaire")) {
		case "connexion_connexion" :
			break;
		case "recherche_rechercher" :
			request.setAttribute("livresRecherches",Livre.rechercherLivres(request.getParameter("titre"), request.getParameter("auteur")));
			break;
		case "recherche_reserver" :
		case "profil_annuler" :
		case "gestion_ajouter" :
		case "gestion_rechercher" :
			request.setAttribute("livresRecherches",Livre.rechercherLivres(request.getParameter("titre"), request.getParameter("auteur")));
		case "gestion_valider" :
		case "adherents_selectionner" :
		case "adherents_emprunter_dereserver" :
		case "adherents_restituer" :
		case "adherents_rechercher" :
			request.setAttribute("livresRecherches",Livre.rechercherLivres(request.getParameter("titre"), request.getParameter("auteur")));
		case "adherents_emprunter" :
		default :		
			
		}

		doGet(request, response);
	}

}
