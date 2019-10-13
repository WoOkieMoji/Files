package modele;


import java.awt.Font;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

//**************** Imports **************** 

import controleur.Global;
import outils.connexion.Connection;




public class Joueur extends Objet implements Global {
	
	// **************** Propriétés ****************

	private String pseudo;
	private int numPerso;
	private Label message;
	private JeuServeur jeuServeur;
	private int vie;                  				// vie restante du joueur 
	private int orientation;		  				// tourné vers la gauche (0) ou vers la droite (1)	
	private int etape;				  				// numéro d’étape dans l’animation
	private Boule boule;			 			    // Une boule par joueur
	private static final int MAXVIE = 10;			// Pv max
	private static final int GAIN = 1;				// Gain d'1 pv lorsqu'on touche un autre joueur
	private static final int PERTE = 2;				// Perte de 2 pvs lorsqu'un joueur est touché
	
	
	
	/**
	 * Constructor
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		vie = MAXVIE;
		orientation = 1;
		etape = 1;
	}
	
	
	
	// GETTERS AND SETTERS
	/**
	 * GETTER pseudo
	 * @return pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	
	/**
	 * GETTER message
	 * @return message
	 */
	public Label getMessage() {
		return message;
	}
	
	
	/**
	 * GETTER boule
	 * @return boule
	 */
	public Boule getBoule() {
		return boule;
	}
	
	/**
	 * GETTER orientation
	 * @return orientation
	 */
	public int getOrientation() {
		return orientation;
	}
	
	
	
	
	// **************** Méthodes ****************
	/**
	 * Initialisation du personnage
	 * @param pseudo
	 * @param numPerso
	 */
	public void initPerso(String pseudo, int numPerso, Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		
		label = new Label(Label.getNbLabel(), new JLabel());
		Label.setNbLabel(Label.getNbLabel() + 1);
		label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		jeuServeur.nouveauLabelJeu(label);
		
		message = new Label(Label.getNbLabel(), new JLabel());
		Label.setNbLabel(Label.getNbLabel() + 1);
		message.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		message.getjLabel().setFont(new Font("Dialog", Font.PLAIN, 8));
		jeuServeur.nouveauLabelJeu(message);
		
		// Position de départ aléatoire
		premierePosition(lesJoueurs, lesMurs);
		
		// Affichage du persnnage et son nombre de pv
		affiche(MARCHE, etape);
		
		// Attribution d'une boule
		boule = new Boule(jeuServeur);
		jeuServeur.envoi(boule.getLabel());
	}
	
	

	
	
	/**
	 * Gestion des déplacements des personnages
	 * @param action
	 * @param position
	 * @param orientation
	 * @param lepas
	 * @param max
	 * @param lesJoueurs
	 * @param lesMurs
	 * @return
	 */
	private int deplace(int action, int position, int orientation, int lepas, int max, Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		this.orientation = orientation;
		int ancpos = position;
		position += lepas;
		
		// On empêche de sortir des limites de l'arène
		if (position < 0) {
			position = 0;
		}
		else {
			if (position > max) {
				position = max;
			}
		}
		
		// Gestion de la nouvelle position après déplacement
		if (action == GAUCHE || action == DROITE) {
			posX = position;
		}
		else {
			posY = position;
		}
		
		// Gestion des collisions avec un autre joueur ou un mur
		if (toucheMur(lesMurs) || toucheJoueur(lesJoueurs)) {
			position = ancpos;
		}
		
		// Actualisation de l'état de marche du personnage
		etape = (etape % NBETATSMARCHE) + 1;
		
		return position;
	}
	
	
	
	/**
	 * Gestion d'actions réalisées par les joueurs
	 * @param action
	 * @param lesJoueurs
	 * @param lesMurs
	 */
	public void action(int action, Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		switch(action) {
		case TIRE :
			if (!boule.getLabel().getjLabel().isVisible()) {
				jeuServeur.envoi(FIGHT);
				boule.tireBoule(this, lesMurs, lesJoueurs);
			}
			break;
			
		case GAUCHE : 
			posX = deplace(action, posX, GAUCHE, - LEPAS, L_ARENE - L_PERSO, lesJoueurs, lesMurs);
			break;
			
		case DROITE :
			posX = deplace(action, posX, DROITE, LEPAS, L_ARENE - L_PERSO, lesJoueurs, lesMurs);
			break;
			
		case HAUT :
			posY = deplace(action, posY, orientation, - LEPAS, H_ARENE - H_PERSO - H_MESSAGE, lesJoueurs, lesMurs);
		break;
		
		case BAS :
			posY = deplace(action, posY, orientation, LEPAS, H_ARENE - H_PERSO - H_MESSAGE, lesJoueurs, lesMurs);
			break;
		}
		
		affiche(MARCHE, etape);
	}
	
	
	/**
	 * 
	 * @param etat
	 * @param etape
	 */
	public void affiche(String etat, int etape) {
		// Positionnement + attribution de l'image du personnage
		label.getjLabel().setBounds(posX, posY, L_PERSO, H_PERSO);
		label.getjLabel().setIcon(new ImageIcon(PERSO + numPerso + etat + etape + "d" + orientation + EXTIMAGE));
		
		// Positionnement du message
		message.getjLabel().setBounds(posX - 10, posY + H_PERSO, L_PERSO + 10, H_MESSAGE);	
		message.getjLabel().setText(pseudo + ": " + vie);
		
		jeuServeur.envoi(label);
		jeuServeur.envoi(message);
	}
	

	
	
	/**
	 * Contrôle si le joueur chevauche un des autres joueurs
	 * @param lesJoueurs
	 * @return
	 */
	private boolean toucheJoueur(Hashtable<Connection, Joueur> lesJoueurs) {
		for (Joueur unJoueur : lesJoueurs.values()) {
			if (!unJoueur.equals(this)) {
				if(toucheObjet(unJoueur)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	/**
	 * Contrôle si le joueur chavauche un mur
	 * @param lesMurs
	 * @return
	 */
	private boolean toucheMur(ArrayList<Mur> lesMurs) {
		for(Mur unMur : lesMurs) {
			if(toucheObjet(unMur)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	/**
	 * Calcul du positionnement aléatoire de départ de chaque personnage
	 * @param lesJoueurs
	 * @param lesMurs
	 */
	private void premierePosition(Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		label.getjLabel().setBounds(0, 0, L_PERSO, H_PERSO);
		do {
			posX = (int)Math.round(Math.random() * (L_ARENE - L_PERSO));
			posY = (int)Math.round(Math.random() * (H_ARENE - H_PERSO - H_MESSAGE));
		} while(toucheJoueur(lesJoueurs) || toucheMur(lesMurs));
	}

	
	/**
	 * MAJ des pv lors d'un tir réussi sur un autre joueur
	 */
	public void gainVie() {
		vie += GAIN;
	}
	
	
	/**
	 * MAJ des pvs après avoir été touché par un tir
	 */
	public void perteVie() {
		vie = Math.max(vie - PERTE, 0);
	}
	
	
	/**
	 * Si vie = 0 le personnage est déclaré mort
	 * @return 
	 */
	public boolean estMort() {
		return (vie == 0);
	}
	
	
	/**
	 * Le joueur est mort et disparait
	 */
	public void departJoueur() {
		
		if (label != null) {
			label.getjLabel().setVisible(false);
			message.getjLabel().setVisible(false);
			boule.getLabel().getjLabel().setVisible(false);
			jeuServeur.envoi(label);
			jeuServeur.envoi(boule.getLabel());
			jeuServeur.envoi(message);
		}

	}
	
	
	
	
	
	
	


}
