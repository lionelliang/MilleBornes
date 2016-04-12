package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * @author Guillaume Fournier Classe permettant de créer une liste pour le dépot
 *         de la premiere carte feu vert
 */
public class Feu extends LinkedList<Carte> implements Serializable {
	/**
	 * Position de la pile sur l'interface graphique
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point position;

	// public final Point pJouer = new Point(650,500);

	/**
	 * Getter de la position de la pile dans l'interface.
	 * 
	 * @return point de l'angle supérieur gauche de la pile.
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Setter de la position de la carte sur l'interface
	 * 
	 * @param position
	 *            Point de l'angle supérieur gauche de la pile.
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * Procédure qui dit où montrer la carte Feu Vert lorsqu'elle est déposée.
	 */
	public void montrer() {
		if (this.isEmpty()) {
			return;
		}
		this.getLast().setLocation(position);
		this.getLast().setPosition(getPosition());
		this.getLast().setLocation(position);
		this.getLast().setVisible(true);
		Iterator<Carte> it = this.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			if (c != this.getLast()) {
				c.setVisible(false);
				// c.setPosition(getPosition());
				// c.setLocation(position);
				// c.setFace(true);
				// c.montrer();
			}
		}
	}

	/**
	 * Procédure qui permet de cacher une carte.
	 */
	public void cacher() {
		Iterator<Carte> it = this.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			c.cacher();
			c.setFace(false);
		}
	}
}
