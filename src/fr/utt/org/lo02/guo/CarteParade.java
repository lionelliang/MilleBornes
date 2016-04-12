/**
 * 
 */
package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

/**
 * @author GUO et FOURNIER Classe qui permet la création d'une carte Parade
 */
public class CarteParade extends Carte {

	/**
	 * Nom de la carte Parade
	 */
	private static final long serialVersionUID = 1L;
	private String nomParade;

	/**
	 * Constructeur de la classe CarteParade
	 * 
	 * @param nomB
	 *            Nom de la carte Parade
	 * @param num
	 *            Numéro identifiant de la carte Parade
	 * @param musique
	 *            Chemin vers le fichier audio du bruit que provoque
	 *            l'utilisation d'une carte Parade.
	 * @param m
	 * @param up
	 *            Carte face cachée ou non
	 * @param nomC
	 *            Chemin vers le fichier image de la carte Parade
	 * 
	 */

	public CarteParade(String nomP, int num, String musique, Main m,
			boolean up, String nomC) {
		// TODO Auto-generated constructor stub
		super(m, up, nomC);
		super.setNumCarte(num);
		super.setMusique(musique);
		this.nomParade = nomP;
	}

	/**
	 * Méthode accesseur : retourne le nom de la carte attaque
	 */
	public String getNomParade() {
		return this.nomParade;
	}

	/**
	 * Méthode qui permet de dire si la carte Attaque qui se trouve au dessus de
	 * la pile de bataille du joueur passé en paramètre correspond à la carte
	 * Parade.
	 * 
	 * @param joueurMB
	 * @return vrai si elle correspond, faux sinon.
	 */
	public boolean correspondre(JoueurMB joueurMB) {
		if (joueurMB.getPileBataille().isEmpty()) {
			return false;
		} else
			return (((Carte) joueurMB.getPileBataille().getLast())
					.getNumCarte() == this.getNumCarte() - 10);
	}

	/**
	 * Méthode qui va vérifier si le joueur voulant jouer une carte Parade peut
	 * le faire, ou non.
	 * 
	 * @param joueurMB
	 *            Joueur désirant jouer une carte Parade
	 * @return vrai s'il peut jouer la carte Parade, faux sinon.
	 */
	public boolean possibiliteJouerParade(JoueurMB joueurMB) {
		if (joueurMB.accident() && this.correspondre(joueurMB)) {
			System.out.println("correspondre");
			return true;
		} else if ((joueurMB.verifierJouerFeuVert())
				&& (this.getNomParade() == "Feu Vert"))
			return true;
		else if (joueurMB.limiteVitesse()
				&& (this.getNomParade() == "Fin de limitation")) {
			System.out.println("correspondre de Fin de limitation");
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Procédure qui permet de déplacer une carte parade, soit pour la tirer, soit pour la jeter, soit pour la jouer.
	 */
	public void mouseDragged(MouseEvent arg) {
		// TODO Auto-generated method stub
		if (isCanclick()) {
			Point p = SwingUtilities.convertPoint(this, arg.getPoint(),
					this.getParent());
			// Point p = arg.getPoint();
			this.setLocation(this.getX() + (p.x - beginPoint.x), this.getY()
					+ (p.y - beginPoint.y));
			if (this.piochable == true) {
				joueur.tirerCarte(this);
			} else if (joueur.getListeDefausse().getRegion()
					.contains(p.x + 40, p.y + 48)) {
				joueur.jeterCarte(this);
			} else if (joueur.getRegion().contains(this.getLocation().x + 40,
					this.getLocation().y + 48)) {
				joueur.jouerParade(this);
			}
			beginPoint = p;
		}
	}

}
