package outils.connexion;





//**************** Imports **************** 
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;





public class ServeurSocket extends Thread {
	
	// **************** Propriétés ****************
	private Object leRecepteur;
	private ServerSocket serverSocket;
	
	
	
	
	// **************** Constructeur ****************
	/**
	 * Constructor
	 * @param leRecepteur 
	 * @param port
	 */
	public ServeurSocket(Object leRecepteur, int port){
		this.leRecepteur = leRecepteur;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("erreur grave création socket serveur : " + e);
			System.exit(0);
		}
		
		this.start(); // Mise en place d'un thread d'attente de connexion d'un client
	}	
	

	public void run() {
		Socket socket;
	
		// Boucle infinie d'attente de connexion client
		while (true) {
			try {
				System.out.println("le serveur attend");
				socket = serverSocket.accept();
				System.out.println("un client s'est connecté");
				new Connection(socket, leRecepteur);
			} catch (IOException e) {
				System.out.println("erreur sur l'objet serverSocket : "+e);
				System.exit(0);
			}
		}
	}


}
