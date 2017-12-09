package biblipackage;

/**
 * Implémentation d'un livre avec son titre et son auteur. Il peut y avoir plusieurs exemplaires d'un même livre
 * @author rbaticle
 */
public class Livre {
	private int id;
	private String titre;
	private String auteur;
	private int nb_total;
	private int nb_restant;
	/**
	 * Constructeur
	 * @param titre Le titre du livre
	 * @param auteur L'auteur du livre
	 * @param nb Le nombre d'exemplaires initialement disponibles
	 */
	public Livre(String titre, String auteur, int nb, int id) {
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.nb_total = nb;
		this.nb_restant = nb;
	}
	
	/**
	 * Getter de l'identifiant du livre
	 * @return l'identifiant du livre
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Getter du titre du livre
	 * @return titre du livre
	 */
	public String getTitre() {
		return this.titre;
	}
	
	/**
	 * Getter de l'auteur du livre
	 * @return l'auteur du livre
	 */
	public String getAuteur() {
		return this.auteur;
	}
	
	/**
	 * Getter du nombre total d'exemplaires
	 * @return le nombre total d'exemplaires
	 */
	public int getNb_max() {
		return this.nb_total;
	}
	
	/**
	 * Setter du nombre total d'exemplaires 
	 * @param nb nombre total d'exemplaires 
	 * @return true si le nombre a bien été changé, false sinon
	 */
	public boolean setNb_max(int nb) {
		if (nb < 0)
			return false;
		else {
			this.nb_total = nb;
			return true;
		}		
	}
	
	/**
	 * Getter du nombre d'exemplaires disponibles (non réservés ou empruntés)
	 * @return le nombre d'exemplaires disponibles (non réservés ou empruntés)
	 */
	public int getNb_restant() {
		return this.nb_restant;
	}
	
	/**
	 * Setter du nombre d'exemplaires disponibles (non réservés ou empruntés)
	 * @param nb le nouveau nombre d'exemplaires disponibles
	 * @return true si le nombre a bien été changé, false sinon
	 */
	public boolean setNb_restant(int nb) {
		if(nb > this.nb_total || nb < 0) {
			return false;
		}
		else {
			this.nb_restant = nb;
			return true;
		}
	}
	
	/**
	 * Décrémente le nombre d'exemplaires restants s'il y en a
	 * @return true si la décrémentation a eu lieu, false sinon
	 */
	public boolean emprunterOuReserver() {
		if(this.nb_restant > 0) {
			this.nb_restant -= 1;
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Incrémente le nombre d'exemplaires disponibles s'il le dépasse pas le nombre total
	 * @return true si l'incrémentation a eu lieu, false sinon
	 */
	public boolean restituerOuAnnulerReservation() {
		if(this.nb_restant < this.nb_total) {
			this.nb_restant += 1;
			return true;
		}
		else
			return false;
	}
}
