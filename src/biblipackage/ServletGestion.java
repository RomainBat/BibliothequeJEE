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
 * Servlet implementation class Gestion
 */
@WebServlet("/Gestion")
public class ServletGestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestion() {
        super();
        // TODO : Remove this
		Utilisateur.addUtilisateurToListeUtilisateurs(new Utilisateur("Admin","root","Administrateur Bibliothécaire", true));
		Livre.addLivreToListeLivres(new Livre("Les chaussettes chaudes","Charles Baudelaire",2));
		Livre.addLivreToListeLivres(new Livre("La baignoire","Baudelaire",4));
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
		session = request.getSession();

		if ( request.getParameter("titre") != null || request.getParameter("auteur") != null) {
			if (!(request.getParameter("titre").equals("") && request.getParameter("auteur").equals(""))) {
				// Ajouter un livre
				if(request.getParameter("nombre") != null) {
					// TODO : vérifier que le livre n'existe pas déjà
					Livre.addLivreToListeLivres(new Livre(request.getParameter("titre"),request.getParameter("auteur"),Integer.parseInt(request.getParameter("nombre"))));				
				}
				// Faire une recherche en fonction des paramètres d'entrée
				else {
					request.setAttribute("livresRecherches",Livre.rechercherLivres(request.getParameter("titre"), request.getParameter("auteur")));
				}
			}
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Gestion.jsp");
		rd.forward(request, response);	
	}
}
