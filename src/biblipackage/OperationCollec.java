package biblipackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Implémentation d'un emprunt ou d'une réservation, avec le livre l'utilisateur concerné 
 * @author rbaticle
 */
@Startup
@Local
@Singleton
public class OperationCollec {
	@EJB
	LivreCollec livreCollec;
	@EJB
	UtilisateurCollec utilisateurCollec;
	
	private int nextId = 0;

	private HashMap<Integer,Operation> reservations = new HashMap<Integer,Operation>();
	private HashMap<Integer,Operation> emprunts = new HashMap<Integer,Operation>();
	
	@PostConstruct
	void init() {
		nouvelleReservation("Bob92", 0);
        nouvelEmprunt("Bob92", 1);
	}
	
	/**
	 * Retourne un nouvel identifiant unique parmi les opérations déjà existantes
	 * @return un entier unique parmi les opérations
	 */
	private int calculateNextId() {
		return nextId++;
	}
	
	/**
	 * Effectue la réservation d'un livre si un exemplaire est disponible
	 * @param reservant l'utilisateur qui réserve le livre
	 * @param livre le livre réservé
	 * @return true si la réservation a été effectuée, false sinon
	 */
	public boolean nouvelleReservation(String reservant, int livre) {
		Operation myOperation = new Operation(livre, reservant, calculateNextId());
		if(livreCollec.getListeLivres().get(livre).emprunterOuReserver()) {
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
	public boolean nouvelEmprunt(String emprunteur, int livre) {
		Operation myOperation = new Operation(livre, emprunteur, calculateNextId());
		if(livreCollec.getListeLivres().get(livre).emprunterOuReserver()) {
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
	public boolean annulerReservation(int operationId) {
		Operation ope = reservations.get(operationId);
		livreCollec.getLivreParId(ope.getLivreId()).restituerOuAnnulerReservation();
		return reservations.remove(ope.getId(), ope);
	}
	
	/**
	 * Annule un emprunt
	 * @param operationId l'identifiant de l'emprunt
	 * @return true si l'annulation a bien été effectuée, false sinon
	 */
	public boolean annulerEmprunt(int operationId) {
		Operation ope = emprunts.get(operationId);
		livreCollec.getLivreParId(ope.getLivreId()).restituerOuAnnulerReservation();
		
		return emprunts.remove(ope.getId(), ope);
	}
	
	/**
	 * Récupérer la liste des réservations effectuées par un utilisateur particulier
	 * @param utilisateurIdentifiant identifiant de l'utilisateur dont on récupère les réservations
	 * @return la liste des réservations effectuées par l'utilisateur
	 */
	@Lock(LockType.READ)
	public Operation[] getLivresFromReservationsByUtilisateur(String utilisateurIdentifiant) {
		List<Operation> reservationsFiltres = new ArrayList<Operation>();
		for(Map.Entry<Integer, Operation> entry : reservations.entrySet()) {
		  Operation value = entry.getValue();
		  if(value.getEmprunteurOuReserveurId().equals(utilisateurIdentifiant)) {
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
	@Lock(LockType.READ)
	public Operation[] getLivresFromEmpruntsByUtilisateur(String utilisateurIdentifiant) {
		List<Operation> empruntsFiltres = new ArrayList<Operation>();
		for(Map.Entry<Integer, Operation> entry : emprunts.entrySet()) {
		  Operation value = entry.getValue();
		  if(value.getEmprunteurOuReserveurId().equals(utilisateurIdentifiant)) {
		  		empruntsFiltres.add(value);
		  }
		}
		return empruntsFiltres.toArray(new Operation[empruntsFiltres.size()]);
	}
	
	/**
	 * Supprimer un livre de la liste des livres et des listes de réservations et d'emprunts
	 * @param id l'identifiant du livre à supprimer
	 */
	public void supprimerLivre(int id) {
		livreCollec.supprimerLivreDeListe(id);
		for(Map.Entry<Integer, Operation> entry : emprunts.entrySet()) {
		  Operation value = entry.getValue();
		  int key = entry.getKey();
		  if (livreCollec.getLivreParId(value.getLivreId()).getId() == id)
		  		emprunts.remove(key);
		}
		for(Map.Entry<Integer, Operation> entry : reservations.entrySet()) {
		  Operation value = entry.getValue();
		  int key = entry.getKey();
		  if (livreCollec.getLivreParId(value.getLivreId()).getId() == id)
		  		reservations.remove(key);
		}
	}
	
	public class Operation {
		private int id;
		private int livreId;
		private String emprunteurOuReserveurId;

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
		public int getLivreId() {
			return this.livreId;
		}
		
		/**
		 * Getter de l'utilisateur lié à l'opération
		 * @return l'utilisateur lié à l'opération
		 */
		public String getEmprunteurOuReserveurId() {
			return this.emprunteurOuReserveurId;
		}
		
		/**
		 * Constructeur
		 * @param livre le livre réservé ou emprunté
		 * @param emprunteurOuReserveur l'utilisateur ayant effectué l'opération
		 */
		public Operation(int livreId, String emprunteurOuReserveurId, int id) {
			//TODO gerer user id
			this.id = id;
			this.livreId = livreId;
			this.emprunteurOuReserveurId = emprunteurOuReserveurId;
		}
	}
	
}
