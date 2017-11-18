package biblipackage;

public class Livre {
	private int id;
	private String titre;
	private String auteur;
	private int nb_total;
	private int nb_restant;
	
	private static int nextId = 0;
	
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
	
}
