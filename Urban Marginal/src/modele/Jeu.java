package modele;


//**************** Imports **************** 
import controleur.Controle;
import outils.connexion.Connection;


public abstract class Jeu {

	// **************** Propriétés ****************
	protected Controle controle;
	
	// **************** Méthodes ****************
	
	// Méthodes à redéfinir dans les classes filles JeuClient et JeuServeur 
	/**
	 * Réception d'une connection
	 * @param connection
	 */
	public abstract void setConnection(Connection connection);
	
	/**
	 * Réception d'une information
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
