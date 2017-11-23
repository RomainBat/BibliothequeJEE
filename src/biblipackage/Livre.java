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
	
	//LIST OF LIVRE MANAGEMENT
	
	public static HashMap<Integer,Livre> getListeLivres() {
		return listeLivres;
	}
	
	public static void addLivreToListeLivres(Livre newLivre) {
		listeLivres.put(newLivre.getId(), newLivre);
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
			if (livre.getAuteur().equals(auteur)) {
				it.remove();
			}
		}
		return livresFiltres.toArray(new Livre[livresFiltres.size()]);
	}
	
}
