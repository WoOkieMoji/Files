package vue;


//**************** Imports **************** 
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Global;

import java.awt.FlowLayout;
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
	
	

	//**************** Méthodes **************** 
	
	/**
	 * Ajout d'un mur
	 * @param unMur
	 */
	public void ajoutMur(JLabel unMur) {
		jpnMurs.add(unMur);
		jpnMurs.repaint();
		System.out.println(unMur);
	}
	
	
	/**
	 * Create the frame.
	 */
	public Arene() {
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, L_ARENE+3*MARGE, H_ARENE + H_CHAT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, L_ARENE, H_ARENE);
		jpnJeu.setOpaque(false);
		contentPane.add(jpnJeu);
		jpnJeu.setLayout(null);
		
		JPanel jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, L_ARENE, H_ARENE);
		contentPane.add(jpnMurs);
		jpnMurs.setLayout(null);
		jpnMurs.setOpaque(false);
		
		txtSaisie = new JTextField();
		txtSaisie.setBounds(0, H_ARENE, L_ARENE, H_SAISIE);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ); 
		jspChat.setBounds(0, H_ARENE + H_SAISIE, L_ARENE, H_CHAT - H_SAISIE - 7*MARGE );
		contentPane.add(jspChat);
		
		JTextArea txtChat = new JTextArea();
		jspChat.setViewportView(txtChat);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, L_ARENE, H_ARENE);
		contentPane.add(lblFond);
		lblFond.setIcon(new ImageIcon(FONDARENE));
	}
}
