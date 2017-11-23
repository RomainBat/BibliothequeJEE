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
 * Servlet implementation class Recherche
 */
@WebServlet("/Recherche")
public class ServletRecherche extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRecherche() {
        super();		
        Operation.addUtilisateurToListeUtilisateurs(new Utilisateur("Admin","root","Administrateur Bibliothécaire", true));
		Operation.addLivreToListeLivres(new Livre("Les chaussettes chaudes","Charles Baudelaire",2));
		Operation.addLivreToListeLivres(new Livre("La baignoire","Baudelaire",4));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Recherche.jsp");
		rd.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		Operation.nouvelleReservation(Operation.getUtilisateurParIdentifiant((String)session.getAttribute("id")), Operation.getLivreParId(Integer.parseInt(request.getParameter("livre"))));
		doGet(request, response);
	}

}
