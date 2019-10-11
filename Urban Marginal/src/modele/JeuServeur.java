package modele;





//**************** Imports **************** 
import outils.connexion.Connection;

import java.util.ArrayList;

import controleur.Controle;
import controleur.Global;




public class JeuServeur extends Jeu implements Global {

	
	// **************** Propri�t�s ****************
	
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>(); // Collection de murs
	
	

	
	// **************** Constructor ****************
	/**
	 * Constructor
	 * @param controle
	 */
	public JeuServeur(Controle controle){
		super.controle = controle;
		
		// Initialisation de nblabel, qui repr�sente le dernier label joueur / boule ajout�
		Label.setNbLabel(0);
	}
	
	
	
	// **************** M�thodes ****************

	/**
	 * Construction des murs
	 */
	public void constructionMurs(){
		for(int i = 0; i < NBMURS; i++) {
			System.out.println(NBMURS);
			lesMurs.add(new Mur());
			controle.evenementModele(this, "ajout mur", lesMurs.get(lesMurs.size()-1).getLabel().getjLabel());
		}
	}
	
	
	@Override
	public void setConnection(Connection connection) {

		
	}

	@Override
	public void reception(Connection connection, Object info) {
		
	}
	


}
