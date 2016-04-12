package fr.utt.org.lo02.guo;

/**
 * 
 * @author Guillaume Fournier Classe définissant la stratégie d'attaque d'un
 *         joueur robot.
 */
public class StrategieAttaque implements Strategie {

	@Override
	/**
	 * Méthode définissant le comportement du joueur robot avec une carte attaque.
	 */
	public boolean jouer(JeuJoueur jeuJoueur, JoueurMB joueurMB) {
		// TODO Auto-generated method stub
		CarteAttaque carteAttaque;
		JoueurMB joueurMB2;
		boolean reussi = false;
		for (int i = 0; i < joueurMB.getListeJoueurs().size() - 1; i++) {
			joueurMB2 = joueurMB.suivant();
			for (int j = 0; j < joueurMB2.getJeuJoueur().size(); j++) {
				Carte c = joueurMB2.getJeuJoueur().get(j);
				if (c instanceof CarteAttaque) {
					carteAttaque = (CarteAttaque) c;
					reussi = joueurMB.jouerAttaque(carteAttaque, joueurMB2);
				}

			}
		}

		return reussi;
	}
}
