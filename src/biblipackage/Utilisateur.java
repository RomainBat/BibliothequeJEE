package biblipackage;
import javax.ejb.Local;

/**
 * Implémentation d'un utilisateur du site de gestion de la bibliothèque, avec son nom, mot de passe, identifiant et ses droits
 * @author rbaticle
 */
@Local
public class Utilisateur {
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
	
	/**
	 * Setter de l'identifiant
	 * @param newIdentifiant Le nouvel identifiant
	 */
	public void setIdentifiant(String newIdentifiant) {
		this.identifiant = newIdentifiant;
	}
	
	/**
	 * Setter du mot de passe
	 * @param newMdp Le nouveau mot de passe
	 */
	public void setMdp(String newMdp) {
		this.mdp = newMdp;
	}
	
	/**
	 * Setter du nom
	 * @param newNom Le nouveau nom d'affichage
	 */
	public void setNom(String newNom) {
		this.nom = newNom;
	}
	
	/**
	 * Setter des droits
	 * @param newIsBibliothecaire Les nouveaux droits (true pour administrateur, false sinon)
	 */
	public void setIsBibliothecaire(boolean newIsBibliothecaire) {
		this.isBibliothecaire = newIsBibliothecaire;
	}
}