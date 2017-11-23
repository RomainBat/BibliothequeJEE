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
 * Servlet implementation class Profil
 */
@WebServlet("/Profil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfil() {
        super();
		Operation.addUtilisateurToListeUtilisateurs(new Utilisateur("Admin","root","Administrateur Bibliothécaire", true));
		Operation.addLivreToListeLivres(new Livre("Les chaussettes chaudes","Charles Baudelaire",2));
		Operation.addLivreToListeLivres(new Livre("La baignoire","Baudelaire",4));
		Operation.nouvelleReservation(Operation.getUtilisateurParIdentifiant("Admin"), Operation.getLivreParId(0));
		Operation.nouvelEmprunt(Operation.getUtilisateurParIdentifiant("Admin"), Operation.getLivreParId(1));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		request.setAttribute("emprunts", Operation.getLivresFromEmpruntsByUtilisateur((String)session.getAttribute("id")));
		request.setAttribute("reservations", Operation.getLivresFromReservationsByUtilisateur((String)session.getAttribute("id")));

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Profil.jsp");
		rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ( request.getParameter("resa") != null ) {
			Operation.annulerReservation(Integer.parseInt(request.getParameter("resa")));
		}
		doGet(request, response);
	}

}
