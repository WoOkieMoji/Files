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

	// **************** Propri�t�s ****************
	private EntreeJeu frmEntreeJeu;
	private Jeu leJeu;
	private Arene frmArene; 
	private ChoixJoueur frmChoixJoueur;
	private Connection connection;
	
	
	
	/**
	 * M�thode de d�marrage
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
	 * R�cup�ration de la connexion
	 * @param connection
	 */
	public void setConnection (Connection connection) {
		this.connection = connection;
		
	}
	
	
	public void receptionInfo(Connection connection, Object info) {
		leJeu.reception(connection, info);
	}
	
	/* **********************************************************************************************
	 * Ev�nements provenant de la vue
	 * **********************************************************************************************/
	
	/**
	 * G�re les �v�nements de la vue
	 * @param uneFrame
	 * @param info
	 */
	public void evenementVue(JFrame uneFrame, Object info) {
		// On d�termine dans quelle frame se passe l'�v�nement
		if (uneFrame instanceof EntreeJeu) {
			evenementEntreeJeu(info); 
		}
		else {
			if(uneFrame instanceof ChoixJoueur) {
				evenementChoixJoueur(info);
			}
		}
	}

	/**
	 * On d�termine si le jeu lanc� est c�t� client ou serveur
	 * @param info
	 */
	private void evenementEntreeJeu(Object info) {
		// Test du type de connexion Serveur ou client
		if((String)info == "serveur") {
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			((JeuServeur)leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		}
		else {
			if((new ClientSocket((String)info, PORT, this)).isConnexionOk()) {
				this.leJeu = new JeuClient(this);
				leJeu.setConnection(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
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
	 * Ev�nements provenant du mod�le
	 * **********************************************************************************************/
	
	/**
	 * D�termine si jeuServeur ou JeuClient et g�re les �v�nements issus 
	 * @param unJeu
	 * @param ordre
	 * @param info
	 */
	public void evenementModele(Object unJeu, String ordre, Object info) {
		if(unJeu instanceof JeuServeur) {
			evenementJeuServeur(ordre, info);
		}
	}
	
	/**
	 * G�re les �v�nements venant du serveur
	 * @param ordre
	 * @param info
	 */
	private void evenementJeuServeur(String ordre, Object info) {
		if(ordre.equals("ajout�mur")){
			frmArene.ajoutMur((JLabel)info);
		}
	}
	
	
	
	
	
	
	
	
	
}
