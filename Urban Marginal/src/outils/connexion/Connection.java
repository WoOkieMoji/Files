package outils.connexion;


//**************** Imports **************** 

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * Gestion de la connexion entre 2 ordinateurs distants
 * @author Florian MARTIN
 *
 */

public class Connection extends Thread {
	
	// **************** Propriétés ****************
	private Object leRecepteur;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	
	// **************** Méthodes ****************
	
	/**
	 * Envoi d'informations aux ordinateurs distants Clients ou Serveur
	 * @param unObjet
	 */
	public void envoi(Object unObjet) {
		try {
			out.writeObject(unObjet);
			out.flush(); // On vide le canal de sortie
		} catch (IOException e) {
			System.out.println("Erreur sur l'objet out");
		}
	}
	
	
	
	// **************** Constructeur ****************
	/**
	 * Constructor
	 * @param socket
	 * @param leRecepteur
	 */
	public Connection(Socket socket, Object leRecepteur){
		this.leRecepteur = leRecepteur;
		
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Erreur de création du canal de sortie" + e);
			System.exit(0);
		}
		
		try {
			this.in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Erreur de création du canal d'entrée" + e);
			System.exit(0);
		}
		
		this.start();
		((controleur.Controle)this.leRecepteur).setConnection(this);
	}
	
	// Redéfinition de run() de la classe Thread 
	public void run() {
		boolean inOk  = true;
		Object reception;
		
		while(inOk) {
			try {
				reception = in.readObject();
				((controleur.Controle)leRecepteur).receptionInfo(this, reception);
			} catch (ClassNotFoundException e) {
				System.out.println("Erreur de classe en réception");
				System.exit(0);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "l’ordinateur distant s’est déconnecté");
				inOk = false;
				try {
					in.close();
				} catch (IOException e1) {
					System.out.println("Erreur dans la fermeture du canal d'écoute");
				}
			}
		}
	}
	
	
	
}
