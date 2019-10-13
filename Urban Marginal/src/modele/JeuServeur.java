package modele;





//**************** Imports **************** 
import outils.connexion.Connection;

import java.util.ArrayList;
import java.util.Hashtable;

import controleur.Controle;
import controleur.Global;




public class JeuServeur extends Jeu implements Global {

	
	// **************** Propriétés ****************
	
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>(); // Collection de murs
	private Hashtable<Connection, Joueur> lesJoueurs = new  Hashtable<Connection, Joueur>();
	private ArrayList<Joueur> lesJoueursDansLordre  = new ArrayList<Joueur>();

	
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
			lesMurs.add(new Mur());
			controle.evenementModele(this, "ajout mur", lesMurs.get(lesMurs.size()-1).getLabel().getjLabel());
		}
	}
	
	
	@Override
	public void setConnection(Connection connection) {
		lesJoueurs.put(connection, new Joueur(this));

	}

	@Override
	public void reception(Connection connection, Object info) {
		String[] infos = ((String)info).split(SEPARE);
		String laPhrase;
		
		switch(Integer.parseInt(infos[0])) {
		// Un nouveau joueur arrive
		case PSEUDO : 
			controle.evenementModele(this, "envoi panel murs", connection);
			for(Joueur joueur : lesJoueursDansLordre) {
				super.envoi(connection, joueur.getLabel());
				super.envoi(connection, joueur.getMessage());
				super.envoi(connection, joueur.getBoule().getLabel());
			} 
			
			lesJoueurs.get(connection).initPerso(infos[1], Integer.parseInt(infos[2]), lesJoueurs, lesMurs);
			this.lesJoueursDansLordre.add(this.lesJoueurs.get(connection));
			
			laPhrase = "*****     " + lesJoueurs.get(connection).getPseudo() + " vient de se connecter     *****";
			controle.evenementModele(this, "ajout phrase", laPhrase);
			
			break;
			
		case CHAT:
			laPhrase = lesJoueurs.get(connection).getPseudo() + " > " + infos[1];
			controle.evenementModele(this, "ajout phrase", laPhrase);
			
			break;
			
		case ACTION :
			if (!(lesJoueurs.get(connection).estMort())) {
				lesJoueurs.get(connection).action(Integer.parseInt(infos[1]), lesJoueurs, lesMurs);
				}
		}
	}
	
	
	/**
	 * Demande au contrôleur d'ajouter un joueur dans l'arêne
	 * @param label
	 */
	public void nouveauLabelJeu(Label label) {
		controle.evenementModele(this, "ajout joueur", label.getjLabel());
	}



	public void envoi(Object info) {
		for(Connection connection: lesJoueurs.keySet()) {
			super.envoi(connection, info);
		}
	}

	
	@Override
	public void deconnection(Connection connection) {
		lesJoueurs.get(connection).departJoueur();
		lesJoueurs.remove(connection);
	}
	
	
	
}
