package vue;


//**************** Imports **************** 

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import controleur.Global;
import outils.son.Son;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class ChoixJoueur extends JFrame implements Global{
	
	
	// **************** Propriétés ****************
	private int numPerso;
	private JPanel contentPane;
	private JTextField txtPseudo;
	private JLabel lblPersonnage;
	private Controle controle;
	private Son precedent;
	private Son suivant;
	private Son go;
	private Son welcome;
	
	

	// **************** Méthodes ****************
	private void  affichePerso() {
		lblPersonnage.setIcon(new ImageIcon(PERSO + numPerso + MARCHE + "1d" + DROITE + EXTIMAGE));
	}
	
	/**
	 * Changement de personnage au clic sur la flèche "précédent"
	 */
	private void lblPrecedent_clic() {
		precedent.play();
		numPerso = ((numPerso+1) % NBPERSOS) + 1;
		affichePerso();	
	}
	
	
	
	/**
	 * Changement de personnage au clic sur la flèche "suivant"
	 */
	private void lblSuivant_clic() {
		suivant.play();
		numPerso = (numPerso % NBPERSOS) + 1 ;
		affichePerso();
	}
	
	
	
	/**
	 * Gestion du clic sur le bouton "Go"
	 */
	private void lblGo_clic() {
		if(txtPseudo.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Veuillez entrer un pseudo");
			txtPseudo.requestFocus();
		}
		else {
			go.play();
			controle.evenementVue(this, PSEUDO + SEPARE + txtPseudo.getText() + SEPARE + numPerso);
		}
	}
	
	
	
	/**
	 * Curseur souris classique
	 */
	private void souris_normale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * Modification du curseur souris en doigt
	 */
	private void  souris_doigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	
	
	
	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Controle controle) {
		setTitle("Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		/**
		 * Evènement clic / mouse entered / mouse exited sur label "Precedent"
		 */
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPrecedent_clic();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				souris_doigt();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				souris_normale();
			}
		});
		
		/**
		 * Evènement clic / mouse entered / mouse exited sur label "Go"
		 */
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblGo_clic();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				souris_doigt();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				souris_normale();
			}
		});
		lblGo.setBounds(301, 192, 77, 71);
		contentPane.add(lblGo);
		lblPrecedent.setBounds(56, 145, 46, 48);
		contentPane.add(lblPrecedent);
		
		
		/**
		 * Evènement clic / mouse entered / mouse exited sur label "Suivant"
		 */
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblSuivant_clic();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				souris_doigt();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				souris_normale();
			}
		});
		lblSuivant.setBounds(291, 145, 46, 48);
		contentPane.add(lblSuivant);
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(143, 243, 119, 22);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		lblPersonnage = new JLabel("");
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonnage.setBounds(139, 113, 123, 119);
		contentPane.add(lblPersonnage);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		lblFond.setIcon(new ImageIcon(FONDCHOIX));
		contentPane.add(lblFond);
		
		// Focus sur le nom du joueur au chargement de la frame
		txtPseudo.requestFocus();
		
		// Initialisations
		this.controle = controle;
		numPerso = 1; // On part du premier personnage de la liste
		affichePerso();
		
		// Sons
		precedent = new Son(SONPRECEDENT);
		suivant = new Son(SONSUIVANT);
		go = new Son(SONGO);
		welcome = new Son(SONWELCOME);

		welcome.play();
	}
	
	
}
