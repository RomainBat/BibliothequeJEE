package biblipackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implémentation d'un emprunt ou d'une réservation, avec le livre l'utilisateur concerné 
 * @author rbaticle
 */
public class Operation {
	private int id;
	private Livre livre;
	private Utilisateur emprunteurOuReserveur;
	
	private static int nextId = 0;

	private static HashMap<Integer,Operation> reservations = new HashMap<Integer,Operation>();
	private static HashMap<Integer,Operation> emprunts = new HashMap<Integer,Operation>();
	
	/**
	 * Constructeur
	 * @param livre le livre réservé ou emprunté
	 * @param emprunteurOuReserveur l'utilisateur ayant effectué l'opération
	 */
	public Operation(Livre livre, Utilisateur emprunteurOuReserveur) {
		//TODO gerer user id
		this.id = Operation.calculateNextId();
		this.livre = livre;
		this.emprunteurOuReserveur = emprunteurOuReserveur;
	}
	
	/**
	 * Getter de l'identifiant de l'opération
	 * @return l'identifiant de l'opération
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Getter du livre de l'opération
	 * @return le livre de l'opération
	 */
	public Livre getLivre() {
		return this.livre;
	}
	
	/**
	 * Getter de l'utilisateur lié à l'opération
	 * @return l'utilisateur lié à l'opération
	 */
	public Utilisateur getEmprunteurOuReserveur() {
		return this.emprunteurOuReserveur;
	}
	
	/**
	 * Retourne un nouvel identifiant unique parmi les opérations déjà existantes
	 * @return un entier unique parmi les opérations
	 */
	private static int calculateNextId() {
		return nextId++;
	}
	
	/**
	 * Effectue la réservation d'un livre si un exemplaire est disponible
	 * @param reservant l'utilisateur qui réserve le livre
	 * @param livre le livre réservé
	 * @return true si la réservation a été effectuée, false sinon
	 */
	public static boolean nouvelleReservation(Utilisateur reservant, Livre livre) {
		Operation myOperation = new Operation(livre, reservant);
		if(Livre.getListeLivres().get(livre.getId()).emprunterOuReserver()) {
			reservations.put(myOperation.id, myOperation);
			return true;
		}
		else return false;
	}
	
	/**
	 * Effectue l'emprunt d'un livre si un exemplaire est disponible
	 * @param emprunteur l'utilisateur qui emprunte le livre
	 * @param livre le livre emprunté
	 * @return true si la réservation a été effectuée, false sinon
	 */
	public static boolean nouvelEmprunt(Utilisateur emprunteur, Livre livre) {
		Operation myOperation = new Operation(livre, emprunteur);
		if(Livre.getListeLivres().get(livre.getId()).emprunterOuReserver()) {
			emprunts.put(myOperation.getId(), myOperation);
			return true;
		}
		else return false;
	}
	
	/**
	 * Annule une réservation
	 * @param operationId l'identifiant de la réservation
	 * @return true si l'annulation a bien été effectuée, false sinon
	 */
	public static boolean annulerReservation(int operationId) {
		Operation ope = reservations.get(operationId);
		ope.getLivre().restituerOuAnnulerReservation();
		return reservations.remove(ope.getId(), ope);
	}
	
	/**
	 * Annule un emprunt
	 * @param operationId l'identifiant de l'emprunt
	 * @return true si l'annulation a bien été effectuée, false sinon
	 */
	public static boolean annulerEmprunt(int operationId) {
		Operation ope = emprunts.get(operationId);
		ope.getLivre().restituerOuAnnulerReservation();
		
		return emprunts.remove(ope.getId(), ope);
	}
	
	/**
	 * Récupérer la liste des réservations effectuées par un utilisateur particulier
	 * @param utilisateurIdentifiant identifiant de l'utilisateur dont on récupère les réservations
	 * @return la liste des réservations effectuées par l'utilisateur
	 */
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
	
	/**
	 * Récupérer la liste des emprunts effectuées par un utilisateur particulier
	 * @param utilisateurIdentifiant identifiant de l'utilisateur dont on récupère les emprunts
	 * @return la liste des emprunts effectuées par l'utilisateur
	 */
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
	
	/**
	 * Supprimer un livre de la liste des livres et des listes de réservations et d'emprunts
	 * @param id l'identifiant du livre à supprimer
	 */
	public static void supprimerLivre(int id) {
		Livre.supprimerLivreDeListe(id);
		for(Map.Entry<Integer, Operation> entry : emprunts.entrySet()) {
		    Operation value = entry.getValue();
		    int key = entry.getKey();
		    if (value.getLivre().getId() == id)
		    		emprunts.remove(key);
		}
		for(Map.Entry<Integer, Operation> entry : reservations.entrySet()) {
		    Operation value = entry.getValue();
		    int key = entry.getKey();
		    if (value.getLivre().getId() == id)
		    		reservations.remove(key);
		}
	}
}
