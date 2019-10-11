package modele;


//**************** Imports **************** 
import controleur.Controle;
import outils.connexion.Connection;


public abstract class Jeu {

	// **************** Propri�t�s ****************
	protected Controle controle;
	
	// **************** M�thodes ****************
	
	// M�thodes � red�finir dans les classes filles JeuClient et JeuServeur 
	/**
	 * R�ception d'une connection
	 * @param connection
	 */
	public abstract void setConnection(Connection connection);
	
	/**
	 * R�ception d'une information
	 * @param connection
	 * @param info
	 */
	public abstract void reception(Connection connection, Object info);
	
	/**
	 * Envoi d'une information
	 * @param connection
	 * @param info
	 */
	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}
	
}
