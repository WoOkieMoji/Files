/**
 * Projet: Urban Marginal, jeu 2D multijoueurs en java
 * @author Florian MARTIN
 * Date: 05/10/2019
 */

package vue;


// **************** Imports ****************



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;






public class EntreeJeu extends JFrame {
	
	// **************** Propriétés ****************

	private JPanel contentPane;
	private JTextField txtIp;
	private Controle controle;
	
	// Evènements
	
	/**
	 * Clic sur le bouton Start pour lancer le serveur.
	 */
	private void btnStart_clic() {
		controle.evenementVue(this, "serveur"); 
	}
	
	/**
	 * Clic sur le bouton Connect pour se connecter à un serveur existant.
	 */
	private void btnConnect_clic() {
		controle.evenementVue(this, txtIp.getText());
	}

	/**
	 * Clic sur le bouton Exit pour fermer la frame.
	 */
	private void btnExit_clicked() {
		System.exit(0);
	}
	
	/**
	 * Create the frame.
	 * @param controle 
	 */
	public EntreeJeu(Controle controle) {
		setTitle("127.0.0.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnStart_clic();
			}
		});
		
		btnStart.setBounds(214, 22, 89, 23);
		contentPane.add(btnStart);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnConnect_clic();
			}
		});
		
		btnConnect.setBounds(214, 62, 89, 23);
		contentPane.add(btnConnect);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnExit_clicked();
			}
		});
		
		btnExit.setBounds(214, 106, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblStartAServer = new JLabel("Start a server:");
		lblStartAServer.setBounds(20, 22, 82, 23);
		contentPane.add(lblStartAServer);
		
		JLabel lblConnectAnExisting = new JLabel("Connect an existing server:");
		lblConnectAnExisting.setBounds(20, 66, 184, 14);
		contentPane.add(lblConnectAnExisting);
		
		JLabel lblIpServer = new JLabel("IP server:");
		lblIpServer.setBounds(20, 110, 60, 14);
		contentPane.add(lblIpServer);
		
		txtIp = new JTextField();
		txtIp.setText("127.0.0.1");
		txtIp.setBounds(90, 107, 86, 20);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		// Récupération du controleur.
		this.controle = controle;
	}
}
