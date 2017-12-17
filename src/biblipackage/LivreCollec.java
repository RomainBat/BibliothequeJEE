package biblipackage;

import javax.ejb.Singleton;
import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.LockType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Local
@Singleton
public class LivreCollec {
	private static int nextId = 0;
	private static HashMap<Integer,Livre> listeLivres = new HashMap<Integer,Livre>();
	
	public void init() {
		creerNouveauLivre("Les chaussettes chaudes","Maurice",2);
        creerNouveauLivre("La baignoire","Baudelaire",4);
	}
	
	/**
	 * Retourne un nouvel identifiant unique parmi les livres déjà existants
	 * @return un entier unique parmi les livres
	 */
	public int calculateNextId() {
		return nextId++;
	}
	
	/**
	 * Modifie le nombre d'exemplaires total et disponibles  
	 * @param id l'identifiant du livre modifié
	 * @param diff l'augmentation (ou diminution si négatif) du nombre d'exemplaire
	 */
	public void modifierNombreExemplaires (int id, int diff) {
		Livre livre = getLivreParId(id);
		livre.setNb_max(livre.getNb_max() + diff);
		livre.setNb_restant(livre.getNb_restant() + diff);
	}
		
	/**
	 * Getter de la liste des livres
	 * @return la liste des livres
	 */
	@Lock(LockType.READ)
	public HashMap<Integer,Livre> getListeLivres() {
		return listeLivres;
	}
	
	/**
	 * Ajouter un livre à la liste des livres
	 * @param newLivre le livre à ajouter
	 */
	private void addLivreAListeLivres(Livre newLivre) {
		listeLivres.put(newLivre.getId(), newLivre);
	}
	
	/**
	 * Créer un livre s'il la combinaison auteur-titre n'existe pas déjà
	 * @param titre le titre du livre
	 * @param auteur l'auteur du livre
	 * @param nombre le nombre d'exemplaire de ce livre
	 */
	public void creerNouveauLivre(String titre, String auteur, int nombre) {
		if (!(titre.equals("") || auteur.equals(""))) {
			if(nombre > 0 && getLivresParTitreAuteur(titre, auteur).length == 0) {
				addLivreAListeLivres(new Livre(titre,auteur,nombre,calculateNextId()));				
			}
		}
	}
	
	/**
	 * Getter d'un livre selon son identifiants
	 * @param id l'identifiant du livre
	 * @return le livre dont l'identifiant est spécifié
	 */
	@Lock(LockType.READ)
	public Livre getLivreParId(int id) {
		return listeLivres.get(id);
	}
	
	/**
	 * Récupérer la liste des livres écrits par un auteur en particulier
	 * @param auteur l'auteur des livres à retourner
	 * @return une liste de livres
	 */
	@Lock(LockType.READ)
	private List<Livre> getListLivresParAuteur(String auteur) {
		List<Livre> livresFiltres = new ArrayList<Livre>();
		for(Map.Entry<Integer, Livre> entry : listeLivres.entrySet()) {
		    Livre value = entry.getValue();
		    if(value.getAuteur().matches(".*" + auteur + ".*")) {
		    		livresFiltres.add(value);
		    }
		}
		return livresFiltres;
	}
	
	/**
	 * Récupérer un tableau de livres écrits par un auteur en particulier
	 * @param auteur l'auteur des livres à retourner
	 * @return un tableau de livres
	 */
	@Lock(LockType.READ)
	private Livre[] getLivresParAuteur(String auteur) {
		List<Livre> livresFiltres = getListLivresParAuteur(auteur);
		return livresFiltres.toArray(new Livre[livresFiltres.size()]);
	}
	
	/**
	 * Récupérer la liste des livres avec un titre en particulier
	 * @param titre le titre des livres à retourner
	 * @return une liste de livres
	 */
	@Lock(LockType.READ)
	private List<Livre> getListLivresParTitre(String titre) {
		List<Livre> livresFiltres = new ArrayList<Livre>();
		for(Map.Entry<Integer, Livre> entry : listeLivres.entrySet()) {
		    Livre value = entry.getValue();
		    if(value.getTitre().matches(".*" + titre + ".*")) {
		    		livresFiltres.add(value);
		    }
		}
		return livresFiltres;
	}

	/**
	 * Récupérer la liste des livres avec un titre en particulier
	 * @param titre le titre des livres à retourner
	 * @return un tableau de livres
	 */
	@Lock(LockType.READ)
	private Livre[] getLivresParTitre(String titre) {
		List<Livre> livresFiltres = getListLivresParTitre(titre);
		return livresFiltres.toArray(new Livre[livresFiltres.size()]);
	}
	
	/**
	 * Récupérer un tableau de livres écrits par un auteur en particulier avec un titre particulier
	 * @param auteur l'auteur des livres à retourner
	 * @param titre le titre des livres à retourner
	 * @return un tableau de livres
	 */
	@Lock(LockType.READ)
	private Livre[] getLivresParTitreAuteur(String titre, String auteur) {
		List<Livre> livresFiltres = getListLivresParTitre(titre);		 
		Iterator<Livre> it = livresFiltres.iterator();
		while (it.hasNext()) {
			Livre livre = it.next();
			if (!livre.getAuteur().matches(".*" + auteur + ".*")) {
				it.remove();
			}
		}
		return livresFiltres.toArray(new Livre[livresFiltres.size()]);
	}
	
	/**
	 * Recherche les livres correspondant au titre et à l'auteur passés en paramètre (si string vide, paramètre ignoré)
	 * @param titre le titre des livres à retourner 
	 * @param auteur l'auteur des livres à retourner
	 * @return Un tableau de livres qui correspond à la recherche, null si aucune correspondance n'est faite
	 */
	@Lock(LockType.READ)
	public Livre[] rechercherLivres(String titre, String auteur) {
		// TODO Enlever si on appelle la methode que audn on la recherche
		if ( titre != null || auteur != null) {
			if(!titre.equals("")) {
				if (!auteur.equals("")) {
					return getLivresParTitreAuteur(titre,auteur);
				}
				else
					return getLivresParTitre(titre);
			}
			else {
				return getLivresParAuteur(auteur);
			}
		}
		return null;
	}
	
	public boolean emprunterOuReserver(int id) {
		return getLivreParId(id).emprunterOuReserver();
	}
	
	public boolean restituerOuAnnulerReservation(int id) {
		return getLivreParId(id).restituerOuAnnulerReservation();
	}
	
	/**
	 * Supprimer un livre de la liste de livres selon son identifiant.
	 * @param id l'identifiant du livre à supprimer
	 */
	public void supprimerLivreDeListe(int id) {
		listeLivres.remove(id);
	}
	
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
}
