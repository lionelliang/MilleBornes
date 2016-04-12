/**
 * 
 */
package fr.utt.org.lo02.guo;

/**
 * @author GUO et FOURNIER Interface des différentes stratégies du joueur robot.
 * @see StrategieAvancer
 * @see StrategieAttaque
 * @see StrategieParade
 * @see StrategiePrevoyant
 */
public interface Strategie {

	/**
	 * méthode implémentée dans les classes implémentant cette interface.
	 * 
	 * @param jeuJoueur
	 * @param joueurMB
	 * @return
	 */
	public boolean jouer(JeuJoueur jeuJoueur, JoueurMB joueurMB);
}
