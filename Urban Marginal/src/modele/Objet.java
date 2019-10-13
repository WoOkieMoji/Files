package modele;








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
	
	
	/**
	 * SETTER posX
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	
	/**
	 * SETTER posY
	 * @param posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	
	/** 
	 * contr�le si l�objet actuel touche l�objet pass� en param�tre 
	 * @param objet 
	 * @return vrai si les 2 objets se touchent 
	 */ 
	public boolean toucheObjet (Objet objet) {
		if (objet.label==null) { 
			return false ;    
		}
		else{    
			if (objet.label.getjLabel()==null) {      
				return false ;   
			}
			else{   
				int l_obj = objet.label.getjLabel().getWidth() ;      
				int h_obj = objet.label.getjLabel().getHeight() ;    
				int l_this = this.label.getjLabel().getWidth() ;      
				int h_this = this.label.getjLabel().getHeight() ;     
				return(!((this.posX+l_this<objet.posX || this.posX>objet.posX+l_obj) || (this.posY+h_this<objet.posY || this.posY>objet.posY+h_obj)));    
				}      
			} 
		}
	
}
