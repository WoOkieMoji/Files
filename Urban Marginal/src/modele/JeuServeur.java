package modele;





//**************** Imports **************** 
import outils.connexion.Connection;

import java.util.ArrayList;

import controleur.Controle;
import controleur.Global;




public class JeuServeur extends Jeu implements Global {

	
	// **************** Propriétés ****************
	
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>(); // Collection de murs
	
	

	
	// **************** Constructor ****************
	/**
	 * Constructor
	 * @param controle
	 */
	public JeuServeur(Controle controle){
		super.controle = controle;
		
		// Initialisation de nblabel, qui représente le dernier label joueur / boule ajouté
		Label.setNbLabel(0);
	}
	
	
	
	// **************** Méthodes ****************

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
