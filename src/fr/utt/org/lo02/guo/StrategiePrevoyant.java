package fr.utt.org.lo02.guo;

import java.util.Iterator;

/**
 * 
 * @author Guillaume Fournier Classe définissant la stratégie prévoyante d'un
 *         joueur robot.
 */

public class StrategiePrevoyant implements Strategie {

	@Override
	/**
	 * Méthode définissant le comportement du joueur robot avec une carte Parade.
	 */
	public boolean jouer(JeuJoueur jeuJoueur, JoueurMB joueurMB) {
		// TODO Auto-generated method stub
		CarteBotte carteBotte;
		Iterator<Carte> it = jeuJoueur.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c instanceof CarteBotte) {
				carteBotte = (CarteBotte) c;
				return joueurMB.jouerBotte(carteBotte);
			}
		}
		return false;
	}

}
