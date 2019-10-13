package modele;



//**************** Imports **************** 
import outils.connexion.Connection;

import javax.swing.JPanel;

import controleur.Controle;




public class JeuClient extends Jeu{
	
	// **************** Propriétés ****************
	private Connection connection;
	
	
	
	// **************** Constructor ****************
	/**
	 * Constructor
	 * @param controle
	 */
	public JeuClient(Controle controle){
		super.controle = controle;
	}
	

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		if(info instanceof JPanel) { // Réception des murs
			controle.evenementModele(this, "ajout panel murs", info);
		}
		else { // Réception d'un personnage
			if(info instanceof Label) {
				controle.evenementModele(this, "ajout joueur", info);
			}
			else { // Réception du contenu du chat
				if (info instanceof String) {
					controle.evenementModele(this, "remplace chat", info);
				}
				else {
					if(info instanceof Integer) {
						controle.evenementModele(this, "son", info);
					}
				}
			}
		}
	}
	
	
	@Override
	public void deconnection(Connection connection) {
		System.exit(0);
	}
	
	
	/**
	 * Envoi d'une information vers l'ordinateur distant
	 * @param info
	 */
	public void envoi(Object info) {
		super.envoi(connection, info);
	}
	
	


}
