/**
 * 
 */
package fr.utt.org.lo02.guo;

/**
 * @author GUO et FOURNIER Classe qui permet de créer un joueur robot.
 */
public class JoueurRobot extends JoueurMB {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	/**
 *
 */
	private Strategie strategie = null;

	/**
	 * Constructeur de la classe JoueurRobot
	 * 
	 * @param f
	 * @param nomJoueur
	 * @param listCartes
	 * @param listeDefausse
	 * @param listeJoueurs
	 */
	public JoueurRobot(Main f, String nomJoueur, ListeCartes listCartes,
			ListeDefausse listeDefausse, ListeJoueurs listeJoueurs) {
		// TODO Auto-generated constructor stub
		super(f, nomJoueur, listCartes, listeDefausse, listeJoueurs);
	}

	/**
	 * Getter of the property <tt>strategie</tt>
	 * 
	 * @return Returns the strategie.
	 * 
	 */

	public Strategie getStrategie() {
		return strategie;
	}

	/**
	 * Setter de la strategie du joueur robot.
	 * 
	 * @param strategie
	 */
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}

	/**
	 * Procédure d'un tour de jeu d'un joueur robot.
	 */
	public boolean jouer() {

		boolean reussi = false;
		Carte c = null;

		for (int i = 0; i < jeuJoueur.size(); i++) {
			c = jeuJoueur.get(i);
			if (!reussi && (c instanceof CarteBotte)) {
				this.strategie = new StrategiePrevoyant();
				reussi = this.strategie.jouer(this.jeuJoueur, this);
				continue;
			} else if (c instanceof CarteParade) {
				this.strategie = new StrategieParade();
				reussi = this.strategie.jouer(this.jeuJoueur, this);
				continue;
			} else if (!reussi && (c instanceof CarteEtape)) {
				this.strategie = new StrategieAvancer();
				reussi = this.strategie.jouer(this.jeuJoueur, this);
				continue;
			} else if (!reussi && (c instanceof CarteAttaque)) {
				this.strategie = new StrategieAttaque();
				reussi = this.strategie.jouer(this.jeuJoueur, this);
				continue;
			}
		}
		if (!reussi) {
			reussi = this.jeterCarte(this.jeuJoueur.get(0));
		}
		return reussi;
	}
}
