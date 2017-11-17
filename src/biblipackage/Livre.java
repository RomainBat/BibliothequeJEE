package biblipackage;

import java.util.HashMap;

public class Livre {
	private int id;
	private String titre;
	private String auteur;
	private int nb_total;
	private int nb_restant;
	
	private static int nextId = 0;
	//private static HashMap<String,Integer> emprunts = new HashMap<String,Integer>();
	
	public Livre(String titre, String auteur, int nb) {
		this.id = this.getNextId();
		this.titre = titre;
		this.auteur = auteur;
		this.nb_total = nb;
		this.nb_restant = nb;
	}
	
	private static int getNextId() {
		return nextId++;
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
	
	public boolean emprunter(Utilisateur utilisateur) {
		if(this.nb_restant > 0) {
			this.nb_restant -= 1;
			return true;
		}
		else
			return false;
	}
	
	public boolean restituer(Utilisateur utilisateur) {
		if(this.nb_restant < this.nb_total) {
			this.nb_restant += 1;
			return true;
		}
		else
			return false;
	}
	
}
