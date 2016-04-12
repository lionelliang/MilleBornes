/**
 * 
 */
package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

/**
 * @author GUO et FOURNIER Classe qui permet la création d'une carte Botte.
 */
public class CarteBotte extends Carte {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomBotte;

	/**
	 * Constructeur de la classe CarteBotte
	 * 
	 * @param nomB
	 *            Nom de la carte Botte
	 * @param num
	 *            Numéro identifiant de la carte Botte
	 * @param musique
	 *            Chemin vers le fichier audio du bruit que provoque
	 *            l'utilisation d'une carte Botte.
	 * @param m
	 * @param up
	 *            Carte face cachée ou non
	 * @param nomC
	 *            Chemin vers le fichier image de la carte Botte
	 * 
	 */

	public CarteBotte(String nomB, int num, String musique, Main m, boolean up,
			String nomC) {
		// TODO Auto-generated constructor stub
		super(m, up, nomC);
		super.setNumCarte(num);
		super.setMusique(musique);
		this.nomBotte = nomB;
	}

	/**
	 * Methode accesseur de l'attribut nomB retourne le nom de la carte botte
	 */

	public String getNomBotte() {
		return this.nomBotte;
	}

	/**
	 * methode qui va vÃ©rifier si la carte Botte correspond Ã  la derniÃ¨re
	 * carte Attaque posÃ©e sur la pile de bataille du joueur
	 * 
	 * @return
	 */
	public boolean correspondreAttaque() {
		return true;
	}

	/**
	 * Méthode qui vérifie si une carteBotte peut etre jouee
	 * 
	 * @param joueurMB
	 * @return
	 */
	public boolean possibiliteJouerBotte(JoueurMB joueurMB) {
		return true;
	}

	@Override
	/**
	 * Procédure qui permet de déplacer une carte botte, soit pour la tirer, soit pour la jeter, soit pour la jouer. 
	 */
	public void mouseDragged(MouseEvent arg) {
		// TODO Auto-generated method stub
		if (isCanclick()) {
			Point p = SwingUtilities.convertPoint(this, arg.getPoint(),
					this.getParent());
			this.setLocation(this.getX() + (p.x - beginPoint.x), this.getY()
					+ (p.y - beginPoint.y));
			if (this.piochable == true) {
				joueur.tirerCarte(this);
			} else if (joueur.getListeDefausse().getRegion()
					.contains(p.x + 40, p.y + 48)) {
				joueur.jeterCarte(this);
			} else if (joueur.getRegion().contains(this.getLocation().x + 40,
					this.getLocation().y + 48)) {
				joueur.jouerBotte(this);
			}
			beginPoint = p;
		}
	}
}
