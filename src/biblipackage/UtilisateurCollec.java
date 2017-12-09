package biblipackage;

import java.util.Collection;
import java.util.HashMap;

public class UtilisateurCollec {
	private static HashMap<String,Utilisateur> listeUtilisateurs = new HashMap<String,Utilisateur>();
	
	/**
	 * Récupérer un tableau avec tous les utilisateurs
	 * @return Un tableau avec tous les utilisateurs
	 */
	public static Utilisateur[] getUtilisateurs() {
		Collection<Utilisateur> values = listeUtilisateurs.values();
	    return values.toArray(new Utilisateur[values.size()]);
	}
	
	/**
	 * Récupérer un utilisateur à partir de son identifiant
	 * @param identifiant l'identifiant de l'utilisateur à retourner
	 * @return l'utilisateur correspondant
	 */
	public static Utilisateur getUtilisateurParIdentifiant(String identifiant) {
		return listeUtilisateurs.get(identifiant);
	}
	
	/**
	 * Ajouter un utilisateur à la liste des utilisateurs
	 * @param newUtilisateur l'utilisateur à ajouter à la liste
	 */
	public static void addUtilisateurToListeUtilisateurs(Utilisateur newUtilisateur) {
		listeUtilisateurs.put(newUtilisateur.getIdentifiant(), newUtilisateur);
	}
	
	/**
	 * Vérifie l'existence d'un utilsateur à partir de son identifiant et son mot de passe
	 * @param id l'identifiant testé
	 * @param mdp l'identifiant testé
	 * @return l'utilisateur trouvé s'il y en a un, false sinon
	 */
	public static Utilisateur utilisateurExiste(String id, String mdp) {
		Utilisateur user;
		if (id != null) {
			if(getUtilisateurParIdentifiant(id) != null) {
				user = getUtilisateurParIdentifiant(id);
				if(user.getMdp().equals(mdp)) {
					return user;
				}
			}
		}
		return null;
	}
}
