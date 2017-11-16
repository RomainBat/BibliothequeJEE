
public class Utilisateur {
	private String identifiant;
	private String mdp;
	
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
