/**
 * 
 */
package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.SwingUtilities;

/**
 * @author GUO et FOURNIER Classe qui permet la création d'une carte attaque.
 */
public class CarteAttaque extends Carte {

	private static final long serialVersionUID = 1L;
	/**
	 * Attributs : nom de la carte attaque
	 */

	private String nomAttaque;
	private ListeJoueurs listeJoueurs;

	/**
	 * Constructeur de la classe CarteAttaque
	 * 
	 * @param nomA
	 *            nom de la carte Attaque
	 * @param num
	 *            numero de la carte
	 * @param musique
	 *            Chemin vers le fichier audio du bruit que provoque
	 *            l'utilisation d'une carte Attaque
	 * @param m
	 * @param up
	 *            Carte face cachée ou non
	 * @param nomC
	 *            Chemin vers le fichier image de la carte Attaque
	 */
	public CarteAttaque(String nomA, int num, String musique, Main m,
			boolean up, String nomC) {
		// TODO Auto-generated constructor stub
		super(m, up, nomC);
		super.setNumCarte(num);
		super.setMusique(musique);
		this.nomAttaque = nomA;
		listeJoueurs = m.getListeJoueurs();
	}

	public String getNomAttaque() {
		return this.nomAttaque;
	}

	public void setNomAttaque(String nomAttaque) {
		this.nomAttaque = nomAttaque;
	}

	public ListeJoueurs getListeJoueurs() {
		return listeJoueurs;
	}

	public void setListeJoueurs(ListeJoueurs listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	/**
	 * Méthode qui vérifie si une carte Attaque peut être jouée ou non.
	 * 
	 * @param joueurMB1
	 *            joueur qui desire jouer une carte attaque
	 * @param joueurMB2
	 *            joueur sur lequel la carte attaque doit etre deposee Methode
	 *            qui dÃ©ternmine si le joueur peut ou non jouer une carte
	 *            attaque.
	 */
	public boolean possibiliteJouerAttaque(JoueurMB joueurMB1,
			JoueurMB joueurMB2) {
		if (joueurMB1 == joueurMB2) {
			return false;
		} else if (CarteAttaque.correspondre(this, joueurMB2) != null) {
			return false;
		} else if (this.getNomAttaque() == "Limitation de vitesse") {
			if (joueurMB2.limiteVitesse())
				return false;
			else
				return true;
		} else if (this.getNomAttaque() == "Feu rouge") {
			if (joueurMB2.verifierFeuVert() && joueurMB1.verifierFeuVert())
				return true;
			else
				return false;
		} else {
			if (joueurMB1.verifierFeuVert() && !joueurMB2.accident()
					&& joueurMB2.verifierFeuVert())
				return true;
			else
				return false;
		}
	}

	/**
	 * Méthode qui sert à vérifier si une carte botte correspondant à la carte
	 * attaque passée en parametre se trouve dans le jeu du joueur passé en
	 * parametre
	 * 
	 * @param carte
	 *            Carte attaque pour laquelle on aimerait connaitre la botte
	 *            correspondante
	 * @param joueur
	 *            Joueur dont on désire vérifier les bottes qu'il possède.
	 * @return La carte botte qui correspond à la carte Attaque passée en
	 *         parametre
	 */
	public static Carte correspondre(Carte carte, JoueurMB joueur) {
		if (joueur.getPileBotte().isEmpty()) {
			return null;
		} else {
			Iterator<Carte> it = joueur.getPileBotte().iterator();
			while (it.hasNext()) {
				Carte c = it.next();
				if (carte.getNumCarte() == c.getNumCarte() - 20) {
					return c;
				} else if (carte.getNumCarte() == 25 && c.getNumCarte() == 44) {
					return c;
				} else
					return null;
			}
			return null;
		}
	}

	@Override
	/**
	 * Procédure qui permet de déplacer une carte attaque, soit pour la tirer, soit pour la jeter, soit pour la jouer.
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
			} else if (joueur
					.getListeDefausse()
					.getRegion()
					.contains(this.getLocation().x + 40,
							this.getLocation().y + 48)) {
				joueur.jeterCarte(this);
			} else {
				Iterator<JoueurMB> it = listeJoueurs.iterator();
				JoueurMB joueur2;
				while (it.hasNext()) {
					joueur2 = it.next();
					if (joueur2.getRegion().contains(this.getLocation().x + 40,
							this.getLocation().y + 48)) {
						joueur.jouerAttaque(this, joueur2);
					}
				}
			}
			beginPoint = p;
		}
	}
}
