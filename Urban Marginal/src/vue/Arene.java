package vue;


//**************** Imports **************** 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import controleur.Global;
import outils.son.Son;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;





public class Arene extends JFrame implements Global {

	
	//**************** Propriétés **************** 

	private JPanel contentPane;
	private JTextField txtSaisie;
	private JPanel jpnMurs;
	private JPanel jpnJeu;
	private boolean client;
	private Controle controle;
	private JTextArea txtChat ;
	private Son[] lessons = new Son[SON.length];

	//**************** Méthodes **************** 
	
	
	/**
	 * GETTER jpnMurs
	 * @return jpnMurs
	 */
	public JPanel getJpnMurs() {
		return jpnMurs;
	}
	
	
	/**
	 * GETTER txtChat
	 * @return txtChat
	 */
	public String getContenuTxtChat() {
		return txtChat.getText();
	}
	
	
	/**
	 * On écrase le contenu de txtChat
	 * @param unString
	 */
	public void remplaceChat(String contenuTxtChat) {
		txtChat.setText(contenuTxtChat);
	}
	
	
	/**
	 * Ajout d'un mur
	 * @param unMur
	 */
	public void ajoutMur(JLabel unMur) {
		jpnMurs.add(unMur);
		jpnMurs.repaint();
	}
	
	
	/**
	 * Affichage d'un joueur côté serveur
	 * @param unJoueur
	 */
	public void ajoutJoueur(JLabel unJoueur) {
		jpnJeu.add(unJoueur);
		jpnJeu.repaint();
	}
	
	
	/**
	 * Transfert du JPanel du JeuServeur au JeuClient
	 */
	public void ajoutPanelMurs(JPanel unJPanel) {
		jpnMurs.add(unJPanel);
		jpnMurs.repaint();
		contentPane.requestFocus();
	}
	
	
	/**
	 * 
	 * @param num
	 * @param unLabel
	 */
	public void ajoutModifJoueur(int num, JLabel unLabel){
		try {
			jpnJeu.remove(num);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		
		jpnJeu.add(unLabel, num);
		jpnJeu.repaint();
	}
	
	
	/**
	 * Gère la touche appuyée dans la zone de saisie de texte du client
	 * @param arg0
	 */
	private void txtSaisie_keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!txtSaisie.getText().equals("")) {
				controle.evenementVue(this, CHAT + SEPARE + txtSaisie.getText());
				txtSaisie.setText("");
				contentPane.requestFocus();
			}
		}
	}
	
	
	/**
	 * Gère la touche appuyée sur la zone de l'arène
	 * @param arg0
	 */
	private void contentPane_keyPressed(KeyEvent arg0) {
		int valeur = -1;
		
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_SPACE : 
			valeur = TIRE;
			break;
		
		case KeyEvent.VK_LEFT :
			valeur = GAUCHE;
			break;
			
		case KeyEvent.VK_RIGHT :
			valeur = DROITE;
			break;
			
		case KeyEvent.VK_UP :
			valeur = HAUT;
			break;
			
		case KeyEvent.VK_DOWN :
			valeur = BAS;
			break;
		}	
		
		// Si une action est réalisée on envoie l'info au serveur via le contrôleur
		if (valeur != -1) {
			controle.evenementVue(this, ACTION + SEPARE + valeur);
		}
	}
	
	
	
	/**
	 * Ajout d'un message sur le chat
	 * @param unePhrase
	 */
	public void ajoutChat(String unePhrase) {
		txtChat.setText(unePhrase + "\r\n" + txtChat.getText());
	}
	
	
	
	public void joueSon(int son) {
		lessons[son].play();
	}
	
	
	/**
	 * Create the frame.
	 */
	public Arene(String typeJeu, Controle controle) {
		
		this.controle = controle;
		
		// Arene pour un client ou un serveur ?
		client = (typeJeu.equals("client"));
		
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, L_ARENE+3*MARGE, H_ARENE + H_CHAT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		if (client) {
			contentPane.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					contentPane_keyPressed(arg0);
				}
			});
		}
		
		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, L_ARENE, H_ARENE);
		jpnJeu.setOpaque(false);
		contentPane.add(jpnJeu);
		jpnJeu.setLayout(null);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, L_ARENE, H_ARENE);
		contentPane.add(jpnMurs);
		jpnMurs.setLayout(null);
		jpnMurs.setOpaque(false);
		
		
		if (client) {
			txtSaisie = new JTextField();
			txtSaisie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					txtSaisie_keyPressed(arg0);
				}
			});
			txtSaisie.setBounds(0, H_ARENE, L_ARENE, H_SAISIE);
			contentPane.add(txtSaisie);
			txtSaisie.setColumns(10);
		}

		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ); 
		jspChat.setBounds(0, H_ARENE + H_SAISIE, L_ARENE, H_CHAT - H_SAISIE - 7*MARGE );
		contentPane.add(jspChat);
		
		txtChat = new JTextArea();
		jspChat.setViewportView(txtChat);
		/*
		txtChat.setForeground(Color.red);
		*/
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, L_ARENE, H_ARENE);
		contentPane.add(lblFond);
		lblFond.setIcon(new ImageIcon(FONDARENE));
		
		
		if (client) {
			(new Son(SONAMBIANCE)).playContinue() ;
			
			for (int i = 0; i < SON.length; i++) {
				lessons[i] = new Son(CHEMINSONS + SON[i]);
			}
		}
	}
}
