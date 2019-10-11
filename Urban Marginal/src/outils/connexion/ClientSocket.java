package outils.connexion;




//**************** Imports **************** 


import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import java.io.IOException;





public class ClientSocket {
	
	// **************** Propri�t�s ****************
	private boolean connexionOk;
	
	// **************** Constructeur ****************
	/**
	 * Constructor
	 * @param ip
	 * @param port
	 * @param leRecepteur
	 */
	public ClientSocket(String ip, int port, Object leRecepteur){
		connexionOk = false;
		try {
			Socket socket = new Socket(ip, port);
			System.out.println("Connexion au serveur r�ussie");
			connexionOk = true;
			new Connection(socket, leRecepteur);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Serveur indisponible");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Probl�me d'entr�e/sortie");
		}
	}

	/**
	 * @return the connexionOk
	 */
	public boolean isConnexionOk() {
		return connexionOk;
	}
	
	
	
}
