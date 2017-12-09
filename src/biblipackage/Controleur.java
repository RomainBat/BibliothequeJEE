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
	public static final int NON_CONNECTE = -1;
	public static final int CONNEXION_REFUSEE = 0;
	public static final int CONNEXION_ADMIN = 1;
	public static final int CONNEXION_UTILISATEUR = 2;
	
	private static final long serialVersionUID = 1L;
	private String page = "/";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controleur() {
        super();
        UtilisateurCollec.addUtilisateurToListeUtilisateurs(new Utilisateur("Admin","root","Raymond Bibliothécaire", true));
        UtilisateurCollec.addUtilisateurToListeUtilisateurs(new Utilisateur("Bob92","user","Robert Adhérent", false));
		LivreCollec.creerNouveauLivre("Les chaussettes chaudes","Maurice",2);
		LivreCollec.creerNouveauLivre("La baignoire","Baudelaire",4);
		Operation.nouvelleReservation(UtilisateurCollec.getUtilisateurParIdentifiant("Bob92"), LivreCollec.getLivreParId(0));
		Operation.nouvelEmprunt(UtilisateurCollec.getUtilisateurParIdentifiant("Bob92"), LivreCollec.getLivreParId(1));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Liens des différents templates
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    if (session.isNew()) {
			session.setAttribute("etatConnexion", NON_CONNECTE);
	    }

		if (request.getParameter("page") != null) {
			switch(request.getParameter("page")) {
			case "Recherche" :
				page = "/Recherche.jsp";
				break;
			case "Connexion" :
				switch((Integer)session.getAttribute("etatConnexion")) {
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
				request.setAttribute("adherents", UtilisateurCollec.getUtilisateurs());
				page = "/Adherents.jsp";
				break;
			case "Gestion" :
				page = "/Gestion.jsp";
				break;
			case "Deconnexion" :
				page = "/Site?page=Connexion";
				session.setAttribute("etatConnexion", NON_CONNECTE);
				session.invalidate();
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
	    HttpSession session = request.getSession();
	    if (session.isNew()) {
			session.setAttribute("etatConnexion", NON_CONNECTE);
	    }
		switch(request.getParameter("formulaire")) {
		case "connexion_connexion" :
			tryConnexion(request.getParameter("id"), request.getParameter("mdp"), session);
			break;
		case "recherche_reserver" :
			Operation.nouvelleReservation(UtilisateurCollec.getUtilisateurParIdentifiant((String)session.getAttribute("id")), LivreCollec.getLivreParId(Integer.parseInt(request.getParameter("livre"))));
		case "recherche_rechercher" :
		case "gestion_rechercher" :
		case "adherents_rechercher" :
			request.setAttribute("livresRecherches",LivreCollec.rechercherLivres(request.getParameter("titre"), request.getParameter("auteur")));
			break;
		case "profil_annuler" :
			Operation.annulerReservation(Integer.parseInt(request.getParameter("resa")));
			break;
		case "gestion_ajouter" :
			LivreCollec.creerNouveauLivre(request.getParameter("titre"),request.getParameter("auteur"),Integer.parseInt(request.getParameter("nombre")));				
			break;
		case "gestion_valider" :
			LivreCollec.modifierNombreExemplaires(Integer.parseInt(request.getParameter("livre")), Integer.parseInt(request.getParameter("difference")));
			request.setAttribute("livresRecherches",LivreCollec.rechercherLivres(request.getParameter("titre"), request.getParameter("auteur")));
			break;
		case "gestion_supprimer" :
			Operation.supprimerLivre(Integer.parseInt(request.getParameter("livre")));
			request.setAttribute("livresRecherches",LivreCollec.rechercherLivres(request.getParameter("titre"), request.getParameter("auteur")));
			break;
		case "adherents_emprunter_dereserver" :
			Operation.annulerReservation(Integer.parseInt(request.getParameter("reservation")));
		case "adherents_emprunter" :
			Operation.nouvelEmprunt(UtilisateurCollec.getUtilisateurParIdentifiant(request.getParameter("adherent")), LivreCollec.getLivreParId(Integer.parseInt(request.getParameter("livre"))));
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
	public void tryConnexion(String id, String mdp, HttpSession session) throws IOException {
		Utilisateur utilisateur = UtilisateurCollec.utilisateurExiste(id,mdp);
		if (utilisateur != null) {
			session.setAttribute("id", utilisateur.getIdentifiant());
			session.setAttribute("isBibliothecaire",utilisateur.getIsBibliothecaire());
			session.setAttribute("nom",utilisateur.getNom());
			if (utilisateur.getIsBibliothecaire()) {
				session.setAttribute("etatConnexion", CONNEXION_ADMIN);
			}
			else {
				session.setAttribute("etatConnexion", CONNEXION_UTILISATEUR);
			}
		}
		else
			session.setAttribute("etatConnexion", CONNEXION_REFUSEE);
	}
}
