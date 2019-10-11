package modele;




//**************** Imports **************** 

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import controleur.Global;







public class Mur extends Objet implements Global {

	// **************** Propriétés ****************

	
	/**
	 * Constructor
	 */
	public Mur(){
		// Calcul des coordonnées aléatoires des 20 murs obstacles
		posX = (int)Math.round(Math.random() * (L_ARENE - L_MUR));
		posY = (int)Math.round(Math.random() * (H_ARENE - H_MUR));
		
		// Création du label mur
		label = new Label(-1, new JLabel());
		
		// Centrage du contenu du jLabel horizontalement et verticalement
		label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		
		// Définition de la taille du label et de son fond
		label.getjLabel().setBounds(posX, posY, L_MUR, H_MUR);
		label.getjLabel().setIcon(new ImageIcon(MUR));
	}
	
	
	
	
	
	
}
