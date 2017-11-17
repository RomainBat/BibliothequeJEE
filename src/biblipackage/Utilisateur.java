package biblipackage;

public class Utilisateur {
	private String identifiant;
	private String mdp;
	private String nom;
	private boolean isBibliothecaire;
	
	public Utilisateur(String identifiant, String mdp, String nom, boolean isBibliothecaire) {
		this.identifiant = identifiant;
		this.mdp = mdp;
		this.nom = nom;
		this.isBibliothecaire = isBibliothecaire;
	}
	
	public String getIdentifiant() {
		return this.identifiant;
	}
	
	public String getMdp() {
		return this.mdp;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public boolean getIsBibliothecaire() {
		return this.isBibliothecaire;
	}
	
	public void setIdentifiant(String newIdentifiant) {
		this.identifiant = newIdentifiant;
	}
	
	public void setMdp(String newMdp) {
		this.mdp = newMdp;
	}
	
	public void setNom(String newNom) {
		this.nom = newNom;
	}
	
	public void setIsBibliothecaire(boolean newIsBibliothecaire) {
		this.isBibliothecaire = newIsBibliothecaire;
	}
}