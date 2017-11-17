package biblipackage;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	public static boolean annulerReservation(Operation reservation) {
		listeLivres.get(reservation.getLivre()).restituerOuAnnulerReservation();
		return reservations.remove(reservation.getId(), reservation);
	}
	
	public static boolean annulerEmprunt(Operation emprunt) {
		listeLivres.get(emprunt.getLivre()).restituerOuAnnulerReservation();
		return emprunts.remove(emprunt.getId(), emprunt);
	}
	
	public static HashMap<Integer,Operation> getLivresFromReservationsByUtilisateur(String utilisateurIdentifiant) {
		HashMap<Integer,Operation> reservationsFiltres = new HashMap<Integer,Operation>();
		for(Map.Entry<Integer, Operation> entry : reservations.entrySet()) {
			int key = entry.getKey();
		    Operation value = entry.getValue();
		    if(value.getEmprunteurOuReserveur().getIdentifiant() == utilisateurIdentifiant) {
		    	reservationsFiltres.put(key, value);
		    }
		}
		return reservationsFiltres;
	}
	
	public static HashMap<Integer,Operation> getLivresFromEmpruntsByUtilisateur(String utilisateurIdentifiant) {
		HashMap<Integer,Operation> empruntsFiltres = new HashMap<Integer,Operation>();
		for(Map.Entry<Integer, Operation> entry : emprunts.entrySet()) {
			int key = entry.getKey();
		    Operation value = entry.getValue();
		    if(value.getEmprunteurOuReserveur().getIdentifiant() == utilisateurIdentifiant) {
		    	empruntsFiltres.put(key, value);
		    }
		}
		return empruntsFiltres;
	}
}
