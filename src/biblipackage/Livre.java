package biblipackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Livre {
	private int id;
	private String titre;
	private String auteur;
	private int nb_total;
	private int nb_restant;
	
	private static int nextId = 0;

	private static HashMap<Integer,Livre> listeLivres = new HashMap<Integer,Livre>();
	
	public Livre(String titre, String auteur, int nb) {
		this.id = Livre.calculateNextId();
		this.titre = titre;
		this.auteur = auteur;
		this.nb_total = nb;
		this.nb_restant = nb;
	}

	private static int calculateNextId() {
		return nextId++;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public String getAuteur() {
		return this.auteur;
	}
	
	public int getNb_max() {
		return this.nb_total;
	}
	
	public boolean setNb_max(int nb) {
		if (nb < 0)
			return false;
		else {
			this.nb_total = nb;
			return true;
		}		
	}
	
	public int getNb_restant() {
		return this.nb_restant;
	}
	
	public boolean setNb_restant(int nb) {
		if(nb > this.nb_total || nb < 0) {
			return false;
		}
		else {
			this.nb_restant = nb;
			return true;
		}
	}
	
	public static void modifierNombreExemplaires (int id, int diff) {
		Livre livre = getLivreParId(id);
		livre.setNb_max(livre.getNb_max() + diff);
		livre.setNb_restant(livre.getNb_restant() + diff);
	}
	
	public boolean emprunterOuReserver() {
		if(this.nb_restant > 0) {
			this.nb_restant -= 1;
			return true;
		}
		else
			return false;
	}
	
	public boolean restituerOuAnnulerReservation() {
		if(this.nb_restant < this.nb_total) {
			this.nb_restant += 1;
			return true;
		}
		else
			return false;
	}
	
	//LISTE DE LIVRES MANAGEMENT
	
	public static HashMap<Integer,Livre> getListeLivres() {
		return listeLivres;
	}
	
	private static void addLivreAListeLivres(Livre newLivre) {
		listeLivres.put(newLivre.getId(), newLivre);
	}
	
	public static void creerNouveauLivre(String titre, String auteur, int nombre) {
		if (!(titre.equals("") || auteur.equals(""))) {
			if(nombre > 0 && getLivresParTitreAuteur(titre, auteur).length == 0) {
				Livre.addLivreAListeLivres(new Livre(titre,auteur,nombre));				
			}
		}
	}
	
	public static Livre getLivreParId(int id) {
		return listeLivres.get(id);
	}
	
	private static List<Livre> getListLivresParAuteur(String auteur) {
		List<Livre> livresFiltres = new ArrayList<Livre>();
		for(Map.Entry<Integer, Livre> entry : listeLivres.entrySet()) {
		    Livre value = entry.getValue();
		    if(value.getAuteur().equals(auteur)) {
		    		livresFiltres.add(value);
		    }
		}
		return livresFiltres;
	}
	
	public static Livre[] getLivresParAuteur(String auteur) {
		List<Livre> livresFiltres = getListLivresParAuteur(auteur);
		return livresFiltres.toArray(new Livre[livresFiltres.size()]);
	}
	
	private static List<Livre> getListLivresParTitre(String titre) {
		List<Livre> livresFiltres = new ArrayList<Livre>();
		for(Map.Entry<Integer, Livre> entry : listeLivres.entrySet()) {
		    Livre value = entry.getValue();
		    if(value.getTitre().equals(titre)) {
		    		livresFiltres.add(value);
		    }
		}
		return livresFiltres;
	}
	
	public static Livre[] getLivresParTitre(String titre) {
		List<Livre> livresFiltres = getListLivresParTitre(titre);
		return livresFiltres.toArray(new Livre[livresFiltres.size()]);
	}
	
	public static Livre[] getLivresParTitreAuteur(String titre, String auteur) {
		List<Livre> livresFiltres = getListLivresParTitre(titre);		 
		Iterator<Livre> it = livresFiltres.iterator();
		while (it.hasNext()) {
			Livre livre = it.next();
			if (!livre.getAuteur().equals(auteur)) {
				it.remove();
			}
		}
		return livresFiltres.toArray(new Livre[livresFiltres.size()]);
	}
	
	public static Livre[] rechercherLivres(String titre, String auteur) {
		// TODO Enlever si on appelle la methode que audn on la recherche
		if ( titre != null || auteur != null) {
			if(!titre.equals("")) {
				if (!auteur.equals("")) {
					return Livre.getLivresParTitreAuteur(titre,auteur);
				}
				else
					return Livre.getLivresParTitre(titre);
			}
			else {
				return Livre.getLivresParAuteur(auteur);
			}
		}
		return null;
	}
	
}
