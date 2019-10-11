package modele;


//**************** Imports ****************






/**
 * Classe m�re des objets boule, mur, joueur
 * @author Florian MARTIN
 *
 */
public abstract class Objet {

	
	
	// **************** Propri�t�s ****************

	// Position du JLabel
	protected int posX, posY;
	protected Label label;
	
	
	
	// **************** M�thodes ****************
	/**
	 * GETTER posX
	 * @return posX
	 */
	public int getPosX(){
		return posX;
	}
	
	/**
	 * GETTER posY
	 * @return posY
	 */
	public int getPosY() {
		return posY;
	}
	
	/**
	 * GETTER label
	 * @return label
	 */
	public Label getLabel() {
		return label;
	}
	
	
	
}
