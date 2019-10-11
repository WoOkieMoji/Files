package modele;



//**************** Imports **************** 
import outils.connexion.Connection;
import controleur.Controle;




public class JeuClient extends Jeu{
	
	// **************** Propriétés ****************
	private Connection connection;
	
	

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		
	}
	
	/**
	 * Envoi d'une information vers l'ordinateur distant
	 * @param info
	 */
	public void envoi(Object info) {
		super.envoi(connection, info);
	}
	
	
	// **************** Constructor ****************
	/**
	 * Constructor
	 * @param controle
	 */
	public JeuClient(Controle controle){
		super.controle = controle;
	}

}
