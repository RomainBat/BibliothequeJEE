package biblipackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LivreCollec {
	private static int nextId = 0;
	private static HashMap<Integer,Livre> listeLivres = new HashMap<Integer,Livre>();
	
	/**
	 * Retourne un nouvel identifiant unique parmi les livres déjà existants
	 * @return un entier unique parmi les livres
	 */
	public static int calculateNextId() {
		return nextId++;
	}
	
	/**
	 * Modifie le nombre d'exemplaires total et disponibles  
	 * @param id l'identifiant du livre modifié
	 * @param diff l'augmentation (ou diminution si négatif) du nombre d'exemplaire
	 */
	public static void modifierNombreExemplaires (int id, int diff) {
		Livre livre = getLivreParId(id);
		livre.setNb_max(livre.getNb_max() + diff);
		livre.setNb_restant(livre.getNb_restant() + diff);
	}
		
	/**
	 * Getter de la liste des livres
	 * @return la liste des livres
	 */
	public static HashMap<Integer,Livre> getListeLivres() {
		return listeLivres;
	}
	
	/**
	 * Ajouter un livre à la liste des livres
	 * @param newLivre le livre à ajouter
	 */
	private static void addLivreAListeLivres(Livre newLivre) {
		listeLivres.put(newLivre.getId(), newLivre);
	}
	
	/**
	 * Créer un livre s'il la combinaison auteur-titre n'existe pas déjà
	 * @param titre le titre du livre
	 * @param auteur l'auteur du livre
	 * @param nombre le nombre d'exemplaire de ce livre
	 */
	public static void creerNouveauLivre(String titre, String auteur, int nombre) {
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
	public static Livre getLivreParId(int id) {
		return listeLivres.get(id);
	}
	
	/**
	 * Récupérer la liste des livres écrits par un auteur en particulier
	 * @param auteur l'auteur des livres à retourner
	 * @return une liste de livres
	 */
	private static List<Livre> getListLivresParAuteur(String auteur) {
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
	private static Livre[] getLivresParAuteur(String auteur) {
		List<Livre> livresFiltres = getListLivresParAuteur(auteur);
		return livresFiltres.toArray(new Livre[livresFiltres.size()]);
	}
	
	/**
	 * Récupérer la liste des livres avec un titre en particulier
	 * @param titre le titre des livres à retourner
	 * @return une liste de livres
	 */
	private static List<Livre> getListLivresParTitre(String titre) {
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
	private static Livre[] getLivresParTitre(String titre) {
		List<Livre> livresFiltres = getListLivresParTitre(titre);
		return livresFiltres.toArray(new Livre[livresFiltres.size()]);
	}
	
	/**
	 * Récupérer un tableau de livres écrits par un auteur en particulier avec un titre particulier
	 * @param auteur l'auteur des livres à retourner
	 * @param titre le titre des livres à retourner
	 * @return un tableau de livres
	 */
	private static Livre[] getLivresParTitreAuteur(String titre, String auteur) {
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
	public static Livre[] rechercherLivres(String titre, String auteur) {
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
	
	/**
	 * Supprimer un livre de la liste de livres selon son identifiant.
	 * @param id l'identifiant du livre à supprimer
	 */
	public static void supprimerLivreDeListe(int id) {
		listeLivres.remove(id);
	}
}
