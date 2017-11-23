package biblipackage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletEmprunts
 */
@WebServlet("/")
public class ServletAdherents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdherents() {
        super();
		Utilisateur.addUtilisateurToListeUtilisateurs(new Utilisateur("Admin","root","Administrateur Bibliothécaire", true));
		Utilisateur.addUtilisateurToListeUtilisateurs(new Utilisateur("John","user","Utilisateur Random", false));
		Livre.addLivreToListeLivres(new Livre("Les chaussettes chaudes","Charles Baudelaire",2));
		Livre.addLivreToListeLivres(new Livre("La baignoire","Baudelaire",4));
		Operation.nouvelEmprunt(Utilisateur.getUtilisateurParIdentifiant("John"), Livre.getLivreParId(1));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(false);
		// On set la liste utilisateurs
		request.setAttribute("adherents", Utilisateur.getUtilisateurs());
		// Si un utilisateur est sélectionné
		if ( request.getParameter("adherent") != null) {
			// On récupère ses réservations et emprunts
			request.setAttribute("livresReserves", Operation.getLivresFromReservationsByUtilisateur(request.getParameter("adherent")));
			request.setAttribute("livresEmpruntes", Operation.getLivresFromEmpruntsByUtilisateur(request.getParameter("adherent")));
			request.setAttribute("selectedAdherent", request.getParameter("adherent"));
			
			// En cas d'emprunt
			if(request.getParameter("typeOperation") != null) {
				if(request.getParameter("typeOperation").equals("emprunt")) {	
					// Suite à une réservation
					if (request.getParameter("reservation") != null) {
						Operation.annulerReservation(Integer.parseInt(request.getParameter("reservation")));
						request.setAttribute("livresReserves", Operation.getLivresFromReservationsByUtilisateur(request.getParameter("adherent")));
					}
					Operation.nouvelEmprunt(Utilisateur.getUtilisateurParIdentifiant(request.getParameter("adherent")), Livre.getLivreParId(Integer.parseInt(request.getParameter("livre"))));
					request.setAttribute("livresEmpruntes", Operation.getLivresFromEmpruntsByUtilisateur(request.getParameter("adherent")));
				}
				// En cas de restitution
				else if (request.getParameter("typeOperation").equals("restitution")) {
					Operation.annulerEmprunt(Integer.parseInt(request.getParameter("emprunt")));
					request.setAttribute("livresEmpruntes", Operation.getLivresFromEmpruntsByUtilisateur(request.getParameter("adherent")));
				}
			}
			// En cas de recherche
			request.setAttribute("livresRecherches",Livre.rechercherLivres(request.getParameter("titre"), request.getParameter("auteur")));
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Adherents.jsp");
		rd.forward(request, response);
	}

}
