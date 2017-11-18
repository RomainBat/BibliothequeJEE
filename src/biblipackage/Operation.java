package biblipackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Operation {
	private int id;
	private Livre livre;
	private Utilisateur emprunteurOuReserveur;
	
	private static int nextId = 0;

	private static HashMap<String,Utilisateur> listeUtilisateurs = new HashMap<String,Utilisateur>();
	private static HashMap<Integer,Livre> listeLivres = new HashMap<Integer,Livre>();

	private static HashMap<Integer,Operation> reservations = new HashMap<Integer,Operation>();
	private static HashMap<Integer,Operation> emprunts = new HashMap<Integer,Operation>();
	
	public Operation(Livre livre, Utilisateur emprunteurOuReserveur) {
		//TODO gerer user id
		this.id = Operation.calculateNextId();
		this.livre = livre;
		this.emprunteurOuReserveur = emprunteurOuReserveur;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Livre getLivre() {
		return this.livre;
	}
	
	public Utilisateur getEmprunteurOuReserveur() {
		return this.emprunteurOuReserveur;
	}
	
	private static int calculateNextId() {
		return nextId++;
	}
	
	public static Utilisateur getUtilisateurParIdentifiant(String identifiant) {
		return listeUtilisateurs.get(identifiant);
	}
	
	public static void addLivreToListeLivres(Livre newLivre) {
		listeLivres.put(newLivre.getId(), newLivre);
	}
	
	public static void addUtilisateurToListeUtilisateurs(Utilisateur newUtilisateur) {
		listeUtilisateurs.put(newUtilisateur.getIdentifiant(), newUtilisateur);
	}
	
	public static boolean nouvelleReservation(Utilisateur reservant, Livre livre) {
		Operation myOperation = new Operation(livre, reservant);
		if(listeLivres.get(livre.getId()).emprunterOuReserver()) {
			reservations.put(myOperation.id, myOperation);
			return true;
		}
		else return false;
	}
	
	public static boolean nouvelEmprunt(Utilisateur emprunteur, Livre livre) {
		Operation myOperation = new Operation(livre, emprunteur);
		if(listeLivres.get(livre.getId()).emprunterOuReserver()) {
			emprunts.put(myOperation.id, myOperation);
			return true;
		}
		else return false;
	}
	
	public static boolean annulerOperation(int operationId) {
		Operation ope = reservations.get(operationId);
		listeLivres.get(ope.getLivre().getId()).restituerOuAnnulerReservation();
		return reservations.remove(ope.getId(), ope);
	}
	
	public static Livre getLivreParId(int id) {
		return listeLivres.get(id);
	}
	
	public static Operation[] getLivresFromReservationsByUtilisateur(String utilisateurIdentifiant) {
		List<Operation> reservationsFiltres = new ArrayList<Operation>();
		for(Map.Entry<Integer, Operation> entry : reservations.entrySet()) {
		    Operation value = entry.getValue();
		    if(value.getEmprunteurOuReserveur().getIdentifiant().equals(utilisateurIdentifiant)) {
		    		reservationsFiltres.add(value);
		    }
		}
		return reservationsFiltres.toArray(new Operation[reservationsFiltres.size()]);
	}
	
	public static Operation[] getLivresFromEmpruntsByUtilisateur(String utilisateurIdentifiant) {
		List<Operation>  empruntsFiltres = new ArrayList<Operation>();
		for(Map.Entry<Integer, Operation> entry : emprunts.entrySet()) {
		    Operation value = entry.getValue();
		    if(value.getEmprunteurOuReserveur().getIdentifiant().equals(utilisateurIdentifiant)) {
		    		empruntsFiltres.add(value);
		    }
		}
		return empruntsFiltres.toArray(new Operation[empruntsFiltres.size()]);
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
