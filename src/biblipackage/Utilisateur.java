package biblipackage;



public class Utilisateur {
	private String identifiant;
	private String mdp;
	//TODO Ajouter un Nom

	public Utilisateur(String identifiant, String mdp) {
		this.identifiant = identifiant;
		this.mdp = mdp;
	}

	public String getIdentifiant() {
		return this.identifiant;
	}

	public String getMdp() {
		return this.mdp;
	}
}
