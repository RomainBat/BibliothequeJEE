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
 * Servlet controlleur du site de gestion de la bibliothèque
 * @author athouary
 */
@WebServlet("/Site")
public class Controleur extends HttpServlet {
	private static final int NON_CONNECTE = -1;
	private static final int CONNEXION_REFUSEE = 0;
	private static final int CONNEXION_ADMIN = 1;
	private static final int CONNEXION_UTILISATEUR = 2;
	
	private static final long serialVersionUID = 1L;
	private String page = "/";
	private HttpSession session;
	int etatConnexion = NON_CONNECTE;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controleur() {
        super();
		Utilisateur.addUtilisateurToListeUtilisateurs(new Utilisateur("Admin","root","Administrateur Bibliothécaire", true));
		Utilisateur.addUtilisateurToListeUtilisateurs(new Utilisateur("Bob92","user","Utilisateur Commun", false));
		Livre.creerNouveauLivre("Les chaussettes chaudes","Maurice",2);
		Livre.creerNouveauLivre("La baignoire","Baudelaire",4);
		Operation.nouvelleReservation(Utilisateur.getUtilisateurParIdentifiant("Bob92"), Livre.getLivreParId(0));
		Operation.nouvelEmprunt(Utilisateur.getUtilisateurParIdentifiant("Bob92"), Livre.getLivreParId(1));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Liens des différents templates
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(false);

		if (request.getParameter("page") != null) {
			switch(request.getParameter("page")) {
			case "Recherche" :
				page = "/Recherche.jsp";
				break;
			case "Connexion" :
				switch(etatConnexion) {
				case CONNEXION_REFUSEE :
					// TODO: Set un attribute pour affichage erreur
				case NON_CONNECTE :
					page = "/Connexion.jsp";
					break;
				case CONNEXION_ADMIN :
					page = "/Site?page=Gestion";
					break;
				case CONNEXION_UTILISATEUR :
					page = "/Site?page=Profil";
					break;
				}
				break;
			case "Profil" :
				request.setAttribute("emprunts", Operation.getLivresFromEmpruntsByUtilisateur((String)session.getAttribute("id")));
				request.setAttribute("reservations", Operation.getLivresFromReservationsByUtilisateur((String)session.getAttribute("id")));
				page = "/Profil.jsp";
				break;
			case "Adherents" :
				request.setAttribute("adherents", Utilisateur.getUtilisateurs());
				page = "/Adherents.jsp";
				break;
			case "Gestion" :
				page = "/Gestion.jsp";
				break;
			case "Deconnexion" :
				page = "/Site?page=Connexion";
				session.invalidate();
				etatConnexion = NON_CONNECTE;
				break;
			default :
				page = "/404.jsp";
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				break;
			}
		}
		else
			page = "/Site?page=Connexion";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
		rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Gestion des formulaires
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(false);
		
		switch(request.getParameter("formulaire")) {
		case "connexion_connexion" :
			etatConnexion = connexion(request.getParameter("id"), request.getParameter("mdp"), request);
			break;
		case "recherche_reserver" :
			Operation.nouvelleReservation(Utilisateur.getUtilisateurParIdentifiant((String)session.getAttribute("id")), Livre.getLivreParId(Integer.parseInt(request.getParameter("livre"))));
		case "recherche_rechercher" :
		case "gestion_rechercher" :
		case "adherents_rechercher" :
			request.setAttribute("livresRecherches",Livre.rechercherLivres(request.getParameter("titre"), request.getParameter("auteur")));
			break;
		case "profil_annuler" :
			Operation.annulerReservation(Integer.parseInt(request.getParameter("resa")));
			break;
		case "gestion_ajouter" :
			Livre.creerNouveauLivre(request.getParameter("titre"),request.getParameter("auteur"),Integer.parseInt(request.getParameter("nombre")));				
			break;
		case "gestion_valider" :
			Livre.modifierNombreExemplaires(Integer.parseInt(request.getParameter("livre")), Integer.parseInt(request.getParameter("difference")));
			request.setAttribute("livresRecherches",Livre.rechercherLivres(request.getParameter("titre"), request.getParameter("auteur")));
			break;
		case "gestion_supprimer" :
			Operation.supprimerLivre(Integer.parseInt(request.getParameter("livre")));
			request.setAttribute("livresRecherches",Livre.rechercherLivres(request.getParameter("titre"), request.getParameter("auteur")));
			break;
		case "adherents_emprunter_dereserver" :
			Operation.annulerReservation(Integer.parseInt(request.getParameter("reservation")));
		case "adherents_emprunter" :
			Operation.nouvelEmprunt(Utilisateur.getUtilisateurParIdentifiant(request.getParameter("adherent")), Livre.getLivreParId(Integer.parseInt(request.getParameter("livre"))));
		case "adherents_selectionner" :
			request.setAttribute("livresReserves", Operation.getLivresFromReservationsByUtilisateur(request.getParameter("adherent")));
			request.setAttribute("livresEmpruntes", Operation.getLivresFromEmpruntsByUtilisateur(request.getParameter("adherent")));
			request.setAttribute("selectedAdherent", request.getParameter("adherent"));
			break;
		case "adherents_restituer" :
			Operation.annulerEmprunt(Integer.parseInt(request.getParameter("emprunt")));
			request.setAttribute("livresReserves", Operation.getLivresFromReservationsByUtilisateur(request.getParameter("adherent")));
			request.setAttribute("livresEmpruntes", Operation.getLivresFromEmpruntsByUtilisateur(request.getParameter("adherent")));
			request.setAttribute("selectedAdherent", request.getParameter("adherent"));
			break;
		default :
			break;
			
		}

		doGet(request, response);
	}
	
	/**
	 * Tente de connecter un utilisateurs avec les identifiants fournis
	 * @param id l'id fourni par l'utilisateur
	 * @param mdp le mdp fourni par l'utilisateur
	 * @param request la requête envoyée par le client
	 * @return CONNEXION_ADMIN si l'utilisateur est connecté et administrateur, CONNEXION_UTILISATEUR sinon, CONNEXION_REFUSEE si les identifiants de correspondent pas à un utilisateur en base
	 * @throws IOException
	 */
	private int connexion(String id, String mdp, HttpServletRequest request) throws IOException {
		Utilisateur user = Utilisateur.utilisateurExiste(id,mdp);
		if (user != null) {
			session = request.getSession();
			session.setAttribute("id", user.getIdentifiant());
			session.setAttribute("isBibliothecaire",user.getIsBibliothecaire());
			session.setAttribute("nom",user.getNom());
			session.setMaxInactiveInterval(30*60);
			
			if (user.getIsBibliothecaire()) {
				return CONNEXION_ADMIN;
			}
			else {
				return CONNEXION_UTILISATEUR;
			}
		}
		else
			return CONNEXION_REFUSEE;
	}
}
