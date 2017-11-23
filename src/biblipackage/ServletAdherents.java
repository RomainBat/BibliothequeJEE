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
@WebServlet("/Adherents")
public class ServletAdherents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdherents() {
        super();
		Operation.addUtilisateurToListeUtilisateurs(new Utilisateur("Admin","root","Administrateur Bibliothécaire", true));
		Operation.addUtilisateurToListeUtilisateurs(new Utilisateur("John","user","Utilisateur Random", false));
		Operation.addLivreToListeLivres(new Livre("Les chaussettes chaudes","Charles Baudelaire",2));
		Operation.addLivreToListeLivres(new Livre("La baignoire","Baudelaire",4));
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
		request.setAttribute("adherents", Operation.getUtilisateurs());
		if ( request.getParameter("adherent") != null) {
			request.setAttribute("livresReserves", Operation.getLivresFromReservationsByUtilisateur(request.getParameter("adherent")));
			request.setAttribute("livresEmpruntes", Operation.getLivresFromEmpruntsByUtilisateur(request.getParameter("adherent")));
			request.setAttribute("selectedAdherent", request.getParameter("adherent"));
			
			// En cas d'emprunt
			if(request.getParameter("typeOperation") != null) {
				if(request.getParameter("typeOperation").equals("emprunt")) {	
					// Suite à une réservation
					if (request.getParameter("operation") != null) {
						Operation.annulerReservation(Integer.parseInt(request.getParameter("operation")));
					}
					Operation.nouvelEmprunt(Operation.getUtilisateurParIdentifiant(request.getParameter("adherent")), Operation.getLivreParId(Integer.parseInt(request.getParameter("livre"))));
				}
				// En cas de restitution
				else if (request.getParameter("typeOperation").equals("restitution")) {
					Operation.annulerEmprunt(Integer.parseInt(request.getParameter("operation")));
				}
			}
			// En cas de recherche
			if ( request.getParameter("titre") != null || request.getParameter("auteur") != null) {
				if(!request.getParameter("titre").equals("")) {
					if (!request.getParameter("auteur").equals("")) {
						request.setAttribute("livresRecherches", Operation.getLivresParTitreAuteur(request.getParameter("titre"),request.getParameter("auteur")));
					}
					else
						request.setAttribute("livresRecherches", Operation.getLivresParTitre(request.getParameter("titre")));
				}
				else {
					request.setAttribute("livresRecherches", Operation.getLivresParAuteur(request.getParameter("auteur")));
				}
			}
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Adherents.jsp");
		rd.forward(request, response);
	}

}
