package fr.utt.org.lo02.guo;

import java.util.Iterator;

/**
 * 
 * @author Guillaume Fournier
 * Classe définissant la stratégie d'un joueur robot pour aligner des kilomètres.
 */
/**
 * 
 * @author Guillaume Fournier Classe définissant la stratégie d'un joueur robot
 *         pour aligner des kilomètres.
 */
public class StrategieAvancer implements Strategie {

	@Override
	/**
	 * Méthode définissant le comportement du joueur robot avec une carte Etape.
	 */
	public boolean jouer(JeuJoueur jeuJoueur, JoueurMB joueurMB) {
		// TODO Auto-generated method stub
		CarteEtape carteEtape;
		Iterator<Carte> it = jeuJoueur.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (11 <= c.getNumCarte() && c.getNumCarte() <= 15) {
				carteEtape = (CarteEtape) c;
				return joueurMB.jouerEtape(carteEtape);
			}

		}
		return false;
	}

}
