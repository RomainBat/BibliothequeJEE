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
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();        
        //TODO : Remove this
		Operation.addUtilisateurToListeUtilisateurs(new Utilisateur("Admin","root","Administrateur Bibliothécaire", true));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
		String mdp = request.getParameter("mdp");
		Utilisateur user;
		if (id != null) {
			if(Operation.getUtilisateurParIdentifiant(id) != null) {
				user = Operation.getUtilisateurParIdentifiant(id);
				if(user.getMdp().equals(mdp)) {
					HttpSession session = request.getSession();
					session.setAttribute("id", id);
					session.setAttribute("isBibliothecaire",user.getIsBibliothecaire());
					session.setAttribute("nom",user.getNom());
					session.setMaxInactiveInterval(30*60);
					
					if (user.getIsBibliothecaire()) {
						response.sendRedirect("Gestion");
					}
					else {
						response.sendRedirect("Profil");
					}
				}
				else {
					//TODO: Handle wrong password
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Connexion.jsp");
					rd.forward(request, response);
				}
			}
			else {
				//TODO: Handle wrong id
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Connexion.jsp");
				rd.forward(request, response);
			}
		}
	}
}
