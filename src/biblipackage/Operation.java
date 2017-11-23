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
	
	public static boolean nouvelleReservation(Utilisateur reservant, Livre livre) {
		Operation myOperation = new Operation(livre, reservant);
		if(Livre.getListeLivres().get(livre.getId()).emprunterOuReserver()) {
			reservations.put(myOperation.id, myOperation);
			return true;
		}
		else return false;
	}
	
	public static boolean nouvelEmprunt(Utilisateur emprunteur, Livre livre) {
		Operation myOperation = new Operation(livre, emprunteur);
		if(Livre.getListeLivres().get(livre.getId()).emprunterOuReserver()) {
			emprunts.put(myOperation.getId(), myOperation);
			return true;
		}
		else return false;
	}
	
	public static boolean annulerReservation(int operationId) {
		Operation ope = reservations.get(operationId);
		ope.getLivre().restituerOuAnnulerReservation();
		return reservations.remove(ope.getId(), ope);
	}
	
	public static boolean annulerEmprunt(int operationId) {
		Operation ope = emprunts.get(operationId);
		ope.getLivre().restituerOuAnnulerReservation();
		
		return emprunts.remove(ope.getId(), ope);
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
}
