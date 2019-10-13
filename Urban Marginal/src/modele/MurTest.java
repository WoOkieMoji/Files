/**
 * 
 */
package modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author fmart
 *
 */
class MurTest {

	
	
	private Mur mur1 = new Mur() ;
	private Mur mur2 = new Mur() ;
	
	/**
	 * Test method for {@link modele.Objet#toucheObjet(modele.Objet)}.
	 */
	@Test
	public void testToucheObjet1() {
		mur1.posX = 10 ;
		mur1.posY = 10 ;
		mur2.posX = 20 ;
		mur2.posY = 20 ;
		assertTrue(mur1.toucheObjet(mur2));
	}


	/**
	 * Test method for {@link modele.Objet#toucheObjet(modele.Objet)}.
	 */
	/*@Test
	public void testToucheObjet2() {
		mur1.posX = 10 ;
		mur1.posY = 10 ;
		mur2.posX = 60 ;
		mur2.posY = 60 ;
		assertTrue(mur1.toucheObjet(mur2));
	}*/

}
