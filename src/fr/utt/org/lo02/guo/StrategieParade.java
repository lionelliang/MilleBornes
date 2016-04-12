/**
 * 
 */
package fr.utt.org.lo02.guo;

import java.util.Iterator;

/**
 * @author GUOF Classe définissant la stratégie défensive d'un joueur robot.
 */

public class StrategieParade implements Strategie {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.utt.org.lo02.guo.Strategie#jouer(fr.utt.org.lo02.guo.JeuJoueur,
	 * fr.utt.org.lo02.guo.JoueurMB)
	 */
	@Override
	/**
	 * Méthode définissant le comportement du joueur robot avec une carte Parade.
	 */
	public boolean jouer(JeuJoueur jeuJoueur, JoueurMB joueurMB) {
		// TODO Auto-generated method stub
		CarteParade carteParade;
		Iterator<Carte> it = jeuJoueur.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c instanceof CarteParade) {
				carteParade = (CarteParade) c;
				return joueurMB.jouerParade(carteParade);
			}
		}
		return false;
	}

}
