package fr.utt.org.lo02.guo;

import java.awt.Point;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class PileVitesse extends LinkedList<Carte> implements Serializable {
	/**
	 * 
	 * @author Guillaume Fournier Classe permettnt la création de la pile de
	 *         limitation de vitesse de chaque joueur.
	 */
	private static final long serialVersionUID = 1L;
	private Point position;

	// public final Point pJouer = new Point(570,500);

	/**
	 * Getter sur le point supérieur gauche de la pile de cartes sur
	 * l'interface.
	 * 
	 * @return point supérieur gauche de la pile de cartes sur l'interface.
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Setter sur le point supérieur gauche de la pile de cartes sur
	 * l'interface.
	 * 
	 * @param position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * Procédure permettant de ne montrer que la derniere carte de la pile.
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
			}
		}
	}

	/**
	 * Procédure qui, si on le désire, permet de cacher la pile de vitesse.
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
