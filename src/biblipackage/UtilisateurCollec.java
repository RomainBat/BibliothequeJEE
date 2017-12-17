package biblipackage;

import java.util.Collection;
import java.util.HashMap;

import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Local
@Singleton
public class UtilisateurCollec {
	private HashMap<String,Utilisateur> listeUtilisateurs = new HashMap<String,Utilisateur>();
	
	public void init() {
		addUtilisateurToListeUtilisateurs("Admin","root","Raymond Bibliothécaire", true);
	    addUtilisateurToListeUtilisateurs("Bob92","user","Robert Adhérent", false);
	}
	
	/**
	 * Récupérer un tableau avec tous les utilisateurs
	 * @return Un tableau avec tous les utilisateurs
	 */
	@Lock(LockType.READ)
	public Utilisateur[] getUtilisateurs() {
		Collection<Utilisateur> values = listeUtilisateurs.values();
	  return values.toArray(new Utilisateur[values.size()]);
	}
	
	/**
	 * Récupérer un utilisateur à partir de son identifiant
	 * @param identifiant l'identifiant de l'utilisateur à retourner
	 * @return l'utilisateur correspondant
	 */
	@Lock(LockType.READ)
	public Utilisateur getUtilisateurParIdentifiant(String identifiant) {
		return listeUtilisateurs.get(identifiant);
	}
	
	/**
	 * Ajouter un utilisateur à la liste des utilisateurs
	 * @param newUtilisateur l'utilisateur à ajouter à la liste
	 */
	public void addUtilisateurToListeUtilisateurs(String identifiant, String mdp, String nom, boolean isBibliothecaire) {
		Utilisateur newUtilisateur = new Utilisateur(identifiant, mdp, nom, isBibliothecaire);
		listeUtilisateurs.put(newUtilisateur.getIdentifiant(), newUtilisateur);
	}
	
	/**
	 * Vérifie l'existence d'un utilsateur à partir de son identifiant et son mot de passe
	 * @param id l'identifiant testé
	 * @param mdp l'identifiant testé
	 * @return l'utilisateur trouvé s'il y en a un, false sinon
	 */
	@Lock(LockType.READ)
	public boolean utilisateurExiste(String id, String mdp) {
		Utilisateur user;
		if (id != null) {
			if(getUtilisateurParIdentifiant(id) != null) {
				user = getUtilisateurParIdentifiant(id);
				if(user.getMdp().equals(mdp)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Lock(LockType.READ)
	public String getNom(String id) {
		return getUtilisateurParIdentifiant(id).getNom();
	}
	
	@Lock(LockType.READ)
	public boolean getIsBibliothecaire(String id) {
		return getUtilisateurParIdentifiant(id).getIsBibliothecaire();
	}
	
	private class Utilisateur {
		private String identifiant;
		private String mdp;
		private String nom;
		private boolean isBibliothecaire;
		
		/**
		 * Constructeur
		 * @param identifiant Identifiant de connexion de l'utilisateur (unique)
		 * @param mdp Mot de passe de l'utilisateur (à maintenir crypté)
		 * @param nom Nom d'affichage de l'utilisateur
		 * @param isBibliothecaire True si l'utilisateur est administrateur, false sinon 
		 */
		public Utilisateur(String identifiant, String mdp, String nom, boolean isBibliothecaire) {
			this.identifiant = identifiant;
			this.mdp = mdp;
			this.nom = nom;
			this.isBibliothecaire = isBibliothecaire;
		}
		
		/**
		 * Getter de l'identifiant 
		 * @return L'identifiant de l'utilisateur
		 */
		public String getIdentifiant() {
			return this.identifiant;
		}
		
		/**
		 * Getter du mot de passe
		 * @return le mot de passe de l'utilisateur
		 */
		public String getMdp() {
			return this.mdp;
		}
		
		/**
		 * Getter du nom
		 * @return le nom d'affichage de l'utilisateur
		 */
		public String getNom() {
			return this.nom;
		}
		
		/**
		 * Getter du statut (des droits)
		 * @return true si l'utilisateur est administrateur, false sinon
		 */
		public boolean getIsBibliothecaire() {
			return this.isBibliothecaire;
		}
	}	
}
