package controleur;



//**************** Imports **************** 

import vue.EntreeJeu;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.net.ServerSocket;
import outils.connexion.ServeurSocket;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import vue.Arene;
import vue.ChoixJoueur;
import modele.Label;




public class Controle implements Global{

	// **************** Propriétés ****************
	private EntreeJeu frmEntreeJeu;
	private Jeu leJeu;
	private Arene frmArene; 
	private ChoixJoueur frmChoixJoueur;
	private Connection connection;
	
	
	
	/**
	 * Méthode de démarrage
	 * @param args
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructor
	 */
	private Controle(){
		this.frmEntreeJeu = new EntreeJeu(this);
		this.frmEntreeJeu.setVisible(true);
	}

	/**
	 * Récupération de la connexion
	 * @param connection
	 */
	public void setConnection (Connection connection) {
		this.connection = connection;
		if (leJeu instanceof JeuServeur) {
			leJeu.setConnection(connection);
		}
		
	}
	
	
	public void receptionInfo(Connection connection, Object info) {
		leJeu.reception(connection, info);
	}
	
	/* **********************************************************************************************
	 * Evénements provenant de la vue
	 * **********************************************************************************************/
	
	/**
	 * Gère les évènements de la vue
	 * @param uneFrame
	 * @param info
	 */
	public void evenementVue(JFrame uneFrame, Object info) {
		// On détermine dans quelle frame se passe l'évènement
		if (uneFrame instanceof EntreeJeu) {
			evenementEntreeJeu(info); 
		}
		else {
			if (uneFrame instanceof ChoixJoueur) {
				evenementChoixJoueur(info);
			}
			else {
				if (uneFrame instanceof Arene) {
					evenementArene(info);
			}
				
			}
		}
	}
	
	
	private void evenementArene(Object info) {
		((JeuClient)leJeu).envoi(info);
	}

	/**
	 * On détermine si le jeu lancé est côté client ou serveur
	 * @param info
	 */
	private void evenementEntreeJeu(Object info) {
		// Test du type de connexion Serveur ou client
		if((String)info == "serveur") {
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene("serveur", this);
			((JeuServeur)leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		}
		else {
			if((new ClientSocket((String)info, PORT, this)).isConnexionOk()) {
				this.leJeu = new JeuClient(this);
				leJeu.setConnection(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene("client", this);
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);


			}
		}
	}
	
	
	
	
	/**
	 * Envoi d'infos de pseudo au serveur / fermeture de la frame ChoixJoueur / Affichage de la frame Arene
	 * @param info
	 */
	private void evenementChoixJoueur(Object info) {
		((JeuClient)leJeu).envoi(info);
		frmChoixJoueur.dispose();
		frmArene.setVisible(true);
	}
	
	
	/* **********************************************************************************************
	 * Evénements provenant du modèle
	 * **********************************************************************************************/
	
	/**
	 * Détermine si jeuServeur ou JeuClient et gère les évènements issus 
	 * @param unJeu
	 * @param ordre
	 * @param info
	 */
	public void evenementModele(Object unJeu, String ordre, Object info) {
		if(unJeu instanceof JeuServeur) {
			evenementJeuServeur(ordre, info);
		}
		else {
			if (unJeu instanceof JeuClient) {
				evenementJeuClient(ordre, info);
			}
		}
	}
	
	
	/**
	 * Gère les événements provenant du client
	 * @param ordre
	 * @param info
	 */
	private void evenementJeuClient(String ordre, Object info) {
		if(ordre.equals("ajout panel murs")) {
			frmArene.ajoutPanelMurs(((JPanel)info));
		}
		else {
			if(ordre.equals("ajout joueur")) {
				frmArene.ajoutModifJoueur(((Label)info).getNumLabel(), ((Label)info).getjLabel());
			}
			else {
				if(ordre.equals("remplace chat")) {
					frmArene.remplaceChat((String)info);
				}
				else {
					if (ordre.equals("son")){
						frmArene.joueSon((Integer)info);
					}
				}
			}
		}
	}
	
	
	
	
	/**
	 * Gère les évènements venant du serveur
	 * @param ordre
	 * @param info
	 */
	private void evenementJeuServeur(String ordre, Object info) {
		if(ordre.equals("ajout mur")){
			frmArene.ajoutMur((JLabel)info);
		}
		else {
			if (ordre.equals("envoi panel murs")) {
				((JeuServeur)leJeu).envoi(((Connection)info), frmArene.getJpnMurs());
			}
			else {
				if(ordre.equals("ajout joueur")) {
					frmArene.ajoutJoueur((JLabel)info);
				}
				else {
					if (ordre.equals("ajout phrase")) {
						frmArene.ajoutChat((String)info);
						((JeuServeur)leJeu).envoi(frmArene.getContenuTxtChat());
						
					}
				}
			}
		}	
	}
	
	
	/**
	 * Gestion de la déconnexion d'un ordi distant
	 * @param connection
	 */
	public void deconnection(Connection connection) {
		leJeu.deconnection(connection);
	}
	
	
	
	
	
	
}
