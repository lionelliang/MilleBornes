/**
 * 
 */
package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author GUO et FOURNIER Classe qui permet de gérer les kilometres parcourus
 *         par les joueurs.
 */
public class Avancement extends ArrayList<Carte> implements Serializable {

	private static final long serialVersionUID = 1L;
	private int avancementTotal = 0;
	private Point position;

	/**
	 * Accesseur au point "position"
	 * 
	 * @return le point "position"
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Setter de la position
	 * 
	 * @param position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * Getter de l'avancement total du joueur.
	 * 
	 * @return le nombre de kilometres parcourus par le joueur.
	 */
	public int getAvancementTotal() {
		return avancementTotal;
	}

	/**
	 * Setter de l'avancement total d'un joeuur : permet d'ajouter des
	 * kilometres parcourus au joueur.
	 * 
	 * @param avancement
	 *            : nombre de kilometres à ajouter à l'avancement total du
	 *            joueur.
	 */
	public void setAvancementTotal(int avancement) {
		this.avancementTotal += avancement;
	}

	/**
	 * Procédure qui permet d'afficher les cartes étapes posées par un joueur
	 */
	public void montrer() {
		Iterator<Carte> it = this.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			c.setPosition(position);
			c.setLocation(position);
			c.montrer();
			c.setFace(true);
		}
	}

	/**
	 * Procédure qui permet de cacher les cartes étapes posées par un joueur
	 */
	public void cacher() {
		Iterator<Carte> it = this.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			c.setPosition(position);
			c.setLocation(position);
		}
	}

}
