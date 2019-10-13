package modele;


import java.util.ArrayList;
import java.util.Hashtable;

//**************** Imports **************** 

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import controleur.Global;
import outils.connexion.Connection;






public class Boule extends Objet implements Global {

	// **************** Propriétés ****************

	private JeuServeur jeuServeur;
	
	
	
	/**
	 * Constructor
	 */
	public Boule(JeuServeur jeuServeur) {
		// Création du label Boule
		label = new Label(Label.getNbLabel(), new JLabel());
		Label.setNbLabel(Label.getNbLabel() + 1);
		
		// Centrage et positionnement du contenu du JLabel
		label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		label.getjLabel().setBounds(0, 0, L_BOULE, H_BOULE);
		label.getjLabel().setIcon(new ImageIcon(BOULE));
		label.getjLabel().setVisible(false);
	
		this.jeuServeur = jeuServeur;
		
		jeuServeur.nouveauLabelJeu(label);
	}
	
	
	// **************** Méthodes ****************

	public void tireBoule(Joueur attaquant, ArrayList<Mur> lesMurs, Hashtable<Connection, Joueur> lesJoueurs) {
		
		if (attaquant.getOrientation() == GAUCHE) {
			posX = attaquant.getPosX() - L_BOULE - 1;
		}
		else {
			posX = attaquant.getPosX() + L_PERSO + 1;
		}
		
		posY = attaquant.getPosY() + H_PERSO / 2;
		
		new Attaque(attaquant, jeuServeur, lesMurs, lesJoueurs);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
