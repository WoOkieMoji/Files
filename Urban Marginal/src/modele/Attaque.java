package modele;


import java.util.ArrayList;
import java.util.Hashtable;

//**************** Imports **************** 

import controleur.Global;
import outils.connexion.Connection;





public class Attaque extends Thread implements Global {

	//**************** Propriétés **************** 
	
	private Joueur attaquant;
	private JeuServeur jeuServeur;
	private ArrayList<Mur> lesMurs;
	private Hashtable<Connection, Joueur> lesJoueurs;
	
	
	
	/**
	 * Constructor
	 * @param attaquant
	 * @param jeuServeur
	 */
	public Attaque(Joueur attaquant, JeuServeur jeuServeur, ArrayList<Mur> lesMurs, Hashtable<Connection, Joueur> lesJoueurs) {
		this.attaquant = attaquant;
		this.jeuServeur = jeuServeur;
		this.lesMurs = lesMurs;
		this.lesJoueurs = lesJoueurs;
		
		// Lancement du thread
		this.start();
	}
	
	
	
	//**************** Méthodes **************** 

	public void run() {
		attaquant.affiche(MARCHE, 1);

		Boule laboule = attaquant.getBoule();
		int orientation = attaquant.getOrientation();
		
		laboule.getLabel().getjLabel().setVisible(true);
		
		Joueur victime = null;
		
		do {
			if (orientation == GAUCHE) {
				laboule.setPosX(laboule.getPosX() - LEPAS);
			}
			else {
				laboule.setPosX(laboule.getPosX() + LEPAS);
			}
			
			// Déplacement actualisé de laboule
			laboule.getLabel().getjLabel().setBounds(laboule.getPosX(), laboule.getPosY(), L_BOULE, H_BOULE);
			pause(10, 0);
			// Envoie de la position de la boule à tous les joueurs
			jeuServeur.envoi(laboule.getLabel());
			
			victime = toucheJoueur();
		} while(laboule.getPosX() >= 0 && laboule.getPosX() <= L_ARENE && !toucheMur() && victime == null);
		
		
		
		// Test si le joueur touché n'est pas déjà mort
		if (!(victime == null) && !victime.estMort()) {
			jeuServeur.envoi(HURT);
			// MAJ des pvs victime / attaquant
			victime.perteVie();
			attaquant.gainVie();
			
			for (int i = 1; i <= NBETATSBLESSE; i++) {
				victime.affiche(BLESSE, i);
				pause(80, 0);
			}
			
			// Test si le joueur touché est mort
			if (victime.estMort()) {
				jeuServeur.envoi(DEATH);
				for (int i = 1; i <= NBETATSMORT; i++) {
					victime.affiche(MORT, i);
					pause(80, 0);
				}
			}
			else {
				victime.affiche(MARCHE, 1);
			}
			// Repositionnement initial du personnage
			attaquant.affiche(MARCHE, 1);
		}
		
		// la boule a fini son parcours et redevient invisible
		laboule.getLabel().getjLabel().setVisible(false);
		jeuServeur.envoi(laboule.getLabel());
		
	}
	
	
	/**
	 * Génère un ralentissement des tirs
	 * @param milli
	 * @param nano
	 */
	public void pause(long milli, int nano) {
		try {
			Thread.sleep(milli, nano);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Test si la boule touche un mur
	 * @return true si mur touché
	 */
	private boolean toucheMur() {
		for(Mur unMur : lesMurs) {
			if(attaquant.getBoule().toucheObjet(unMur)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Test si la boule touche un joueur
	 * @return true si joueur touché
	 */
	private Joueur toucheJoueur() {
		for(Joueur unJoueur : lesJoueurs.values()) {
			if(attaquant.getBoule().toucheObjet(unJoueur)) {
				return unJoueur;
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
