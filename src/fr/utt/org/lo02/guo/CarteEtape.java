package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

/**
 * @author GUO et FOURNIER Classe qui permet la création de cartes Etape.
 */
public class CarteEtape extends Carte {

	private static final long serialVersionUID = 1L;

	private int valeur;

	/**
	 * Constructeur de la classe CarteEtape
	 * 
	 * @param nomB
	 *            Nom de la carte Etape
	 * @param num
	 *            Numéro identifiant de la carte Etape
	 * @param musique
	 *            Chemin vers le fichier audio du bruit que provoque
	 *            l'utilisation d'une carte Etape.
	 * @param m
	 * @param up
	 *            Carte face cachée ou non
	 * @param nomC
	 *            Chemin vers le fichier image de la carte Etape
	 * 
	 */
	public CarteEtape(int nbkilometres, int num, String musique, Main m,
			boolean up, String nomC) {
		super(m, up, nomC);
		super.setNumCarte(num);
		super.setMusique(musique);
		this.valeur = nbkilometres;
	}

	/**
	 * accesseur de l'attribut nbkilometres
	 * 
	 * @return
	 */
	public int getValeur() {
		return this.valeur;
	}

	/**
	 * MÃ©thode qui va vÃ©rifier si le joueur est dans la capacitÃ© de jouer une
	 * carte etape ou non
	 * 
	 * @param joueurMB
	 *            joueur qui dÃ©sire jouer une carte botte.
	 * @return vrai si le joueur le peut, faux sinon.
	 */
	public boolean possibiliteJouer(JoueurMB joueurMB) {
		if (joueurMB.verifierFeuVert()) {
			System.out.println("feu vert");
			if (joueurMB.accident()) {
				return false;
			} else if (joueurMB.limiteVitesse() && this.valeur > 50) {
				return false;
			} else
				return true;
		} else
			return false;
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
				joueur.jouerEtape(this);
			}
			beginPoint = p;
		}
	}

}
